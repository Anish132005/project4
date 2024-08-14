
<%@page import="com.rays.pro4.controller.LoanCtl"%>
<%@page import="com.rays.pro4.controller.AtmCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
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
<title>Loan Page</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#udatee").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2020',
		});
	});
</script>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.LoanBean"
		scope="request"></jsp:useBean>
	<%@ include file="Header.jsp"%>

	<center>

		<form action="<%=ORSView.LOAN_CTL%>" method="post">

			<div align="center">
				<h1>

					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<tr>
						<th><font size="5px"> Loan update </font></th>
					</tr>
					<%
						} else {
					%>
					<tr>
						<th><font size="5px"> Loan Add </font></th>
					</tr>
					<%
						}
					%>
				</h1>

				<h3>
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			</div>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>

				<tr>
					<th align="left">Loan_Start_Date <span style="color: red">*</span>
						:
					</th>
					<td><input type="text" name="Loan_Start_Date"
						placeholder="Enter Loan_Start_Date" size="26" readonly="readonly"
						id="udatee"
						value="<%=DataUtility.getDateString(bean.getLoan_Start_Date())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("Loan_Start_Date", request)%></font></td>
				</tr>

				<tr>

					<th style="padding: 3px"></th>
				</tr>


				<tr>
					<th align="left">Customer_Id <span style="color: red">*</span>
						:
					</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("AWS4561120", "AWS4561120");
							map.put("UCD8521479", "UCD8521479");
							map.put("PGF4125633", "PGF4125633");

							String hlist = HTMLUtility.getList("Customer_Id", String.valueOf(bean.getCustomer_Id()), map);
						%> <%=hlist%>
					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("Customer_Id", request)%></font></td>
				</tr>

				<tr>
					<th style="padding: 3px"></th>
				</tr>



				<tr>
					<th align="left">Loan_Amount <span style="color: red">*</span>
						:
					</th>
					<td><input type="text" name="Loan_Amount"
						placeholder="Enter Loan_Amount " size="26" style="width: 210px"
						oninput="validateNumericInput(this)" maxlength="9"
						value="<%=DataUtility.getStringData(bean.getLoan_Amount())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("Loan_Amount", request)%></font></td>

				</tr>

				<tr>
					<th style="padding: 3px"></th>
				</tr>


				<tr>
					<th align="left">Interest_Rate<span style="color: red">*</span>
						:
					</th>
					<td><input type="text" name="Interest_Rate"
						placeholder="Enter Interest_Rate " size="26" style="width: 210px"
						oninput="validateNumericInput(this)" maxlength="9"
						value="<%=DataUtility.getStringData(bean.getInterest_Rate())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("Interest_Rate", request)%></font></td>

				</tr>

				<tr>
					<th style="padding: 3px"></th>
				</tr>


				<tr>
					<th></th>
					<%
						if (bean.getId() > 0) {
					%>
					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=LoanCtl.OP_UPDATE%>"> &nbsp;
						&nbsp; <input type="submit" name="operation"
						value="<%=LoanCtl.OP_CANCEL%>"></td>

					<%
						} else {
					%>

					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=LoanCtl.OP_SAVE%>"> &nbsp;

						&nbsp; <input type="submit" name="operation"
						value="<%=LoanCtl.OP_RESET%>"></td>

					<%
						}
					%>
				</tr>
			</table>
		</form>
	</center>

	<%@ include file="Footer.jsp"%>
</body>
</html>
