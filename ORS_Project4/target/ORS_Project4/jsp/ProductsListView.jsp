<%@page import="com.rays.pro4.controller.ProductsListCtl"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.Bean.ProductsBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="java.util.Map"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products page</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>

</head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#Udate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2002',
		//dateFormat:'yy-mm-dd'
		});
	});

	function limitInputLength(input, maxLength) {
		if (input.value.length > maxLength) {
			input.value = input.value.slice(0, maxLength);
		}
	}
</script>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.ProductsBean"
		scope="request"></jsp:useBean>
	<%@include file="Header.jsp"%>
	<form action="<%=ORSView.PRODUCTS_LIST_CTL%>" method="post">
		<center>

			<div align="center">
				<h1>Product Details List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			</div>
			<%
				Map map = (Map) request.getAttribute("prolist");

				int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
			%>

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<ProductsBean> it = list.iterator();
				if (list.size() != 0) {
			%>
			<table width="100%" align="center">

				<td align="center"><label>Name</font> :

				</label> <input type="text" name="Name" placeholder="Enter Name"
					value="<%=ServletUtility.getParameter("Name", request)%>">
				<td align="center"><label>Description</font> :

				</label> <input type="text" name="Description"
					placeholder="Enter Description"
					value="<%=ServletUtility.getParameter("Description", request)%>">
				<td align="center"><label>DateOfpurchase</font> :
				</label> <input type="text" name="DateOfpurchase" id="Udate"
					placeholder="Enter DateOfpurchase" readonly="readonly"
					value="<%=ServletUtility.getParameter("DateOfpurchase", request)%>">
				<td align="center"><label>Price</label> <input type="number"
					name="price" id="quantityInput" placeholder="Enter Price"
					value="<%=ServletUtility.getParameter("price", request)%>"
					oninput="limitInputLength(this, 9)"> <label>Category</font>
						:
				</label> <%=HTMLUtility.getList1("Category", String.valueOf(bean.getCategory()), map)%>

					<input type="submit" name="operation"
					value="<%=ProductsListCtl.OP_RESET%>"> <input type="submit"
					name="operation" value="<%=ProductsListCtl.OP_SEARCH%>"></td>
			</table>
			<br>

			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">


				<tr style="background: Yellow">
					<th><input type="checkbox" id="select_all" name="select">Select
						All</th>

					<th>S.No.</th>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>DateOfpurchase</th>
					<th>Category</th>
					<th>Edit</th>
				</tr>
				<%
					while (it.hasNext()) {
							bean = it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>

					<td><%=index++%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getDescription()%></td>
					<td><%=bean.getPrice()%></td>
					<td><%=bean.getDateOfpurchase()%></td>
					<td><%=bean.getCategory()%></td>
					<td><a href="ProductsCtl?id=<%=bean.getId()%>">Edit</td>
				</tr>
				<%
					}
				%>

				<table width="100%">

					<tr>
						<th></th>
						<%
							if (pageNo == 1) {
						%>
						<td><input type="submit" name="operation" disabled="disabled"
							value="<%=ProductsListCtl.OP_PREVIOUS%>"></td>
						<%
							} else {
						%>
						<td><input type="submit" name="operation"
							value="<%=ProductsListCtl.OP_PREVIOUS%>"></td>
						<%
							}
						%>

						<td><input type="submit" name="operation"
							value="<%=ProductsListCtl.OP_DELETE%>"></td>
						<td align="center"><input type="submit" name="operation"
							value="<%=ProductsListCtl.OP_NEW%>"></td>

						<td align="right"><input type="submit" name="operation"
							value="<%=ProductsListCtl.OP_NEXT%>"
							<%=(list.size() < pageSize || next == 0) ? "disabled" : ""%>></td>



					</tr>
				</table>
				<%
					}
					if (list.size() == 0) {
				%>
				<td align="center"><input type="submit" name="operation"
					value="<%=ProductsListCtl.OP_BACK%>"></td>


				<%
					}
				%>

				<input type="hidden" name="pageNo" value="<%=pageNo%>">
				<input type="hidden" name="pageSize" value="<%=pageSize%>">

				</form>
				</br>
				</br>
				</br>
				</br>
				</br>
				</br>
				</br>

				</center>
				<%@include file="Footer.jsp"%>
</body>
</html>