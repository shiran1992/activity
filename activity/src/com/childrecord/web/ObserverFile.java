package com.childrecord.web;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.childrecord.entity.Student;
import com.childrecord.service.ObserveFile;
import com.google.gson.Gson;

/**
 * Servlet implementation class ObserverFile
 */
public class ObserverFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ObserveFile observefile = new ObserveFile();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObserverFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
	    Gson json = new Gson();
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
		String stu_id = null!=jsonObject.get("stu_id")?jsonObject.get("stu_id").getAsString():"";
		String tea_id = null!=jsonObject.get("tea_id")?jsonObject.get("tea_id").getAsString():"";
		String activity_id = null!=jsonObject.get("activity_id")?jsonObject.get("activity_id").getAsString():"";
		Object res = observefile.view(stu_id, tea_id, activity_id);
		response.getWriter().write(json.toJson(res));
	}

}
