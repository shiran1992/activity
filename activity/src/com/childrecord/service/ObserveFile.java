package com.childrecord.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.childrecord.dao.StudentDao;
import com.childrecord.dao.TeacherDao;
import com.childrecord.entity.Student;
import com.childrecord.entity.Teacher;
import com.google.gson.Gson;

public class ObserveFile {

	
	private StudentDao studentDao = new StudentDao();
	
	private TeacherDao teacherDao = new TeacherDao();
	
	public  Object view(String stu_id,String tea_id,String activity_id){
		if(!(null==stu_id)&&!stu_id.equals("")){
			Student student = studentDao.querySelfById(stu_id);
			return student;
		}
		if(!(null==tea_id)&&!tea_id.equals("")){
			Teacher teacher = teacherDao.query(tea_id,"");
			return teacher;
		}
		if(!(null==activity_id)&&!activity_id.equals("")){
			Map map = new HashMap<String,Object>();
			List<Student> students = studentDao.query(activity_id,"0");
			List<Teacher> tea = studentDao.queryTeacher(activity_id, "1");
			map.put("students", students);
			map.put("tea", tea);
			return map;
		}
		return null;
	}
	
	public String validateStudentInfo(String idcardnum,String name,String idnum,String phone){
		String bol = studentDao.validateStuInfo(idcardnum, name, idnum, phone);
		return bol;
	}
}
