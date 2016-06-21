package com.example.user.taskmanager;

/**
 * Created by nparamban2595 on 6/21/2016.
 */
public class Student{
    //Declaring studentid, name, first name, last name and mark
    private int id;
    private String name;
    private String fname;
    private String lname;
    private Integer mark;

    //getter for student ID
    public long getId() {
        return id;
    }
    //setter for student ID
    public void setId(int id) {
        this.id = id;
    }
    //getter for student First Name
    public String getFname() {
        return this.fname;
    }
    //setter for student First Name
    public void setFname(String name) {
        this.fname = name;
    }
    //getter for student Last Name
    public String getLname() {
        return this.lname;
    }
    //setter for student Last Name
    public void setLname(String name) {
        this.lname = name;
    }
    //getter for student Mark
    public Integer getMark() {
        return this.mark;
    }
    //setter for student Mark
    public void setMark(Integer mark) {
        this.mark = mark;
    }

}