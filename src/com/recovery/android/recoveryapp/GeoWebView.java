package com.recovery.android.recoveryapp;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GeoWebView extends Activity{
	Context context;
	final Activity myActivity = this;
	/**
	 * WebViewClient subclass loads all hyperlinks in the existing WebView
	 */
	public class GeoWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// When user clicks a hyperlink, load in the existing WebView
			view.loadUrl(url);
			return true;
		}
	}
	
	/**
	 * WebChromeClient subclass handles UI-related calls
	 * Note: think chrome as in decoration, not the Chrome browser
	 */
	public class GeoWebChromeClient extends WebChromeClient {
		@Override
		public void onGeolocationPermissionsShowPrompt(String origin,
				GeolocationPermissions.Callback callback) {
			// Always grant permission since the app itself requires location
			// permission and the user has therefore already granted it
			
			callback.invoke(origin, true, false);
		}
		@Override
		public void onProgressChanged(WebView view, int progress){
			myActivity.setProgress(progress * 100);
		}
	}
	
	WebView mWebView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent launchingIntent = getIntent();
		 String content = launchingIntent.getData().toString();
		 context = this;
		 
		 
			
			myActivity.getWindow().requestFeature(Window.FEATURE_PROGRESS);
			myActivity.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
			
			
		setContentView(R.layout.main);
		mWebView = (WebView) findViewById(R.id.webview);
		
		
		// Brower niceties -- pinch / zoom, follow links in place
		mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.setWebViewClient(new GeoWebViewClient());
		mWebView.setWebChromeClient(new WebChromeClient(){
			public void onProgressChanged(WebView view, int progress){
				myActivity.setProgress(progress * 100);
			}
		});
		
		// Below required for geolocation
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setGeolocationEnabled(true);
		mWebView.setWebChromeClient(new GeoWebChromeClient());
		
		// Load site
		mWebView.loadUrl(content);
	}

	@Override
	public void onBackPressed() {
		// Pop the browser back stack or exit the activity
		if (mWebView.canGoBack()) {
			mWebView.goBack();
		}
		else {
			super.onBackPressed();
		}
	}
}


