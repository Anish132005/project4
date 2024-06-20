package com.rays.pro4.controller;

/**
 * @author Anish Malviya
 *
 */
public interface ORSView {

	public String APP_CONTEXT = "/ORS_Project4";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public static String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public static String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";
	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";
	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";
	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";
	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";
	public String TIMETABLE_VIEW = PAGE_FOLDER + "/TimeTableView.jsp";
	public String TIMETABLE_LIST_VIEW = PAGE_FOLDER + "/TimeTableListView.jsp";
	public String ERROR_VIEW = PAGE_FOLDER + "/ErrorView.jsp";

	public String PRODUCT_VIEW = PAGE_FOLDER + "/ProductView.jsp";
	public String PRODUCT_LIST_VIEW = PAGE_FOLDER + "/ProductListView.jsp";

	public String DOCTOR_VIEW = PAGE_FOLDER + "/DoctorView.jsp";
	public String DOCTOR_LIST_VIEW = PAGE_FOLDER + "/DoctorListView.jsp";

	public String BANK_VIEW = PAGE_FOLDER + "/BankView.jsp";
	public String BANK_LIST_VIEW = PAGE_FOLDER + "/BankListView.jsp";

	public String ORDER_VIEW = PAGE_FOLDER + "/OrderView.jsp";
	public String ORDER_LIST_VIEW = PAGE_FOLDER + "/OrderListView.jsp";

	public String PATIENT_VIEW = PAGE_FOLDER + "/PatientView.jsp";
	public String PATIENT_LIST_VIEW = PAGE_FOLDER + "/PatientListView.jsp";

	public String SHOP_VIEW = PAGE_FOLDER + "/ShopView.jsp";
	public String SHOP_LIST_VIEW = PAGE_FOLDER + "/ShopListView.jsp";

	public String EMP_VIEW = PAGE_FOLDER + "/EmpView.jsp";
	public String EMP_LIST_VIEW = PAGE_FOLDER + "/EmpListView.jsp";

	public String PROJECT_VIEW = PAGE_FOLDER + "/ProjectView.jsp";
	public String PROJECT_LIST_VIEW = PAGE_FOLDER + "/ProjectListView.jsp";

	public String VEHICLE_VIEW = PAGE_FOLDER + "/VehicleView.jsp";
	public String VEHICLE_LIST_VIEW = PAGE_FOLDER + "/VehicleListView.jsp";

	public String PAYMENT_VIEW = PAGE_FOLDER + "/PaymentView.jsp";
	public String PAYMENT_LIST_VIEW = PAGE_FOLDER + "/PaymentListView.jsp";

	public String INVENTORY_VIEW = PAGE_FOLDER + "/InventoryView.jsp";
	public String INVENTORY_LIST_VIEW = PAGE_FOLDER + "/InventoryListView.jsp";

	// public String ERROR_VIEW5 = PAGE_FOLDER + "/ErrorView5.jsp";

	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";
	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";
	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";
	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";
	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";
	public String TIMETABLE_CTL = APP_CONTEXT + "/ctl/TimeTableCtl";
	public String TIMETABLE_LIST_CTL = APP_CONTEXT + "/ctl/TimeTableListCtl";
	public String ERROR_CTL = APP_CONTEXT + "/ErrorCtl";

	public String PRODUCT_CTL = APP_CONTEXT + "/ctl/ProductCtl";
	public String PRODUCT_LIST_CTL = APP_CONTEXT + "/ctl/ProductListCtl";

	public String DOCTOR_CTL = APP_CONTEXT + "/ctl/DoctorCtl";
	public String DOCTOR_LIST_CTL = APP_CONTEXT + "/ctl/DoctorListCtl";

	public String BANK_CTL = APP_CONTEXT + "/ctl/BankCtl";
	public String BANK_LIST_CTL = APP_CONTEXT + "/ctl/BankListCtl";

	public String ORDER_CTL = APP_CONTEXT + "/ctl/OrderCtl";
	public String ORDER_LIST_CTL = APP_CONTEXT + "/ctl/OrderListCtl";

	public String PATIENT_CTL = APP_CONTEXT + "/ctl/PatientCtl";
	public String PATIENT_LIST_CTL = APP_CONTEXT + "/ctl/PatientListCtl";

	public String SHOP_CTL = APP_CONTEXT + "/ctl/ShopCtl";
	public String SHOP_LIST_CTL = APP_CONTEXT + "/ctl/ShopListCtl";

	public String EMP_CTL = APP_CONTEXT + "/ctl/EmpCtl";
	public String EMP_LIST_CTL = APP_CONTEXT + "/ctl/EmpListCtl";

	public String PROJECT_CTL = APP_CONTEXT + "/ctl/ProjectCtl";
	public String PROJECT_LIST_CTL = APP_CONTEXT + "/ctl/ProjectListCtl";

	public String VEHICLE_CTL = APP_CONTEXT + "/ctl/VehicleCtl";
	public String VEHICLE_LIST_CTL = APP_CONTEXT + "/ctl/VehicleListCtl";

	public String PAYMENT_CTL = APP_CONTEXT + "/ctl/PaymentCtl";
	public String PAYMENT_LIST_CTL = APP_CONTEXT + "/ctl/PaymentListCtl";

	public String INVENTORY_CTL = APP_CONTEXT + "/ctl/InventoryCtl";
	public String INVENTORY_LIST_CTL = APP_CONTEXT + "/ctl/InventoryListCtl";

}
