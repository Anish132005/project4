package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BankBean;
import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Model.BankModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "BankCtl", urlPatterns = { "/ctl/BankCtl" })
public class BankCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("BankName"))) {
			request.setAttribute("BankName", PropertyReader.getValue("error.require", "BankName"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("BankName"))) {
			request.setAttribute("BankName", " BankName contains alphabet only");
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("AccountNo"))) {
			request.setAttribute("AccountNo", PropertyReader.getValue("error.require", "AccountNo"));
			pass = false;
		}else if (!DataValidator.isMobileNo(request.getParameter("AccountNo"))) {
			request.setAttribute("AccountNo", "AccountNo. must be 10 Digit ");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Dob"))) {
			request.setAttribute("Dob", PropertyReader.getValue("error.require", "Dob"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Address"))) {
			request.setAttribute("Address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		BankBean bean = new BankBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setBankName(DataUtility.getString(request.getParameter("BankName")));

		bean.setAccountNo(DataUtility.getString(request.getParameter("AccountNo")));

		bean.setDob(DataUtility.getDate(request.getParameter("Dob")));

		bean.setAddress(DataUtility.getString(request.getParameter("Address")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		BankModel model = new BankModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Bank Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			BankBean bean;

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

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println(">>>><<<<>><<><<><<><>**********" + id + op);

		BankModel model = new BankModel();
		
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.BANK_LIST_CTL, request, response);
			return;
		}

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			BankBean bean = (BankBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Bank is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("Bank not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Bank is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("Bank not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.BANK_VIEW;
	}

}
