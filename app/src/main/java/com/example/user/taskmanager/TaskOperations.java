package com.example.user.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 6/20/2016.
 */
public class TaskOperations {
    //Declaring dbHelper class
    private DataBaseWrapper dbHelper;
    // Database fields
    private String[] STUDENT_TABLE_COLUMNS = { DataBaseWrapper.TASK_ID, DataBaseWrapper.TASK_NAME, DataBaseWrapper.TASK_DESC, DataBaseWrapper.TASK_CDATETIME, DataBaseWrapper.TASK_UDATETIME, DataBaseWrapper.TASK_DDATETIME , DataBaseWrapper.TASK_STATUS };
    //declaring sql lite database
    private SQLiteDatabase database;
    //constructor for taskoperation class
    public TaskOperations(Context context) {
        dbHelper = new DataBaseWrapper(context);
    }
    //getting a writable database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    //closing dbhelper class
    public void close() {
        dbHelper.close();
    }
    //Add task DB operation
    public Task addTask(String task, String desc, String ddatetime) {
        //value object to hold student values
        ContentValues values = new ContentValues();
        //put first name, last name and mark in  value object
        values.put(DataBaseWrapper.TASK_NAME, task);
        values.put(DataBaseWrapper.TASK_DESC, desc);
        values.put(DataBaseWrapper.TASK_DDATETIME, ddatetime);
        //do and to get result of database insert operation
        long taskId = database.insert(DataBaseWrapper.TASK, null, values);

        // now that the student is created return it ...
        Cursor cursor = database.query(DataBaseWrapper.TASK,
                STUDENT_TABLE_COLUMNS, DataBaseWrapper.TASK_ID + " = "
                        + taskId, null, null, null, null);

        cursor.moveToFirst();
        //parse cursor object to get student object
        Task tasks = parseTask(cursor);
        cursor.close();
        return tasks;
    }
    //update task operation
    public int updateTask(String task, String desc, String ddatetime, Integer tid, Integer status) {
        //value object to hold task values
        ContentValues values = new ContentValues();
        //put first name, last name and mark in  value object
        values.put(DataBaseWrapper.TASK_NAME, task);
        values.put(DataBaseWrapper.TASK_DESC, desc);
        values.put(DataBaseWrapper.TASK_DDATETIME, ddatetime);
        values.put(DataBaseWrapper.TASK_STATUS, status);
        //making an array to send update condition
        String[] upArr = new String[1];
        //storing student id in update array
        upArr[0] =  Integer.toString(tid);
        //query update and store result in a variable
        int upStat = database.update(DataBaseWrapper.TASK, values, "_id=?",upArr);
        return upStat;

    }
    //Delete task operation
    public void deleteTask(Task task) {
        //get ID form task
        long id = task.getId();
        //calling delete operation
        database.delete(DataBaseWrapper.TASK, DataBaseWrapper.TASK_ID
                + " = " + id, null);
    }
    //Delete task based on ID
    public int deleteTaskID(Integer id) {

        //calling delete operation and return result
        int dqry= database.delete(DataBaseWrapper.TASK, DataBaseWrapper.TASK_ID
                + " = " + id, null);
        return dqry;
    }
    //Get task list
    public List getAllTasks() {
        //array list to store all task
        List tasks = new ArrayList();
        //cursor object to get all task list
        Cursor cursor = database.query(DataBaseWrapper.TASK,
                STUDENT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();

        //iterating through cursor to get store task in arraylist
        while (!cursor.isAfterLast()) {
            Task task = parseTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }

        cursor.close();
        return tasks;
    }
    //Parse cursor to get Task
    private Task parseTask(Cursor cursor) {
        //define a new task object
        Task task = new Task();
        task.setId((cursor.getInt(0)));
        task.setTask(cursor.getString(1));
        task.setDesc(cursor.getString(2));
        task.setCdatetime(cursor.getString(3));
        task.setUdatetime(cursor.getString(4));
        task.setDdatetime(cursor.getString(5));
        task.setStatus(cursor.getInt(6));
        return task;
    }
}
