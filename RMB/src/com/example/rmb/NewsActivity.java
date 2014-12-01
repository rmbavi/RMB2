package com.example.rmb;

import com.example.rmb.entity.Notice;
import java.util.ArrayList;
import java.util.List;

import com.example.rmb.R;

import com.example.rmb.news.adv.AdvClient;

import com.example.rmb.news.adv.Adv;
import com.example.rmb.util.StringUtil;

import com.example.rmb.Setting;

import com.example.rmb.util.JsonUtil;




import com.example.rmb.util.DialogUtil;






import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class NewsActivity extends SuperActivity implements OnClickListener,OnCheckedChangeListener,
		OnGestureListener, OnTouchListener {

	private GestureDetector mGestureDetector;

	private static final int FLING_MIN_DISTANCE = 50;
	private static final int FLING_MIN_VELOCITY = 0;

	private ImageView newImgView;
	private ImageView discussImgView;
	private ImageView lostImgView;
	private ImageView settingImgView;
	private AdvClient client;
	
	private RadioGroup mRadioGroup;
	private RadioButton mRadioButton1;
	private RadioButton mRadioButton2;
	private RadioButton mRadioButton3;
	private RadioButton mRadioButton4;
	private RadioButton mRadioButton5;
	private ImageView mImageView;
	private float mCurrentCheckedRadioLeft;//
	private HorizontalScrollView mHorizontalScrollView;//
	private ViewPager mViewPager;	//
	private ArrayList<View> mViews;//
	
	private Button navBackBtn;
	
	private ListView newsListView;
	private ListView XiaoneiListView;
	private ListView XiaowaiListView;
	private ListView ZhaopinListView;

	private int noticeLimit = 0;
	
	private Dialog progressDialog;
	
	private List<Notice> data = new ArrayList<Notice>();
	
	private boolean isLoadData = false;
	private boolean isLoadAll = false;
	private int page = 0;
	
	private View viewDefault;   //初始视图
	private View viewIndex;
	private View viewXiaonei;
	private View viewXiaowai;
	private View viewZhaopin;
	
	private boolean viewIndexLoaded =false;  //是否加载
	private boolean viewGuoneiLoaded =false;
	private boolean viewGuojiLoaded =false;
	private boolean viewJunshiLoaded =false;
	private boolean viewInternetLoaded =false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_activity);
		
        iniController();
        iniListener();
        iniVariable();
        
        mRadioButton1.setChecked(true);
        mViewPager.setCurrentItem(1);
        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();
        
        navBackBtn = (Button) findViewById(R.id.second_nav_back);

		navBackBtn.setOnClickListener(this);

		// 主页底部导航菜单
		newImgView = (ImageView) findViewById(R.id.imageview_menu_news);
		discussImgView = (ImageView) findViewById(R.id.imageview_menu_discuss);
		lostImgView = (ImageView) findViewById(R.id.imageview_menu_lost);
		settingImgView = (ImageView) findViewById(R.id.imageview_menu_setting);

		mGestureDetector = new GestureDetector(this);
		newImgView.setOnClickListener(this);
		discussImgView.setOnClickListener(this);
		lostImgView.setOnClickListener(this);
		settingImgView.setOnClickListener(this);

		client = new AdvClient(this);
		client.initClientById(R.id.scroll_layout, R.id.page_control,
				R.id.page_title);
		List<com.example.rmb.news.adv.Adv> data = new ArrayList<Adv>();
		System.out.println("1");

		for (int i = 0; i < 5; i++) {
			Adv adv = new Adv();
			// adv.setMessage("adv Num is:" + (i + 1));

			if (i == 0) {
				adv.setMessage(StringUtil.getSubString(
						"贝克汉姆空降北京,开始了他“中超形象大使”之旅的第一站", 14));
				adv.setDefaultDrawable(R.drawable.qwee1);
			}
			if (i == 1) {
				adv.setMessage(StringUtil.getSubString("青岛农贸市场摊主围堵城管 双方冲突", 14));
				adv.setDefaultDrawable(R.drawable.qwee2);
			}
			if (i == 2) {
				adv.setMessage(StringUtil.getSubString("世界睡眠日，中国小姐的睡姿优美", 14));
				adv.setDefaultDrawable(R.drawable.qwee3);
			}
			if (i == 3) {
				adv.setMessage(StringUtil.getSubString("亚洲杯预选赛,中国1：0胜伊拉克", 14));
				adv.setDefaultDrawable(R.drawable.qwee4);
			}
			if (i == 4) {
				adv.setMessage(StringUtil.getSubString(
						"3月22日，郑州市农业路与东明路上空吹起塑料泡沫，犹如天空下起白色冰雹", 14));
				adv.setDefaultDrawable(R.drawable.qwee5);
			}
			data.add(adv);
		}
		System.out.println("1");
		client.setData(data);
		client.start();

	}
	private Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 1:
				progressDialog.dismiss();
				isLoadData = false;
				isLoadAll = true;
				((NoticeAdapter)newsListView.getAdapter()).notifyDataSetChanged();
				break;
			case 2:
				progressDialog.dismiss();
				newsListView.setAdapter(new NoticeAdapter(NewsActivity.this));
				noticeLimit = data.size();
				isLoadData = false;
				break;
			case 3:
				progressDialog.dismiss();
				Toast.makeText(NewsActivity.this, getString(R.string.internet_error), 1000).show();
				isLoadData = false;
				break;
			case 4:
				progressDialog.dismiss();
				((NoticeAdapter)newsListView.getAdapter()).notifyDataSetChanged();
				isLoadData = false;
				break;
				
			}
		}
	};
	
	// 校内
	private Handler loadXiaoneihandler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 1:
				progressDialog.dismiss();
				isLoadData = false;
				isLoadAll = true;
				((NoticeAdapter)XiaoneiListView.getAdapter()).notifyDataSetChanged();
				break;
			case 2:
				progressDialog.dismiss();
				XiaoneiListView.setAdapter(new NoticeAdapter(NewsActivity.this));
				noticeLimit = data.size();
				isLoadData = false;
				break;
			case 3:
				progressDialog.dismiss();
				Toast.makeText(NewsActivity.this, getString(R.string.internet_error), 1000).show();
				isLoadData = false;
				break;
			case 4:
				progressDialog.dismiss();
				((NoticeAdapter)XiaoneiListView.getAdapter()).notifyDataSetChanged();
				isLoadData = false;
				break;
				
			}
		}
	};
	
	// 校外
	private Handler loadXiaowaihandler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 1:
				progressDialog.dismiss();
				isLoadData = false;
				isLoadAll = true;
				((NoticeAdapter)XiaowaiListView.getAdapter()).notifyDataSetChanged();
				break;
			case 2:
				progressDialog.dismiss();
				XiaowaiListView.setAdapter(new NoticeAdapter(NewsActivity.this));
				noticeLimit = data.size();
				isLoadData = false;
				break;
			case 3:
				progressDialog.dismiss();
				Toast.makeText(NewsActivity.this, getString(R.string.internet_error), 1000).show();
				isLoadData = false;
				break;
			case 4:
				progressDialog.dismiss();
				((NoticeAdapter)XiaowaiListView.getAdapter()).notifyDataSetChanged();
				isLoadData = false;
				break;
				
			}
		}
	};
	
