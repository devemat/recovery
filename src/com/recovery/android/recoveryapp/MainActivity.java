package com.recovery.android.recoveryapp;


import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setListAdapter(ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.MainOptions, R.layout.list_item));
        
        getListView().setOnItemClickListener(new OnItemClickListener() {
        	
        	final String[] links = getResources().getStringArray(R.array.MainOptionsLinks);
        	final String[] options = getResources().getStringArray(R.array.MainOptions);
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            	String userSelect = options[position];
            	
            	if(userSelect == options[2] || userSelect == options[3] 
            	   || userSelect == options[5] || userSelect == options[7] || userSelect == options[8]){
            		String contentLink = links[position];
            		Intent showContent = new Intent(getApplicationContext(), LoadPage.class);
            		showContent.setData(Uri.parse(contentLink));
            		startActivity(showContent);
            	}
            	else if(userSelect == options[0]){
            		Intent myIntent = new Intent(view.getContext(), TwelveSteps.class);
        			startActivityForResult(myIntent,0);
            	}
            	else if(userSelect == options[1]){
            		Intent myIntent = new Intent(view.getContext(), BigBook.class);
        			startActivityForResult(myIntent,0);
            	}
            	else if(userSelect == options[4]){
            		String contentLink = links[position];
            		Intent showContent = new Intent(getApplicationContext(), GeoWebView.class);
            		showContent.setData(Uri.parse(contentLink));
            		startActivity(showContent);
            	}
            	else if(userSelect == options[6]){
            		Intent myIntent = new Intent(view.getContext(), Prayers.class);
        			startActivityForResult(myIntent,0);
            	}
            	else if(userSelect == options[9]){
            		Intent myIntent = new Intent(view.getContext(), TenthStep.class);
        			startActivityForResult(myIntent,0);
            	}
            	
            }
        	
        });
        
       
    }
    
}