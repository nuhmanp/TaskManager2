package com.example.user.taskmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 6/20/2016.
 */
public class DataBaseWrapper extends SQLiteOpenHelper {
    //Defining database name, fields and versions
    public static final String TASK = "Task";
    public static final String TASK_ID = "_id";
    public static final String TASK_NAME = "_task";
    public static final String TASK_DESC = "_desc";
    public static final String TASK_CDATETIME = "_CDATETIME";
    public static final String TASK_UDATETIME = "_UDATETIME";
    public static final String TASK_DDATETIME = "_DDATETIME";
    private static final String DATABASE_NAME = "Tasks.db";
    private static final int DATABASE_VERSION = 1;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "create table " + TASK
            + "(" + TASK_ID + " integer primary key autoincrement, "
            + TASK_NAME + " text not null,"
            + TASK_DESC + " text not null,"
            + TASK_CDATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
            + TASK_UDATETIME + " DATETIME ,"
            + TASK_DDATETIME + " DATETIME );";
    //constructor for DB Wrapper
    public DataBaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //DB create
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }
    //DB upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you should do some logging in here
        // ..

        db.execSQL("DROP TABLE IF EXISTS " + TASK);
        onCreate(db);
    }

}