//  军事
		private Handler loadZhaopinhandler = new Handler(){
			
			public void handleMessage(android.os.Message msg) {
				switch(msg.what){
				case 1:
					progressDialog.dismiss();
					isLoadData = false;
					isLoadAll = true;
					((NoticeAdapter)ZhaopinListView.getAdapter()).notifyDataSetChanged();
					break;
				case 2:
					progressDialog.dismiss();
					ZhaopinListView.setAdapter(new NoticeAdapter(NewsActivity.this));
					noticeLimit = data.size();
					isLoadData = false;
					break;
				case 3:
					progressDialog.dismiss();
					Toast.makeText(NewsActivity.this, getString(R.string.internet_error), 1000).show();
					isLoadData = false;
					break;
				case 4:
					progressDialog.dismiss();
					((NoticeAdapter)ZhaopinListView.getAdapter()).notifyDataSetChanged();
					isLoadData = false;
					break;
					
				}
			}
		};
    private Runnable freshRunnable = new Runnable(){
		@Override
		public void run() {
			isLoadData = true;
			//Map<String,String> params = new HashMap<String,String>();
			//params.put("type", ""+noticeType);
			//params.put("limit", ""+noticeLimit);
			try {
				//String json = HttpRequest.sendGetRequest(Setting.GET_NOTICE_INFO_LIST_URL, params);
				String json= "{\"result\":\"true\",\"information\":[{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"英国足球巨星大卫·贝克汉姆来 到北京\",\"subview\":\"“中国青少年足球发展及中超联赛推广大使”新闻发布会，有了新身\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"习近平今日启程访问俄罗斯 夫人彭丽媛将陪同\",\"subview\":\"国家主席习近平22日上午乘专机离开北京，对俄罗斯、坦桑尼亚、南非、刚果共和国进行国事访问，并出席在南非德班举行的金砖国家领导人第五次会晤。彭丽媛、王沪宁、栗战书、杨洁篪等陪同出访。\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"盛光祖任董事长\",\"subview\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"xxxxx\",\"subview\":\"盛光祖任董事长\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"}]}";
				if(StringUtil.isNotEmpty(json)){
					System.out.println("json ==== "+json);
					//重新加载时json不为空认为是取到第一页的数据
					page = 1;
					List<Notice> list = JsonUtil.noticeListFromJson(json);
					if(list != null){
						data = list;
						if(list.size()<Setting.PAGE_SIZE){
							isLoadAll = true;
							handler.sendEmptyMessage(2);
						}else{
							handler.sendEmptyMessage(2);
						}
						
					}else {
						//加载结束了，没有加载更多的选项出现
						handler.sendEmptyMessage(1);
					}
				}else {
					handler.sendEmptyMessage(3);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				handler.sendEmptyMessage(3);
			}
		}
	};
	
	private Runnable loadXiaoneiRunnable = new Runnable(){
		@Override
		public void run() {
			isLoadData = true;
			//Map<String,String> params = new HashMap<String,String>();
			//params.put("type", ""+noticeType);
			//params.put("limit", ""+noticeLimit);
			try {
				//String json = HttpRequest.sendGetRequest(Setting.GET_NOTICE_INFO_LIST_URL, params);
				String json= "{\"result\":\"true\",\"information\":[{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"英国足球巨星大卫·贝克汉姆来 到北京\",\"subview\":\"“中国青少年足球发展及中超联赛推广大使”新闻发布会，有了新身\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"习近平今日启程访问俄罗斯 夫人彭丽媛将陪同\",\"subview\":\"国家主席习近平22日上午乘专机离开北京，对俄罗斯、坦桑尼亚、南非、刚果共和国进行国事访问，并出席在南非德班举行的金砖国家领导人第五次会晤。彭丽媛、王沪宁、栗战书、杨洁篪等陪同出访。\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"盛光祖任董事长\",\"subview\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"xxxxx\",\"subview\":\"盛光祖任董事长\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"}]}";
				if(StringUtil.isNotEmpty(json)){
					System.out.println("json ==== "+json);
					//重新加载时json不为空认为是取到第一页的数据
					page = 1;
					List<Notice> list = JsonUtil.noticeListFromJson(json);
					if(list != null){
						data = list;
						if(list.size()<Setting.PAGE_SIZE){
							isLoadAll = true;
							loadXiaoneihandler.sendEmptyMessage(2);
						}else{
							loadXiaoneihandler.sendEmptyMessage(2);
						}
						
					}else {
						//加载结束了，没有加载更多的选项出现
						loadXiaoneihandler.sendEmptyMessage(1);
					}
				}else {
					loadXiaoneihandler.sendEmptyMessage(3);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				loadXiaoneihandler.sendEmptyMessage(3);
			}
		}
	};
	
	//国际
	private Runnable loadXiaowaiRunnable = new Runnable(){
		@Override
		public void run() {
			isLoadData = true;
			//Map<String,String> params = new HashMap<String,String>();
			//params.put("type", ""+noticeType);
			//params.put("limit", ""+noticeLimit);
			try {
				//String json = HttpRequest.sendGetRequest(Setting.GET_NOTICE_INFO_LIST_URL, params);
				String json= "{\"result\":\"true\",\"information\":[{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"英国足球巨星大卫·贝克汉姆来 到北京\",\"subview\":\"“中国青少年足球发展及中超联赛推广大使”新闻发布会，有了新身\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"传铁路总公司为正部级 \",\"subview\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"盛光祖任董事长\",\"subview\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"xxxxx\",\"subview\":\"盛光祖任董事长\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"}]}";
				if(StringUtil.isNotEmpty(json)){
					System.out.println("json ==== "+json);
					//重新加载时json不为空认为是取到第一页的数据
					page = 1;
					List<Notice> list = JsonUtil.noticeListFromJson(json);
					if(list != null){
						data = list;
						if(list.size()<Setting.PAGE_SIZE){
							isLoadAll = true;
							loadXiaowaihandler.sendEmptyMessage(2);
						}else{
							loadXiaowaihandler.sendEmptyMessage(2);
						}
						
					}else {
						//加载结束了，没有加载更多的选项出现
						loadXiaowaihandler.sendEmptyMessage(1);
					}
				}else {
					loadXiaowaihandler.sendEmptyMessage(3);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				loadXiaowaihandler.sendEmptyMessage(3);
			}
		}
	};
	
	
	
	//军事
	private Runnable loadZhaopinRunnable = new Runnable(){
				@Override
				public void run() {
					isLoadData = true;
					//Map<String,String> params = new HashMap<String,String>();
					//params.put("type", ""+noticeType);
					//params.put("limit", ""+noticeLimit);
					try {
						//String json = HttpRequest.sendGetRequest(Setting.GET_NOTICE_INFO_LIST_URL, params);
						String json= "{\"result\":\"true\",\"information\":[{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"英国足球巨星大卫·贝克汉姆来 到北京\",\"subview\":\"“中国青少年足球发展及中超联赛推广大使”新闻发布会，有了新身\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"传铁路总公司为正部级 \",\"subview\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"盛光祖任董事长\",\"subview\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"xxxxx\",\"subview\":\"盛光祖任董事长\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"},{\"date\":\"2012-01-14\",\"information_id\":111,\"title\":\"原铁道部人士透露，铁道部分拆后成立的中国铁路\",\"subview\":\"这是假数据\"}]}";
						if(StringUtil.isNotEmpty(json)){
							System.out.println("json ==== "+json);
							//重新加载时json不为空认为是取到第一页的数据
							page = 1;
							List<Notice> list = JsonUtil.noticeListFromJson(json);
							if(list != null){
								data = list;
								if(list.size()<Setting.PAGE_SIZE){
									isLoadAll = true;
									loadZhaopinhandler.sendEmptyMessage(2);
								}else{
									loadZhaopinhandler.sendEmptyMessage(2);
								}
								
							}else {
								//加载结束了，没有加载更多的选项出现
								loadZhaopinhandler.sendEmptyMessage(1);
							}
						}else {
							loadZhaopinhandler.sendEmptyMessage(3);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						loadZhaopinhandler.sendEmptyMessage(3);
					}
				}
			};
	
	   private void iniVariable() {

	    	viewDefault = getLayoutInflater().inflate(R.layout.layout_0, null);
	    	//初始化页面加载数据
	    	viewIndex = getLayoutInflater().inflate(R.layout.layout_1, null);
	    	viewXiaonei = getLayoutInflater().inflate(R.layout.layout_2, null);
	        viewXiaowai = getLayoutInflater().inflate(R.layout.layout_3, null);
	    	viewZhaopin = getLayoutInflater().inflate(R.layout.layout_4, null);
	    	
	    	newsListView = (ListView) viewIndex.findViewById(R.id.news_list);
	    	//newsListView.setDivider(getApplication().getResources().getDrawable(R.drawable.list_divider_line)); //设置分割线
	    	newsListView.setOnScrollListener(new OnScrollListener() {
				
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {
					//if(isLoadData == false && !isLoadAll){
						//if(view.getLastVisiblePosition() == view.getCount() - 1){
							
							/*ImageView img = (ImageView) progressDialog.findViewById(R.id.progress_img);
							Animation animation = AnimationUtils.loadAnimation(NewsActivity.this, R.anim.progress_anim);
							img.startAnimation(animation);
							progressDialog.show();
							Thread loadMoreThread = new Thread(loadMoreRunnable);
							loadMoreThread.start();*/
						//}
					//}
				}
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
					
				}
			});
	    	newsListView.setOnItemClickListener(new OnItemClickListener() {
	    		   @Override
	    		    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
	    			   System.out.println("listview counts = >"+parent.getCount());
	    			   if(position >= data.size()){
	    				   return;
	    			   }else{
	    				   
	    				   Intent intent = new Intent();
	    				   intent.setClass(NewsActivity.this, NewsContentActivity.class);
	    				   startActivity(intent);
	    			   }
	    			   //View v =  parent.getChildAt(position);
	    			   
	    		        //for(int i=0;i<parent.getCount();i++){
	    		            //View v=parent.getChildAt(parent.getCount()-1-i);
	    		          //  if (position == i) {
	    		               // v.setBackgroundColor(Color.RED);
	    		                
	    		            //} else {
	    		              //  v.setBackgroundColor(Color.TRANSPARENT);
	    		            //}
	    		        //}
	    		    }
	    		});


	    	
	    	progressDialog = DialogUtil.createProgressDialog(this, "正在通信，请稍后...");
			
			ImageView img = (ImageView) progressDialog.findViewById(R.id.progress_img);
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.progress_anim);
			img.startAnimation(animation);
			progressDialog.show();
			Thread thread = new Thread(freshRunnable);
			thread.start();
	    	
			// TODO Auto-generated method stub
	    	mViews = new ArrayList<View>();
	    	mViews.add(viewDefault);
	    	mViews.add(viewIndex);
	    	mViews.add(viewXiaonei);
	    	mViews.add(viewXiaowai);
	    	mViews.add(viewZhaopin);
	    	mViews.add(viewDefault);
	    	
	    	mViewPager.setAdapter(new MyPagerAdapter());
	    	
		}
	   
	   private float getCurrentCheckedRadioLeft() {
			// TODO Auto-generated method stub
			if (mRadioButton1.isChecked()) {
				//Log.i("zj", "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo1));
				return getResources().getDimension(R.dimen.rdo1);
			}else if (mRadioButton2.isChecked()) {
				//Log.i("zj", "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo2));
				return getResources().getDimension(R.dimen.rdo2);
			}else if (mRadioButton3.isChecked()) {
				//Log.i("zj", "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo3));
				return getResources().getDimension(R.dimen.rdo3);
			}else if (mRadioButton4.isChecked()) {
				//Log.i("zj", "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo4));
				return getResources().getDimension(R.dimen.rdo4);
			}else if (mRadioButton5.isChecked()) {
				//Log.i("zj", "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo5));
				return getResources().getDimension(R.dimen.rdo5);
			}
			return 0f;
		}

		private void iniListener() {
			// TODO Auto-generated method stub
			
			mRadioGroup.setOnCheckedChangeListener(this);
			
			
			mViewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());
		}
	   
	   private void iniController() {
			// TODO Auto-generated method stub
			mRadioGroup = (RadioGroup)findViewById(R.id.radioGroup);
			mRadioButton1 = (RadioButton)findViewById(R.id.btn1);
			mRadioButton2 = (RadioButton)findViewById(R.id.btn2);
			mRadioButton3 = (RadioButton)findViewById(R.id.btn3);
			mRadioButton4 = (RadioButton)findViewById(R.id.btn4);
			
			mImageView = (ImageView)findViewById(R.id.img1);
			
			mHorizontalScrollView = (HorizontalScrollView)findViewById(R.id.horizontalScrollView);
			
			mViewPager = (ViewPager)findViewById(R.id.pager);
		}
	   
	   private class MyPagerAdapter extends PagerAdapter{

			@Override
			public void destroyItem(View v, int position, Object obj) {
				// TODO Auto-generated method stub
				((ViewPager)v).removeView(mViews.get(position));
			}

			@Override
			public void finishUpdate(View arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mViews.size();
			}

			@Override
			public Object instantiateItem(View v, int position) {
				
				
				((ViewPager)v).addView(mViews.get(position));
				return mViews.get(position);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public void restoreState(Parcelable arg0, ClassLoader arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Parcelable saveState() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void startUpdate(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
	   
	   private class MyPagerOnPageChangeListener implements OnPageChangeListener{

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			/**
			 * 
			 */
			@Override
			public void onPageSelected(int position) {
				
				System.out.println("position=>"+position);
				if (position == 0) {
					mViewPager.setCurrentItem(1);
				}else if (position == 1) {
					mRadioButton1.performClick();
				}else if (position == 2) {
					mRadioButton2.performClick();
				}else if (position == 3) {
					mRadioButton3.performClick();
				}else if (position == 4) {
					mRadioButton4.performClick();
				}else if (position == 5){
					mRadioButton5.performClick();
				}else if (position == 6) {
					mViewPager.setCurrentItem(5);
				}	
				
				
				//移动加载子项页
				loadSubPager(position);
				
				
			}
			
		}
	   
	   public void loadSubPager(int position){
			//国内新闻
			if(position==2 && !viewGuoneiLoaded){
				XiaoneiListView = (ListView) viewXiaonei.findViewById(R.id.news_list);
				//guoNeiListView.setDivider(null); //去除分割线
				XiaoneiListView.setOnScrollListener(new OnScrollListener() {
					
					@Override
					public void onScrollStateChanged(AbsListView view, int scrollState) {
						//if(isLoadData == false && !isLoadAll){
							//if(view.getLastVisiblePosition() == view.getCount() - 1){
								
								/*ImageView img = (ImageView) progressDialog.findViewById(R.id.progress_img);
								Animation animation = AnimationUtils.loadAnimation(NewsActivity.this, R.anim.progress_anim);
								img.startAnimation(animation);
								progressDialog.show();
								Thread loadMoreThread = new Thread(loadMoreRunnable);
								loadMoreThread.start();*/
							//}
						//}
					}
					
					@Override
					public void onScroll(AbsListView view, int firstVisibleItem,
							int visibleItemCount, int totalItemCount) {
						
					}
				});
		    	
		    	progressDialog = DialogUtil.createProgressDialog(NewsActivity.this, "正在加载，请稍后...");
				
				ImageView img = (ImageView) progressDialog.findViewById(R.id.progress_img);
				Animation animation = AnimationUtils.loadAnimation(NewsActivity.this, R.anim.progress_anim);
				img.startAnimation(animation);
				progressDialog.show();
				
				Thread thread = new Thread(loadXiaoneiRunnable);
				thread.start();
				
				viewGuoneiLoaded  = true;
			}else if(position==3 && !viewGuojiLoaded){ //国际
				XiaowaiListView = (ListView) viewXiaowai.findViewById(R.id.news_list);
				//guoJiListView.setDivider(null); //去除分割线
				XiaowaiListView.setOnScrollListener(new OnScrollListener() {
					
					@Override
					public void onScrollStateChanged(AbsListView view, int scrollState) {
						//if(isLoadData == false && !isLoadAll){
							//if(view.getLastVisiblePosition() == view.getCount() - 1){
								
								/*ImageView img = (ImageView) progressDialog.findViewById(R.id.progress_img);
								Animation animation = AnimationUtils.loadAnimation(NewsActivity.this, R.anim.progress_anim);
								img.startAnimation(animation);
								progressDialog.show();
								Thread loadMoreThread = new Thread(loadMoreRunnable);
								loadMoreThread.start();*/
							//}
						//}
					}
					
					@Override
					public void onScroll(AbsListView view, int firstVisibleItem,
							int visibleItemCount, int totalItemCount) {
						
					}
				});
		    	
		    	progressDialog = DialogUtil.createProgressDialog(NewsActivity.this, "正在加载，请稍后...");
				
				ImageView img = (ImageView) progressDialog.findViewById(R.id.progress_img);
				Animation animation = AnimationUtils.loadAnimation(NewsActivity.this, R.anim.progress_anim);
				img.startAnimation(animation);
				progressDialog.show();
				
				Thread thread = new Thread(loadXiaowaiRunnable);
				thread.start();
				
				viewGuojiLoaded  = true;
			}else if(position==4 && !viewJunshiLoaded){ //军事
				ZhaopinListView = (ListView) viewZhaopin.findViewById(R.id.news_list);
				//junShiListView.setDivider(null); //去除分割线
				ZhaopinListView.setOnScrollListener(new OnScrollListener() {
					
					@Override
					public void onScrollStateChanged(AbsListView view, int scrollState) {
						//if(isLoadData == false && !isLoadAll){
							//if(view.getLastVisiblePosition() == view.getCount() - 1){
								
								/*ImageView img = (ImageView) progressDialog.findViewById(R.id.progress_img);
								Animation animation = AnimationUtils.loadAnimation(NewsActivity.this, R.anim.progress_anim);
								img.startAnimation(animation);
								progressDialog.show();
								Thread loadMoreThread = new Thread(loadMoreRunnable);
								loadMoreThread.start();*/
							//}
						//}
					}
					
					@Override
					public void onScroll(AbsListView view, int firstVisibleItem,
							int visibleItemCount, int totalItemCount) {
						
					}
				});
		    	
		    	progressDialog = DialogUtil.createProgressDialog(NewsActivity.this, "正在加载，请稍后...");
				
				ImageView img = (ImageView) progressDialog.findViewById(R.id.progress_img);
				Animation animation = AnimationUtils.loadAnimation(NewsActivity.this, R.anim.progress_anim);
				img.startAnimation(animation);
				progressDialog.show();
				
				Thread thread = new Thread(loadZhaopinRunnable);
				thread.start();
				
				viewJunshiLoaded  = true;
				
			}
		}
	   
	   public class NoticeAdapter extends BaseAdapter {

			private LayoutInflater inflater;
			
			public NoticeAdapter(Context context){
				inflater = LayoutInflater.from(context);
			}
			
			@Override
			public int getCount() {
				
					return data.size()+1;
				
			}

			@Override
			public Object getItem(int arg0) {
				return data.get(arg0);
			}

			@Override
			public long getItemId(int arg0) {
				return arg0;
			}

			@Override
			public View getView(int arg0, View view, ViewGroup group) {
				if(arg0<data.size()){
					if(view == null){
						view = inflater.inflate(R.layout.news_layout_1_item, null);
					}
					ImageView newsImage = (ImageView) view.findViewById(R.id.news_item_img_default);
					TextView dateText = (TextView) view.findViewById(R.id.news_item_date);
					TextView titleText = (TextView) view.findViewById(R.id.news_item_title);
					TextView subviewText = (TextView) view.findViewById(R.id.news_item_sub_title);
					if(dateText == null || titleText == null||subviewText == null){
						view = inflater.inflate(R.layout.news_layout_1_item, null);
						newsImage = (ImageView) view.findViewById(R.id.news_item_img_default);
						dateText = (TextView) view.findViewById(R.id.news_item_date);
						titleText = (TextView) view.findViewById(R.id.news_item_title);
						subviewText = (TextView) view.findViewById(R.id.news_item_sub_title);
					}
					Notice notice = data.get(arg0);
					if(notice != null){
						if(arg0%2 != 0){
							newsImage.setImageResource(R.drawable.test_news_xizhuxi);
						}else{
							newsImage.setImageResource(R.drawable.test_news_117_96);
						}
						dateText.setText(notice.getDate());
						titleText.setText(StringUtil.getSubString(notice.getTitle(), 12));
						subviewText.setText(StringUtil.getSubString(notice.getSubview(), 30));
					}
				}else {
					if(isLoadAll){
						view = inflater.inflate(R.layout.load_end_item, null);
					}else {
						view = inflater.inflate(R.layout.load_more_item, null);
					}
					
						
				}

				return view;
			}
			
		}
		
		
		



	public void showExitDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("您确定要退出软件吗？");
		builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				NewsActivity.this.finish();
			}
		});
		builder.setCancelable(false);
		builder.create().show();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showExitDialog();
		}
		return true;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
