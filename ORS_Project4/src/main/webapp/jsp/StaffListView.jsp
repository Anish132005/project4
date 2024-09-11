<%@page import="com.rays.pro4.controller.StaffListCtl"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Bean.StaffBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="java.util.Map"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Staff page</title>
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
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.StaffBean"
		scope="request"></jsp:useBean>
	<%@include file="Header.jsp"%>
	<form action="<%=ORSView.STAFF_LIST_CTL%>" method="post">
		<center>

			<div align="center">
				<h1>Staff List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			</div>
			<%
				Map<Integer, String> map = (Map<Integer, String>) request.getAttribute("prolist");
				int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
			%>
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<StaffBean> it = list.iterator();
				if (list.size() != 0) {
			%>
			<table width="100%" align="center">

				<td align="center"><label>identifier</font> :
				</label> <input type="number" name="identifier"
					oninput="limitInputLength(this, 9)" placeholder="Enter identifier"
					value="<%=ServletUtility.getParameter("identifier", request)%>">
				<td align="center"><label>fullName</font>
				</label> <input type="text" name="fullName" placeholder="Enter fullName"
					value="<%=ServletUtility.getParameter("fullName", request)%>">
				<td align="center"><label>previousEmployer</font>
				</label> <input type="text" name="previousEmployer"
					placeholder="Enter previousEmployer"
					value="<%=ServletUtility.getParameter("previousEmployer", request)%>">
				<td align="center"><label>JoiningDate</font> :
				</label> <input type="text" name="dob" placeholder="Enter JoiningDate"
					id="Udate" readonly="readonly"
					value="<%=ServletUtility.getParameter("dob", request)%>">
				<th align="left">Division<span style="color: red">*</span> :
				</th>
				<td></label> <%=HTMLUtility.getList1("division", String.valueOf(bean.getDivision()), map)%>

					<input type="submit" name="operation"
					value="<%=StaffListCtl.OP_SEARCH%>"> <input type="submit"
					name="operation" value="<%=StaffListCtl.OP_RESET%>"></td>
			</table>

			<br>

			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">


				<tr style="background: yellow">
					<th><input type="checkbox" id="select_all" name="select">Select
						All</th>

					<th>S.No.</th>
					<th>identifier</th>
					<th>fullName</th>
					<th>dob</th>
					<th>division</th>
					<th>previousEmployer</th>
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
					<td><%=bean.getIdentifier()%></td>
					<td><%=bean.getFullName()%></td>
					<td><%=bean.getDob()%></td>
					<td><%=map.get(Integer.parseInt(bean.getDivision()))%></td>
					<td><%=bean.getPreviousEmployer()%></td>
					<td><a href="StaffCtl?id=<%=bean.getId()%>">Edit</td>
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
							value="<%=StaffListCtl.OP_PREVIOUS%>"></td>
						<%
							} else {
						%>
						<td><input type="submit" name="operation"
							value="<%=StaffListCtl.OP_PREVIOUS%>"></td>
						<%
							}
						%>

						<td><input type="submit" name="operation"
							value="<%=StaffListCtl.OP_DELETE%>"></td>
						<td align="center"><input type="submit" name="operation"
							value="<%=StaffListCtl.OP_NEW%>"></td>

						<td align="right"><input type="submit" name="operation"
							value="<%=StaffListCtl.OP_NEXT%>"
							<%=(list.size() < pageSize || next == 0) ? "disabled" : ""%>></td>



					</tr>
				</table>
				<%
					}
					if (list.size() == 0) {
				%>
				<td align="center"><input type="submit" name="operation"
					value="<%=StaffListCtl.OP_BACK%>"></td>


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