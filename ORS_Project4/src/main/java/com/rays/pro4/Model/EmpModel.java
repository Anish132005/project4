package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.EmpBean;
import com.rays.pro4.Bean.ShopBean;
import com.rays.pro4.Util.JDBCDataSource;

public class EmpModel {

	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_employee");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(EmpBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_employee values(?,?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getEmpName());
		pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(4, bean.getCompany());
		pstmt.setString(5, bean.getSalary());

		int i = pstmt.executeUpdate();
		System.out.println("Employee Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(EmpBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_employee where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("Employee delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(EmpBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn
				.prepareStatement("update st_employee set empName = ?, dob = ?, company = ?, salary = ? where id = ?");

		pstmt.setString(1, bean.getEmpName());
		pstmt.setDate(2, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(3, bean.getCompany());
		pstmt.setString(4, bean.getSalary());
		pstmt.setLong(5, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Employee update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public EmpBean findByPK(long pk) throws Exception {

		String sql = "select * from st_employee where id = ?";
		EmpBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new EmpBean();
			bean.setId(rs.getLong(1));
			bean.setEmpName((rs.getString(2)));
			bean.setDob(rs.getDate(3));
			bean.setCompany(rs.getString(4));
			bean.setSalary(rs.getString(5));

		}

		rs.close();

		return bean;
	}

	public List search(EmpBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_employee where 1=1");
		if (bean != null) {

			if (bean.getEmpName() != null && bean.getEmpName().length() > 0) {
				sql.append(" AND EmpName like '" + bean.getEmpName() + "%'");
			}

			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
				Date d = new Date(bean.getDob().getTime());
				sql.append(" AND Dob = '" + d + "'");
				System.out.println("done");
			}

			if (bean.getCompany() != null && bean.getCompany().length() > 0) {
				sql.append(" AND Company like '" + bean.getCompany() + "%'");
			}
			if (bean.getSalary() != null && bean.getSalary().length() > 0) {
				sql.append(" AND Salary like '" + bean.getSalary() + "%'");
			}

			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}

		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);

		}

		System.out.println("sql query search >>= " + sql.toString());
		List list = new ArrayList();

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new EmpBean();
			bean.setId(rs.getLong(1));
			bean.setEmpName(rs.getString(2));
			bean.setDob(rs.getDate(3));
			bean.setCompany((rs.getString(4)));
			bean.setSalary((rs.getString(5)));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_employee");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			EmpBean bean = new EmpBean();

			bean.setId(rs.getLong(1));
			bean.setEmpName(rs.getString(2));
			bean.setDob(rs.getDate(3));
			bean.setCompany(rs.getString(4));
			bean.setSalary(rs.getString(5));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}
