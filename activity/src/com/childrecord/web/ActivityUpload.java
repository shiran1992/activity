package com.childrecord.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import com.childrecord.service.ActivityService;
import com.childrecord.util.RandomStr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ActivityUpload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ActivityService activityService = new ActivityService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		boolean res;
		String fileName = "";				// 表单字段元素的name属性值
		String filedName = "";				//获取文件名称
		String picPath = "";
		String name = "";
		String location= "";
		String start_time= "";
		String end_time= "";
		String endsign_time= "";
		int max_people = 0;
		int free = 0;
		int pay_money= 0;
		String image_top= "";
		String image_middle= "";
		String image_under = "";
		String is_recommend = "";
		String is_hot = "";
		String address = "";
		int teacherid = 0;
		// 请求信息中的内容是否是multipart类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 上传文件的存储路径（服务器文件系统上的绝对文件路径）
		String uploadFilePath = request.getSession().getServletContext()
				.getRealPath("upload/");
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// 解析from表单中所有文件
				@SuppressWarnings("unchecked")
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) {
						// 判断，是普通表单
						fileName = item.getFieldName();
						// 表单字段的name属性
						if (fileName.equals("name")) {
							name = item.getString("utf-8");
						}
						if (fileName.equals("location")) {
							location = item.getString("utf-8");
						} 
						if (fileName.equals("start_time")) {
							start_time = item.getString("utf-8");
						} 
						if (fileName.equals("end_time")) {
							end_time = item.getString("utf-8");
						} 
						if (fileName.equals("endsign_time")) {
							endsign_time = item.getString("utf-8");
						} 
						if (fileName.equals("max_people")) {
							max_people = Integer.parseInt(item.getString("utf-8"));
						} 
						if (fileName.equals("free")) {
							free = Integer.parseInt(item.getString("utf-8"));
						} 
						if (fileName.equals("pay_money")) {
							pay_money = Integer.parseInt(item.getString("utf-8"));
						} 
						if (fileName.equals("image_top")) {
							image_top = item.getString("utf-8");
						} 
						if (fileName.equals("image_middle")) {
							image_middle = item.getString("utf-8");
						} 
						if (fileName.equals("image_under")) {
							image_under = item.getString("utf-8");
						} 
						if (fileName.equals("is_recommend")) {
							is_recommend = item.getString("utf-8");
						} 
						if (fileName.equals("is_hot")) {
							is_hot = item.getString("utf-8");
						} 
						if (fileName.equals("teacherid")) {
							teacherid = Integer.parseInt(item.getString("utf-8"));
						} 
						if (fileName.equals("address")) {
							address = item.getString("utf-8");
						}
					} else {
						// 文件表单字段
						// 表单字段的name属性
						fileName = item.getFieldName();
						if (fileName.equals("image_top")) {
							// 小图1
							filedName = item.getName();
							if (fileName != null && !filedName.equals("")) {
								File fullFile = new File(item.getName());
								File saveFile = new File(uploadFilePath,
										RandomStr.getRandomString(7)+".jpg");
								item.write(saveFile);
								image_top = saveFile.getName();
								picPath = saveFile.toString();
								image_top = "http://localhost:8080/activity/upload/"+image_top;
							}
						} else if (fileName.equals("image_middle")) {
							// 小图2
							filedName = item.getName();
							if (fileName != null && !filedName.equals("")) {
								File fullFile = new File(item.getName());
								File saveFile = new File(uploadFilePath,
										RandomStr.getRandomString(6)+".jpg");
								item.write(saveFile);
								image_middle = saveFile.getName();
								picPath = saveFile.toString();
								image_middle = "http://localhost:8080/activity/upload/"+image_middle;
							}
						} else if (fileName.equals("image_under")) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 
							// 小图3
							filedName = item.getName();
							if (fileName != null && !filedName.equals("")) {
								File fullFile = new File(item.getName());
								File saveFile = new File(uploadFilePath,
										RandomStr.getRandomString(5)+".jpg");
								item.write(saveFile);
								image_under = saveFile.getName();
								picPath = saveFile.toString();
								image_under = "http://localhost:8080/activity/upload/"+image_under;
							}
						}
						
					}
				}
			
			
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		res = activityService.publishActivity(name, location, start_time, end_time, endsign_time, max_people, free, pay_money, image_top, image_middle, image_under, is_recommend, is_hot, teacherid,address);
		response.getWriter().write("[{\"res\":"+res+"}]");
	}

}
