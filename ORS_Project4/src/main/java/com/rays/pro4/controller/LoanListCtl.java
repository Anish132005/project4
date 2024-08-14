package com.rays.pro4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.LoanBean;
import com.rays.pro4.Model.LoanModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "LoanListCtl", urlPatterns = { "/ctl/LoanListCtl" })
public class LoanListCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {

		HashMap map = new HashMap();
		map.put("AWS4561120", "AWS4561120");
		map.put("UCD8521479", "UCD8521479");
		map.put("PGF4125633", "PGF4125633");

		request.setAttribute("rlist", map);

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		LoanBean bean = new LoanBean();
 
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setLoan_Start_Date(DataUtility.getDate(request.getParameter("Loan_Start_Date")));


		bean.setCustomer_Id(DataUtility.getString(request.getParameter("Customer_Id")));

		bean.setLoan_Amount(DataUtility.getInt(request.getParameter("Loan_Amount")));
		
		bean.setInterest_Rate(DataUtility.getInt(request.getParameter("Interest_Rate")));


//	System.out.println("anish...edrfgyjkl.rdtyui"+bean.getProjectName());

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List list = null;
		List nextList = null;

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		LoanBean bean = (LoanBean) populateBean(request);

		LoanModel model = new LoanModel();

		try {
			list = model.search(bean, pageNo, pageSize);
			nextList = model.search(bean, pageNo + 1, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("list" + list);

		request.setAttribute("nextlist", nextList.size());

		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage("No record found ", request);
		}

		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List list;
		List nextList = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String op = DataUtility.getString(request.getParameter("operation"));
		LoanBean bean = (LoanBean) populateBean(request);

		String[] ids = request.getParameterValues("ids");

		LoanModel model = new LoanModel();

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
			pageNo--;
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.LOAN_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.LOAN_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				LoanBean deletebean = new LoanBean();
				for (String id : ids) {
					deletebean.setId(DataUtility.getInt(id));

					try {
						model.delete(deletebean);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					ServletUtility.setSuccessMessage("loan is Deleted Successfully", request);
				}
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		}

		try {

			list = model.search(bean, pageNo, pageSize);

			nextList = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("nextlist", nextList.size());

		} catch (Exception e) {
			ServletUtility.handleException(e, request, response);
			return;
		}
		if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
			ServletUtility.setErrorMessage("No record found ", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.setBean(bean, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.LOAN_LIST_VIEW;
	}

}



