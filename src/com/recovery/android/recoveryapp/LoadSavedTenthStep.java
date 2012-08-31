package com.recovery.android.recoveryapp;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;

public class LoadSavedTenthStep extends Activity{
	Context context;
	WebView mWebView;
	RAMenuOptions mOptions;
	String newString;
	String date=null;
	StringBuffer dbData = new StringBuffer();
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		Intent launchingIntent = getIntent();
		date = launchingIntent.getData().toString();
		
		
		
		context = this;
		setContentView(R.layout.main);
		mWebView = (WebView)findViewById(R.id.webview);
		mOptions = new RAMenuOptions(context, mWebView);
		mWebView.getSettings().setBuiltInZoomControls(true);
		
		
		
		try{
		   	SavedTenth();
		}catch(UnsupportedEncodingException e){
			
		}
	}
	
	private void SavedTenth() throws UnsupportedEncodingException{
		DatabaseManager db = new DatabaseManager(this);
		
		
		ArrayList<Object> row;
		
		
		try{
			ArrayList<ArrayList<Object>> data = db.getAllRowsAsArrays();
			dbData.append("<html><body>");
			
			for(int position=data.size(); position>0; position--){
				row = data.get(position-1);
				
				if(row.get(1).toString().equalsIgnoreCase(date)){
					
					
					dbData.append("<b><big>Date:</big></b> ");
					dbData.append(row.get(1).toString() + "<br /><br />");
					
					specChars(row.get(2).toString());
					dbData.append("<b>" + getString(R.string.resentful) + "</b>"); //resentful
					dbData.append("<p>" + newString + "</p>");
					
					specChars(row.get(3).toString());
					dbData.append("<b>" + getString(R.string.selfish) + "</b>");
					dbData.append("<p>" + newString + "</p>");
					
					specChars(row.get(4).toString());
					dbData.append("<b>" + getString(R.string.dishonest) + "</b>");
					dbData.append("<p>" + newString + "</p>");
					
					specChars(row.get(5).toString());
					dbData.append("<b>" + getString(R.string.afraid) + "</b>");
					dbData.append("<p>" + newString + "</p>");
					
					specChars(row.get(6).toString());
					dbData.append("<b>" + getString(R.string.apology) + "</b>");
					dbData.append("<p>" + newString + "</p>");
					
					specChars(row.get(7).toString());
					dbData.append("<b>" + getString(R.string.another) + "</b>");
					dbData.append("<p>" + newString + "</p>");
					
					specChars(row.get(8).toString());
					dbData.append("<b>" + getString(R.string.kind) + "</b>");
					dbData.append("<p>" + newString + "</p>");
					
					specChars(row.get(9).toString());
					dbData.append("<b>" + getString(R.string.better) + "</b>");
					dbData.append("<p>" + newString + "</p>");
					
					specChars(row.get(10).toString());
					dbData.append("<b>" + getString(R.string.myself) + "</b>");
					dbData.append("<p>" + newString + "</p>");
					
					specChars(row.get(11).toString());
					dbData.append("<b>" + getString(R.string.others) + "</b>");
					dbData.append("<p>" + newString + "</p>");
					
					specChars(row.get(12).toString());
					dbData.append("<b>" + getString(R.string.redink) + "</b>");
					dbData.append("<p>" + newString + "</p>");
					
					dbData.append("<hr />");
				}
				
			}
			dbData.append("</body></html>");
			mWebView.loadData(dbData.toString(), "text/html", "utf-8");
			
		}catch(SQLException e){
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
		finally{
			db.dbClose();
		}
	}
	
	public void specChars(String savedString){
		newString  = savedString.replaceAll("'", "&apos;");
		newString  = savedString.replaceAll("\"", "&quot;");
		newString  = savedString.replaceAll("&", "&amp;");
		newString  = savedString.replaceAll("<", "&lt;");
		newString  = savedString.replaceAll(">", "&gt;");
		newString  = savedString.replaceAll("%", "&#37;");
	}
	private static final int EMAIL = Menu.FIRST;
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		menu.add(0, EMAIL, 0, "EMAIL").setIcon(android.R.drawable.ic_menu_send);
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
			 Intent i = new Intent(LoadSavedTenthStep.this, MainActivity.class);
	         startActivity(i);
	         return true;
		case EMAIL:
    		emailTenthStep();
    		return true;
		}
		return true;
	}
	
	private void emailTenthStep(){
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);  
		emailIntent.setType("plain/text");  
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, android.text.Html.fromHtml(dbData.toString()).toString());  
		startActivity(Intent.createChooser(emailIntent, "Send your email in:"));   
		
	}
	
	

}
