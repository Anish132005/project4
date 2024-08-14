package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.InventoryBean;
import com.rays.pro4.Bean.VichcleTrackingBean;
import com.rays.pro4.Util.JDBCDataSource;

public class VichcleTrackingModel {

	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_vichcletracking");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(VichcleTrackingBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_vichcletracking values(?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setDouble(2, bean.getLat());
		pstmt.setDouble(3, bean.getLongId());
		pstmt.setInt(4, bean.getVichcleId());
		int i = pstmt.executeUpdate();
		System.out.println("vichcletracking Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(VichcleTrackingBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_vichcletracking where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("vichcletracking delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(VichcleTrackingBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		
		PreparedStatement pstmt = conn.prepareStatement("update st_vichcletracking set lat = ?, longid = ?, vichicle = ? where id = ?");

		pstmt.setDouble(1, bean.getLat());
		pstmt.setDouble(2, bean.getLongId());
		pstmt.setInt(3, bean.getVichcleId());
		pstmt.setLong(4, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("vichcletracking update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public VichcleTrackingBean findByPK(long pk) throws Exception {

		String sql = "select * from st_vichcletracking where id = ?";
		VichcleTrackingBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new VichcleTrackingBean();
			bean.setId(rs.getLong(1));
			bean.setLat((rs.getDouble(2)));
			bean.setLongId(rs.getDouble(3));
			bean.setVichcleId(rs.getInt(4));

		}

		rs.close();

		return bean;
	}

	public List search(VichcleTrackingBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_vichcletracking where 1=1");
		if (bean != null) {

			if (bean.getLat() != null && bean.getLat() > 0) {
				sql.append(" AND Lat = '" + bean.getLat() + "%'");
			}

			if (bean.getLongId() != null && bean.getLongId() > 0) {
				sql.append(" AND LongId = '" + bean.getLongId() + "%'");
			}
			
			 if (bean.getVichcleId() != null && bean.getVichcleId() > 0) {
			  sql.append(" AND VichcleId = " + bean.getVichcleId() ); 
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

			bean = new VichcleTrackingBean();
			bean.setId(rs.getLong(1));
			bean.setLat(rs.getDouble(2));
			bean.setLongId(rs.getDouble(3));
			bean.setVichcleId(rs.getInt(4));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_vichcletracking");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			VichcleTrackingBean bean = new VichcleTrackingBean();

			bean.setId(rs.getLong(1));
			bean.setLat(rs.getDouble(2));
			bean.setLongId(rs.getDouble(3));
			bean.setVichcleId(rs.getInt(4));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}
