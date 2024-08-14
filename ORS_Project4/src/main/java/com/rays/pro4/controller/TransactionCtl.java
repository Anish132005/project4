package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.TransactionBean;
import com.rays.pro4.Model.TransactionModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "TransactionCtl", urlPatterns = { "/ctl/TransactionCtl" })
public class TransactionCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("Description"))) {
			request.setAttribute("Description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		/*
		 * else if (!DataValidator.isTooLong("Location", 100)) {
		 * request.setAttribute("Location", " Location contains 100 words only"); pass =
		 * false; }
		 */

		if (DataValidator.isNull(request.getParameter("Transaction_Date"))) {
			request.setAttribute("Transaction_Date", PropertyReader.getValue("error.require", "Transaction_Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("Transaction_Date"))) {
			request.setAttribute("Transaction_Date", PropertyReader.getValue("error.date", " Transaction_Date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Transaction_Type"))) {
			request.setAttribute("Transaction_Type", PropertyReader.getValue("error.require", "Transaction_Type"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Account_id"))) {
			request.setAttribute("Account_id", PropertyReader.getValue("error.require", "Account_id"));
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		TransactionBean bean = new TransactionBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setDescription(DataUtility.getString(request.getParameter("Description")));

		bean.setTransaction_Date(DataUtility.getDate(request.getParameter("Transaction_Date")));

		bean.setTransaction_Type(DataUtility.getString(request.getParameter("Transaction_Type")));

		bean.setAccount_id(DataUtility.getString(request.getParameter("Account_id")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		TransactionModel model = new TransactionModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Project Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			TransactionBean bean;

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

		TransactionModel model = new TransactionModel();

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.TRANSACTION_LIST_CTL, request, response);
			return;

		}
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			TransactionBean bean = (TransactionBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Transaction is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("Transaction not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Transaction is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("Transaction not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.TRANSACTION_VIEW;
	}

}
