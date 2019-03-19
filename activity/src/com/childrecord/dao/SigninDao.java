package com.childrecord.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.childrecord.entity.Signin;
import com.childrecord.entity.Student;
import com.childrecord.util.DBConnection;
import com.childrecord.util.getTime;

public class SigninDao {
	public boolean save(String id) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			// 1、联数据库
			conn = DBConnection.getConnection();
			// 2、SQL语句
			String sql = "insert into signin(person_id,time) "
					+ " values(?,now())";
			// 3、获得PreparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setString(1, id);
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
	
	public boolean queryIsSign(String id) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		boolean res = false;
		try {
			// 1、联数据库
			conn = DBConnection.getConnection();
			// 2、SQL语句
			String sql = "select count(*) cou from signin where person_id = ? and to_days(time) =to_days(now()) ";
			// 3、获得PreparedStatement对象
			prep = conn.prepareStatement(sql);
			// 4、设置？的值
			prep.setString(1, id);
			// 5、执行SQL语句
			rs = prep.executeQuery();
			while (rs.next()) {
				int count = rs.getInt("cou");
				res = count>0?true:false;
			}
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
	
	public List<Student> queryHaveSignin() {

		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Student> students = new ArrayList<Student>();

		try {
			conn = DBConnection.getConnection();
			StringBuilder  sql =  new StringBuilder("select student.* , signin.time from student "
					+ "left join signin on signin.person_id = student.id where to_days(signin.time)=to_days(now())");
			System.out.print(sql.toString());
			prep = conn.prepareStatement(sql.toString());
			rs = prep.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAddress(rs.getString("address"));
				student.setAge(rs.getInt("age"));
				student.setIdcard(rs.getString("idcard"));
				student.setCalss(rs.getString("calss"));
				student.setGrade(rs.getString("grade"));
				student.setIdnum(rs.getInt("idnum"));
				student.setMajor(rs.getString("major"));
				student.setPhone(rs.getString("phone"));
				student.setSex(rs.getString("sex"));
				student.setTime(rs.getString("time"));
				students.add(student);
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

		return students;
	}
	
	public List<Student> queryHavenotSignin() {

		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Student> students = new ArrayList<Student>();

		try {
			conn = DBConnection.getConnection();
			StringBuilder  sql =  new StringBuilder("select  student.*,time FROM student left join signin on signin.person_id = student.id where student.id not in  "
					+ "(select student.id from student left join signin on signin.person_id = student.id where to_days(time) =to_days(now())) ");
			System.out.print(sql.toString());

			prep = conn.prepareStatement(sql.toString());
			rs = prep.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setAddress(rs.getString("address"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setIdcard(rs.getString("idcard"));
				student.setCalss(rs.getString("calss"));
				student.setGrade(rs.getString("grade"));
				student.setIdnum(rs.getInt("idnum"));
				student.setMajor(rs.getString("major"));
				student.setPhone(rs.getString("phone"));
				student.setSex(rs.getString("sex"));
				student.setTime(rs.getString("time"));
				students.add(student);
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

		return students;
	}
}
