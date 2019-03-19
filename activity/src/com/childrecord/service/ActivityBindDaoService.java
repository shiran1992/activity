  package com.childrecord.service;

import java.util.List;

import com.childrecord.dao.ActivityBindDao;
import com.childrecord.entity.ActivityBind;

public class ActivityBindDaoService {
	private ActivityBindDao activityBindDao = new ActivityBindDao();
			
	public Object queryActivityPerson(String activity_id){
		return activityBindDao.queryActivityPerson(activity_id);
	}	
	
	public boolean signup(String person_id,String activity_id,String is_for,String is_for_id,String type){
		ActivityBind activityBind = new ActivityBind();
		ActivityBind queryactivityBind = new ActivityBind();
		activityBind.setPerson_id(Integer.parseInt(person_id));
		activityBind.setActivity_id(Integer.parseInt(activity_id));
		activityBind.setIs_for(Integer.parseInt(is_for));
		activityBind.setIs_for_id(Integer.parseInt(is_for_id));
		activityBind.setType(Integer.parseInt(type));
		queryactivityBind.setActivity_id(Integer.parseInt(activity_id));
		queryactivityBind.setPerson_id(Integer.parseInt(person_id));
		List<ActivityBind> list = activityBindDao.query(queryactivityBind);
		if(list.size()>0){
			return false;
		}else{
			return activityBindDao.save(activityBind);
		}
	}
}
