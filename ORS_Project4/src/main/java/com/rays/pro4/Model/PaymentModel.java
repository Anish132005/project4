package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.PaymentBean;
import com.rays.pro4.Bean.VehicleBean;
import com.rays.pro4.Util.JDBCDataSource;

public class PaymentModel {

	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_payment");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(PaymentBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_payment values(?,?,?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getAmount());
		pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(5, bean.getUPI());
		pstmt.setString(6, bean.getBank());

		int i = pstmt.executeUpdate();
		System.out.println("Payment Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(PaymentBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_payment where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("Payment delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(PaymentBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn
				.prepareStatement("update st_payment set name = ?, amount = ?, dob = ?, upi = ?,bank = ? where id = ?");

		pstmt.setString(1, bean.getName());
		pstmt.setString(2, bean.getAmount());
		pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(4, bean.getUPI());
		pstmt.setString(5, bean.getBank());
		pstmt.setLong(6, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Payment  update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public PaymentBean findByPK(long pk) throws Exception {

		String sql = "select * from st_payment where id = ?";
		PaymentBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new PaymentBean();
			bean.setId(rs.getLong(1));
			bean.setName((rs.getString(2)));
			bean.setAmount(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setUPI(rs.getString(5));
			bean.setBank(rs.getString(6));

		}

		rs.close();

		return bean;
	}

	public List search(PaymentBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_payment where 1=1");
		if (bean != null) {

			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND Name like '" + bean.getName() + "%'");
			}

			if (bean.getAmount() != null && bean.getAmount().length() > 0) {
				sql.append(" AND Amount like '" + bean.getValue() + "%'");
			}

			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
				Date d = new Date(bean.getDob().getTime());
				sql.append(" AND Dob = '" + d + "'");
				System.out.println("done");
			}

			if (bean.getUPI() != null && bean.getUPI().length() > 0) {
				sql.append(" AND UPI like '" + bean.getUPI() + "%'");
			}

			if (bean.getBank() != null && bean.getBank().length() > 0) {
				sql.append(" AND Bank like '" + bean.getBank() + "%'");
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

			bean = new PaymentBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setAmount(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setUPI((rs.getString(5)));
			bean.setBank((rs.getString(6)));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_payment");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			PaymentBean bean = new PaymentBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setAmount(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setUPI(rs.getString(5));
			bean.setBank(rs.getString(6));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}
