package com.childrecord.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.childrecord.entity.Activity;
import com.childrecord.util.DBConnection;
import com.childrecord.util.getTime;
import com.google.gson.Gson;

public class ActivityDao {
	
	public boolean save(Activity activity) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			// 1、联数据库
			conn = DBConnection.getConnection();
			// 2、SQL语句
			String sql = "insert into activity(name,location,start_time,end_time,endsign_time,max_people,free,pay_money,image_top,image_middle,image_under,is_recommend,is_hot,teacherid,address) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			// 3、获得PreparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setString(1, activity.getName());
			prep.setString(2, activity.getLocation());
			prep.setString(3, activity.getStart_time());
			prep.setString(4, activity.getEnd_time());
			prep.setString(5, activity.getEndsign_time());
			prep.setInt(6,activity.getMax_people());
			prep.setInt(7,activity.getFree());
			prep.setInt(8, activity.getPay_money());
			prep.setString(9,activity.getImage_top());
			prep.setString(10,activity.getImage_middle());
			prep.setString(11,activity.getImage_under());
			prep.setString(12,activity.getIs_recommend());
			prep.setString(13,activity.getIs_hot());
			prep.setInt(14,activity.getTeacherid());
			prep.setString(15,activity.getAddress());
			// 5、执行SQL语句
			prep.executeUpdate();
		} catch (Exception e) {
			 throw new RuntimeException(e);
		} finally {
			// 6、关闭资源
			try {
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return true;
	}

	public List<Activity> query(Activity activity,String teacherid,String startime,String endtime,String endsign_time) {

		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Activity> activitys = new ArrayList<Activity>();

		try {
			conn = DBConnection.getConnection();
			StringBuilder  sql =  new StringBuilder("select * from activity where 1=1");
			List<Object> params = new ArrayList<Object>();
			String name = activity.getName();
			String is_recommend = activity.getIs_recommend();
			String is_hot = activity.getIs_hot();
			String id = activity.getId()+"";
			if(id != null && !id.equals("0")) {
				sql.append(" and id = ?");
				params.add(id);
			}
			if(teacherid != null && !teacherid.trim().isEmpty()) {
				sql.append(" and teacherid = ?");
				params.add(teacherid);
			}
			if(name != null && !name.trim().isEmpty()) {
				sql.append(" and name like ?");
				
				params.add("%" + name + "%");
			}
			if(startime!= null && !startime.trim().isEmpty()){
				sql.append(" and start_time >= ?");
				params.add(startime);
			}
			if(endtime!= null && !endtime.trim().isEmpty()){
				sql.append(" and end_time <= ?");
				params.add(endtime);
			}
			if(endsign_time!= null && !endsign_time.trim().isEmpty()){
				sql.append(" and endsign_time <= ?");
				params.add(endsign_time);
			}
			if(is_recommend!= null && !is_recommend.trim().isEmpty()){
				sql.append(" and is_recommend = ?");
				params.add(is_recommend);
			}
			if(is_hot!= null && !is_hot.trim().isEmpty()){
				sql.append(" and is_hot = ?");
				params.add(is_hot);
			}
			System.out.print(sql.toString());
			prep = conn.prepareStatement(sql.toString());
			if(params!=null){
				for(int i = 0; i < params.size(); i++){
					prep.setObject(i+1, params.get(i));
	            }
			}
			rs = prep.executeQuery();
			while (rs.next()) {
				Activity activityson = new Activity();
				activityson.setId(rs.getInt("id"));
				activityson.setName(rs.getString("name"));
				activityson.setLocation(rs.getString("location"));
				activityson.setStart_time(rs.getString("start_time"));
				activityson.setEnd_time(rs.getString("end_time"));
				activityson.setEndsign_time(rs.getString("endsign_time")); 
				activityson.setMax_people(rs.getInt("max_people"));
				activityson.setPay_money(rs.getInt("pay_money"));
				activityson.setFree(rs.getInt("free"));
				activityson.setImage_top(rs.getString("image_top"));
				activityson.setImage_middle(rs.getString("image_middle"));
				activityson.setImage_under(rs.getString("image_under"));
				activityson.setIs_recommend(rs.getString("is_recommend"));
				activityson.setIs_hot(rs.getString("is_hot"));
				activityson.setTeacherid(rs.getInt("teacherid"));
				activityson.setAddress(rs.getString("address"));
				activitys.add(activityson);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			if (conn != null)
				conn.close();
			if (prep != null)
				prep.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return activitys;
	}

	public boolean modify(Activity activity) {
		Connection conn = null;
		PreparedStatement prep = null;
		boolean res = false;
		try {
			conn = DBConnection.getConnection();
			String sql = "update activity SET name=?,location=?,start_time=?,end_time=? ,endsign_time=? ,max_people=? ,free=? ,pay_money=? ,image_top=? ,image_middle=? ,image_under=?,is_recommend=?,is_hot=?,address=? WHERE id=?";
			prep = conn.prepareStatement(sql);

			prep.setString(1, activity.getName());
			prep.setString(2, activity.getLocation());
			prep.setString(3, activity.getStart_time());
			prep.setString(4, activity.getEnd_time());
			prep.setString(5, activity.getEndsign_time());
			prep.setInt(6, activity.getMax_people());
			prep.setInt(7, activity.getFree());
			prep.setInt(8, activity.getPay_money());
			prep.setString(9, activity.getImage_top());
			prep.setString(10, activity.getImage_middle());
			prep.setString(11, activity.getImage_under());
			prep.setString(12, activity.getIs_recommend());
			prep.setString(13, activity.getIs_hot());
			prep.setInt(15, activity.getId());
			prep.setString(14, activity.getAddress());
			res = prep.executeUpdate()>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (prep != null)
					prep.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return res;
	}
	
	public boolean del(String id) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from activity  WHERE id=?";
			prep = conn.prepareStatement(sql);
			prep.setString(1,id);
			prep.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (prep != null)
					prep.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return true;
	}
	
	public List<Activity> queryActivity(String type,String id) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Activity> activitys = new ArrayList<Activity>();

		try {
			conn = DBConnection.getConnection();
			StringBuilder  sql =  new StringBuilder("select * from activity "
				+"LEFT JOIN activity_bind on activity.id = activity_bind.activity_id "
				+"LEFT JOIN student on student.id = activity_bind.person_id and type = ? "
				+"WHERE student.id = ? ");
			List<Object> params = new ArrayList<Object>();
			prep = conn.prepareStatement(sql.toString());
			prep.setString(1,type);
			prep.setString(2,id);
			rs = prep.executeQuery();
			while (rs.next()) {
				Activity activityson = new Activity();
				activityson.setId(rs.getInt("id"));
				activityson.setName(rs.getString("name"));
				activityson.setLocation(rs.getString("location"));
				activityson.setStart_time(rs.getString("start_time"));
				activityson.setEnd_time(rs.getString("end_time"));
				activityson.setEndsign_time(rs.getString("endsign_time")); 
				activityson.setMax_people(rs.getInt("max_people"));
				activityson.setPay_money(rs.getInt("pay_money"));
				activityson.setFree(rs.getInt("free"));
				activityson.setImage_top(rs.getString("image_top"));
				activityson.setImage_middle(rs.getString("image_middle"));
				activityson.setImage_under(rs.getString("image_under"));
				activityson.setIs_recommend(rs.getString("is_recommend"));
				activityson.setIs_hot(rs.getString("is_hot"));
				activityson.setTeacherid(rs.getInt("teacherid"));
				activityson.setAddress(rs.getString("address"));
				activitys.add(activityson);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			if (conn != null)
				conn.close();
			if (prep != null)
				prep.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return activitys;
	}
}
