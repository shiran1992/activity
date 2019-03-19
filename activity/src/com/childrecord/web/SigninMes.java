package com.childrecord.web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.childrecord.service.SigninService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class SigninMes
 */
public class SigninMes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SigninService signService = new SigninService();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SigninMes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
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
		String flag = jsonObject.get("flag").getAsString();
		Object res = signService.signinMes(flag);
		response.getWriter().write(json.toJson(res));
	}

}
