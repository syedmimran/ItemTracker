package com.example.itemtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String REPORTED_ITEM_TABLE = "REPORTED_ITEM_TABLE";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_MISSING = "MISSING";
    public static final String COLUMN_BROKEN = "BROKEN";
    public static final String COLUMN_ITEM_DETAILS = "ITEM_DETAILS";
    public static final String COLUMN_ITEM_ID = "ITEM_ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Report.db", null, 1);
    }

    // This is the first time a database is accessed. Should be a code to create database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + REPORTED_ITEM_TABLE + " (" + COLUMN_ITEM_NAME + " TEXT , " + COLUMN_ITEM_ID + " TEXT PRIMARY KEY , " + COLUMN_MISSING + " BOOL, " + COLUMN_BROKEN + " BOOL, " + COLUMN_ITEM_DETAILS + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//    public String getDetails(ReportItemModel reportItemModel){
//        String returnDetails = new String();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String queryString = "SELECT " + COLUMN_ITEM_DETAILS + " FROM " + REPORTED_ITEM_TABLE;
//        return returnDetails;
//
//    }
    public boolean addOne(ReportItemModel reportItemModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();

        cv.put(COLUMN_ITEM_NAME, reportItemModel.getName());
        cv.put(COLUMN_ITEM_ID, reportItemModel.getId());
        cv.put(COLUMN_MISSING, reportItemModel.isMissing());
        cv.put(COLUMN_BROKEN, reportItemModel.isBroken());
        cv.put(COLUMN_ITEM_DETAILS, reportItemModel.getDetails());

        long insert = db.insert(REPORTED_ITEM_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean deleteOne(ReportItemModel reportItemModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + REPORTED_ITEM_TABLE + " WHERE " +  COLUMN_ITEM_ID + " = " + reportItemModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }
    public List<ReportItemModel> getAll(){
        List<ReportItemModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + REPORTED_ITEM_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            do{
                String itemName = cursor.getString(0);
                String itemId = cursor.getString(1);
                boolean isMissing = cursor.getInt(2) == 1 ? true: false;
                boolean isBroken = cursor.getInt(3) == 1 ? true: false;
                String itemDetails = cursor.getString(4);

                ReportItemModel newItem = new ReportItemModel(itemName, itemId, isMissing, isBroken, itemDetails);
                returnList.add(newItem);
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;

    }
}
