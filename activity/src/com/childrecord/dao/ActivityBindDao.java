package com.childrecord.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.childrecord.entity.Activity;
import com.childrecord.entity.ActivityBind;
import com.childrecord.entity.SignUpMes;
import com.childrecord.util.DBConnection;
import com.childrecord.util.getTime;
import com.google.gson.Gson;

public class ActivityBindDao {
	public List<ActivityBind> query(ActivityBind activitybind) {

		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<ActivityBind> actiActivityBinds = new ArrayList<ActivityBind>();

		try {
			conn = DBConnection.getConnection();
			StringBuilder  sql =  new StringBuilder("select * from activity_bind where 1=1 ");
			List<Object> params = new ArrayList<Object>();
			String id = activitybind.getId()+"";
			String person_id = activitybind.getPerson_id()+"";
			String activity_id = activitybind.getActivity_id()+"";
			String is_for = activitybind.getIs_for() +"";
			String is_for_id = activitybind.getIs_for_id()+"";
			String type = activitybind.getType() +"";

			if(id != null && !id.trim().equals("0")) {
				sql.append(" and id = ?");
				params.add(id);
			}
			
			if(type != null && !type.trim().equals("0")) {
				sql.append(" and type = ?");
				params.add(type);
			}
			if(person_id != null && !person_id.trim().equals("0")) {
				sql.append(" and person_id = ?");
				params.add(person_id);
			}
			
			if(activity_id != null && !activity_id.trim().equals("0")) {
				sql.append(" and activity_id = ?");
				params.add(activity_id);
			}
			
			if(is_for != null && !is_for.trim().equals("0")) {
				sql.append(" and is_for = ?");
				params.add(is_for);
			}
			
			if(is_for_id != null && !is_for_id.trim().equals("0")) {
				sql.append(" and is_for_id = ?");
				params.add(is_for_id);
			}
			prep = conn.prepareStatement(sql.toString());
			System.out.print(sql.toString());
			if(params!=null){
				for(int i = 0; i < params.size(); i++){
					prep.setObject(i+1, params.get(i));
	            }
			}
			rs = prep.executeQuery();
			while (rs.next()) {
				ActivityBind activitybindson = new ActivityBind();
				activitybindson.setId(rs.getInt("id"));
				activitybindson.setActivity_id(rs.getInt("person_id"));
				activitybindson.setIs_for(rs.getInt("activity_id"));
				activitybindson.setIs_for_id(rs.getInt("is_for"));
				activitybindson.setPerson_id(rs.getInt("is_for_id"));
				activitybindson.setTime(rs.getString("time"));
				actiActivityBinds.add(activitybindson);
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

		return actiActivityBinds;
	}
	
	public boolean save(ActivityBind activitybind) {
		Connection conn = null;
		PreparedStatement prep = null;
		boolean res = false;
		try {
			// 1、联数据库
			conn = DBConnection.getConnection();
			// 2、SQL语句
			String sql = "insert into activity_bind(person_id,activity_id,is_for,is_for_id,type,time) "
					+ " values(?,?,?,?,?,now())";
			// 3、获得PreparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setInt(1, activitybind.getPerson_id());
			prep.setInt(2, activitybind.getActivity_id());
			prep.setInt(3, activitybind.getIs_for());
			prep.setInt(4, activitybind.getIs_for_id());
			prep.setInt(5, activitybind.getType());
			// 5、执行SQL语句
			prep.executeUpdate();
			res=true;
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
		return res;
	}
	
	public boolean del(String id) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from activity_bind  WHERE activity_id=?";
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
	
	public Object queryActivityPerson(String activity_id) {

		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<SignUpMes> signupmess = new ArrayList<SignUpMes>();
		Map map = new HashMap<String,Object>();
		try {
			conn = DBConnection.getConnection();
			StringBuilder  sql =  new StringBuilder("select name,if(type=0,'学生','老师')type,time from activity_bind inner join teacher on teacher.id = activity_bind.person_id and activity_bind.type = 1 where activity_id = ?");
			prep = conn.prepareStatement(sql.toString());
			// 4、设置？的值
			prep.setString(1, activity_id);
			// 5、执行SQL语句
			rs = prep.executeQuery();
			while (rs.next()) {
				SignUpMes signupmes = new SignUpMes();
				signupmes.setName(rs.getString("name"));
				signupmes.setTime(rs.getString("time"));
				signupmes.setType(rs.getString("type"));
				signupmess.add(signupmes);
			}
			StringBuilder  sqltwo =  new StringBuilder("select name,if(type=0,'学生','老师') type, time  from student left join activity_bind on student.id = activity_bind.person_id and activity_bind.type = 0 where activity_id = ?");
			prep = conn.prepareStatement(sqltwo.toString());
			// 4、设置？的值
			prep.setString(1, activity_id);
			// 5、执行SQL语句
			rs = prep.executeQuery();
			while (rs.next()){
				SignUpMes signupmes = new SignUpMes();
				signupmes.setName(rs.getString("name"));
				signupmes.setTime(rs.getString("time"));
				signupmes.setType(rs.getString("type"));
				signupmess.add(signupmes);
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
		
		return signupmess;
	}	
}
