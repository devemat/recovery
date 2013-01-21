package com.recovery.android.recoveryapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoadPage extends Activity{
	Context context;
	WebView viewer;
	RAMenuOptions mOptions;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		 
		 Intent launchingIntent = getIntent();
		 String content = launchingIntent.getData().toString();
		 context = this;
		
		final Activity myActivity = this;
		
		myActivity.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		myActivity.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
		
		setContentView(R.layout.main);
		viewer = (WebView)findViewById(R.id.webview);
		
		mOptions = new RAMenuOptions(context, viewer);
		
		viewer.getSettings().setJavaScriptEnabled(true);
		viewer.getSettings().setBuiltInZoomControls(true);
		viewer.setWebViewClient(new HelloWebViewClient());
		
		viewer.setWebChromeClient(new WebChromeClient(){
			public void onProgressChanged(WebView view, int progress){
				myActivity.setProgress(progress * 100);
			}
		});
		
		viewer.loadUrl(content);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		
		try{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_options, menu);
		}catch(Exception e){
			
		}
		
		return true;
	}
	
	public boolean onPrepareOptionsMenu(Menu menu){
		super.onPrepareOptionsMenu(menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.select_text:
			mOptions.copyText();
			return true;
		case R.id.find_text:
			mOptions.searchText();
			return true;
		case R.id.home:
			 Intent i = new Intent(LoadPage.this, MainActivity.class);
	         startActivity(i);
	         return true;
		}
		return true;
	}
	
	
	
	@Override
    public void onConfigurationChanged(Configuration  newConfig) {
      super.onConfigurationChanged(newConfig);
    

    }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && viewer.canGoBack()) {
	        viewer.goBack();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	private class HelloWebViewClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url){
			view.loadUrl(url);
			return true;
		}
	}
	
	}
	
	

