
<%@page import="com.rays.pro4.controller.LoanListCtl"%>
<%@page import="com.rays.pro4.Bean.LoanBean"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.rays.pro4.controller.AtmListCtl"%>
<%@page import="com.rays.pro4.Bean.AtmBean"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<script>
	function validateNumericInput(inputField) {
		// Get the value entered by the user
		var inputValue = inputField.value;

		// Regular expression to check if the input is numeric
		var numericPattern = /^\d*$/;

		// Test the input value against the numeric pattern
		if (!numericPattern.test(inputValue)) {
			// If input is not numeric, clear the field
			inputField.value = inputValue.replace(/[^\d]/g, ''); // Remove non-numeric characters
		}
	}
</script>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />

<title>Loan List</title>
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
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.LoanBean"
		scope="request"></jsp:useBean>
	<%@include file="Header.jsp"%>


	<form action="<%=ORSView.LOAN_LIST_CTL%>" method="post">

		<center>

			<div align="center">
				<h1>Loan List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			</div>

			<%
				int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
				List plist = (List) request.getAttribute("prolist");
				HashMap map = (HashMap) request.getAttribute("rlist");
			%>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<LoanBean> it = list.iterator();

				if (list.size() != 0) {
			%>
			<table width="100%" align="center">
				<tr>


					<td align="center">&emsp;<label>Loan_Amount :</label> <input
						type="number" name="Loan_Amount"
						placeholder="Enter Loan_Amount " 
						oninput="this.value = this.value.slice(0, 9)"
						value="<%=ServletUtility.getParameter("Loan_Amount", request)%>">
						
					<td align="center"><label>Loan_Start_Date</font> :
					</label> <input type="text" name="Loan_Start_Date" placeholder="Enter dOB" id="udate"
						readonly="readonly"
						value="<%=ServletUtility.getParameter("Loan_Start_Date", request)%>">

						&emsp; <label>Customer_Id</font> :
					</label> <%=HTMLUtility.getList("Customer_Id", String.valueOf(bean.getCustomer_Id()), map)%>
					 
                        <td align="center">&emsp;<label>Interest_Rate :</label> <input
						type="number" name="Interest_Rate"
						placeholder="Enter Interest_Rate "
						oninput="this.value = this.value.slice(0, 9)"
						value="<%=ServletUtility.getParameter("Interest_Rate", request)%>">
						
						&nbsp; <input type="submit" name="operation"
						value="<%=LoanListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation" value="<%=LoanListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr style="background: skyblue">
					<th><input type="checkbox" id="select_all" name="select">Select
						All</th>

					<th>S.No.</th>
					<th>Loan_Start_Date</th>
					<th>Customer_Id</th>
					<th>Loan_Amount</th>
					<th>Interest_Rate</th>
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
					<td><%=bean.getLoan_Start_Date()%></td>
					<td><%=bean.getCustomer_Id()%></td>
					<td><%=bean.getLoan_Amount()%></td>
					<td><%=bean.getInterest_Rate()%></td>
					<td><a href="LoanCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						value="<%=LoanListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=LoanListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>

					<td><input type="submit" name="operation"
						value="<%=LoanListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=LoanListCtl.OP_NEW%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=LoanListCtl.OP_NEXT%>"
						<%=(list.size() < pageSize || next == 0) ? "disabled" : ""%>></td>



				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=LoanListCtl.OP_BACK%>"></td>
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
