package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.ProductBean;
import com.rays.pro4.Bean.ShopBean;
import com.rays.pro4.Model.ProductModel;
import com.rays.pro4.Model.ShopModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "ShopCtl", urlPatterns = { "/ctl/ShopCtl" })
public class ShopCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("ShopName"))) {
			request.setAttribute("ShopName", PropertyReader.getValue("error.require", "ShopName"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("ShopName"))) {
			request.setAttribute("ShopName", " ShopName contains alphabet only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("ProductName"))) {
			request.setAttribute("ProductName", PropertyReader.getValue("error.require", "ProductName"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("PurchaseDate"))) {
			request.setAttribute("PurchaseDate", PropertyReader.getValue("error.require", "PurchaseDate"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("PurchaseDate"))) {
			request.setAttribute("PurchaseDate", PropertyReader.getValue("error.date", "Date Of PurchaseDate"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Amount"))) {
			request.setAttribute("Amount", PropertyReader.getValue("error.require", "Amount"));
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		ShopBean bean = new ShopBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setShopName(DataUtility.getString(request.getParameter("ShopName")));

		bean.setProductName(DataUtility.getString(request.getParameter("ProductName")));

		bean.setPurchaseDate(DataUtility.getDate(request.getParameter("PurchaseDate")));

		bean.setAmount(DataUtility.getString(request.getParameter("Amount")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		ShopModel model = new ShopModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Order Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			ShopBean bean;

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

		ShopModel model = new ShopModel();
		
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.SHOP_LIST_CTL, request, response);
			return;

		}
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			ShopBean bean = (ShopBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Shop is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("Shop not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Shop is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("Shop not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.SHOP_VIEW;
	}

}
