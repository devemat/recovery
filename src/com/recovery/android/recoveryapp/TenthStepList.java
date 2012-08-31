package com.recovery.android.recoveryapp;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;




public class TenthStepList extends ListActivity{
	String[] tenthStep=null; 
	ArrayList<Object> row;
	ArrayList<ArrayList<Object>> data;
	String[] dates = null;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 DatabaseManager db = new DatabaseManager(this);
		 
		 
		 try{
			  data = db.getAllRowsAsArrays();
			 dates = new String[data.size()];
			 tenthStep = new String[data.size()];
			 int i = 0;
			 for(int position=data.size(); position>0; position--){
				 row = data.get(position-1);
				 dates[i] = row.get(1).toString();
				 i++;
			 }
		 }catch(SQLException e){
				Log.e("DB ERROR", e.toString());
				e.printStackTrace();
			}
		 finally{
				db.dbClose();
			}
		
		 this.setListAdapter(new ArrayAdapter<String>(this, 
				 android.R.layout.simple_list_item_1, dates)); 
		 getListView().setOnItemClickListener(new OnItemClickListener() {
			 
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Intent myIntent = new Intent(getApplicationContext(), LoadSavedTenthStep.class);
				
				myIntent.setData(Uri.parse(dates[position]));
    		
				startActivity(myIntent);
				
			}
			 
		 });
	 }

}
