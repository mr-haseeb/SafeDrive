package com.example.safedrive.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "activities_db";
    // Table Name
    private static final String TABLE_NAME = "activities_table";


    private static final String COL_1 = "ID";
    private static final String COL_2 = "class";
    private static final String COL_3 = "NOSE";
    private static final String COL_4 = "LEFT_EYE";
    private static final String COL_5 = "RIGHT_EYE";
    private static final String COL_6 = "LEFT_EAR";
    private static final String COL_7 = "RIGHT_EAR";
    private static final String COL_8 = "LEFT_SHOULDER";
    private static final String COL_9 = "RIGHT_SHOULDER";
    private static final String COL_10 = "LEFT_ELBOW";
    private static final String COL_11 = "RIGHT_ELBOW";
    private static final String COL_12 = "LEFT_WRIST";
    private static final String COL_13 = "RIGHT_WRIST";
    private static final String COL_14 = "LEFT_HIP";
    private static final String COL_15 = "RIGHT_HIP";
    private static final String COL_16 = "LEFT_KNEE";
    private static final String COL_17 = "RIGHT_KNEE";
    private static final String COL_18 = "LEFT_ANKLE";
    private static final String COL_19 = "RIGHT_ANKLE";
    private static final String COL_20 = "score";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,class TEXT,NOSE INTEGER,LEFT_EYE INTEGER,RIGHT_EYE INTEGER,"
                +" LEFT_EAR INTEGER,RIGHT_EAR INTEGER,LEFT_SHOULDER,RIGHT_SHOULDER,LEFT_ELBOW INTEGER,RIGHT_ELBOW INTEGER,LEFT_WRIST INTEGER,"
                +"RIGHT_WRIST INTEGER,LEFT_HIP INTEGER,RIGHT_HIP INTEGER,LEFT_KNEE INTEGER,RIGHT_KNEE INTEGER,LEFT_ANKLE INTEGER,RIGHT_ANKLE INTEGER,score INTEGER)");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public boolean insertData( String clas, int NOSE, int LEFT_EYE, int RIGHT_EYE, int LEFT_EAR, int RIGHT_EAR,
                              int LEFT_SHOULDER, int RIGHT_SHOULDER, int LEFT_ELBOW, int RIGHT_ELBOW, int LEFT_WRIST, int RIGHT_WRIST,
                              int LEFT_HIP, int RIGHT_HIP, int LEFT_KNEE, int RIGHT_KNEE, int LEFT_ANKLE, int RIGHT_ANKLE, int score) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        contentValues.put(COL_2, clas);
        contentValues.put(COL_3 ,NOSE);
        contentValues.put(COL_4,LEFT_EYE);
        contentValues.put(COL_5,RIGHT_EYE);
        contentValues.put(COL_6,LEFT_EAR );
        contentValues.put(COL_7,RIGHT_EAR );
        contentValues.put(COL_8,LEFT_SHOULDER );
        contentValues.put(COL_9,RIGHT_SHOULDER );
        contentValues.put(COL_10,LEFT_ELBOW );
        contentValues.put(COL_11, RIGHT_ELBOW);
        contentValues.put(COL_12,LEFT_WRIST );
        contentValues.put(COL_13,RIGHT_WRIST );
        contentValues.put(COL_14,LEFT_HIP);
        contentValues.put(COL_15,RIGHT_HIP );


        // insert row
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result==-1){
            return false;
        }
        else{
            return true;
        }


    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(int id, String clas, int NOSE, int LEFT_EYE, int RIGHT_EYE, int LEFT_EAR, int RIGHT_EAR,
                              int LEFT_SHOULDER, int RIGHT_SHOULDER, int LEFT_ELBOW, int RIGHT_ELBOW, int LEFT_WRIST, int RIGHT_WRIST,
                              int LEFT_HIP, int RIGHT_HIP, int LEFT_KNEE, int RIGHT_KNEE, int LEFT_ANKLE, int RIGHT_ANKLE, int score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2, clas);
        contentValues.put(COL_3 ,NOSE);
        contentValues.put(COL_4,LEFT_EYE);
        contentValues.put(COL_5,RIGHT_EYE);
        contentValues.put(COL_6,LEFT_EAR );
        contentValues.put(COL_7,RIGHT_EAR );
        contentValues.put(COL_8,LEFT_SHOULDER );
        contentValues.put(COL_9,RIGHT_SHOULDER );
        contentValues.put(COL_10,LEFT_ELBOW );
        contentValues.put(COL_11, RIGHT_ELBOW);
        contentValues.put(COL_12,LEFT_WRIST );
        contentValues.put(COL_13,RIGHT_WRIST );
        contentValues.put(COL_14,LEFT_HIP);
        contentValues.put(COL_15,RIGHT_HIP );

        // updating row
         db.update(TABLE_NAME,contentValues,"id=?",new String[]{String.valueOf(id)});
         return true;
    }

//    public int getNotesCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//
//        int count = cursor.getCount();
//        cursor.close();
//
//
//        // return count
//        return count;
//    }



    public Integer deleteData( String id ) {
        SQLiteDatabase db = this.getWritableDatabase();

       return db.delete(TABLE_NAME, "Id = ?", new String[]{String.valueOf(String.valueOf(id))});


    }
}