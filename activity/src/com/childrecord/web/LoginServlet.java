package com.childrecord.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.childrecord.entity.Student;
import com.childrecord.service.LoginService;
import com.google.gson.Gson;

public class LoginServlet extends HttpServlet {
	
	LoginService loginservice = new LoginService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	    StringBuilder buffer = new StringBuilder();  
	    BufferedReader reader = request.getReader();  
	    String line;  
	     while ((line = reader.readLine()) != null) {  
	        buffer.append(line);  
	     }  
	    String body = buffer.toString(); 
	    Gson gson = new Gson();
	    Student student = gson.fromJson(body,Student.class);
	    String idnum = student.getIdnum()+"";
	    String password = student.getPassword();
	    Map map = loginservice.userLogin(idnum, password);
	    String flag = map.get("flag").toString();
	    if(flag.equals("true")){
		    String type =map.get("type").toString();
			response.getWriter().write("[{\"flag\":true,\"type\":"+type+",\"ident\":"+gson.toJson(map.get("self"))+"}]");
	    }else{
			response.getWriter().write("[{\"flag\":false}]");
	    }
	}
}
