package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.ProductsBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DatabaseException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Util.JDBCDataSource;

public class ProductsModel {
	public int nextPK() throws DatabaseException {

		String sql = "SELECT MAX(ID) FROM st_products";
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {

			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;

	}

	public long add(ProductsBean bean) throws ApplicationException, DuplicateRecordException {

		String sql = "INSERT INTO st_products VALUES(?,?,?,?,?,?)";

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setInt(4, bean.getPrice());
			pstmt.setDate(5, new java.sql.Date(bean.getDateOfpurchase().getTime()));
			pstmt.setString(6, bean.getCategory());

			int a = pstmt.executeUpdate();
			System.out.println("ho gyua re" + a);
			conn.commit();
			pstmt.close();

		} catch (Exception e) {

			try {
				e.printStackTrace();
				conn.rollback();

			} catch (Exception e2) {
				e2.printStackTrace();

				throw new ApplicationException("Exception : add rollback exceptionn" + e2.getMessage());
			}
		}

		finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void delete(ProductsBean bean) throws ApplicationException {

		String sql = "DELETE FROM st_products WHERE ID=?";
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();
			System.out.println(i + "data deleted");
			conn.commit();
			pstmt.close();

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new ApplicationException("Exception: Delete rollback Exception" + e2.getMessage());
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void update(ProductsBean bean) throws ApplicationException, DuplicateRecordException {

		String sql = "UPDATE st_products SET Name=?,Description=?,Price=?,DateOfpurchase=?,Category =? WHERE ID=?";
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setInt(3, bean.getPrice());
			pstmt.setDate(4, new java.sql.Date(bean.getDateOfpurchase().getTime()));
			pstmt.setString(5, bean.getCategory());
			pstmt.setLong(6, bean.getId());

			pstmt.executeUpdate();
			int i = pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();

			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new ApplicationException("Exception : Update Rollback Exception " + e2.getMessage());
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/*
	 * public List search(OrderBean bean) throws ApplicationException { return
	 * search(bean); }
	 */

	public List search(ProductsBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("SELECT * FROM st_products WHERE 1=1");
		if (bean != null) {
			if (bean != null && bean.getId() > 0) {

				sql.append(" AND id = " + bean.getId());

			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND Name like '" + bean.getName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND Description like '" + bean.getDescription() + "%'");
			}
			

			if (bean.getDateOfpurchase() != null && bean.getDateOfpurchase().getTime() > 0) {
				Date d = new Date(bean.getDateOfpurchase().getDate());
				sql.append(" AND DateOfpurchase like '" + new java.sql.Date(bean.getDateOfpurchase().getTime()) + "%'");
			}

			if (bean.getCategory() != null && bean.getCategory().length() > 0) {
				sql.append(" AND Category like '" + bean.getCategory() + "%'");
			}

			if (pageSize > 0) {

				pageNo = (pageNo - 1) * pageSize;

				sql.append(" Limit " + pageNo + ", " + pageSize);

			}
		}
		System.out.println("sql>>>>>>>>>> " + sql.toString());

		List list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductsBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setPrice(rs.getInt(4));
				bean.setDateOfpurchase(rs.getDate(5));
				bean.setCategory(rs.getString(6));

				list.add(bean);

			}
			rs.close();
		} catch (Exception e) {

			throw new ApplicationException("Exception: Exception in Search User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}

	public ProductsBean findByPK(long pk) throws ApplicationException {

		String sql = "SELECT * FROM st_products WHERE ID=?";
		ProductsBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductsBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setPrice(rs.getInt(4));
				bean.setDateOfpurchase(rs.getDate(5));
				bean.setCategory(rs.getString(6));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();

			throw new ApplicationException("Exception : Exception in getting Payment by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_products");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductsBean bean = new ProductsBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setPrice(rs.getInt(4));
				bean.setDateOfpurchase(rs.getDate(5));
				bean.setCategory(rs.getString(6));

				list.add(bean);

			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

}
