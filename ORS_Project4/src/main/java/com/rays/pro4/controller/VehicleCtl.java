package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.ProjectBean;
import com.rays.pro4.Bean.VehicleBean;
import com.rays.pro4.Model.ProjectModel;
import com.rays.pro4.Model.VehicleModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "VehicleCtl", urlPatterns = { "/ctl/VehicleCtl" })
public class VehicleCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("VehicleName"))) {
			request.setAttribute("VehicleName", PropertyReader.getValue("error.require", "VehicleName"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("VehicleName"))) {
			request.setAttribute("VehicleName", " VehicleName contains alphabet only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("VehiclePrice"))) {
			request.setAttribute("VehiclePrice", PropertyReader.getValue("error.require", "VehiclePrice"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dOB"))) {
			request.setAttribute("dOB", PropertyReader.getValue("error.require", "dOB"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dOB"))) {
			request.setAttribute("dOB", PropertyReader.getValue("error.date", " dOB"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("MobileNubmer"))) {
			request.setAttribute("MobileNubmer", PropertyReader.getValue("error.require", "MobileNubmer"));
			pass = false;
		}
		
		
		if (DataValidator.isNull(request.getParameter("RTO"))) {
			request.setAttribute("RTO", PropertyReader.getValue("error.require", "RTO"));
			pass = false;
		}
		 
		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		VehicleBean bean = new VehicleBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setVehicleName(DataUtility.getString(request.getParameter("VehicleName")));

		bean.setVehiclePrice(DataUtility.getString(request.getParameter("VehiclePrice")));

		bean.setdOB(DataUtility.getDate(request.getParameter("dOB")));

		bean.setMobileNubmer(DataUtility.getString(request.getParameter("MobileNubmer")));
		
		bean.setRTO(DataUtility.getString(request.getParameter("RTO")));
		
		

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		VehicleModel model = new VehicleModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Project Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			VehicleBean bean;

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

		VehicleModel model = new VehicleModel();

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.VEHICLE_LIST_CTL, request, response);
			return;

		}
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			VehicleBean bean = (VehicleBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Vehicle is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("Vehicle not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Vehicle is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("Vehicle not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.VEHICLE_VIEW;
	}

}



