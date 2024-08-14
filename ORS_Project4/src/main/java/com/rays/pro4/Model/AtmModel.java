package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.AtmBean;

import com.rays.pro4.Util.JDBCDataSource;

public class AtmModel {

	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_atms");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(AtmBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_atms values(?,?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getLocation());
		pstmt.setDouble(3, bean.getCash_available());
		pstmt.setDate(4, new java.sql.Date(bean.getLast_Restocked_Date().getTime()));
		pstmt.setString(5, bean.getRemark());

		int i = pstmt.executeUpdate();
		System.out.println("ATM Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(AtmBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_atms where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("ATM delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(AtmBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_atms set location = ?, cash_available = ?, last_restocked_date = ?, remark = ? where id = ?");

		pstmt.setString(1, bean.getLocation());
		pstmt.setDouble(2, bean.getCash_available());
		pstmt.setDate(3, new java.sql.Date(bean.getLast_Restocked_Date().getTime()));
		pstmt.setString(4, bean.getRemark());
		pstmt.setLong(5, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Atm update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public AtmBean findByPK(long pk) throws Exception {

		String sql = "select * from st_atms where id = ?";
		AtmBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new AtmBean();
			bean.setId(rs.getLong(1));
			bean.setLocation((rs.getString(2)));
			bean.setCash_available(rs.getDouble(3));
			bean.setLast_Restocked_Date(rs.getDate(4));
			bean.setRemark(rs.getString(5));

		}

		rs.close();

		return bean;
	}

	public List search(AtmBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_atms where 1=1");
		if (bean != null) {

			if (bean.getLocation() != null && bean.getLocation().length() > 0) {
				sql.append(" AND Location like '" + bean.getLocation() + "%'");
			}

			if (bean.getCash_available() != null && bean.getCash_available() > 0) {
				sql.append(" AND Cash_available = " + bean.getCash_available());
			}

			if (bean.getLast_Restocked_Date() != null && bean.getLast_Restocked_Date().getTime() > 0) {
				Date d = new Date(bean.getLast_Restocked_Date().getTime());
				sql.append(" AND last_restocked_date ='" + d + "'");
				System.out.println("done");
			}

			if (bean.getRemark() != null && bean.getRemark().length() > 0) {
				sql.append(" AND Remark like '" + bean.getRemark() + "%'");
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

			bean = new AtmBean();
			bean.setId(rs.getLong(1));
			bean.setLocation(rs.getString(2));
			bean.setCash_available(rs.getDouble(3));
			bean.setLast_Restocked_Date(rs.getDate(4));
			bean.setRemark((rs.getString(5)));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_atms");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			AtmBean bean = new AtmBean();

			bean.setId(rs.getLong(1));
			bean.setLocation(rs.getString(2));
			bean.setCash_available(rs.getDouble(3));
			bean.setLast_Restocked_Date(rs.getDate(4));
			bean.setRemark(rs.getString(5));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}
