package com.rays.pro4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.ClientBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.ClientModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "ClientCtl", urlPatterns = { "/ctl/ClientCtl" })
public class ClientCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {
		ClientModel model = new ClientModel();

		Map<Integer, String> map = new HashMap<Integer, String>();

		map.put(1, "indore");
		map.put(2, "Bhopal");
		map.put(3, "devash");

		request.setAttribute("prolist", map);

	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("identifier"))) {
			request.setAttribute("identifier", PropertyReader.getValue("error.require", "identifier"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("contactName"))) {
			request.setAttribute("contactName", PropertyReader.getValue("error.require", "contactName"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("contactName"))) {
			request.setAttribute("contactName", " contactName must contains alphabet only");
			pass = false;

		} else if (DataValidator.isTooLong(request.getParameter("contactName"), 45)) {
			request.setAttribute("contactName", "contactName contain 45 characters");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "dob"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("location"))) {
			request.setAttribute("location", PropertyReader.getValue("error.require", "location"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("phoneNumber"))) {
			request.setAttribute("phoneNumber", PropertyReader.getValue("error.require", "phoneNumber"));
			pass = false;

		}

		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		ClientBean bean = new ClientBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setIdentifier(DataUtility.getInt(request.getParameter("identifier")));
		bean.setContactName(DataUtility.getString(request.getParameter("contactName")));
		bean.setLocation(DataUtility.getString(request.getParameter("location")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setPhoneNumber(DataUtility.getString(request.getParameter("phoneNumber")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));

		ClientModel model = new ClientModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("car Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			ClientBean bean;

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

		ClientModel model = new ClientModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			System.out.println("milll gyaaaaaaaa iski ");
			ClientBean bean = (ClientBean) populateBean(request);
			System.out.println("data populate ho gyaaaa ");
			try {
				if (id > 0) {

					model.update(bean);
					ServletUtility.setBean(bean, request);

					ServletUtility.setSuccessMessage("client  is successfully Updated", request);
				} else {
					System.out.println(" U ctl DoPost 33333");
					long pk = model.add(bean);
					System.out.println("aaajjajajajjajja");
					// ServletUtility.setBean(bean, request);
					ServletUtility.setBean(bean, request);
					System.out.println("sdfghjkhgfdsfghjkljhgfds");
					ServletUtility.setSuccessMessage("client is successfully Added", request);

					bean.setId(pk);
				}

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {

				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			ClientBean bean = (ClientBean) populateBean(request);
			try {
				model.delete(bean);

				ServletUtility.redirect(ORSView.CLIENT_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println(" U  ctl Do post 77777");

			ServletUtility.redirect(ORSView.CLIENT_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.CLIENT_VIEW;
	}

}
