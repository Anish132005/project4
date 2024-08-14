package com.rays.pro4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.OrderBean;
import com.rays.pro4.Bean.SupplierBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.OrderModel;
import com.rays.pro4.Model.SupplierModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "SupplierCtl", urlPatterns = { "/ctl/SupplierCtl" })
public class SupplierCtl extends BaseCtl {
	
	@Override
	protected void preload(HttpServletRequest request) {
		SupplierModel model = new SupplierModel();

		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "High");
		map.put(2, "Bad");

		request.setAttribute("prolist", map);
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("Name"))) {
			request.setAttribute("Name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("Name"))) {
			request.setAttribute("Name", "Name  must contains alphabet only");
			pass = false;
		} else if (DataValidator.isTooLong(request.getParameter("Name"), 45)) {
			request.setAttribute("Name", "Name contain 45 words");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Category"))) {
			request.setAttribute("Category", PropertyReader.getValue("error.require", "Category"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Registrationdate"))) {
			request.setAttribute("Registrationdate", PropertyReader.getValue("error.require", "Registrationdate"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("Registrationdate"))) {
			request.setAttribute("Registrationdate", PropertyReader.getValue("error.date", "Registrationdate"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("payment"))) {
			request.setAttribute("payment", PropertyReader.getValue("error.require", "payment"));
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		SupplierBean bean = new SupplierBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("Name")));
		bean.setCategory(DataUtility.getString(request.getParameter("Category")));
		bean.setRegistrationdate(DataUtility.getDate(request.getParameter("Registrationdate")));
		bean.setPayment(DataUtility.getInt(request.getParameter("payment")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in supplier get method.....");

		String op = DataUtility.getString(request.getParameter("operation"));

		SupplierModel model = new SupplierModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("supplier Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			SupplierBean bean;

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

		SupplierModel model = new SupplierModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			SupplierBean bean = (SupplierBean) populateBean(request);

			try {
				if (id > 0) {

					model.update(bean);
					ServletUtility.setBean(bean, request);

					ServletUtility.setSuccessMessage("supplier  is successfully Updated", request);
				} else {
					System.out.println(" U ctl DoPost 33333");
					long pk = model.add(bean);
					// ServletUtility.setBean(bean, request);
					ServletUtility.setBean(bean, request);

					ServletUtility.setSuccessMessage("supplier is successfully Added", request);

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

			SupplierBean bean = (SupplierBean) populateBean(request);
			try {
				model.delete(bean);

				ServletUtility.redirect(ORSView.SUPPLIER_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println(" U  ctl Do post 77777");

			ServletUtility.redirect(ORSView.SUPPLIER_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.SUPPLIER_VIEW;
	}

}
