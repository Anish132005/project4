package com.rays.pro4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.ProductsBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Model.ProductsModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "ProductsListCtl", urlPatterns = { "/ctl/ProductsListCtl" })
public class ProductsListCtl extends BaseCtl {
	@Override
	protected void preload(HttpServletRequest request) {
		ProductsModel model = new ProductsModel();

		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "High");
		map.put(2, "Medium");
		map.put(3, "Low");

		request.setAttribute("prolist", map);
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

		List list = null;
		List nextList = null;

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		ProductsBean bean = (ProductsBean) populateBean(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println(">>>>>>>>>>>>>>>helooo" + bean.getDateOfpurchase());

		ProductsModel model = new ProductsModel();

		try {
			list = model.search(bean, pageNo,  pageSize);
			System.out.println("list" + list);

			nextList = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("nextlist", nextList.size());

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			// ServletUtility.setBean(bean, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {

			ServletUtility.handleException(e, request, response);
			return;
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ProductsListCtl doPost Start");

		List list;
		List nextList = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String op = DataUtility.getString(request.getParameter("operation"));
		ProductsBean bean = (ProductsBean) populateBean(request);

		String[] ids = request.getParameterValues("ids");
		ProductsModel model = new ProductsModel();

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
			pageNo--;

		} else if (OP_NEW.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PRODUCTS_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PRODUCTS_LIST_CTL, request, response);
			return;

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				ProductsBean deletebean = new ProductsBean();
				for (String id : ids) {
					deletebean.setId(DataUtility.getInt(id));
					try {
						model.delete(deletebean);
					} catch (ApplicationException e) {

						ServletUtility.handleException(e, request, response);
						return;
					}

					ServletUtility.setSuccessMessage("Product is Deleted Successfully", request);
				}
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		}
		try {

			list = model.search(bean, pageNo, pageSize);

			nextList = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("nextlist", nextList.size());

		} catch (ApplicationException e) {

			ServletUtility.handleException(e, request, response);
			return;
		}
		if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
			ServletUtility.setErrorMessage("No record found ", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.setBean(bean, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.PRODUCTS_LIST_VIEW;
	}

}
