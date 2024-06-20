package com.rays.pro4.Bean;

import java.util.Date;

public class ProjectBean extends BaseBean {

	private String FirstName;
	private String LastName;
	private Date Dob;
	private String ProjectName;

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Date getDob() {
		return Dob;
	}

	public void setDob(Date dob) {
		Dob = dob;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	@Override
	public String getkey() {
		return ProjectName;
	}

	@Override
	public String getValue() {
		return ProjectName;
	}

}
