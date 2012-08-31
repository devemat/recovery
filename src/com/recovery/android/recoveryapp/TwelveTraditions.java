package com.recovery.android.recoveryapp;


import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class TwelveTraditions extends ListActivity{
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setListAdapter(ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.TwelveTraditionsOptions, R.layout.list_item));
        
        getListView().setOnItemClickListener(new OnItemClickListener() {
        	final String[] links = getResources().getStringArray(R.array.TwelveTraditionsOptionsLinks);
        	
        	 @Override
             public void onItemClick(AdapterView<?> parent, View view,
                     int position, long id) {
             	String contentLink = links[position];
         		Intent showContent = new Intent(getApplicationContext(), LoadPage.class);
         		showContent.setData(Uri.parse(contentLink));
         		startActivity(showContent);
        	 }
        });
	}

}
