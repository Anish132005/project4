package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.AtmBean;
import com.rays.pro4.Bean.CustomerBean;
import com.rays.pro4.Util.JDBCDataSource;



public class CustomerModel {

	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_customer");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(CustomerBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_customer values(?,?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getName());
		pstmt.setDate(3, new java.sql.Date(bean.getDate_of_Birth().getTime()));
		pstmt.setString(4, bean.getPhoneNumber());
		pstmt.setString(5, bean.getGender());

		int i = pstmt.executeUpdate();
		System.out.println("Customer Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(CustomerBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_customer where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("Customer delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(CustomerBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_customer set Name = ?, Date_of_birth = ?, phoneNumber = ?, Gender = ? where id = ?");

		pstmt.setString(1, bean.getName());
		pstmt.setDate(2, new java.sql.Date(bean.getDate_of_Birth().getTime()));
		pstmt.setString(3, bean.getPhoneNumber());
		pstmt.setString(4, bean.getGender());
		pstmt.setLong(5, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Customer update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public CustomerBean findByPK(long pk) throws Exception {

		String sql = "select * from st_customer where id = ?";
		CustomerBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new CustomerBean();
			bean.setId(rs.getLong(1));
			bean.setName((rs.getString(2)));
			bean.setDate_of_Birth(rs.getDate(3));
			bean.setPhoneNumber(rs.getString(4));
			bean.setGender(rs.getString(5));

		}

		rs.close();

		return bean;
	}

	public List search(CustomerBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_customer where 1=1");
		if (bean != null) {

			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND Name like '" + bean.getName() + "%'");
			}

			if (bean.getDate_of_Birth() != null && bean.getDate_of_Birth().getTime() > 0) {
				Date d = new Date(bean.getDate_of_Birth().getTime());
				sql.append(" AND Date_of_Birth = '" + d + "'");
				System.out.println("done");
			}

			if (bean.getPhoneNumber() != null && bean.getPhoneNumber().length()> 0) {
				sql.append(" AND PhoneNumber like '" + bean.getPhoneNumber() + "%'");
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

			bean = new CustomerBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDate_of_Birth(rs.getDate(3));
			bean.setPhoneNumber(rs.getString(4));
			bean.setGender((rs.getString(5)));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_customer");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			CustomerBean bean = new CustomerBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDate_of_Birth(rs.getDate(3));
			bean.setPhoneNumber(rs.getString(4));
			bean.setGender(rs.getString(5));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}
