<%@page import="com.rays.pro4.controller.StockCtl"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Car Page</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#udatee").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2002',
		});
	});

	function limitInputLength(input, maxLength) {
		if (input.value.length > maxLength) {
			input.value = input.value.slice(0, maxLength);
		}
	}

	function validateAndSave() {
		const inputField = document.getElementById('numberInput');
		const value = parseFloat(inputField.value);

		// Check if the value is a positive number
		if (isNaN(value) || value <= 0) {
			alert('Please enter a valid positive number.');
			inputField.focus();
			return false;
		}

		// Save the positive number
		saveNumber(value);
		alert('Number saved: ' + value);
		return false; // Prevent form submission for demonstration
	}

	function saveNumber(number) {
		// Implement your saving logic here (e.g., sending to server or storing locally)
		console.log('Saving the number:', number);
	}
</script>
</head>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.StockBean"
		scope="request"></jsp:useBean>
	<%@ include file="Header.jsp"%>

	<center>
		<form action="<%=ORSView.STOCK_CTL%>" method="post">

			<div align="center">
				<h1>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<tr>
						<th><font size="5px"> Update StockPurchase </font></th>
					</tr>
					<%
						} else {
					%>
					<tr>
						<th><font size="5px"> Add StockPurchase </font></th>
					</tr>
					<%
						}
					%>

					<%
						Map map = (Map) request.getAttribute("prolist");
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
					<th align="left">orderType<span style="color: red">*</span> :
					</th>
					<td></label> <%=HTMLUtility.getList1("orderType", String.valueOf(bean.getOrderType()), map)%>

					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("orderType", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 1px"></th>
				</tr>



				<tr>
					<th style="padding: 1px"></th>
				</tr>
				<tr>
					<th align="left">quantity<span style="color: red">*</span> :
					</th>
					<td><input type="number" name="quantity" id="quantityInput"
						placeholder="Enter quantity" style="width: 205px"
						value="<%=DataUtility.getStringData(bean.getQuantity())%>"
						 oninput="limitInputLength(this, 9)"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("quantity", request)%></font></td>
				</tr>
				
				<tr>
					<th style="padding: 1px"></th>
				</tr>
				
				
				<tr>
					<th align="left">purchasePrice<span style="color: red">*</span> :
					</th>
					<td><input type="number" name="purchasePrice" id="quantityInput"
						placeholder="Enter purchasePrice" style="width: 205px"
						value="<%=DataUtility.getStringData(bean.getPurchasePrice())%>"
						 oninput="limitInputLength(this, 20)"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("purchasePrice", request)%></font></td>
				</tr>

				<tr>
					<th style="padding: 1px"></th>
				</tr>

				<tr>
					<th align="left">purchaseDate <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="purchaseDate" placeholder="Enter purchaseDate "
						size="26" id="udatee" readonly="readonly"
						value="<%=DataUtility.getDateString(bean.getPurchaseDate())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("purchaseDate", request)%></font></td>
				</tr>

				<tr>
					<th style="padding: 1px"></th>
				</tr>



				<th></th>
				<%
					if (bean.getId() > 0) {
				%>
				<td colspan="2">&nbsp; &emsp; <input type="submit"
					name="operation" value="<%=StockCtl.OP_UPDATE%>"> &nbsp;
					&nbsp; <input type="submit" name="operation"
					value="<%=StockCtl.OP_CANCEL%>"></td>

				<%
					} else {
				%>

				<td colspan="2">&nbsp; &emsp; <input type="submit"
					name="operation" value="<%=StockCtl.OP_SAVE%>"> &nbsp; &nbsp;
					<input type="submit" name="operation" value="<%=StockCtl.OP_RESET%>"></td>

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