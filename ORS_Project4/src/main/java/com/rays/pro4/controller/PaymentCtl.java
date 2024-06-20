package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.PaymentBean;
import com.rays.pro4.Model.PaymentModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "PaymentCtl", urlPatterns = { "/ctl/PaymentCtl" })
public class PaymentCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("Name"))) {
			request.setAttribute("Name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("Name"))) {
			request.setAttribute("Name", " Name contains alphabet only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Amount"))) {
			request.setAttribute("Amount", PropertyReader.getValue("error.require", "Amount"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("Amount"))) {
			request.setAttribute("Amount", " Amount contains Number only");
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("Dob"))) {
			request.setAttribute("Dob", PropertyReader.getValue("error.require", "Dob"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("Dob"))) {
			request.setAttribute("Dob", PropertyReader.getValue("error.date", " Dob"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("UPI"))) {
			request.setAttribute("UPI", PropertyReader.getValue("error.require", "UPI"));
			pass = false;
		}
		
		
		if (DataValidator.isNull(request.getParameter("Bank"))) {
			request.setAttribute("Bank", PropertyReader.getValue("error.require", "Bank"));
			pass = false;
		
	} else if (!DataValidator.isName(request.getParameter("Bank"))) {
		request.setAttribute("Bank", " Bank contains alphabet only");
		pass = false;
	}
		 
		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		PaymentBean bean = new PaymentBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request.getParameter("Name")));

		bean.setAmount(DataUtility.getString(request.getParameter("Amount")));

		bean.setDob(DataUtility.getDate(request.getParameter("Dob")));

		bean.setUPI(DataUtility.getString(request.getParameter("UPI")));
		
		bean.setBank((DataUtility.getString(request.getParameter("Bank"))));
		
		

		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		PaymentModel model = new PaymentModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("Project Edit Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
			PaymentBean bean;

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

		PaymentModel model = new PaymentModel();

		if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("op cancle === > " + op);
			ServletUtility.redirect(ORSView.PATIENT_LIST_CTL, request, response);
			return;

		}
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			PaymentBean bean = (PaymentBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Payment is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("Payment not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Payment is successfully Added", request);
					bean.setId(pk);
				} catch (Exception e) {
					System.out.println("Payment not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.PAYMENT_VIEW;
	}

}






