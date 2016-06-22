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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Main5Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static EditText editText7;
    static EditText editText8;
    EditText editText6;
    EditText editText5;
    TaskOperations taskDBoperation;
    int intValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
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
        //getting Task button
        editText6 = (EditText)findViewById(R.id.editText6);
        //getting Task description button
        editText5 = (EditText)findViewById(R.id.editText5);

        editText7 = (EditText)findViewById(R.id.editText7);

        editText7.setEnabled(false);
        editText8 = (EditText)findViewById(R.id.editText8);

        editText8.setEnabled(false);
        taskDBoperation = new TaskOperations(this);
        //opening student db operation to get getWritableDatabase
        taskDBoperation.open();
        setTitle("Update Task");
        Intent mIntent = getIntent();
        intValue = mIntent.getIntExtra("intVariableName", 0);
        if(intValue == 0){
            //if no ID recieved
            taskDBoperation.close();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();


        }else{

            Task task = taskDBoperation.getTask(intValue);

            editText6.setText(task.getTask());
            editText5.setText(task.getDesc());
            editText7.setText(task.getCdatetime());
            editText8.setText(task.getDdatetime());

        }
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
        getMenuInflater().inflate(R.menu.main5, menu);
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
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, Main22Activity.class);
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

            editText7.setText(year + "/" + (month+1) + "/" + day);


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

            editText8.setText(hourOfDay + ":" + minute);


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
    public void updateTask(View view){

        String dtime = editText7.getText().toString()+" "+editText8.getText().toString()+":00";
        taskDBoperation.updateTask(editText6.getText().toString(),editText5.getText().toString(),dtime,intValue);
        Toast.makeText(getApplicationContext(),
                "Task Data updated", Toast.LENGTH_LONG).show();

        taskDBoperation.close();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
