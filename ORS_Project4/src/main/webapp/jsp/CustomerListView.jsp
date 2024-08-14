
<%@page import="com.rays.pro4.controller.CustomerListCtl"%>
<%@page import="com.rays.pro4.Bean.CustomerBean"%>
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
<!-- <script>
    // JavaScript function to disallow numbers in a text field
    function disallowNumbers(event) {
      const key = event.key;
      // Check if the pressed key is a number
      if (/\d/.test(key)) {
        // Cancel the key press
        event.preventDefault();
      }
    }
  </script> -->
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />

<title>Customer List</title>
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
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.CustomerBean"
		scope="request"></jsp:useBean>
	<%@include file="Header.jsp"%>


	<form action="<%=ORSView.CUSTOMER_LIST_CTL%>" method="post">

		<center>

			<div align="center">
				<h1>Customer List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			</div>

			<%
				int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
				List plist = (List) request.getAttribute("prolist");
				HashMap map = (HashMap) request.getAttribute("lolist");
			%>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<CustomerBean> it = list.iterator();

				if (list.size() != 0) {
			%>
			<table width="100%" align="center">
				<tr>


					<td align="center">&emsp;<label>Name :</label> 
						
						<td><input type="text" id="Name" name="Name"    placeholder="Enter Name "
						value="<%=ServletUtility.getParameter("Name", request)%>">
						
					<td align="center"><label>Date_of_Birth</font> :
					</label> <input type="text" name="Date_of_Birth" placeholder="Enter dOB" id="udate"
						readonly="readonly"
						value="<%=ServletUtility.getParameter("Date_of_Birth", request)%>">

						&emsp; <label>Gender</font> :
					</label> <%=HTMLUtility.getList("Gender", String.valueOf(bean.getGender()), map)%>
					<td align="center">&emsp;<label>PhoneNumber</font> :
					</label> <input type="number" name="PhoneNumber" placeholder="Enter PhoneNumber "
					oninput="this.value = this.value.slice(0, 10)"
						value="<%=ServletUtility.getParameter("PhoneNumber", request)%>">


						&nbsp; <input type="submit" name="operation"
						value="<%=CustomerListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation" value="<%=CustomerListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr style="background: skyblue">
					<th><input type="checkbox" id="select_all" name="select">Select
						All</th>

					<th>S.No.</th>
					<th>Name</th>
					<th>Date_of_Birth</th>
					<th>PhoneNumber</th>
					<th>Gender</th>
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
					<td><%=bean.getDate_of_Birth()%></td>
					<td><%=bean.getPhoneNumber()%></td>
					<td><%=bean.getGender()%></td>
					<td><a href="CustomerCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						value="<%=CustomerListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=CustomerListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>

					<td><input type="submit" name="operation"
						value="<%=CustomerListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=CustomerListCtl.OP_NEW%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=CustomerListCtl.OP_NEXT%>"
						<%=(list.size() < pageSize || next == 0) ? "disabled" : ""%>></td>



				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=CustomerListCtl.OP_BACK%>"></td>
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
