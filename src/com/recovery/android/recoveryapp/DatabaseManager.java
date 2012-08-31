package com.recovery.android.recoveryapp;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager {
	Context context;
	
	private SQLiteDatabase db;
	
	private final String DB_NAME = "RecoveryApp1";
	private final int DB_VERSION = 1;
	
	private final String TABLE_NAME = "tenth_step_form";
	private final String TABLE_ROW_ID = "id";
	private final String TABLE_ROW_DATE = "date";
	private final String TABLE_ROW_ONE = "resentments";
	private final String TABLE_ROW_TWO = "selfish";
	private final String TABLE_ROW_THREE = "dishonest";
	private final String TABLE_ROW_FOUR = "afraid";
	private final String TABLE_ROW_FIVE = "apology";
	private final String TABLE_ROW_SIX = "discuss";
	private final String TABLE_ROW_SEVEN = "loving";
	private final String TABLE_ROW_EIGHT = "better";
	private final String TABLE_ROW_NINE = "myself";
	private final String TABLE_ROW_TEN = "others";
	private final String TABLE_ROW_ELEVEN = "redink";
	
	public DatabaseManager(Context context){
		this.context = context;
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
		this.db = helper.getWritableDatabase();
	}
	
	private class CustomSQLiteOpenHelper extends SQLiteOpenHelper{
		public CustomSQLiteOpenHelper(Context context){
			super(context, DB_NAME, null, DB_VERSION);
		}
		
			@Override
			public void onCreate(SQLiteDatabase db){
				String newTableQueryString = "create table " + 
					TABLE_NAME + " (" + 
					TABLE_ROW_ID + " integer primary key autoincrement not null," +
					TABLE_ROW_DATE + " text," +
					TABLE_ROW_ONE + " text," +
					TABLE_ROW_TWO + " text," +
					TABLE_ROW_THREE + " text," +
					TABLE_ROW_FOUR + " text," +
					TABLE_ROW_FIVE + " text," +
					TABLE_ROW_SIX + " text," +
					TABLE_ROW_SEVEN + " text," +
					TABLE_ROW_EIGHT + " text," +
					TABLE_ROW_NINE + " text," +
					TABLE_ROW_TEN + " text," +
					TABLE_ROW_ELEVEN + " text" +
					");";
				db.execSQL(newTableQueryString); 
				
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion,
					int newVersion) {
				// TODO Auto-generated method stub
				
			}
		
	}
	
	
	
	public void addRow(String date, String resent, String selfish, 
			String dishonest, String afraid, String apology,
			String discuss, String loving, String better, String myself,
			String others, String redink){
		ContentValues values = new ContentValues();
		values.put(TABLE_ROW_DATE, date);
		values.put(TABLE_ROW_ONE, resent);
		values.put(TABLE_ROW_TWO, selfish);
		values.put(TABLE_ROW_THREE, dishonest);
		values.put(TABLE_ROW_FOUR, afraid);
		values.put(TABLE_ROW_FIVE, apology);
		values.put(TABLE_ROW_SIX, discuss);
		values.put(TABLE_ROW_SEVEN, loving);
		values.put(TABLE_ROW_EIGHT, better);
		values.put(TABLE_ROW_NINE, myself);
		values.put(TABLE_ROW_TEN, others);
		values.put(TABLE_ROW_ELEVEN, redink);
		
		
		try{
			
			db.insert(TABLE_NAME, null, values);
		}catch(Exception e){
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		
		}
		
	}
	
	public ArrayList<ArrayList<Object>> getAllRowsAsArrays(){
		ArrayList<ArrayList<Object>> dataArrays = new ArrayList<ArrayList<Object>>();
		Cursor cursor = null;
		
		try{
			cursor = db.query(TABLE_NAME, new String[]{TABLE_ROW_ID, TABLE_ROW_DATE, 
					TABLE_ROW_ONE, TABLE_ROW_TWO, TABLE_ROW_THREE, TABLE_ROW_FOUR, TABLE_ROW_FIVE,
					TABLE_ROW_SIX, TABLE_ROW_SEVEN, TABLE_ROW_EIGHT, TABLE_ROW_NINE, TABLE_ROW_TEN,
					TABLE_ROW_ELEVEN}, null, null, null, null, null);
			cursor.moveToFirst();
			
			if(!cursor.isAfterLast()){
				do{
					ArrayList<Object> dataList = new ArrayList<Object>();
					dataList.add(cursor.getLong(0));
					dataList.add(cursor.getString(1));
					dataList.add(cursor.getString(2));
					dataList.add(cursor.getString(3));
					dataList.add(cursor.getString(4));
					dataList.add(cursor.getString(5));
					dataList.add(cursor.getString(6));
					dataList.add(cursor.getString(7));
					dataList.add(cursor.getString(8));
					dataList.add(cursor.getString(9));
					dataList.add(cursor.getString(10));
					dataList.add(cursor.getString(11));
					dataList.add(cursor.getString(12));
					dataArrays.add(dataList);
				}
				while(cursor.moveToNext());
			}
		}catch(SQLException e){
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
		finally{
			cursor.close();
		}
		
		return dataArrays;
	}

	public void dbClose() {
		// TODO Auto-generated method stub
		db.close();
	}
}
