package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.LoanBean;
import com.rays.pro4.Model.LoanModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "LoanCtl", urlPatterns = { "/ctl/LoanCtl" })
public class LoanCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("Loan_Start_Date"))) {
			request.setAttribute("Loan_Start_Date", PropertyReader.getValue("error.require", "Loan_Start_Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("Loan_Start_Date"))) {
			request.setAttribute("Loan_Start_Date", PropertyReader.getValue("error.date", "Loan_Start_Date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Customer_Id"))) {
			request.setAttribute("Customer_Id", PropertyReader.getValue("error.require", "Customer_Id"));
			pass = false;
		} else if (DataValidator.isTooLong(request.getParameter("Customer_Id"), 45)) {
			request.setAttribute("Customer_Id", "CustomerId contain 45 words");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Loan_Amount"))) {
			request.setAttribute("Loan_Amount", PropertyReader.getValue("error.require", "Loan_Amount"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Interest_Rate"))) {
			request.setAttribute("Interest_Rate", PropertyReader.getValue("error.require", "Interest_Rate"));
			pass = false;
		} /*
			 * else if (!DataValidator.isInteger(request.getParameter("Interest_Rate"))) {
			 * request.setAttribute("Interest_Rate",
			 * "Interest_Rate contains only Integers "); pass = false; }
			 */

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		LoanBean bean = new LoanBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setLoan_Start_Date(DataUtility.getDate(request.getParameter("Loan_Start_Date")));

		bean.setCustomer_Id(DataUtility.getString(request.getParameter("Customer_Id")));

		System.out.println("loan =========== > " + request.getParameter("Loan_Amount"));

		bean.setLoan_Amount(DataUtility.getInt(request.getParameter("Loan_Amount")));

		System.out.println("loan =>>>>>>>>>>>>>>>>>> " + bean.getLoan_Amount());

		bean.setInterest_Rate(DataUtility.getInt(request.getParameter("Interest_Rate")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		LoanModel model = new LoanModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Loan Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			LoanBean bean;

			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("uctl Do Post");

		String op = DataUtility.getString(request.getParameter("operation"));

		System.out.println("op ===== > " + op);

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println(">>>><<<<>><<><<><<><>**********" + id + op);

		LoanModel model = new LoanModel();

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.LOAN_LIST_CTL, request, response);
			return;

		}
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			LoanBean bean = (LoanBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Loan is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("Loan not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Loan is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("Loan not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.LOAN_VIEW;
	}

}
