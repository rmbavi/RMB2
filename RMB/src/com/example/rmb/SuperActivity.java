package com.example.rmb;

import com.example.rmb.entity.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


public class SuperActivity extends Activity {

	public boolean hasSDCard(){
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { 
			// ���sd�Ƿ����
			Log.v("error", "SD card is not avaiable/writeable right now.");
			Toast.makeText(this, "δ��⵽SD��", Toast.LENGTH_SHORT).show();
			return false;
		} else {
			return true;
		}
	}
	
	public boolean hasNetWork(){
		  // ��ȡ�ֻ��������ӹ�����󣨰�����wi-fi,net�����ӵĹ��� 
	    try { 
	        ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE); 
	        if (connectivity != null) { 
	            // ��ȡ�������ӹ���Ķ��� 
	            NetworkInfo info = connectivity.getActiveNetworkInfo(); 
	            if (info != null&& info.isConnected()) { 
	                // �жϵ�ǰ�����Ƿ��Ѿ����� 
	                if (info.getState() == NetworkInfo.State.CONNECTED) { 
	                    return true; 
	                } 
	            } 
	        } 
	    } catch (Exception e) { 
	    	Log.v("error",e.toString()); 
	    } 
	        return false; 
	}
	
	public boolean isLogin(){
		if((Setting.USER != null && Setting.USER.getUserId()>0) || getLoginState()){
			if(Setting.USER == null){
				Setting.USER = getLoginUser();
			}
			return true;
		}
		return false;
	}
	
	public boolean isLoginAndShowDialog(){
		if(isLogin()){
			return true;
		}else {
			showChoseDialog();
			return false;
		}
	}
	
    public void showChoseDialog(){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("��ʾ");
    	builder.setMessage("����û��¼�����ڵ�¼��");
    	builder.setNegativeButton("ע��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//moveToActivity(RegistActivity.class, false);
			}
		});
    	builder.setPositiveButton("��¼",  new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//moveToActivity(LoginActivity.class, false);
			}
		});
    	builder.setCancelable(true);
    	builder.create().show();
    }
	
	public void cancelLogin(){
		Setting.USER = null;
		updateLoginState("false");
	}
	
	public void moveToActivity(Class<?> activityClass,boolean isFinishCurrentPage){
		Intent intent = new Intent();
		intent.setClass(this, activityClass);
		startActivity(intent);
		if(isFinishCurrentPage){
			this.finish();
		}
	}
	
	public void saveLogin(String userName,String password){
		SharedPreferences userSettingPrefrences = getApplicationContext().getSharedPreferences(Setting.DATA, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = userSettingPrefrences.edit();
		editor.putString(Setting.PHONE_NUMBER, userName);
		editor.putString(Setting.PASSWORD, password);
		editor.commit();
	}
	
	public String getLastPhoneNumber(){
		SharedPreferences userSettingPrefrences = getApplicationContext().getSharedPreferences(Setting.DATA, Context.MODE_PRIVATE);
		return userSettingPrefrences.getString(Setting.PHONE_NUMBER, "");
	}
	
	public String getLastPassword(){
		SharedPreferences userSettingPrefrences = getApplicationContext().getSharedPreferences(Setting.DATA, Context.MODE_PRIVATE);
		return userSettingPrefrences.getString(Setting.PASSWORD, "");
	}
	
	public boolean getLoginState(){
		SharedPreferences userSettingPrefrences = getApplicationContext().getSharedPreferences(Setting.DATA, Context.MODE_PRIVATE);
		String rs = userSettingPrefrences.getString("is_login", "");
		if("true".equals(rs)){
			return true;
		}else {
			return false;
		}
	}
	
	public String getLoginUserId(){
		SharedPreferences userSettingPrefrences = getApplicationContext().getSharedPreferences(Setting.DATA, Context.MODE_PRIVATE);
		return userSettingPrefrences.getString("user_id", "");
	}
	
	public void saveLoginState(String userId,String isLogin){
		SharedPreferences userSettingPrefrences = getApplicationContext().getSharedPreferences(Setting.DATA, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = userSettingPrefrences.edit();
		editor.putString("user_id", userId);
		editor.putString("is_login", isLogin);
		editor.commit();
	}
	
	public void updateLoginState(String isLogin){
		SharedPreferences userSettingPrefrences = getApplicationContext().getSharedPreferences(Setting.DATA, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = userSettingPrefrences.edit();
		editor.putString("is_login", isLogin);
		editor.commit();
	}
	
    public void saveLoginUser(User user){
		SharedPreferences userSettingPrefrences = getApplicationContext().getSharedPreferences(Setting.DATA, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = userSettingPrefrences.edit();
		editor.putString("user_id", ""+user.getUserId());
		editor.putString("phone_number", user.getPhoneNum());
		editor.putString("nickname", user.getNickName());
		editor.putString("sex", user.getSex());
		editor.putString("sign", user.getSign());
		editor.putString("school", user.getSchool());
		editor.putString("image_url", user.getImageUrl());
		editor.putBoolean("isEnable",user.isEnable());
		editor.commit();
    }
    
    public User getLoginUser(){
    	User user = new User();
		SharedPreferences userSettingPrefrences = getApplicationContext().getSharedPreferences(Setting.DATA, Context.MODE_PRIVATE);
		user.setUserId(Integer.parseInt(userSettingPrefrences.getString("user_id", "")));
		user.setPhoneNum(userSettingPrefrences.getString("phone_number", ""));
		user.setNickName(userSettingPrefrences.getString("nickname", ""));
		user.setSex(userSettingPrefrences.getString("nickname", ""));
		user.setSign(userSettingPrefrences.getString("sign", ""));
		user.setSchool(userSettingPrefrences.getString("school", ""));
		user.setImageUrl(userSettingPrefrences.getString("image_url", ""));
		user.setEnable(userSettingPrefrences.getBoolean("isEnable", true));
    	return user;
    	
    }
    
    public int getAppVersionCode(){
    	try {
			PackageManager pm = this.getPackageManager();//contextΪ��ǰActivity������ 
			PackageInfo pi = pm.getPackageInfo(this.getPackageName(), 0);
			return pi.versionCode;
		} catch (Exception e) {
			return 0;
		}

    }
}
