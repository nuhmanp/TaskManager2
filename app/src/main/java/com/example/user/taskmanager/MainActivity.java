package com.example.user.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView listView;
    TaskOperations taskDBoperation;
    ArrayList<MainActivity.Element> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        mItems = new ArrayList<MainActivity.Element>();
        taskDBoperation = new TaskOperations(this);
        //opening student db operation to get getWritableDatabase
        taskDBoperation.open();
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);


        List value = taskDBoperation.getAllTasks();

        Task task;
        //iterating through student object list
        for(Object objt:value){
            //converting object to student object
            String stdDet = "";
            task = (Task)objt;
            //getting ID, first name, last name and mark
            //stdDet = stdDet+"Task ID: "+task.getId()+"\n";
            stdDet = stdDet+task.getTask()+"\n";
            stdDet = stdDet+task.getDesc()+"\n";
            stdDet = stdDet+task.getDdatetime()+"\n";
            //stdDet = stdDet+"Last Name: "+student.getLname()+"\n";
            //stdDet = stdDet+"Mark: "+student.getMark()+"\n\n";
            //tlist.add(stdDet);
            mItems.add(new Element(stdDet, task.getId()));

        }
        //String
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, mItems);


        // Assign adapter to ListView
        listView.setAdapter(adapter);
        // ListView Item Click Listener
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        "ID is " + mItems.get(position).getId(),
                        Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(this, Main4Activity.class);
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                intent.putExtra("intVariableName", mItems.get(position).getId()); //where v is button that is cliked, you will find it as a parameter to onClick method
                startActivity(intent);
            }
        });
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
        getMenuInflater().inflate(R.menu.main, menu);
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
            // Handle the add task action
            Intent intent = new Intent(this, Main2Activity.class);
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
    public static class Element {
        private String mText;
        private int mId;

        public Element(String text, int id) {
            mText = text;
            mId = id;
        }

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            mId = id;
        }

        public String getmText() {
            return mText;
        }

        public void setmText(String mText) {
            this.mText = mText;
        }

        @Override
        public String toString() {
            return mText;
        }
    }
}
