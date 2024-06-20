package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.InventoryBean;
import com.rays.pro4.Bean.ShopBean;
import com.rays.pro4.Model.InventoryModel;
import com.rays.pro4.Model.ShopModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "InventoryCtl", urlPatterns = { "/ctl/InventoryCtl" })
public class InventoryCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("SupplierName"))) {
			request.setAttribute("SupplierName", PropertyReader.getValue("error.require", "SupplierName"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("SupplierName"))) {
			request.setAttribute("SupplierName", " SupplierName contains alphabet only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("LastupdatedDate"))) {
			request.setAttribute("LastupdatedDate", PropertyReader.getValue("error.require", "LastupdatedDate"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("LastupdatedDate"))) {
			request.setAttribute("LastupdatedDate", PropertyReader.getValue("error.date", " LastupdatedDate "));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Qantity"))) {
			request.setAttribute("Qantity", PropertyReader.getValue("error.require", "Qantity"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("Qantity"))) {
			request.setAttribute("Qantity", " Qantity contains integer values only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Product"))) {
			request.setAttribute("Product", PropertyReader.getValue("error.require", "Product"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("Product"))) {
			request.setAttribute("Product", " Product contains alphabet values only");
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		InventoryBean bean = new InventoryBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setSupplierName(DataUtility.getString(request.getParameter("SupplierName")));

		bean.setLastupdatedDate(DataUtility.getDate(request.getParameter("LastupdatedDate")));

		bean.setQantity(DataUtility.getString(request.getParameter("Qantity")));

		bean.setProduct(DataUtility.getString(request.getParameter("Product")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		InventoryModel model = new InventoryModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Order Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			InventoryBean bean;

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

		InventoryModel model = new InventoryModel();

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.INVENTORY_LIST_CTL, request, response);
			return;

		}
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			InventoryBean bean = (InventoryBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("inventory is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("inventory not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("inventory is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("inventory not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.INVENTORY_VIEW;
	}

}
