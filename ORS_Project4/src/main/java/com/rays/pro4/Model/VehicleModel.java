package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.ProjectBean;
import com.rays.pro4.Bean.VehicleBean;
import com.rays.pro4.Util.JDBCDataSource;

public class VehicleModel {

	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_vehicle");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(VehicleBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_vehicle values(?,?,?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getVehicleName());
		pstmt.setString(3, bean.getVehiclePrice());
		pstmt.setDate(4, new java.sql.Date(bean.getdOB().getTime()));
		pstmt.setString(5, bean.getMobileNubmer());
		pstmt.setString(6, bean.getRTO());

		int i = pstmt.executeUpdate();
		System.out.println("Vehicle Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(VehicleBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_vehicle where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("Vehicle delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(VehicleBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_vehicle set VehicleName = ?, VehiclePrice = ?, dob = ?, mobilenumber = ?,rto = ? where id = ?");

		pstmt.setString(1, bean.getVehicleName());
		pstmt.setString(2, bean.getVehiclePrice());
		pstmt.setDate(3, new java.sql.Date(bean.getdOB().getTime()));
		pstmt.setString(4, bean.getMobileNubmer());
		pstmt.setString(5, bean.getRTO());
		pstmt.setLong(6, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Vehicle update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public VehicleBean findByPK(long pk) throws Exception {

		String sql = "select * from st_vehicle where id = ?";
		VehicleBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new VehicleBean();
			bean.setId(rs.getLong(1));
			bean.setVehicleName((rs.getString(2)));
			bean.setVehiclePrice(rs.getString(3));
			bean.setdOB(rs.getDate(4));
			bean.setMobileNubmer(rs.getString(5));
			bean.setRTO(rs.getString(6));

		}

		rs.close();

		return bean;
	}

	public List search(VehicleBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_vehicle where 1=1");
		if (bean != null) {

			if (bean.getVehicleName() != null && bean.getVehicleName().length() > 0) {
				sql.append(" AND VehicleName like '" + bean.getVehicleName() + "%'");
			}

			if (bean.getVehiclePrice() != null && bean.getVehiclePrice().length() > 0) {
				sql.append(" AND VehiclePrice like '" + bean.getValue() + "%'");
			}

			if (bean.getdOB() != null && bean.getdOB().getTime() > 0) {
				Date d = new Date(bean.getdOB().getTime());
				sql.append(" AND dOB = '" + d + "'");
				System.out.println("done");
			}

			if (bean.getMobileNubmer() != null && bean.getMobileNubmer().length() > 0) {
				sql.append(" AND mobilenumber like '" + bean.getMobileNubmer() + "%'");
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

			bean = new VehicleBean();
			bean.setId(rs.getLong(1));
			bean.setVehicleName(rs.getString(2));
			bean.setVehiclePrice(rs.getString(3));
			bean.setdOB(rs.getDate(4));
			bean.setMobileNubmer((rs.getString(5)));
			bean.setRTO((rs.getString(6)));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_vehicle");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			VehicleBean bean = new VehicleBean();

			bean.setId(rs.getLong(1));
			bean.setVehicleName(rs.getString(2));
			bean.setVehiclePrice(rs.getString(3));
			bean.setdOB(rs.getDate(4));
			bean.setMobileNubmer(rs.getString(5));
			bean.setRTO(rs.getString(6));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}
