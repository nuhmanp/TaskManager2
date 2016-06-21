package com.example.user.taskmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nparamban2595 on 6/21/2016.
 */
public class DataBaseWrapper2 extends SQLiteOpenHelper {
    //Defining database name, fields and versions
    public static final String STUDENTS = "Students";
    public static final String STUDENT_ID = "_id";
    public static final String STUDENT_FNAME = "_fname";
    public static final String STUDENT_LNAME = "_lname";
    public static final String STUDENT_MARK = "_mark";
    private static final String DATABASE_NAME = "Students.db";
    private static final int DATABASE_VERSION = 4;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "create table " + STUDENTS
            + "(" + STUDENT_ID + " integer primary key autoincrement, "
            + STUDENT_FNAME + " text not null,"
            + STUDENT_LNAME + " text not null,"
            + STUDENT_MARK + " integer);";
    //constructor for DB Wrapper
    public DataBaseWrapper2(Context context) {
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

        db.execSQL("DROP TABLE IF EXISTS " + STUDENTS);
        onCreate(db);
    }

}