package com.childrecord.service;
import java.util.ArrayList;
import java.util.List;

import com.childrecord.dao.ActivityBindDao;
import com.childrecord.dao.ActivityDao;
import com.childrecord.entity.Activity;
import com.google.gson.Gson;

public class ActivityService {
	
	private ActivityDao activityDao  = new ActivityDao();
	
	private ActivityBindDao  activityBindDao = new ActivityBindDao();
	
	public boolean publishActivity(String name,String location,String start_time,String end_time,String endsign_time,
			int max_people,
			int free,
			int pay_money,
			String image_top,
			String image_middle,
			String image_under,
			String is_recommend,
			String is_hot,
			int teacherid,
			String address
			){
		boolean res = false;
		Activity activity = new Activity();
		activity.setName(name);
		activity.setLocation(location);
		activity.setStart_time(start_time);
		activity.setEnd_time(end_time);
		activity.setEndsign_time(endsign_time);
		activity.setMax_people(max_people);
		activity.setFree(free);
		activity.setPay_money(pay_money);
		activity.setImage_top(image_top);
		activity.setImage_middle(image_middle);
		activity.setImage_under(image_under);
		activity.setIs_recommend(is_recommend);
		activity.setIs_hot(is_hot);
		activity.setTeacherid(teacherid);
		activity.setAddress(address);
		res = activityDao.save(activity);
		return res;
	}

	public List<Activity> query(String name,String is_recommend,String is_hot,String id,String teacherid,String startime,String endtime,String endsign_time) {
		List<Activity> activitys = new ArrayList<Activity>();
		Activity activity = new Activity();
		activity.setName(name);
		activity.setIs_recommend(is_recommend);
		activity.setIs_hot(is_hot);
		activity.setId(Integer.parseInt(id.equals("")?"0":id));
		activitys = activityDao.query(activity, teacherid, startime, endtime, endsign_time);
		return activitys;
	}
	
	public boolean modifyActivity(String name,String location,String start_time,String end_time,String endsign_time,
			int max_people,
			int free,
			int pay_money,
			String image_top,
			String image_middle,
			String image_under,
			String is_recommend,
			String is_hot,
			int teacherid,int id,
			String address
			){
		boolean res = false;
		Activity activity = new Activity();
		activity.setName(name);
		activity.setLocation(location);
		activity.setStart_time(start_time);
		activity.setEnd_time(end_time);
		activity.setEndsign_time(endsign_time);
		activity.setMax_people(max_people);
		activity.setFree(free);
		activity.setId(id);
		activity.setPay_money(pay_money);
		activity.setImage_top(image_top);
		activity.setImage_middle(image_middle);
		activity.setImage_under(image_under);
		activity.setIs_recommend(is_recommend);
		activity.setIs_hot(is_hot);
		activity.setTeacherid(teacherid);
		activity.setAddress(address);
		res = activityDao.modify(activity);
		return res;
	}
	
	public boolean del(String id) {
		activityBindDao.del(id);
		return activityDao.del(id);
		
	}
	
	public List<Activity> queryActivity(String type,String id) {
		List<Activity> list = activityDao.queryActivity(type, id);
		return list;
	}
}
