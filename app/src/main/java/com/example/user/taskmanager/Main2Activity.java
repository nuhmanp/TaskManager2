package com.example.user.taskmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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


import java.util.Calendar;


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

        switch (view.getId()) {
            //add button onclick event handling
            case R.id.addButton:
                String dtime = editText3.getText().toString()+" "+editText4.getText().toString()+":00";
                Task task = taskDBoperation.addTask(editText.getText().toString(),editText2.getText().toString(),dtime);
                Toast.makeText(getApplicationContext(),
                        "Task Data added"+task.getTask(), Toast.LENGTH_LONG).show();
                break;
            case R.id.comButton:
                break;
        }




    }

}
