package com.childrecord.service;

import java.util.ArrayList;
import java.util.List;

import com.childrecord.dao.SigninDao;
import com.childrecord.entity.Student;

public class SigninService {
	
	private SigninDao signindao = new SigninDao();
	
	public Object signinMes(String flag){
		
		List<Student> list = new ArrayList<Student>(); 
		
		if(flag.equals("yes")){
			list = signindao.queryHaveSignin();
		}else{
			list = signindao.queryHavenotSignin();
		}
		return list;
	}
	
		public boolean signin(String id){
			boolean res = false;
			boolean bol = false;
			if(signindao.queryIsSign(id) == false){
				 bol = signindao.save(id);
			}
			return bol;
	}
}
