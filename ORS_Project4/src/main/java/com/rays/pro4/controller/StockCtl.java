package com.rays.pro4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.StockBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.StockModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "StockCtl", urlPatterns = { "/ctl/StockCtl" })
public class StockCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {
		StockModel model = new StockModel();

		Map<Integer, String> map = new HashMap<Integer, String>();

		map.put(1, "Market");
		map.put(2, "Limit");
<<<<<<< HEAD
=======
		
>>>>>>> origin/main

		request.setAttribute("prolist", map);

	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

<<<<<<< HEAD
		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "quantity"));
			pass = false;
		} else if (!DataValidator.isPositiveNumber(Integer.parseInt(request.getParameter("quantity")))) {

			request.setAttribute("quantity", "quantity must contain positive number");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("purchasePrice"))) {

			request.setAttribute("purchasePrice", PropertyReader.getValue("error.require", "purchasePrice"));
			pass = false;
		} 

		if (DataValidator.isNull(request.getParameter("purchaseDate"))) {
			request.setAttribute("purchaseDate", PropertyReader.getValue("error.require", "purchaseDate"));

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("orderType"))) {

			request.setAttribute("orderType", PropertyReader.getValue("error.require", "orderType"));
			pass = false;

=======
		if (DataValidator.isNull(request.getParameter("CarName"))) {
			System.out.println("in carname valdate");
			request.setAttribute("CarName", PropertyReader.getValue("error.require", "CarName"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("CarModel"))) {
			System.out.println("in  CarModel");

			request.setAttribute("CarModel", PropertyReader.getValue("error.require", "CarModel"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Price"))) {
			System.out.println("in  price");

			request.setAttribute("Price", PropertyReader.getValue("error.require", "Price"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "dob"));
			System.out.println("in  dob");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("OwnerName"))) {
			System.out.println("in  OwnerName");

			request.setAttribute("OwnerName", PropertyReader.getValue("error.require", "OwnerName"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("OwnerName"))) {
			request.setAttribute("OwnerName", "OwnerName  must contains alphabet only");
			pass = false;
		} else if (DataValidator.isTooLong(request.getParameter("OwnerName"), 45)) {
			request.setAttribute("OwnerName", "OwnerName contain 45 words");
			pass = false;
>>>>>>> origin/main
		}
		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
<<<<<<< HEAD
		StockBean bean = new StockBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setQuantity(DataUtility.getInt(request.getParameter("quantity")));
		bean.setPurchasePrice(DataUtility.getDouble(request.getParameter("purchasePrice")));
		bean.setPurchaseDate(DataUtility.getDate(request.getParameter("purchaseDate")));
		bean.setOrderType(DataUtility.getString(request.getParameter("orderType")));
=======
		CarBean bean = new CarBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCarName(DataUtility.getString(request.getParameter("CarName")));
		bean.setCarModel(DataUtility.getString(request.getParameter("CarModel")));
		bean.setPrice(DataUtility.getInt(request.getParameter("Price")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setOwnerName(DataUtility.getString(request.getParameter("OwnerName")));
>>>>>>> origin/main

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));

<<<<<<< HEAD
		StockModel model = new StockModel();
=======
		CarModel model = new CarModel();
>>>>>>> origin/main

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("car Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
<<<<<<< HEAD
			StockBean bean;
=======
			CarBean bean;
>>>>>>> origin/main

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
		System.out.println("fdghjkhgfhjkhgfhjkhh");
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("fdghjkhgfhjkhgfhjkhh" + op);
		long id = DataUtility.getLong(request.getParameter("id"));
		System.out.println("millll gyaaa" + id);

		System.out.println(">>>><<<<>><<><<><<><>" + id + op);

<<<<<<< HEAD
		StockModel model = new StockModel();
=======
		CarModel model = new CarModel();
>>>>>>> origin/main

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			System.out.println("milll gyaaaaaaaa iski ");
<<<<<<< HEAD
			StockBean bean = (StockBean) populateBean(request);
=======
			CarBean bean = (CarBean) populateBean(request);
>>>>>>> origin/main

			try {
				if (id > 0) {

					model.update(bean);
					ServletUtility.setBean(bean, request);

<<<<<<< HEAD
					ServletUtility.setSuccessMessage("Stock  is successfully Updated", request);
=======
					ServletUtility.setSuccessMessage("CarDetails  is successfully Updated", request);
>>>>>>> origin/main
				} else {
					System.out.println(" U ctl DoPost 33333");
					long pk = model.add(bean);
					// ServletUtility.setBean(bean, request);
					ServletUtility.setBean(bean, request);

<<<<<<< HEAD
					ServletUtility.setSuccessMessage("Stock is successfully Added", request);
=======
					ServletUtility.setSuccessMessage("CarDetails is successfully Added", request);
>>>>>>> origin/main

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

			StockBean bean = (StockBean) populateBean(request);
			try {
				model.delete(bean);

<<<<<<< HEAD
				ServletUtility.redirect(ORSView.STOCK_CTL, request, response);
=======
				ServletUtility.redirect(ORSView.CAR_CTL, request, response);
>>>>>>> origin/main
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println(" U  ctl Do post 77777");

<<<<<<< HEAD
			ServletUtility.redirect(ORSView.STOCK_LIST_CTL, request, response);
=======
			ServletUtility.redirect(ORSView.CAR_LIST_CTL, request, response);
>>>>>>> origin/main
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return ORSView.STOCK_VIEW;
=======
		return ORSView.CAR_VIEW;
>>>>>>> origin/main
	}

}
