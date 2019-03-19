package com.childrecord.web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.childrecord.service.ActivityService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class QueryActivitys
 */
public class QueryActivitys extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ActivityService activityService = new ActivityService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryActivitys() {
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
		String name = null==jsonObject.get("name")?"":jsonObject.get("name").getAsString();
		String is_recommend = null==jsonObject.get("is_recommend")?"":jsonObject.get("is_recommend").getAsString();
		String is_hot =null==jsonObject.get("is_hot")?"":jsonObject.get("is_hot").getAsString();
		String id = null==jsonObject.get("id")?"":jsonObject.get("id").getAsString();
		String teacherid = null==jsonObject.get("teacherid")?"":jsonObject.get("teacherid").getAsString();
		String startime = null==jsonObject.get("startime")?"":jsonObject.get("startime").getAsString();
		String endtime = null==jsonObject.get("endtime")?"":jsonObject.get("endtime").getAsString();
		String endsign_time = null==jsonObject.get("endsign_time")?"":jsonObject.get("endsign_time").getAsString();
		Object res = activityService.query(name, is_recommend, is_hot, id, teacherid, startime, endtime, endsign_time);
		response.getWriter().write(json.toJson(res));
	}

}
