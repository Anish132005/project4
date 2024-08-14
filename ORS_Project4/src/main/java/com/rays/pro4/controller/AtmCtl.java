package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.AtmBean;
import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.VehicleBean;
import com.rays.pro4.Model.AtmModel;
import com.rays.pro4.Model.VehicleModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "AtmCtl", urlPatterns = { "/ctl/AtmCtl" })
public class AtmCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("Location"))) {
			request.setAttribute("Location", PropertyReader.getValue("error.require", "Location"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("Location"))) {
			request.setAttribute("Location", " Location contains alphabet only");
			pass = false;
		}

		/*
		 * else if (!DataValidator.isTooLong("Location", 100)) {
		 * request.setAttribute("Location", " Location contains 100 words only"); pass =
		 * false; }
		 */

		if (DataValidator.isNull(request.getParameter("Cash_available"))) {
			request.setAttribute("Cash_available", PropertyReader.getValue("error.require", "Cash_available"));
			pass = false;
		} else if (!DataValidator.isDouble(request.getParameter("Cash_available"))) {
			request.setAttribute("Cash_available", " Cash_available contains only integers values ");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Last_Restocked_Date"))) {
			request.setAttribute("Last_Restocked_Date",
					PropertyReader.getValue("error.require", "Last_Restocked_Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("Last_Restocked_Date"))) {
			request.setAttribute("Last_Restocked_Date", PropertyReader.getValue("error.date", " Last_Restocked_Date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Remark"))) {
			request.setAttribute("Remark", PropertyReader.getValue("error.require", "Remark"));
			pass = false;
		}

		else if (DataValidator.isTooLong(request.getParameter("Remark"), 45)) {
		    request.setAttribute("Remark", "Remark contain 45 words");
		    pass = false;
		}else if (!DataValidator.isName(request.getParameter("Remark"))) {
			request.setAttribute("Remark", " Remark contains alphabet only");
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		AtmBean bean = new AtmBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setLocation(DataUtility.getString(request.getParameter("Location")));

		bean.setCash_available(DataUtility.getDouble(request.getParameter("Cash_available")));

		bean.setLast_Restocked_Date(DataUtility.getDate(request.getParameter("Last_Restocked_Date")));

		bean.setRemark(DataUtility.getString(request.getParameter("Remark")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		AtmModel model = new AtmModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Project Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			AtmBean bean;

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

		AtmModel model = new AtmModel();

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.ATM_LIST_CTL, request, response);
			return;

		}
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			AtmBean bean = (AtmBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("ATM is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("ATM not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("ATM is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("ATM not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.ATM_VIEW;
	}

}
