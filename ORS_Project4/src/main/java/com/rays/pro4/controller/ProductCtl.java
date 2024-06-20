package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.OrderBean;
import com.rays.pro4.Bean.ProductBean;
import com.rays.pro4.Model.OrderModel;
import com.rays.pro4.Model.ProductModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "ProductCtl", urlPatterns = { "/ctl/ProductCtl" })
public class ProductCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("productName"))) {
			request.setAttribute("productName", PropertyReader.getValue("error.require", "productName"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("productName"))) {
			request.setAttribute("productName", " productName contains alphabet only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("productPrice"))) {
			request.setAttribute("productPrice", PropertyReader.getValue("error.require", "productPrice"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("productDate"))) {
			request.setAttribute("productDate", PropertyReader.getValue("error.require", "productDate"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("productDate"))) {
			request.setAttribute("productDate", PropertyReader.getValue("error.date", "Date Of productDate"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("productCategory"))) {
			request.setAttribute("productCategory", PropertyReader.getValue("error.require", "productCategory"));
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		ProductBean bean = new ProductBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setProductName(DataUtility.getString(request.getParameter("productName")));

		bean.setProductPrice(DataUtility.getString(request.getParameter("productPrice")));

		bean.setProductDate(DataUtility.getDate(request.getParameter("productDate")));

		bean.setProductCategory(DataUtility.getString(request.getParameter("productCategory")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		ProductModel model = new ProductModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Order Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			ProductBean bean;

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

		ProductModel model = new ProductModel();
		
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.PRODUCT_LIST_CTL, request, response);
			return;
		}

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			ProductBean bean = (ProductBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Product is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("Product not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("order is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("Order not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.PRODUCT_VIEW;
	}

}
