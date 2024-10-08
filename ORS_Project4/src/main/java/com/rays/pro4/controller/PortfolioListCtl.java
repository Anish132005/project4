package com.rays.pro4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.PortfolioBean;

import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Model.PortfolioModel;

import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "PortfolioListCtl", urlPatterns = { "/ctl/PortfolioListCtl" })
public class PortfolioListCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {
		PortfolioModel model = new PortfolioModel();

		Map<Integer, String> map = new HashMap<Integer, String>();

		map.put(1, "Low");
		map.put(2, "Medium");
		map.put(3, "high");

		request.setAttribute("prolist", map);

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		PortfolioBean bean = new PortfolioBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setPortfolioName(DataUtility.getString(request.getParameter("portfolioName")));
		bean.setAmount(DataUtility.getInt(request.getParameter("Amount")));
		bean.setLevel(DataUtility.getString(request.getParameter("level")));
		bean.setStrategy(DataUtility.getString(request.getParameter("Strategy")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List list = null;
		List nextList = null;

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		PortfolioBean bean = (PortfolioBean) populateBean(request);
		String op = DataUtility.getString(request.getParameter("operation"));

		PortfolioModel model = new PortfolioModel();

		try {
			list = model.search(bean, pageNo, pageSize);
			System.out.println("list" + list);

			nextList = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("nextlist", nextList.size());

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			// ServletUtility.setBean(bean, request);

		} catch (ApplicationException e) {

			ServletUtility.handleException(e, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("PaymentListCtl doPost Start");

		List list;
		List nextList = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String op = DataUtility.getString(request.getParameter("operation"));
		PortfolioBean bean = (PortfolioBean) populateBean(request);

		String[] ids = request.getParameterValues("ids");
		PortfolioModel model = new PortfolioModel();

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			if (OP_SEARCH.equalsIgnoreCase(op)) {
				if (request.getParameter("portfolioName").equals("") && request.getParameter("Amount").equals("")
						&& request.getParameter("level").equals("") && request.getParameter("Strategy").equals("")) {
					ServletUtility.setErrorMessage(" Fill at least one search field", request);
				}

			}
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
			pageNo--;

		} else if (OP_NEW.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PORTFOLIO_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PORTFOLIO_LIST_CTL, request, response);
			return;

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				PortfolioBean deletebean = new PortfolioBean();
				for (String id : ids) {
					deletebean.setId(DataUtility.getInt(id));
					try {
						model.delete(deletebean);
					} catch (ApplicationException e) {

						ServletUtility.handleException(e, request, response);
						return;
					}

					ServletUtility.setSuccessMessage("PortFolio is Deleted Successfully", request);
				}
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		}
		try {

			list = model.search(bean, pageNo, pageSize);

			nextList = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("nextlist", nextList.size());

		} catch (ApplicationException e) {

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
		return ORSView.PORTFOLIO_LIST_VIEW;
	}

}
