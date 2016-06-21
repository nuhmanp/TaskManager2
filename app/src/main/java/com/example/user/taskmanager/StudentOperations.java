package com.example.user.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nparamban2595 on 6/21/2016.
 */
public class StudentOperations {

    //Declaring dbHelper class
    private DataBaseWrapper2 dbHelper;
    // Database fields
    private String[] STUDENT_TABLE_COLUMNS = { DataBaseWrapper2.STUDENT_ID, DataBaseWrapper2.STUDENT_FNAME, DataBaseWrapper2.STUDENT_LNAME, DataBaseWrapper2.STUDENT_MARK };
    //declaring sql lite database
    private SQLiteDatabase database;

    //constructor for studentoperation class
    public StudentOperations(Context context) {
        dbHelper = new DataBaseWrapper2(context);
    }
    //getting a writable database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    //closing dbhelper class
    public void close() {
        dbHelper.close();
    }
    //Add student DB operation
    public Student addStudent(String fname, String lname, Integer mark) {
        //value object to hold student values
        ContentValues values = new ContentValues();
        //put first name, last name and mark in  value object
        values.put(DataBaseWrapper2.STUDENT_FNAME, fname);
        values.put(DataBaseWrapper2.STUDENT_LNAME, lname);
        values.put(DataBaseWrapper2.STUDENT_MARK, mark);
        //do and to get result of database insert operation
        long studId = database.insert(DataBaseWrapper2.STUDENTS, null, values);

        // now that the student is created return it ...
        Cursor cursor = database.query(DataBaseWrapper2.STUDENTS,
                STUDENT_TABLE_COLUMNS, DataBaseWrapper2.STUDENT_ID + " = "
                        + studId, null, null, null, null);

        cursor.moveToFirst();
        //parse cursor object to get student object
        Student student = parseStudent(cursor);
        cursor.close();
        return student;
    }
    //update student operation
    public int updateStudent(String fname, String lname, Integer mark, Integer sid) {
        //value object to hold student values
        ContentValues values = new ContentValues();
        //put first name, last name and mark in  value object
        values.put(DataBaseWrapper2.STUDENT_FNAME, fname);
        values.put(DataBaseWrapper2.STUDENT_LNAME, lname);
        values.put(DataBaseWrapper2.STUDENT_MARK, mark);
        //making an array to send update condition
        String[] upArr = new String[1];
        //storing student id in update array
        upArr[0] =  Integer.toString(sid);
        //query update and store result in a variable
        int upStat = database.update(DataBaseWrapper2.STUDENTS, values, "_id=?",upArr);
        return upStat;

    }
    //Delete student operation
    public void deleteStudent(Student student) {
        //get ID form student
        long id = student.getId();
        //calling delete operation
        database.delete(DataBaseWrapper2.STUDENTS, DataBaseWrapper2.STUDENT_ID
                + " = " + id, null);
    }
    //Delete student based on ID
    public int deleteStdID(Integer id) {

        //calling delete operation and return result
        int dqry= database.delete(DataBaseWrapper2.STUDENTS, DataBaseWrapper2.STUDENT_ID
                + " = " + id, null);
        return dqry;
    }
    //Get student list
    public List getAllStudents() {
        //array list to store all student
        List students = new ArrayList();
        //cursor object to get all student list
        Cursor cursor = database.query(DataBaseWrapper2.STUDENTS,
                STUDENT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();

        //iterating through cursor to get store student in arraylist
        while (!cursor.isAfterLast()) {
            Student student = parseStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }

        cursor.close();
        return students;
    }
    //Parse cursor to get Student
    private Student parseStudent(Cursor cursor) {
        //define a new student object
        Student student = new Student();
        student.setId((cursor.getInt(0)));
        student.setFname(cursor.getString(1));
        student.setLname(cursor.getString(2));
        student.setMark((cursor.getInt(3)));
        return student;
    }
}
