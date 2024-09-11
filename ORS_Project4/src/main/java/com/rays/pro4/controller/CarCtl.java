package com.rays.pro4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.CarBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.CarModel;
import com.rays.pro4.Model.SupplierModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "CarCtl", urlPatterns = { "/ctl/CarCtl" })
public class CarCtl extends BaseCtl {

	

	@Override
	protected void preload(HttpServletRequest request) {
		CarModel model = new CarModel();

		Map<Integer, String> map = new HashMap<Integer, String>();

		map.put(1, "Sizuki");
		map.put(2, "Toyta");
		map.put(3, "Mahindra");

		request.setAttribute("prolist", map);

		Map<Integer, String> map1 = new HashMap<Integer, String>();

		map1.put(1, "2018, Swift");
		map1.put(2, "2019, Fortuner");
		map1.put(3, "2019 ,Thar");

		request.setAttribute("prolist1", map1);
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

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
<<<<<<< HEAD
		
=======
		}else if (!DataValidator.isPositveNumber(Integer.parseInt(request.getParameter("Price")))) {
			request.setAttribute("Price", "Price contain only pos");
			pass = false;
>>>>>>> origin/main
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
			/*
			 * } else if (!DataValidator.isAlphanumeric(request.getParameter("OwnerName")))
			 * { request.setAttribute("OwnerName",
			 * "Please enter  letters and numbers both"); pass = false; } else if
			 * (DataValidator.isTooLong(request.getParameter("OwnerName"), 45)) {
			 * request.setAttribute("OwnerName", "OwnerName contain 1-45 Characters"); pass
			 * = false;
			 */
		}
		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		CarBean bean = new CarBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCarName(DataUtility.getString(request.getParameter("CarName")));
		bean.setCarModel(DataUtility.getString(request.getParameter("CarModel")));
		bean.setPrice(DataUtility.getInt(request.getParameter("Price")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setOwnerName(DataUtility.getString(request.getParameter("OwnerName")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));

		CarModel model = new CarModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("car Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			CarBean bean;

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

		CarModel model = new CarModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			System.out.println("milll gyaaaaaaaa iski ");
			CarBean bean = (CarBean) populateBean(request);

			try {
				if (id > 0) {

					model.update(bean);
					ServletUtility.setBean(bean, request);

					ServletUtility.setSuccessMessage("CarDetails  is successfully Updated", request);
				} else {
					System.out.println(" U ctl DoPost 33333");
					long pk = model.add(bean);
					// ServletUtility.setBean(bean, request);
					ServletUtility.setBean(bean, request);

					ServletUtility.setSuccessMessage("CarDetails is successfully Added", request);

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

			CarBean bean = (CarBean) populateBean(request);
			try {
				model.delete(bean);

				ServletUtility.redirect(ORSView.CAR_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println(" U  ctl Do post 77777");

			ServletUtility.redirect(ORSView.CAR_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.CAR_VIEW;
	}

}
