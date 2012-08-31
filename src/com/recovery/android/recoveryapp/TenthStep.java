package com.recovery.android.recoveryapp;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class TenthStep extends ListActivity{
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setListAdapter(ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.TenthStepOptions, R.layout.list_item));
		
		getListView().setOnItemClickListener(new OnItemClickListener() {
			final String[] links = getResources().getStringArray(R.array.TenthStepOptionsLinks);
        	final String[] options = getResources().getStringArray(R.array.TenthStepOptions);
        	
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				String userSelect = options[position];
				if(userSelect == options[0]){
					String contentLink = links[position];
					Intent showContent = new Intent(getApplicationContext(), LoadPage.class);
					showContent.setData(Uri.parse(contentLink));
            		startActivity(showContent);
				}
				else if(userSelect == options[1]){
					Intent myIntent = new Intent(view.getContext(), TenthForm.class);
        			startActivityForResult(myIntent,0);
				}
				else if(userSelect == options[2]){
					Intent myIntent = new Intent(view.getContext(), TenthStepList.class);
        			startActivityForResult(myIntent,0);
				}
				
			}
			
		});
	}

}
