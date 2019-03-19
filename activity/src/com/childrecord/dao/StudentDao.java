package com.childrecord.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.childrecord.entity.Activity;
import com.childrecord.entity.Student;
import com.childrecord.entity.Teacher;
import com.childrecord.util.DBConnection;

public class StudentDao {
	public List<Student> query(String id,String type) {

		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Student> students = new ArrayList<Student>();

		try {
			conn = DBConnection.getConnection();
			StringBuilder  sql =  new StringBuilder("select student.* from student inner join activity_bind on activity_bind.person_id = student.id "
					+ "where activity_bind.activity_id = ? and activity_bind.type = ?");
			List<Object> params = new ArrayList<Object>();
			prep = conn.prepareStatement(sql.toString());
			prep.setInt(1,Integer.parseInt(id));
			prep.setInt(2,Integer.parseInt(type));
			rs = prep.executeQuery();
			while (rs.next()){
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setSex(rs.getString("sex"));
				student.setIdnum(rs.getInt("idnum"));
				student.setMajor(rs.getString("major"));
				student.setPhone(rs.getString("phone"));
				student.setGrade(rs.getString("grade"));
				student.setCalss(rs.getString("calss"));
				student.setIdcard(rs.getString("idcard"));
				student.setAddress(rs.getString("address"));
				student.setPassword(rs.getString("password"));
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
	
	public List<Teacher> queryTeacher(String id,String type) {

		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Teacher> tea = new ArrayList();;

		try {
			conn = DBConnection.getConnection();
			StringBuilder  sql =  new StringBuilder("select teacher.* from teacher inner join activity_bind on activity_bind.person_id = teacher.id "
					+ "where activity_bind.activity_id = ? and activity_bind.type = ?");
			List<Object> params = new ArrayList<Object>();
			prep = conn.prepareStatement(sql.toString());
			prep.setInt(1,Integer.parseInt(id));
			prep.setInt(2,Integer.parseInt(type));
			rs = prep.executeQuery();
			while (rs.next()){
				Teacher tea1 =new Teacher();
				tea1.setId(rs.getInt("id"));
				tea1.setName(rs.getString("name"));
				tea1.setZchen(rs.getString("zchen"));
				tea1.setAge(rs.getInt("age"));
				tea1.setSex(rs.getString("sex"));
				tea1.setMajor(rs.getString("major"));
				tea1.setAcademicdegree(rs.getString("academicdegree"));
				tea1.setPhone(rs.getString("phone"));
				tea1.setEmail(rs.getString("email"));
				tea1.setIdcard(rs.getString("idcard"));
				tea1.setUnit(rs.getString("unit"));
				tea1.setIdnum(rs.getString("idnum"));
				tea1.setPassword(rs.getString("password"));
				tea.add(tea1);
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

		return tea;
	}
	
	public Student querySelf(String idnum) {

		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Student student = null;

		try {
			conn = DBConnection.getConnection();
			StringBuilder  sql =  new StringBuilder("select * from student where idnum = ?");
			
			prep = conn.prepareStatement(sql.toString());
			prep.setString(1,idnum);
			rs = prep.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setSex(rs.getString("sex"));
				student.setIdnum(rs.getInt("idnum"));
				student.setMajor(rs.getString("major"));
				student.setPhone(rs.getString("phone"));
				student.setGrade(rs.getString("grade"));
				student.setCalss(rs.getString("calss"));
				student.setIdcard(rs.getString("idcard"));
				student.setAddress(rs.getString("address"));
				student.setPassword(rs.getString("password"));
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

		return student;
	}
	
	public Student querySelfById(String id) {

		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Student student = null;

		try {
			conn = DBConnection.getConnection();
			StringBuilder  sql =  new StringBuilder("select * from student where id = ?");
			
			prep = conn.prepareStatement(sql.toString());
			prep.setString(1,id);
			rs = prep.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setSex(rs.getString("sex"));
				student.setIdnum(rs.getInt("idnum"));
				student.setMajor(rs.getString("major"));
				student.setPhone(rs.getString("phone"));
				student.setGrade(rs.getString("grade"));
				student.setCalss(rs.getString("calss"));
				student.setIdcard(rs.getString("idcard"));
				student.setAddress(rs.getString("address"));
				student.setPassword(rs.getString("password"));
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

		return student;
	}
	public String validateStuInfo(String idcardnum,String name,String idnum,String phone ) {

		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Student student = null;
		String res = "";
		try {
			conn = DBConnection.getConnection();
			StringBuilder  sql =  new StringBuilder("select * from student where idcard = ? and name = ? and idnum = ? and phone = ?");
			prep = conn.prepareStatement(sql.toString());
			prep.setString(1,idcardnum);
			prep.setString(2,name);
			prep.setString(3,idnum);
			prep.setString(4,phone);
			rs = prep.executeQuery();
			while (rs.next()) {
				res = rs.getString("id");
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

		return res;
	}
}
