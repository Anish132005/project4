package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.ProjectBean;
import com.rays.pro4.Model.ProjectModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "ProjectCtl", urlPatterns = { "/ctl/ProjectCtl" })
public class ProjectCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("FirstName"))) {
			request.setAttribute("FirstName", PropertyReader.getValue("error.require", "FirstName"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("FirstName"))) {
			request.setAttribute("FirstName", " FirstName contains alphabet only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("LastName"))) {
			request.setAttribute("LastName", PropertyReader.getValue("error.require", "LastName"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("LastName"))) {
			request.setAttribute("LastName", " LastName contains alphabet only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Dob"))) {
			request.setAttribute("Dob", PropertyReader.getValue("error.require", "Dob"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("Dob"))) {
			request.setAttribute("Dob", PropertyReader.getValue("error.date", "Current Dob"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("ProjectName"))) {
			request.setAttribute("ProjectName", PropertyReader.getValue("error.require", "ProjectName"));
			pass = false;
		}
		 
		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		ProjectBean bean = new ProjectBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setFirstName(DataUtility.getString(request.getParameter("FirstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("LastName")));

		bean.setDob(DataUtility.getDate(request.getParameter("Dob")));

		bean.setProjectName(DataUtility.getString(request.getParameter("ProjectName")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		ProjectModel model = new ProjectModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Project Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			ProjectBean bean;

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

		ProjectModel model = new ProjectModel();

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.PROJECT_LIST_CTL, request, response);
			return;

		}
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			ProjectBean bean = (ProjectBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Project is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("Project not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Project is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("Project not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.PROJECT_VIEW;
	}

}
