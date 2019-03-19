package com.childrecord.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.childrecord.entity.Activity;
import com.childrecord.entity.Teacher;
import com.childrecord.util.DBConnection;

public class TeacherDao {
	public Teacher query(String id,String idnum) {

		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Teacher tea = null;
		try {
			conn = DBConnection.getConnection();
			StringBuilder  sql =  new StringBuilder("select * from teacher where 1=1");
			
			List<Object> params = new ArrayList<Object>();
			
			if(id != null && !id.trim().isEmpty()) {
				sql.append(" and id = ?");
				params.add(id);
			}
			
			if(idnum != null && !idnum.trim().isEmpty()) {
				sql.append(" and idnum = ?");
				params.add(idnum);
			}
			prep = conn.prepareStatement(sql.toString());
			if(params!=null||params.size()!=0){
				for(int i = 0; i < params.size(); i++){
					prep.setObject(i+1, params.get(i));
	            }
			}
			
			rs = prep.executeQuery();
			while (rs.next()) {
				tea = new Teacher();
				tea.setId(rs.getInt("id"));
				tea.setName(rs.getString("name"));
				tea.setZchen(rs.getString("zchen"));
				tea.setAge(rs.getInt("age"));
				tea.setSex(rs.getString("sex"));
				tea.setMajor(rs.getString("major"));
				tea.setAcademicdegree(rs.getString("academicdegree"));
				tea.setPhone(rs.getString("phone"));
				tea.setEmail(rs.getString("email"));
				tea.setIdcard(rs.getString("idcard"));
				tea.setUnit(rs.getString("unit"));
				tea.setIdnum(rs.getString("idnum"));
				tea.setPassword(rs.getString("password"));
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
}
