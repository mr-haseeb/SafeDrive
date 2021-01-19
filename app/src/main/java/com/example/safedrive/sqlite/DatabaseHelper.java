package com.example.safedrive.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "activities.sqlite";
    // Table Name
    private static final String TABLE_NAME = "activities_table";


    private static final String COL_1 = "ID";
    private static final String COL_2 = "class";

    private static final String COL_3 = "NOSE_X";
    private static final String COL_4 = "NOSE_Y";

    private static final String COL_5 = "LEFT_EYE_X";
    private static final String COL_6 = "LEFT_EYE_Y";

    private static final String COL_7 = "RIGHT_EYE_X";
    private static final String COL_8 = "RIGHT_EYE_Y";

    private static final String COL_9 = "LEFT_EAR_X";
    private static final String COL_10 = "LEFT_EAR_Y";

    private static final String COL_11 = "RIGHT_EAR_X";
    private static final String COL_12 = "RIGHT_EAR_Y";

    private static final String COL_13 = "LEFT_SHOULDER_X";
    private static final String COL_14 = "LEFT_SHOULDER_Y";

    private static final String COL_15 = "RIGHT_SHOULDER_X";
    private static final String COL_16 = "RIGHT_SHOULDER_Y";

    private static final String COL_17 = "LEFT_ELBOW_X";
    private static final String COL_18 = "LEFT_ELBOW_Y";

    private static final String COL_19 = "RIGHT_ELBOW_X";
    private static final String COL_20 = "RIGHT_ELBOW_Y";

    private static final String COL_21 = "LEFT_WRIST_X";
    private static final String COL_22 = "LEFT_WRIST_Y";

    private static final String COL_23 = "RIGHT_WRIST_X";
    private static final String COL_24 = "RIGHT_WRIST_Y";

    private static final String COL_25 = "LEFT_HIP_X";
    private static final String COL_26 = "LEFT_HIP_Y";

    private static final String COL_27 = "RIGHT_HIP_X";
    private static final String COL_28 = "RIGHT_HIP_Y";

    private static final String COL_29 = "LEFT_KNEE_X";
    private static final String COL_30 = "LEFT_KNEE_Y";

    private static final String COL_31 = "RIGHT_KNEE_X";
    private static final String COL_32 = "RIGHT_KNEE_Y";

    private static final String COL_33 = "LEFT_ANKLE_X";
    private static final String COL_34 = "LEFT_ANKLE_Y";

    private static final String COL_35 = "RIGHT_ANKLE_X";
    private static final String COL_36 = "RIGHT_ANKLE_Y";

    private static final String COL_37 = "score";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,class TEXT,NOSE_X REAL,NOSE_Y REAL,LEFT_EYE_X REAL,LEFT_EYE_Y REAL,RIGHT_EYE_X REAL,RIGHT_EYE_Y REAL,"
                +" LEFT_EAR_X REAL,LEFT_EAR_Y REAL,RIGHT_EAR_X REAL,RIGHT_EAR_Y REAL,LEFT_SHOULDER_X REAL,LEFT_SHOULDER_Y REAL,RIGHT_SHOULDER_X REAL,RIGHT_SHOULDER_Y REAL,LEFT_ELBOW_X REAL,LEFT_ELBOW_Y REAL,RIGHT_ELBOW_X REAL,RIGHT_ELBOW_Y REAL,LEFT_WRIST_X REAL,LEFT_WRIST_Y REAL,"
                +"RIGHT_WRIST_X REAL,RIGHT_WRIST_Y REAL,LEFT_HIP_X REAL,LEFT_HIP_Y REAL,RIGHT_HIP_X REAL,RIGHT_HIP_Y REAL,LEFT_KNEE_X REAL,LEFT_KNEE_Y REAL,RIGHT_KNEE_X REAL,RIGHT_KNEE_Y REAL,LEFT_ANKLE_X REAL,LEFT_ANKLE_Y REAL,RIGHT_ANKLE_X REAL,RIGHT_ANKLE_Y REAL,score REAL)");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public boolean insertData( String clas, float NOSE_X,float NOSE_Y, float LEFT_EYE_X,float LEFT_EYE_Y, float RIGHT_EYE_X,float RIGHT_EYE_Y, float LEFT_EAR_X,float LEFT_EAR_Y, float RIGHT_EAR_X,float RIGHT_EAR_Y,
                              float LEFT_SHOULDER_X,float LEFT_SHOULDER_Y, float RIGHT_SHOULDER_X,float RIGHT_SHOULDER_Y, float LEFT_ELBOW_X,float LEFT_ELBOW_Y, float RIGHT_ELBOW_X,float RIGHT_ELBOW_Y, float LEFT_WRIST_X,float LEFT_WRIST_Y, float RIGHT_WRIST_X,float RIGHT_WRIST_Y,
                              float LEFT_HIP_X,float LEFT_HIP_Y, float RIGHT_HIP_X,float RIGHT_HIP_Y, float LEFT_KNEE_X,float LEFT_KNEE_Y, float RIGHT_KNEE_X,float RIGHT_KNEE_Y, float LEFT_ANKLE_X,float LEFT_ANKLE_Y, float RIGHT_ANKLE_X,float RIGHT_ANKLE_Y, float score) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        contentValues.put(COL_2, clas);
        contentValues.put(COL_3 ,NOSE_X);
        contentValues.put(COL_4 ,NOSE_Y);

        contentValues.put(COL_5,LEFT_EYE_X);
        contentValues.put(COL_6,LEFT_EYE_Y);

        contentValues.put(COL_7,RIGHT_EYE_X);
        contentValues.put(COL_8,RIGHT_EYE_Y);

        contentValues.put(COL_9,LEFT_EAR_X);
        contentValues.put(COL_10,LEFT_EAR_Y);

        contentValues.put(COL_11,RIGHT_EAR_X);
        contentValues.put(COL_12,RIGHT_EAR_Y);

        contentValues.put(COL_13,LEFT_SHOULDER_X);
        contentValues.put(COL_14,LEFT_SHOULDER_Y);

        contentValues.put(COL_15,RIGHT_SHOULDER_X);
        contentValues.put(COL_16,RIGHT_SHOULDER_Y);

        contentValues.put(COL_17,LEFT_ELBOW_X);
        contentValues.put(COL_18,LEFT_ELBOW_Y);

        contentValues.put(COL_19, RIGHT_ELBOW_X);
        contentValues.put(COL_20, RIGHT_ELBOW_Y);

        contentValues.put(COL_21,LEFT_WRIST_X);
        contentValues.put(COL_22,LEFT_WRIST_Y);


        contentValues.put(COL_23,RIGHT_WRIST_X);
        contentValues.put(COL_24,RIGHT_WRIST_Y);


        contentValues.put(COL_25,LEFT_HIP_X);
        contentValues.put(COL_26,LEFT_HIP_Y);


        contentValues.put(COL_27,RIGHT_HIP_X);
        contentValues.put(COL_28,RIGHT_HIP_Y);

        contentValues.put(COL_29,LEFT_KNEE_X);
        contentValues.put(COL_30,LEFT_KNEE_Y);

        contentValues.put(COL_31,RIGHT_KNEE_X);
        contentValues.put(COL_32,RIGHT_KNEE_Y);

        contentValues.put(COL_33,LEFT_ANKLE_X);
        contentValues.put(COL_34,LEFT_ANKLE_Y);

        contentValues.put(COL_35,RIGHT_ANKLE_X);
        contentValues.put(COL_36,RIGHT_ANKLE_Y);

        contentValues.put(COL_37,score);




        // insert row
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result==-1){
            return false;
        }
        else{
            Log.d("jjj",contentValues.toString());
            return true;
        }


    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData( int id,String clas, float NOSE_X,float NOSE_Y, float LEFT_EYE_X,float LEFT_EYE_Y, float RIGHT_EYE_X,float RIGHT_EYE_Y, float LEFT_EAR_X,float LEFT_EAR_Y, float RIGHT_EAR_X,float RIGHT_EAR_Y,
                               float LEFT_SHOULDER_X,float LEFT_SHOULDER_Y, float RIGHT_SHOULDER_X,float RIGHT_SHOULDER_Y, float LEFT_ELBOW_X,float LEFT_ELBOW_Y, float RIGHT_ELBOW_X,float RIGHT_ELBOW_Y, float LEFT_WRIST_X,float LEFT_WRIST_Y, float RIGHT_WRIST_X,float RIGHT_WRIST_Y,
                               float LEFT_HIP_X,float LEFT_HIP_Y, float RIGHT_HIP_X,float RIGHT_HIP_Y, float LEFT_KNEE_X,float LEFT_KNEE_Y, float RIGHT_KNEE_X,float RIGHT_KNEE_Y, float LEFT_ANKLE_X,float LEFT_ANKLE_Y, float RIGHT_ANKLE_X,float RIGHT_ANKLE_Y, float score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        contentValues.put(COL_1,id);
        contentValues.put(COL_2, clas);
        contentValues.put(COL_3 ,NOSE_X);
        contentValues.put(COL_4 ,NOSE_Y);

        contentValues.put(COL_5,LEFT_EYE_X);
        contentValues.put(COL_6,LEFT_EYE_Y);

        contentValues.put(COL_7,RIGHT_EYE_X);
        contentValues.put(COL_8,RIGHT_EYE_Y);

        contentValues.put(COL_9,LEFT_EAR_X);
        contentValues.put(COL_10,LEFT_EAR_Y);

        contentValues.put(COL_11,RIGHT_EAR_X);
        contentValues.put(COL_12,RIGHT_EAR_Y);

        contentValues.put(COL_13,LEFT_SHOULDER_X);
        contentValues.put(COL_14,LEFT_SHOULDER_Y);

        contentValues.put(COL_15,RIGHT_SHOULDER_X);
        contentValues.put(COL_16,RIGHT_SHOULDER_Y);

        contentValues.put(COL_19,LEFT_ELBOW_X);
        contentValues.put(COL_20,LEFT_ELBOW_Y);

        contentValues.put(COL_21, RIGHT_ELBOW_X);
        contentValues.put(COL_22, RIGHT_ELBOW_Y);

        contentValues.put(COL_23,LEFT_WRIST_X);
        contentValues.put(COL_24,LEFT_WRIST_Y);


        contentValues.put(COL_25,RIGHT_WRIST_X);
        contentValues.put(COL_26,RIGHT_WRIST_Y);


        contentValues.put(COL_27,LEFT_HIP_X);
        contentValues.put(COL_28,LEFT_HIP_Y);


        contentValues.put(COL_29,RIGHT_HIP_X);
        contentValues.put(COL_30,RIGHT_HIP_Y);

        // updating row
         db.update(TABLE_NAME,contentValues,"id=?",new String[]{String.valueOf(id)});
         return true;
    }

//    public float getNotesCount() {
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

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // db.delete(TABLE_NAME,null,null);
        //db.execSQL("delete * from"+ TABLE_NAME);
//        db.execSQL("TRUNCATE table" + TABLE_NAME);
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();

    }

    public void exportDB() {
        SQLiteDatabase db = this.getWritableDatabase();

        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "data.csv");
        try
        {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            Cursor curCSV = db.rawQuery("SELECT * FROM contacts",null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to exprort
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx)
        {
            Log.e("DataBaseHelper", sqlEx.getMessage(), sqlEx);
        }
    }
}