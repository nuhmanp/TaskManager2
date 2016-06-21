package com.example.user.taskmanager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 6/20/2016.
 */
public class Task {
    private int id;
    private String task;
    private String desc;
    private String cdatetime;
    private String udatetime;
    private String ddatetime;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCdatetime() {
        return cdatetime;
    }

    public void setCdatetime(String cdatetime) {
        this.cdatetime = cdatetime;
    }

    public String getUdatetime() {
        return udatetime;
    }

    public void setUdatetime(String udatetime) {
        this.udatetime = udatetime;
    }

    public String getDdatetime() {
        return ddatetime;
    }

    public void setDdatetime(String ddatetime) {
        this.ddatetime = ddatetime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /*private String getDateTime(String dtime) {
        String ret="";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = (Date)dateFormat.parse(dtime);
            ret=dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;

    }*/
}
