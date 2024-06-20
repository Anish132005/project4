package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.EmpBean;
import com.rays.pro4.Bean.ShopBean;
import com.rays.pro4.Model.EmpModel;
import com.rays.pro4.Model.ShopModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;


@WebServlet(name = "EmpCtl", urlPatterns = { "/ctl/EmpCtl" })
public class EmpCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("EmpName"))) {
			request.setAttribute("EmpName", PropertyReader.getValue("error.require", "EmpName"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("EmpName"))) {
			request.setAttribute("EmpName", " EmpName contains alphabet only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Dob"))) {
			request.setAttribute("Dob", PropertyReader.getValue("error.require", "Dob"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("Dob"))) {
			request.setAttribute("Dob", PropertyReader.getValue("error.date", "Date Of Dob"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("Company"))) {
			request.setAttribute("Company", PropertyReader.getValue("error.require", "Company"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Salary"))) {
			request.setAttribute("Salary", PropertyReader.getValue("error.require", "Salary"));
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		EmpBean bean = new EmpBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setEmpName(DataUtility.getString(request.getParameter("EmpName")));
		
		bean.setDob(DataUtility.getDate(request.getParameter("Dob")));

		bean.setCompany(DataUtility.getString(request.getParameter("Company")));
		
		bean.setSalary(DataUtility.getString(request.getParameter("Salary")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		EmpModel model = new EmpModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Order Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			EmpBean bean;

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

		EmpModel model = new EmpModel();
		
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.EMP_LIST_CTL, request, response);
			return;

		}
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			EmpBean bean = (EmpBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Employee is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("Employee not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Employee is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("Employee not added");
					e.printStackTrace();
				}

			}

		}
		
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.EMP_VIEW;
	}

}



