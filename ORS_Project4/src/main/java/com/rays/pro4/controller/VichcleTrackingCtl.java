package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.InventoryBean;
import com.rays.pro4.Bean.VichcleTrackingBean;
import com.rays.pro4.Model.InventoryModel;
import com.rays.pro4.Model.VichcleTrackingModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "VichcleTrackingCtl", urlPatterns = { "/ctl/VichcleTrackingCtl" })
public class VichcleTrackingCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("Lat"))) {
			request.setAttribute("Lat", PropertyReader.getValue("error.require", "Lat"));
			pass = false;
		} else if (!DataValidator.isDouble(request.getParameter("Lat"))) {
			request.setAttribute("Lat", " Lat contains double values only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("LongId"))) {
			request.setAttribute("LongId", PropertyReader.getValue("error.require", "LongId"));
			pass = false;
			/*
			 * else if (!DataValidator.isDate(request.getParameter("LastupdatedDate"))) {
			 * request.setAttribute("LastupdatedDate", PropertyReader.getValue("error.date",
			 * " LastupdatedDate ")); pass = false;
			 */
		}

		if (DataValidator.isNull(request.getParameter("VichcleId"))) {
			request.setAttribute("VichcleId", PropertyReader.getValue("error.require", "VichcleId"));
			pass = false;
			/*
			 * else if (!DataValidator.isInteger(request.getParameter("Qantity"))) {
			 * request.setAttribute("Qantity", " Qantity contains integer values only");
			 * pass = false;
			 */
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		VichcleTrackingBean bean = new VichcleTrackingBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setLat(DataUtility.getDouble(request.getParameter("Lat")));

		bean.setLongId(DataUtility.getDouble(request.getParameter("LongId")));

		bean.setVichcleId(DataUtility.getInt(request.getParameter("VichcleId")));

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		VichcleTrackingModel model = new VichcleTrackingModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Order Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			VichcleTrackingBean bean;

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

		VichcleTrackingModel model = new VichcleTrackingModel();

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.VICHCLE_TRACKING_LIST_CTL, request, response);
			return;

		}
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			VichcleTrackingBean bean = (VichcleTrackingBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("VichcleTracking is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("VichcleTracking not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Vehical is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("Vehical not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.VICHCLE_TRACKING_VIEW;
	}

}
