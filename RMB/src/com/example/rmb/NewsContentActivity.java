package com.example.rmb;


import java.net.URL;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rmb.util.FileUtils;

public class NewsContentActivity extends SuperActivity implements OnClickListener{
	private TextView title;
	private TextView from;
	private TextView date;
	private TextView content;
	
	private Button backBtn;
	private Button favoritesBtn; 
	private Button shareBtn;
	private Button nightBtn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.news_content_layout);
//
//		title = (TextView) findViewById(R.id.news_title_text);
//		from = (TextView) findViewById(R.id.news_from_text);
//		date = (TextView) findViewById(R.id.news_date_text);
//		content = (TextView) findViewById(R.id.news_content_text);
//		
//		backBtn = (Button) findViewById(R.id.second_nav_back);
//		favoritesBtn = (Button) findViewById(R.id.article_bottom_favorites_btn);
//		shareBtn = (Button) findViewById(R.id.article_bottom_share_btn);
//		nightBtn = (Button) findViewById(R.id.article_bottom_night_btn);
		
		backBtn.setOnClickListener(this);
		favoritesBtn.setOnClickListener(this);
		shareBtn.setOnClickListener(this);
		nightBtn.setOnClickListener(this);
		
		title.setText(Html.fromHtml("习近平携彭丽媛访问俄罗斯"));
		from.setText(Html.fromHtml("腾讯网"));
		date.setText(Html.fromHtml("2013-03-21<hr>"));

		final Html.ImageGetter imageGetter = new Html.ImageGetter() {

			public Drawable getDrawable(String source) {
				Drawable drawable = null;
				URL url;
				try {
					url = new URL(source);
					drawable = Drawable.createFromStream(url.openStream(), "");
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
				drawable.setBounds(20, 5, drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight());
				return drawable;
			};
		};

		final String sText = "测试图片信息：<br><img src=\"http://pic004.cnblogs.com/news/201211/20121108_091749_1.jpg\" />" +
				"<P style=\"TEXT-INDENT: 2em\">&nbsp;&nbsp;&nbsp;&nbsp;【习近平离京出访四国】国家主席习近平22日上午乘专机离开北京，对俄罗斯、坦桑尼亚、南非、刚果共和国进行国事访问，并出席在南非德班举行的金砖国家领导人第五次会晤。彭丽媛、王沪宁、栗战书、杨洁篪等陪同出访。（记者刘东凯、侯丽军）</P>"+
				"<P style=\"TEXT-INDENT: 2em\">&nbsp;&nbsp;&nbsp;&nbsp;【习近平离京出访四国】国家主席习近平22日上午乘专机离开北京，对俄罗斯、坦桑尼亚、南非、刚果共和国进行国事访问，并出席在南非德班举行的金砖国家领导人第五次会晤。彭丽媛、王沪宁、栗战书、杨洁篪等陪同出访。（记者刘东凯、侯丽军）</P>";
		//final String imgUrl = "<img src=\"http://img1.gtimg.com/news/pics/hv1/7/108/1287/83714722.jpg\"/><p>这是1989年9月，习近平和彭丽媛合影。<p><p>";
//		content.setText(Html.fromHtml(FileUtils.readStream(getResources().openRawResource(R.raw.new_content)), imageGetter, null));

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}


//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		switch(v.getId()){
//		case R.id.second_nav_back:
//			finish();
//			break;
//		case R.id.article_bottom_favorites_btn:
//			Toast.makeText(this, "收藏成功", 1000).show();
//			break;
//		case R.id.article_bottom_share_btn:
//			Toast.makeText(this, "分享到QQ，新浪", 1000).show();
//			break;
//		case R.id.article_bottom_night_btn:
//			Toast.makeText(this, "改为夜间阅读模式", 1000).show();
//			break;
//		default:
//			break;
//		}
//		
//	}
}