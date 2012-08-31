package com.recovery.android.recoveryapp;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TenthForm extends Activity{
	
	Context context;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tenth_form);
       
        
        Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(btnListener);
        
    }
    private OnClickListener btnListener = new  OnClickListener(){
    	public void onClick(View v){
    		storeData();
    		
    		finish();
    	}
    };
    
    private void storeData(){
    	DatabaseManager db;
    	 db = new DatabaseManager(this);
    	final EditText editResent, editSelfish, editDishonest, editAfraid, editApology, editAnother,
    	editKind, editBetter, editMyself, editOthers, editRedink;
    	
    	final String resent, selfish, dishonest, afraid, apology, another, kind, better, myself, others, redink;
    	
    	final int mYear, mMonth, mDay;
    	
    	final Calendar c;
    	
    	editResent = (EditText) findViewById(R.id.EditResent);
    	editSelfish = (EditText) findViewById(R.id.EditSelfish);
    	editDishonest = (EditText) findViewById(R.id.EditDishonest);
    	editAfraid = (EditText) findViewById(R.id.EditAfraid);
    	editApology = (EditText) findViewById(R.id.EditApology);
    	editAnother = (EditText) findViewById(R.id.EditAnother);
    	editKind = (EditText) findViewById(R.id.EditKind);
    	editBetter = (EditText) findViewById(R.id.EditBetter);
    	editMyself = (EditText) findViewById(R.id.EditMyself);
    	editOthers = (EditText) findViewById(R.id.EditOthers);
    	editRedink = (EditText) findViewById(R.id.EditRedink);
    	
    	resent = editResent.getText().toString();
    	selfish = editSelfish.getText().toString();
    	dishonest = editDishonest.getText().toString();
    	afraid = editAfraid.getText().toString();
    	apology = editApology.getText().toString();
    	another = editAnother.getText().toString();
    	kind = editKind.getText().toString();
    	better = editBetter.getText().toString();
    	myself = editMyself.getText().toString();
    	others = editOthers.getText().toString();
    	redink = editRedink.getText().toString();

    	c = Calendar.getInstance();
    	mYear = c.get(Calendar.YEAR);
    	mMonth = c.get(Calendar.MONTH);
    	mDay = c.get(Calendar.DAY_OF_MONTH);
    	StringBuffer myDate = new StringBuffer();
    	myDate.append(mMonth+1).append("-").append(mDay).append("-").append(mYear).append(" ");
    	try{
    		db.addRow(myDate.toString(), resent, selfish, dishonest, afraid, apology, another, kind, better, myself,
        			others, redink);
    		Toast.makeText(this, "Tenth step saved!", Toast.LENGTH_SHORT).show();
    	}catch(SQLException e){
    		
    	}
    	finally{
    		db.dbClose();
    	}
    	
    	
    }
}
