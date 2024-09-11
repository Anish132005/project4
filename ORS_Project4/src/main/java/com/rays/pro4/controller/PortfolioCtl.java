package com.rays.pro4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.PortfolioBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.PortfolioModel;

import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "PortfolioCtl", urlPatterns = { "/ctl/PortfolioCtl" })
public class PortfolioCtl extends BaseCtl {

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
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("portfolioName"))) {
			request.setAttribute("portfolioName", PropertyReader.getValue("error.require", "portfolioName"));
			pass = false;
		} else if (!DataValidator.isAlphanumeric(request.getParameter("portfolioName"))) {
			request.setAttribute("portfolioName", "portfolioName  must contains Alphanumeric Character only");
			pass = false;
		} else if (DataValidator.isTooLong(request.getParameter("portfolioName"), 30)) {
			request.setAttribute("portfolioName", "portfolioName contain 30 characters");
			pass = false;
		} else if (!DataValidator.isStringLengthValid(request.getParameter("portfolioName"), 3)) {
			request.setAttribute("portfolioName", "portfolioName  must be at least 3 characters long.");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Amount"))) {

			request.setAttribute("Amount", PropertyReader.getValue("error.require", "Amount"));
			pass = false;
		} else if (!DataValidator.isPositiveNumber(Integer.parseInt(request.getParameter("Amount")))) {

			request.setAttribute("Amount", "Amount must contain positive number");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("level"))) {
			request.setAttribute("level", PropertyReader.getValue("error.require", "level"));

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Strategy"))) {
			request.setAttribute("Strategy", PropertyReader.getValue("error.require", "Strategy"));
			pass = false;
		} else if (DataValidator.isTooLong(request.getParameter("Strategy"), 200)) {
			request.setAttribute("Strategy", "Strategy contain 200 characters");
			pass = false;
		} else if (!DataValidator.isStringLengthValid(request.getParameter("Strategy"), 10)) {
			request.setAttribute("Strategy", "Strategy  must be at least 10 characters long.");
			pass = false;
			return pass;

		}
		return pass;
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
		String op = DataUtility.getString(request.getParameter("operation"));

		PortfolioModel model = new PortfolioModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("car Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			PortfolioBean bean;

			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println(">>>><<<<>><<><<><<><>" + id + op);

		PortfolioModel model = new PortfolioModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			System.out.println("milll gyaaaaaaaa iski ");
			PortfolioBean bean = (PortfolioBean) populateBean(request);

			try {
				if (id > 0) {

					model.update(bean);
					ServletUtility.setBean(bean, request);

					ServletUtility.setSuccessMessage("Portfolio  is successfully Updated", request);
				} else {
					System.out.println(" U ctl DoPost 33333");
					long pk = model.add(bean);
					// ServletUtility.setBean(bean, request);
					ServletUtility.setBean(bean, request);

					ServletUtility.setSuccessMessage("Portfolio is successfully Added", request);

					bean.setId(pk);
				}

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {

				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			PortfolioBean bean = (PortfolioBean) populateBean(request);
			try {
				model.delete(bean);

				ServletUtility.redirect(ORSView.PORTFOLIO_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println(" U  ctl Do post 77777");

			ServletUtility.redirect(ORSView.PORTFOLIO_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {

		return ORSView.PORTFOLIO_VIEW;
	}

}
