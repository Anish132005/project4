
<%@page import="com.rays.pro4.controller.EmpListCtl"%>
<%@page import="com.rays.pro4.Bean.EmpBean"%>
<%@page import="com.rays.pro4.controller.ShopListCtl"%>
<%@page import="com.rays.pro4.Bean.ShopBean"%>
<%@page import="com.rays.pro4.controller.OrderListCtl"%>
<%@page import="com.rays.pro4.Bean.OrderBean"%>
<%@page import="com.rays.pro4.controller.BankListCtl"%>
<%@page import="com.rays.pro4.Bean.BankBean"%>
<%@page import="com.rays.pro4.controller.ProductListCtl"%>
<%@page import="com.rays.pro4.Bean.ProductBean"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />

<title>Employee List</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>

<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#udate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2002',
		//  mindefaultDate : "01-01-1962"
		});
	});
</script>

</head>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.EmpBean"
		scope="request"></jsp:useBean>
	<%@include file="Header.jsp"%>


	<form action="<%=ORSView.EMP_LIST_CTL%>" method="post">

		<center>

			<div align="center">
				<h1>Employee List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			</div>

			<%
			int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
				List plist = (List) request.getAttribute("Company");
			%>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<EmpBean> it = list.iterator();

				if (list.size() != 0) {
			%>
			<table width="100%" align="center">
				<tr>
				
					
					<td align="center">&emsp;<label>EmpName</font> :
					</label> <input type="text" name="EmpName" placeholder="Enter EmpName "
						value="<%=ServletUtility.getParameter("EmpName", request)%>">
					<td align="center">&emsp; <label>Salary</font> :
					</label> <input type="text" name="Salary"
						placeholder="Enter Salary "
						value="<%=ServletUtility.getParameter("Salary ", request)%>">
					 <td align="center"><label>Dob</font> :
					</label> <input type="text" name="Dob" placeholder="Enter Dob" 
						id="udate" readonly="readonly"
						value="<%=ServletUtility.getParameter("Dob", request)%>">
				
			 &emsp; <label>Company</font> :
					</label> <%=HTMLUtility.getList("Company", String.valueOf(bean.getCompany()), plist)%> 
						
						
							&nbsp; <input type="submit" name="operation"
						value="<%=EmpListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation" value="<%=EmpListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr style="background: skyblue">
					<th><input type="checkbox" id="select_all" name="select">Select
						All</th>

					<th>S.No.</th>
					<th>EmpName</th>
					<th>Dob</th>
					<th>Company</th>
					<th>Salary</th>
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
					<td><%=bean.getEmpName()%></td>
					<td><%=bean.getDob()%></td>
					<td><%=bean.getCompany()%></td>
					<td><%=bean.getSalary()%></td>
					<td><a href="EmpCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>

			<table width="100%">
				<tr>
					<th></th>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=EmpListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=EmpListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>

					<td><input type="submit" name="operation"
						value="<%=EmpListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=EmpListCtl.OP_NEW%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=EmpListCtl.OP_NEXT%>"
						<%=(list.size() < pageSize || next == 0) ? "disabled" : ""%>></td>



				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=EmpListCtl.OP_BACK%>"></td>
			<%
				}
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
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
s