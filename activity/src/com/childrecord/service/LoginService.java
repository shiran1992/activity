package com.childrecord.service;

import java.util.HashMap;
import java.util.Map;

import com.childrecord.dao.StudentDao;
import com.childrecord.dao.TeacherDao;
import com.childrecord.entity.Student;
import com.childrecord.entity.Teacher;

public class LoginService {
	
	private StudentDao studentDao = new StudentDao();
	
	private TeacherDao teacherDao = new TeacherDao();
	
	public Map<String, Object> userLogin(String idnum,String password){
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		Student student = studentDao.querySelf(idnum);
		
		Teacher teacher = teacherDao.query("", idnum);
		
		if(null==student && null==teacher){
			map.put("flag","false");
		}else if(null!=student){
			if(student.getPassword().equals(password)){
				map.put("flag","true");
				map.put("type","0");
				map.put("self", student);
			}else{
				map.put("flag","false");
			}
		}else{
			if(teacher.getPassword().equals(password)){
				map.put("flag","true");
				map.put("type","1");
				map.put("self", teacher);
			}else{
				map.put("flag","false");
			}
		}
		return map;
	}
}
