package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.AtmBean;
import com.rays.pro4.Bean.LoanBean;
import com.rays.pro4.Util.JDBCDataSource;

public class LoanModel {
	
	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_loans");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(LoanBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_loans values(?,?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setDate(2, new java.sql.Date(bean.getLoan_Start_Date().getTime()));
		pstmt.setString(3, bean.getCustomer_Id());
		pstmt.setInt(4, bean.getLoan_Amount());
		pstmt.setInt(5, bean.getInterest_Rate());
		
		int i = pstmt.executeUpdate();
		System.out.println("Loans Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(LoanBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_loans where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("Loans delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(LoanBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_loans set loan_start_date = ?, customer_id = ?, loan_amount = ?, interest_rate = ? where id = ?");

		pstmt.setDate(1, new java.sql.Date(bean.getLoan_Start_Date().getTime()));
		pstmt.setString(2, bean.getCustomer_Id());
		pstmt.setInt(3, bean.getLoan_Amount());
		pstmt.setInt(4, bean.getInterest_Rate());
		pstmt.setLong(5, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Loan update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public LoanBean findByPK(long pk) throws Exception {

		String sql = "select * from st_loans where id = ?";
		LoanBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new LoanBean();
			bean.setId(rs.getLong(1));
			bean.setLoan_Start_Date(rs.getDate(2));
			bean.setCustomer_Id((rs.getString(3)));
			bean.setLoan_Amount(rs.getInt(4));
			bean.setInterest_Rate(rs.getInt(5));
			

		}

		rs.close();

		return bean;
	}

	public List search(LoanBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_loans where 1=1");
		if (bean != null) {

			if (bean.getLoan_Start_Date() != null && bean.getLoan_Start_Date().getTime() > 0) {
				Date d = new Date(bean.getLoan_Start_Date().getTime());
				sql.append(" AND Loan_Start_Date ='" + d + "'");
				System.out.println("done");
			}

			if (bean.getCustomer_Id()!= null && bean.getCustomer_Id().length() > 0) {
				sql.append(" AND Customer_Id like '" + bean.getCustomer_Id()+"%'");
			}


			if (bean.getLoan_Amount() != null && bean.getLoan_Amount() > 0) {
				sql.append(" AND Loan_Amount = " + bean.getLoan_Amount() );
			}
			
			if (bean.getInterest_Rate() != null && bean.getInterest_Rate() > 0) {
				sql.append(" AND Interest_Rate = " + bean.getInterest_Rate() );
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

			bean = new LoanBean();
			bean.setId(rs.getLong(1));
			bean.setLoan_Start_Date(rs.getDate(2));
			bean.setCustomer_Id(rs.getString(3));
			bean.setLoan_Amount(rs.getInt(4));
			bean.setInterest_Rate((rs.getInt(5)));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_loans");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			LoanBean bean = new LoanBean();

			bean.setId(rs.getLong(1));
			bean.setLoan_Start_Date(rs.getDate(2));
			bean.setCustomer_Id(rs.getString(3));
			bean.setLoan_Amount(rs.getInt(4));
			bean.setInterest_Rate(rs.getInt(5));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}



