
<%@page import="com.rays.pro4.controller.VichcleTrackingCtl"%>
<%@page import="com.rays.pro4.controller.InventoryCtl"%>
<%@page import="com.rays.pro4.controller.ShopCtl"%>
<%@page import="com.rays.pro4.controller.OrderCtl"%>
<%@page import="com.rays.pro4.controller.BankCtl"%>
<%@page import="com.rays.pro4.controller.ProductCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>vichcletracking Page</title>

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
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.VichcleTrackingBean"
		scope="request"></jsp:useBean>
	<%@ include file="Header.jsp"%>

	<center>

		<form action="<%=ORSView.VICHCLE_TRACKING_CTL%>" method="post">

			<div align="center">
				<h1>

					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<tr>
						<th><font size="5px"> VichcleTracking update </font></th>
					</tr>
					<%
						} else {
					%>
					<tr>
						<th><font size="5px"> Add VichcleTracking </font></th>
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
					<th align="left">Lat <span style="color: red">*</span> :
					</th>
					<td><input type="number" name="Lat" placeholder="Enter Lat "
						size="26" style="width: 210px"
						value="<%=DataUtility.getStringFromDouble(bean.getLat())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("Lat", request)%></font></td>

				</tr>

				<tr>
					<th style="padding: 3px"></th>
				</tr>


				<%--  <tr>
					<th align="left">LongId <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="LongId" placeholder="Enter LongId "
						size="26" value="<%=DataUtility.getStringFromDouble(bean.getLongId())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("LongId", request)%></font></td>

				</tr>
  --%>
				<!-- <tr>
					<th style="padding: 3px"></th>
				</tr> -->

				<tr>
					<th align="left">LongId <span style="color: red">*</span> :
					</th>
					<td><input type="number" name="LongId"
						placeholder="Enter LongId " size="26" style="width: 210px"
						value="<%=DataUtility.getStringFromDouble(bean.getLongId())%>"></td>
					<td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("LongId", request)%></font></td>

				</tr>

				<tr>
					<th style="padding: 3px"></th>
				</tr>


				<tr>
					<th align="left">VichcleId <span style="color: red">*</span> :
					</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("1", "1");
							map.put("2", "2");

							String hlist = HTMLUtility.getList("VichcleId", String.valueOf(bean.getVichcleId()), map);
						%> <%=hlist%>
					</td>
					<td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("VichcleId", request)%></font></td>
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
						name="operation" value="<%=VichcleTrackingCtl.OP_UPDATE%>">
						&nbsp; &nbsp; <input type="submit" name="operation"
						value="<%=VichcleTrackingCtl.OP_CANCEL%>"></td>

					<%
						} else {
					%>

					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=VichcleTrackingCtl.OP_SAVE%>">
						&nbsp; &nbsp; <input type="submit" name="operation"
						value="<%=VichcleTrackingCtl.OP_RESET%>"></td>

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
