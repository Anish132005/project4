package com.rays.pro4.Bean;

import java.util.Date;

public class EmpBean extends BaseBean {

	private String EmpName;
	private Date Dob;
	private String Company;
	private String Salary;

	public String getEmpName() {
		return EmpName;
	}

	public void setEmpName(String empName) {
		EmpName = empName;
	}

	public Date getDob() {
		return Dob;
	}

	public void setDob(Date dob) {
		Dob = dob;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public String getSalary() {
		return Salary;
	}

	public void setSalary(String salary) {
		Salary = salary;
	}

	@Override
	public String getkey() {
		return Company;
	}

	@Override
	public String getValue() {
		return Company;
	}

}
