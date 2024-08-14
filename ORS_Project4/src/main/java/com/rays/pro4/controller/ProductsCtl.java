package com.rays.pro4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.ProductsBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.ProductsModel;
import com.rays.pro4.Model.SupplierModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "ProductsCtl", urlPatterns = { "/ctl/ProductsCtl" })
public class ProductsCtl extends BaseCtl {
	
	@Override
	protected void preload(HttpServletRequest request) {
		SupplierModel model = new SupplierModel();

		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "High");
		map.put(2, "Medium");
		map.put(3, "Low");

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
		if (DataValidator.isNull(request.getParameter("Description"))) {
			request.setAttribute("Description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		
		} else if (DataValidator.isTooLong(request.getParameter("Description"), 400)) {
			request.setAttribute("Description", "Description contain 400 words");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Price"))) {
			request.setAttribute("Price", PropertyReader.getValue("error.require", "Price"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("DateOfpurchase"))) {
			request.setAttribute("DateOfpurchase", PropertyReader.getValue("error.require", "DateOfpurchase"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("DateOfpurchase"))) {
			request.setAttribute("DateOfpurchase", PropertyReader.getValue("error.date", "DateOfpurchase"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Category"))) {
			request.setAttribute("Category", PropertyReader.getValue("error.require", "Category"));
			pass = false;
		}

		/*
		 * if (!DataValidator.isInteger(request.getParameter("Quantity"))) {
		 * request.setAttribute("Quantity", "Quantity contain intger value only"); pass
		 * = false; }
		 */

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		ProductsBean bean = new ProductsBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("Name")));
		bean.setDescription(DataUtility.getString(request.getParameter("Description")));
		bean.setPrice(DataUtility.getInt(request.getParameter("Price")));
		bean.setDateOfpurchase(DataUtility.getDate(request.getParameter("DateOfpurchase")));
		bean.setCategory(DataUtility.getString(request.getParameter("Category")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));

		ProductsModel model = new ProductsModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("order Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			ProductsBean bean;

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

		ProductsModel model = new ProductsModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			ProductsBean bean = (ProductsBean) populateBean(request);

			try {
				if (id > 0) {

					model.update(bean);
					ServletUtility.setBean(bean, request);

					ServletUtility.setSuccessMessage("Product  is successfully Updated", request);
				} else {
					System.out.println(" U ctl DoPost 33333");
					long pk = model.add(bean);
					// ServletUtility.setBean(bean, request);
					ServletUtility.setBean(bean, request);

					ServletUtility.setSuccessMessage("Product is successfully Added", request);

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

			ProductsBean bean = (ProductsBean) populateBean(request);
			try {
				model.delete(bean);

				ServletUtility.redirect(ORSView.PRODUCTS_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println(" U  ctl Do post 77777");

			ServletUtility.redirect(ORSView.PRODUCTS_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.PRODUCTS_VIEW;
	}

}
