<!DOCTYPE html>

<%@page import="com.rays.pro4.Bean.UserBean"%>
<%@page import="com.rays.pro4.Bean.RoleBean"%>
<%@ page import="com.rays.pro4.controller.LoginCtl"%>
<%@page import="com.rays.pro4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>


<body>
	<%
		UserBean userBean = (UserBean) session.getAttribute("user");
		boolean userLoggedIn = userBean != null;
		String welcomeMsg = "Hi, ";
		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			welcomeMsg += userBean.getFirstName() + " (" + role + ")";
		} else {
			welcomeMsg += "Guest";
		}
	%>

	<table>
		<tr>
			<th></th>
			<td width="90%"><a href="<%=ORSView.WELCOME_CTL%>">Welcome</b></a> |
				<%
				if (userLoggedIn) {
			%> <a
				href=" <%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</b></a>

				<%
					} else {
				%> <a href="<%=ORSView.LOGIN_CTL%>">Login</b></a> <%
 	}
 %></td>
			<td rowspan="2">
				<h1 align="right">
					<img src="<%=ORSView.APP_CONTEXT%>/img/customLogo.jpg" width="175"
						height="50">
				</h1>
			</td>
		</tr>

		<tr>
			<th></th>
			<td>
				<h3><%=welcomeMsg%></h3>
			</td>
		</tr>

		<%
			if (userLoggedIn) {
		%>

		<tr>
			<th></th>
			<td colspan="2"><font style="font-size: 18px"> <a
					href="<%=ORSView.MY_PROFILE_CTL%>">MyProfile</b></a> | <a
					href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</b></a> | <a
					href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</b></a> | <a
					href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet
						MeritList</b>
				</a> | <%
					if (userBean.getRoleId() == RoleBean.ADMIN) {
				%> <a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</b></a> | <a
					href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> | <a
					href="<%=ORSView.USER_CTL%>">Add User</b></a> | <a
					href="<%=ORSView.USER_LIST_CTL%>">User List</b></a> | <a
					href="<%=ORSView.COLLEGE_CTL%>">Add College</b></a> | <a
					href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> | <a
					href="<%=ORSView.ROLE_CTL%>">Add Role</b></a> | <a
					href="<%=ORSView.ROLE_LIST_CTL%>">Role List</b></a> | <a
					href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> | <a
					href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> | <a
					href="<%=ORSView.COURSE_CTL%>">Add Course</b></a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</b></a> | <a
					href="<%=ORSView.SUBJECT_CTL%>">Add Subject</b></a> | <a
					href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</b></a> | <a
					href="<%=ORSView.FACULTY_CTL%>">Add Faculty</b></a> | <a
					href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</b></a> | <a
					href="<%=ORSView.TIMETABLE_CTL%>">Add TimeTable</b></a> | <a
					href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</b></a> | <a
					href="<%=ORSView.PRODUCT_CTL%>">Add Product</b></a> | <a
					href="<%=ORSView.PRODUCT_LIST_CTL%>">Product List</b></a> |<a
					href="<%=ORSView.DOCTOR_CTL%>"> ADD Doctor</b></a> |<a
					href="<%=ORSView.DOCTOR_LIST_CTL%>">Doctor List</b></a> | <a
					href="<%=ORSView.BANK_CTL%>"> Add Bank</b></a> | <a
					href="<%=ORSView.BANK_LIST_CTL%>">Bank List</b></a> | <a
					href="<%=ORSView.PATIENT_CTL%>">Patient Add</b></a> | <a
					href="<%=ORSView.PATIENT_LIST_CTL%>">Patient List</b></a>| <a
					href="<%=ORSView.SHOP_CTL%>">Shop Add</b></a>| <a
					href="<%=ORSView.SHOP_LIST_CTL%>">Shop List</b></a>| <a
					href="<%=ORSView.EMP_CTL%>">Employee Add</b></a>| <a
					href="<%=ORSView.EMP_LIST_CTL%>">Employee List</b></a> | <a
					href="<%=ORSView.PROJECT_CTL%>">Project Add</b></a> | <a
					href="<%=ORSView.PROJECT_LIST_CTL%>">Project List</b></a>| <a
					href="<%=ORSView.VEHICLE_CTL%>">Vehicle Add</b></a> | <a
					href="<%=ORSView.VEHICLE_LIST_CTL%>">Vehicle List</b></a>| <a
					href="<%=ORSView.PAYMENT_CTL%>"> Payment Add</b></a>| <a
					href="<%=ORSView.INVENTORY_CTL%>"> Inventory Add</b></a>| <a
					href="<%=ORSView.INVENTORY_LIST_CTL%>"> Inventory List</b></a> | <a
					href="<%=ORSView.VICHCLE_TRACKING_CTL%>"> VichcleTracking Add</b></a> |
					<a href="<%=ORSView.VICHCLE_TRACKING_LIST_CTL%>">
						VichcleTracking List</b>
				</a> | <a href="<%=ORSView.LOAN_CTL%>"> Loan Add</b></a> | <a
					href="<%=ORSView.LOAN_LIST_CTL%>"> Loan List</b></a> | <a
					href="<%=ORSView.SUPPLIER_CTL%>"> Supplier Add</b></a> | <a
					href="<%=ORSView.SUPPLIER_LIST_CTL%>"> Supplier List</b></a> | <a
					href="<%=ORSView.PRODUCTS_CTL%>"> product add</b></a> | <a
					href="<%=ORSView.PRODUCTS_LIST_CTL%>">product List</b></a> | <a
					href="<%=ORSView.CAR_CTL%>">Car add</b></a>| <a
<<<<<<< HEAD
					href="<%=ORSView.CAR_LIST_CTL%>">Car List</b></a> | <a
					href="<%=ORSView.STOCK_CTL%>">Stock add</b></a>| <a
					href="<%=ORSView.STOCK_LIST_CTL%>">Stock List</b></a> | <a
					href="<%=ORSView.PORTFOLIO_CTL%>">Portfolio add</b></a>| <a
					href="<%=ORSView.PORTFOLIO_LIST_CTL%>">Portfolio List</b></a> | <a
					href="<%=ORSView.STAFF_CTL%>">Staff add</b></a>| <a
					href="<%=ORSView.STAFF_LIST_CTL%>">Staff List</b></a> | <a
					href="<%=ORSView.CLIENT_CTL%>">Client add</b></a>| <a
					href="<%=ORSView.CLIENT_LIST_CTL%>">Client List</b></a> | <a
					href="<%=ORSView.ATM_CTL%>"> ATM ADD</b></a> | <a
					href="<%=ORSView.ATM_LIST_CTL%>"> ATM List</b></a> | <a
					href="<%=ORSView.CUSTOMER_CTL%>"> Customer ADD</b></a> | <a
					href="<%=ORSView.CUSTOMER_LIST_CTL%>"> Customer List</b></a> | <a
					href="<%=ORSView.ORDER_CTL%>"> Order ADD</b></a> | <a
=======
					href="<%=ORSView.CAR_LIST_CTL%>">Car List</b></a>
					| <a
					href="<%=ORSView.STOCK_CTL%>">Stock add</b></a>| <a
					href="<%=ORSView.STOCK_LIST_CTL%>">Stock List</b></a>
					
					
					  <%-- 		
					| <a
					href="<%=ORSView.ATM_CTL%>"> ATM ADD</b></a>  | <a
					href="<%=ORSView.ATM_LIST_CTL%>"> ATM List</b></a>  
					
					| <a
					href="<%=ORSView.CUSTOMER_CTL%>"> Customer ADD</b></a>  | <a
					href="<%=ORSView.CUSTOMER_LIST_CTL%>"> Customer List</b></a>  
					
					
 --%> | <a href="<%=ORSView.ORDER_CTL%>"> Order ADD</b></a> | <a
>>>>>>> origin/main
					href="<%=ORSView.ORDER_LIST_CTL%>"> Order List</b></a> |<a
					target="blank" href="<%=ORSView.JAVA_DOC_VIEW%>">Java Doc</b></a> <%
 	}
 %> <%
 	if (userBean.getRoleId() == RoleBean.STUDENT) {
 %> <a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> | <a
					href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</b></a> | <a
					href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</b></a> | <a
					href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</b></a> | <a
					href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</b></a> | <%
 	}
 %> <%
 	if (userBean.getRoleId() == RoleBean.KIOSK) {
 %> <a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> | <a
					href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</b></a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</b></a> | <%
 	}
 %> <%
 	if (userBean.getRoleId() == RoleBean.FACULTY) {
 			// System.out.println("======>><><>"+userBean.getRoleId());
 %> <a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</b></a> | <a
					href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> | <a
					href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> | <a
					href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> | <a
					href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</b></a> | <a
					href="<%=ORSView.SUBJECT_CTL%>">Add Subject</b></a> | <br> <a
					href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</b></a> | <a
					href="<%=ORSView.TIMETABLE_CTL%>">Add TimeTable</b></a> | <a
					href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</b></a> | <%
 	}
 %> <%
 	if (userBean.getRoleId() == RoleBean.COLLEGE) {
 			//    System.out.println("======>><><>"+userBean.getRoleId());
 %> <a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</b></a> | <a
					href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> | <a
					href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> | <a
					href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> | <a
					href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</b></a> | <a
					href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</b></a> | <a
					href="<%=ORSView.COURSE_LIST_CTL%>">Course List</b></a> | <%
 	}
 %>

			</font></td>
		</tr>
		<%
			}
		%>
	</table>
	<hr>
</body>
</html>