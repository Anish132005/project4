package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.TransactionBean;
import com.rays.pro4.Util.JDBCDataSource;

public class TransactionModel {

	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_transaction");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(TransactionBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_transaction values(?,?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getDescription());
		pstmt.setDate(3, new java.sql.Date(bean.getTransaction_Date().getTime()));
		pstmt.setString(4, bean.getTransaction_Type());
		pstmt.setString(5, bean.getAccount_id());

		int i = pstmt.executeUpdate();
		System.out.println("Transaction Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(TransactionBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_transaction where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("Transaction delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(TransactionBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_transaction set description = ?, transaction_date = ?, transaction_type = ?, account_id = ? where id = ?");

		pstmt.setString(1, bean.getDescription());
		pstmt.setDate(2, new java.sql.Date(bean.getTransaction_Date().getTime()));
		pstmt.setString(4, bean.getTransaction_Type());
		pstmt.setString(5, bean.getAccount_id());
		pstmt.setLong(6, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Transaction update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public TransactionBean findByPK(long pk) throws Exception {

		String sql = "select * from st_transaction where id = ?";
		TransactionBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new TransactionBean();
			bean.setId(rs.getLong(1));
			bean.setDescription((rs.getString(2)));
			bean.setTransaction_Date(rs.getDate(4));
			bean.setTransaction_Type(rs.getString(5));
			bean.setAccount_id(rs.getString(6));
		}

		rs.close();

		return bean;
	}

	public List search(TransactionBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_transaction where 1=1");
		if (bean != null) {

			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND Description like '" + bean.getDescription() + "%'");
			}

			if (bean.getTransaction_Date() != null && bean.getTransaction_Date().getTime() > 0) {
				Date d = new Date(bean.getTransaction_Date().getTime());
				sql.append(" AND Transaction_Date = '" + d + "'");
				System.out.println("done");
			}

			if (bean.getTransaction_Type() != null && bean.getTransaction_Type().length() > 0) {
				sql.append(" AND Transaction_Type like '" + bean.getTransaction_Type() + "%'");
			}

			if (bean.getAccount_id() != null && bean.getAccount_id().length() > 0) {
				sql.append(" AND Account_id like '" + bean.getAccount_id() + "%'");
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

			bean = new TransactionBean();
			bean.setId(rs.getLong(1));
			bean.setDescription(rs.getString(2));
			bean.setTransaction_Date(rs.getDate(3));
			bean.setTransaction_Type((rs.getString(4)));
			bean.setAccount_id(rs.getString(5));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_transaction");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			TransactionBean bean = new TransactionBean();

			bean.setId(rs.getLong(1));
			bean.setDescription(rs.getString(2));
			bean.setTransaction_Date(rs.getDate(3));
			bean.setTransaction_Type(rs.getString(4));
			bean.setAccount_id(rs.getString(5));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}
