package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.AtmBean;
import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.CustomerBean;
import com.rays.pro4.Model.AtmModel;
import com.rays.pro4.Model.CustomerModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "CustomerCtl", urlPatterns = { "/ctl/CustomerCtl" })
public class CustomerCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("Name"))) {
			request.setAttribute("Name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("Name"))) {
			request.setAttribute("Name", " Name contains alphabet only");
			pass = false;
		}

		/*
		 * else if (!DataValidator.isTooLong("Location", 100)) {
		 * request.setAttribute("Location", " Location contains 100 words only"); pass =
		 * false; }
		 */

		
		
		if (DataValidator.isNull(request.getParameter("Date_of_Birth"))) {
			request.setAttribute("Date_of_Birth",
					PropertyReader.getValue("error.require", "Date_of_Birth"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("Date_of_Birth"))) {
			request.setAttribute("Date_of_Birth", PropertyReader.getValue("error.date", " Date_of_Birth"));
			pass = false;
		}	
			if (DataValidator.isNull(request.getParameter("PhoneNumber"))) {
				request.setAttribute("PhoneNumber", PropertyReader.getValue("error.require", "PhoneNumber"));
				pass = false;
			} 
		if (DataValidator.isNull(request.getParameter("Gender"))) {
			request.setAttribute("Gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}

		

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CustomerBean bean = new CustomerBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		System.out.println("pkayyy"+bean.getId());

	    bean.setName(DataUtility.getString(request.getParameter("Name")));

		bean.setDate_of_Birth(DataUtility.getDate(request.getParameter("Date_of_Birth")));

		bean.setPhoneNumber(DataUtility.getString(request.getParameter("PhoneNumber")));
		System.out.println("okayyy phone number "+bean.getPhoneNumber());
		
		bean.setGender(DataUtility.getString(request.getParameter("Gender")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		CustomerModel model = new CustomerModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Project Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			CustomerBean bean;

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

		CustomerModel model = new CustomerModel();

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.CUSTOMER_LIST_CTL, request, response);
			return;

		}
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			CustomerBean bean = (CustomerBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("customer  is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("customer not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("customer is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("customer not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.CUSTOMER_VIEW;
	}

}


