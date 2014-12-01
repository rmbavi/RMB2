package com.example.rmb.util;


import com.example.rmb.R;
import android.app.Dialog;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogUtil {

	public static Dialog createProgressDialog(Context context, String message) {
		
		Dialog dialog = null;
		dialog = new Dialog(context, R.style.dialog_progress);
		dialog.setContentView(R.layout.progress_dialog);
		dialog.setCanceledOnTouchOutside(false); // ���õ����Ե�Ƿ���ʧ
		// dialog.setCancelable(false);
		ImageView img = (ImageView) dialog.findViewById(R.id.progress_img);
		Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.progress_anim);
		img.startAnimation(animation);
		TextView text = (TextView) dialog.findViewById(R.id.progress_msg);
		text.setText(message);
		return dialog;
		
	}

}
