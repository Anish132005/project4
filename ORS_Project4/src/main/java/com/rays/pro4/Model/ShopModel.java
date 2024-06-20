package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.ProductBean;
import com.rays.pro4.Bean.ShopBean;
import com.rays.pro4.Util.JDBCDataSource;

public class ShopModel {
	
	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_shooping");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(ShopBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_shooping values(?,?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getShopName());
		pstmt.setString(3, bean.getProductName());
		pstmt.setDate(4, new java.sql.Date(bean.getPurchaseDate().getTime()));
		pstmt.setString(5, bean.getAmount());

		int i = pstmt.executeUpdate();
		System.out.println("Shop Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(ShopBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_shooping where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("Shop delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(ShopBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_shooping set shopname = ?, productname = ?, purchasedate = ?, amount = ? where id = ?");

		pstmt.setString(1, bean.getShopName());
		pstmt.setString(2, bean.getProductName());
		pstmt.setDate(3, new java.sql.Date(bean.getPurchaseDate().getTime()));
		pstmt.setString(4, bean.getAmount());
		pstmt.setLong(5, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Shop update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public ShopBean findByPK(long pk) throws Exception {

		String sql = "select * from st_shooping where id = ?";
		ShopBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new ShopBean();
			bean.setId(rs.getLong(1));
			bean.setShopName((rs.getString(2)));
			bean.setProductName(rs.getString(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setAmount(rs.getString(5));

		}

		rs.close();

		return bean;
	}

	public List search(ShopBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_shooping where 1=1");
		if (bean != null) {

			if (bean.getShopName() != null && bean.getShopName().length() > 0) {
				sql.append(" AND ShopName like '" + bean.getShopName() + "%'");
			}

			if (bean.getProductName() != null && bean.getProductName().length() > 0) {
				sql.append(" AND ProductName like '" + bean.getProductName() + "%'");
			}

			if (bean.getPurchaseDate() != null && bean.getPurchaseDate().getTime() > 0) {
				Date d = new Date(bean.getPurchaseDate().getTime());
				sql.append(" AND PurchaseDate = '" + d + "'");
				System.out.println("done");
			}

			if (bean.getAmount() != null && bean.getAmount().length() > 0) {
				sql.append(" AND Amount like '" + bean.getAmount() + "%'");
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

			bean = new ShopBean();
			bean.setId(rs.getLong(1));
			bean.setShopName(rs.getString(2));
			bean.setProductName(rs.getString(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setAmount((rs.getString(5)));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_shooping");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			ShopBean bean = new ShopBean();

			bean.setId(rs.getLong(1));
			bean.setShopName(rs.getString(2));
			bean.setProductName(rs.getString(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setAmount(rs.getString(5));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}


