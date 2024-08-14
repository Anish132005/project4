<%@page import="com.rays.pro4.controller.CarCtl"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
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
</script>
</head>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.CarBean"
		scope="request"></jsp:useBean>
	<%@ include file="Header.jsp"%>

	<center>
		<form action="<%=ORSView.CAR_CTL%>" method="post">

			<div align="center">
				<h1>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<tr>
						<th><font size="5px"> Update CarDetails </font></th>
					</tr>
					<%
						} else {
					%>
					<tr>
						<th><font size="5px"> Add CarDetails </font></th>
					</tr>
					<%
						}
					%>

					 <%
						Map map = (Map) request.getAttribute("prolist");
						Map map1 = (Map) request.getAttribute("prolist1");
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
					<th align="left">CarName<span style="color: red">*</span> :
					</th>
					<td></label> <%=HTMLUtility.getList1("CarName", String.valueOf(bean.getCarName()), map)%>

					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("CarName", request)%></font></td>
				</tr>
				<tr>
					<th style="padding: 1px"></th>
				</tr>

				<tr>
					<th align="left">CarModel<span style="color: red">*</span> :
					</th>
					<td></label> <%=HTMLUtility.getList2("CarModel", String.valueOf(bean.getCarModel()), map1)%>

					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("CarModel", request)%></font></td>
				</tr>

				<tr>
					<th style="padding: 1px"></th>
				</tr>
				<tr>
					<th align="left">Price<span style="color: red">*</span> :
					</th>
					<td><input type="number" name="Price" id="quantityInput"
						placeholder="Enter Price" style="width: 205px"
						value="<%=DataUtility.getStringData(bean.getPrice())%>"
						oninput="limitInputLength(this, 9)"></td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("Price", request)%></font></td>
				</tr>

				<tr>
					<th style="padding: 1px"></th>
				</tr>

				<tr>
					<th align="left">dob <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="dob" placeholder="Enter Date "
						size="26" id="udatee" readonly="readonly"
						value="<%=DataUtility.getDateString(bean.getDob())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
				
				<tr>
					<th style="padding: 1px"></th>
				</tr>

<tr>
					<th align="left">OwnerName <span style="color: red">*</span>
						:
					</th>
					<td><input type="text" name="OwnerName"
						placeholder="Enter OwnerName" size="26"
						value="<%=DataUtility.getStringData(bean.getOwnerName())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("OwnerName", request)%></font></td>

				</tr>
				
				<tr>
					<th style="padding: 1px"></th>
				</tr>
				


				<th></th>
				<%
					if (bean.getId() > 0) {
				%>
				<td colspan="2">&nbsp; &emsp; <input type="submit"
					name="operation" value="<%=CarCtl.OP_UPDATE%>"> &nbsp;
					&nbsp; <input type="submit" name="operation"
					value="<%=CarCtl.OP_CANCEL%>"></td>

				<%
					} else {
				%>

				<td colspan="2">&nbsp; &emsp; <input type="submit"
					name="operation" value="<%=CarCtl.OP_SAVE%>"> &nbsp; &nbsp;
					<input type="submit" name="operation" value="<%=CarCtl.OP_RESET%>"></td>

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