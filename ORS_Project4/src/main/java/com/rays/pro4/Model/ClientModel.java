package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.ClientBean;
import com.rays.pro4.Util.JDBCDataSource;

public class ClientModel {
	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_client");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(ClientBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_client values(?,?,?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setInt(2, bean.getIdentifier());
		pstmt.setString(3, bean.getContactName());
		pstmt.setString(4, bean.getLocation());
		pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(6, bean.getPhoneNumber());

		int i = pstmt.executeUpdate();
		System.out.println("client Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(ClientBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_client where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("client delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(ClientBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_client set Identifier = ?, FullName = ?, Joiningdate = ?, Division = ?,PreviousEmployer=? where id = ?");

		pstmt.setInt(1, bean.getIdentifier());
		pstmt.setString(2, bean.getContactName());
		pstmt.setString(3, bean.getLocation());
		pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(5, bean.getPhoneNumber());
		pstmt.setLong(6, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("staff update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public ClientBean findByPK(long pk) throws Exception {

		String sql = "select * from st_client where id = ?";
		ClientBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new ClientBean();
			bean.setId(rs.getLong(1));
			bean.setIdentifier(rs.getInt(2));
			bean.setContactName(rs.getString(3));
			bean.setLocation(rs.getString(4));
			bean.setDob(rs.getDate(5));
			bean.setPhoneNumber(rs.getString(6));

		}

		rs.close();

		return bean;
	}

	public List search(ClientBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_client where 1=1");
		if (bean != null) {

			if (bean.getIdentifier() != null && bean.getIdentifier() > 0) {
				sql.append(" AND identifier =" + bean.getIdentifier());
			}

			if (bean.getContactName() != null && bean.getContactName().length() > 0) {
				sql.append(" AND contactName like '" + bean.getContactName() + "%'");
			}
			if (bean.getLocation() != null && bean.getLocation().length() > 0) {
				sql.append(" AND location like '" + bean.getLocation() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
				Date d = new Date(bean.getDob().getTime());
				sql.append(" AND dob = '" + d + "'");
				System.out.println("done");
			}
			if (bean.getPhoneNumber() != null && bean.getPhoneNumber().length() > 0) {
				sql.append(" AND phoneNumber like '" + bean.getPhoneNumber() + "%'");
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

			bean = new ClientBean();
			bean.setId(rs.getLong(1));
			bean.setIdentifier(rs.getInt(2));
			bean.setContactName(rs.getString(3));
			bean.setLocation(rs.getString(4));
			bean.setDob(rs.getDate(5));
			bean.setPhoneNumber(rs.getString(6));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_client");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			ClientBean bean = new ClientBean();

			bean.setId(rs.getLong(1));
			bean.setIdentifier(rs.getInt(2));
			bean.setContactName(rs.getString(3));
			bean.setLocation(rs.getString(4));
			bean.setDob(rs.getDate(5));
			bean.setPhoneNumber(rs.getString(6));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}
