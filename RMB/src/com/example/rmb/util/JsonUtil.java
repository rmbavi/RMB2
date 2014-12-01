package com.example.rmb.util;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.rmb.entity.NewsPaperNumber;
import com.example.rmb.entity.Notice;
import com.example.rmb.entity.User;
import com.example.rmb.entity.UserInfo;
import com.example.rmb.entity.VersionInfo;



public class JsonUtil {
	

	
	public static VersionInfo versionInfoFromJson(String json){
		VersionInfo info = null;
		try {
			JSONObject jObj = new JSONObject(json);
			String result = jObj.getString("result");
			if(result != null && !"".equals(result)){
				if(result.equalsIgnoreCase("true")){
					info = new VersionInfo();
					info.setVersionCode(jObj.getString("versioncode"));
					info.setVersionName(jObj.getString("versionname"));
					info.setDescription(jObj.getString("description"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	
	
	public  static User userFromJson(String json){
		User user = null;
		if(json != null && !"".equals(json)){
			try {
				JSONObject jObj = new JSONObject(json);
				String result = jObj.getString("result");
				//服务器给出了正确的响�?
				if(result != null && !"".equals(result)){
					
					//登陆成功
					if(result.equalsIgnoreCase("true")){
						JSONObject userObj = jObj.getJSONObject("user");
						user = new User();
						user.setUserId(userObj.getInt("user_id"));
						user.setPhoneNum(userObj.getString("phone_number"));
						user.setNickName(userObj.getString("nickname"));
						user.setSex(userObj.getString("sex"));
						user.setSchool(userObj.getString("school"));
						user.setSign(userObj.getString("sign"));
						user.setLoginMessage(jObj.getString("message"));
						user.setImageUrl(userObj.getString("image_url"));
						String enable = userObj.getString("is_enable");
						if("true".equalsIgnoreCase(enable)){
							user.setEnable(true);
						}else {
							user.setEnable(false);
						}
						
					}else {
						return user;
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	public  static UserInfo userInfoFromJson(String json){
		UserInfo user = null;
		if(json != null && !"".equals(json)){
			try {
				JSONObject jObj = new JSONObject(json);
				String result = jObj.getString("result");
				//服务器给出了正确的响�?
				if(result != null && !"".equals(result)){
					
					//登陆成功
					if(result.equalsIgnoreCase("true")){
						JSONObject userObj = jObj.getJSONObject("user");
						user = new UserInfo();
						user.setNickName(userObj.getString("nickname"));
						user.setSex(userObj.getString("sex"));
						user.setSchool(userObj.getString("school"));
						user.setSign(userObj.getString("sign"));
						user.setImageUrl(userObj.getString("image_url"));
						String enable = userObj.getString("is_enable");
						if("true".equalsIgnoreCase(enable)){
							user.setEnable(true);
						}else {
							user.setEnable(false);
						}
					}else {
						return user;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	
	
	
	public static List<Notice> noticeListFromJson(String json){
		List<Notice> list = null;
		if(json!= null && !"".equals(json)){
			try {
				JSONObject jObj = new JSONObject(json);
				String result = jObj.getString("result");
				//服务器给出了正确的响�?
				if(result != null && !"".equals(result) && "true".equals(result)){
					list = new ArrayList<Notice>();
					JSONArray jsonArray = jObj.getJSONArray("information");
					for(int i=0;i<jsonArray.length();i++){
						JSONObject obj = jsonArray.getJSONObject(i);
						Notice notice = new Notice();
						notice.setId(obj.getInt("information_id"));
						notice.setTitle(obj.getString("title"));
						notice.setDate(obj.getString("date"));
						notice.setSubview(obj.getString("subview"));
						list.add(notice);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/*
	public static List<NewsPaperNumber> newsPaperNumberFromJson(String json){
		List<NewsPaperNumber> list = null;
		if(json!= null && !"".equals(json)){
			try {
				JSONObject jObj = new JSONObject(json);
				String result = jObj.getString("result");
				//服务器给出了正确的响�?
				if(result != null && !"".equals(result) && "true".equals(result)){
					list = new ArrayList<NewsPaperNumber>();
					JSONArray jsonArray = jObj.getJSONArray("information");
					for(int i=0;i<jsonArray.length();i++){
						JSONObject obj = jsonArray.getJSONObject(i);
						Notice notice = new Notice();
						notice.setId(obj.getInt("information_id"));
						notice.setTitle(obj.getString("title"));
						list.add(notice);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	*/
	
	
}

