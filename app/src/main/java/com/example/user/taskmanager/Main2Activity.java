package com.example.user.taskmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.text.format.DateFormat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static EditText editText3;
    static EditText editText4;
    TaskOperations taskDBoperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        editText3 = (EditText)findViewById(R.id.editText3);

        editText3.setEnabled(false);
        editText4 = (EditText)findViewById(R.id.editText4);

        editText4.setEnabled(false);
        //Defining new Student DB operation object
        taskDBoperation = new TaskOperations(this);
        //opening student db operation to get getWritableDatabase
        taskDBoperation.open();
        setTitle("Add a new Task");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the Home action
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, Main22Activity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, Main3Activity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override

        public Dialog onCreateDialog(Bundle savedInstanceState){

            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);

            int month = c.get(Calendar.MONTH);

            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);

        }



        @Override

        public void onDateSet(DatePicker view, int year, int month, int day) {

            Toast.makeText(getActivity(), "Date selected:"  + day + "/" + (month+1) + "/" + year, Toast.LENGTH_SHORT).show();

            editText3.setText(year + "/" + (month+1) + "/" + day);


        }

    }
    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override

        public Dialog onCreateDialog(Bundle savedInstanceState) {

// Use the current time as the default values for the picker

            final Calendar c = Calendar.getInstance();

            int hour = c.get(Calendar.HOUR_OF_DAY);

            int minute = c.get(Calendar.MINUTE);

// Create a new instance of TimePickerDialog and return it

            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));

        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Toast.makeText(getActivity(), "Time selected is: " + hourOfDay + " : " + minute, Toast.LENGTH_SHORT).show();

            editText4.setText(hourOfDay + ":" + minute);


        }

    }
    public void showTime(View view){



        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");

    }

    public void showDate(View view){

        DialogFragment newFragment = new DatePickerFragment();

        newFragment.show(getFragmentManager(), "datePicker");



    }
    public void addAction(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        EditText editText4 = (EditText) findViewById(R.id.editText4);
        Task task;
        String dtime;
        Intent intent = new Intent(this, MainActivity.class);

        switch (view.getId()) {
            //add button onclick event handling
            case R.id.addButton:
                dtime = editText3.getText().toString()+" "+editText4.getText().toString()+":00";
                task = taskDBoperation.addTask(editText.getText().toString(),editText2.getText().toString(),dtime);
                Toast.makeText(getApplicationContext(),
                        "Task Data added"+task.getTask(), Toast.LENGTH_LONG).show();

                startActivity(intent);
                finish();
                break;
            case R.id.comButton:
                dtime = editText3.getText().toString()+" "+editText4.getText().toString()+":00";
                java.text.DateFormat dateFormats = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                try{
                    Date d = dateFormats.parse(dtime);
                    Date d2 = new Date();
                    if(d.before(d2)){
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
                        mBuilder.setSmallIcon(R.drawable.ic_stat_t);
                        //insult string array
                        String[] insult = new String[] { "a walking vomit",
                                "Time will run on you",
                                "You swine.",
                                "You worthless bag of mud",
                                "The only thing worse than your logic is your manners",
                                "Sort of like parking in a handicap space",
                                " you may not hear from me again for a while.",
                                "double-talking idiot"
                        };

                        int rnd = new Random().nextInt(insult.length);
                        mBuilder.setContentTitle(insult[rnd]);
                        mBuilder.setContentText("what you are doing right now.....???");
                        Intent resultIntent = new Intent(this, MainActivity.class);
                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                        stackBuilder.addParentStack(Main6Activity.class);

// Adds the Intent that starts the Activity to the top of the stack
                        stackBuilder.addNextIntent(resultIntent);
                        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                        mBuilder.setContentIntent(resultPendingIntent);
                        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.
                        int randomPIN = (int)(Math.random()*9000)+1000;
                        mNotificationManager.notify(randomPIN, mBuilder.build());
                        Toast.makeText(getApplicationContext(),
                                "You completed task lately", Toast.LENGTH_LONG).show();

                    }
                }catch(Exception e){

                }

                //Calendar cal = Calendar.getInstance();
                //dateFormats.format(cal.getTime());
                task = taskDBoperation.addTask(editText.getText().toString(),editText2.getText().toString(),dtime);
                taskDBoperation.completeTask(task.getId(),0);
                Toast.makeText(getApplicationContext(),
                        "Task Data added and completed"+task.getTask(), Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
                break;
        }




    }
    private String getDateTime(String dtime) {
        String ret="";
        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = (Date)dateFormat.parse(dtime);
            ret=dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;

    }

}
