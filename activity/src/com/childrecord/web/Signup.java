package com.childrecord.web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.childrecord.service.ActivityBindDaoService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Signup
 */
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ActivityBindDaoService activityBindDaoService = new ActivityBindDaoService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	    Gson json = new Gson();
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
		String person_id = jsonObject.get("person_id").getAsString();
		String activity_id = jsonObject.get("activity_id").getAsString();
		String is_for = jsonObject.get("is_for").getAsString();
		String is_for_id = jsonObject.get("is_for_id").getAsString();
		String type = jsonObject.get("type").getAsString();
		Object obj = activityBindDaoService.signup(person_id, activity_id, is_for, is_for_id, type);
		response.getWriter().write(json.toJson(obj));
	}

}
