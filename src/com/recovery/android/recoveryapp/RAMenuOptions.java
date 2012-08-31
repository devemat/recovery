package com.recovery.android.recoveryapp;

import java.lang.reflect.Method;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.app.Activity;

public class RAMenuOptions {
	private Context mContext;
	private LinearLayout container;
	private Button nextButton, closeButton;
	private WebView mWebView;
	private EditText findBox;
	
	public RAMenuOptions(Context context,  WebView theWebView) {
		this.mContext = context;
		this.container = (LinearLayout) ((Activity) context).findViewById(R.id.layoutId);
		this.mWebView = theWebView;
	}
	
	public void copyText(){
		try{
			KeyEvent shiftPressEvent = new KeyEvent(0, 0, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT, 0, 0);
			shiftPressEvent.dispatch(mWebView);
		}catch(Exception e){
			throw new AssertionError(e);
		}
	}
	
	public void searchText(){
		nextButton = new Button(mContext);
		
		nextButton.setText("Next");
		nextButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				mWebView.findNext(true);
			}
		});
		
		container.addView(nextButton);
		
		closeButton = new Button(mContext);
		closeButton.setText("Close");
		closeButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				container.removeAllViews();
				
			}
		});
		//container = (LinearLayout)findViewById(R.id.layoutId);
		container.addView(closeButton);
		
		findBox = new EditText(mContext);
		findBox.setMinEms(30);
		findBox.setSingleLine(true);
		findBox.setHint("Acceptance");
		
		findBox.setOnKeyListener(new OnKeyListener(){
			public boolean onKey(View v, int keyCode, KeyEvent event){
				if((event.getAction() == KeyEvent.ACTION_DOWN) && ((keyCode == KeyEvent.KEYCODE_ENTER))){
					mWebView.findAll(findBox.getText().toString());
					
					try{
						Method m = WebView.class.getMethod("setFindIsUp", Boolean.TYPE);
						m.invoke(mWebView, true);
					}catch(Exception ignored){}
				}
				return false;
			}
		});
		//container = (LinearLayout)findViewById(R.id.layoutId);
		container.addView(findBox);
	}
}
