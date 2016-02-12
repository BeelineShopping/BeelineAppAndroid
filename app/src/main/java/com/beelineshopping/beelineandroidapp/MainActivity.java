package com.beelineshopping.beelineandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Context context;

    String testStr;
    BeelineDbHelper mDbHelper = new BeelineDbHelper(this);
    DatabaseUtils dbUtils;

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

        context = getApplicationContext();

        //Database code----------------------------------------------------------------------------
// Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(BeelineContract.StoreDetails.COLUMN_NAME_ENTRY_ID, "1");
        values.put(BeelineContract.StoreDetails.COLUMN_NAME_CITY, "Redmond");

// Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                BeelineContract.StoreDetails.TABLE_NAME,
                BeelineContract.FoodDetails.COLUMN_NAME_NULLABLE,
                values);



        db = mDbHelper.getReadableDatabase();

// Read data
        String[] projection = {
                BeelineContract.StoreDetails._ID,
                BeelineContract.StoreDetails.COLUMN_NAME_ENTRY_ID,
                BeelineContract.StoreDetails.COLUMN_NAME_CITY
        };

//// How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FoodContract.FoodDetails.COLUMN_NAME_UPDATED + " DESC";

        Cursor c = db.query(
                BeelineContract.FoodDetails.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        String crStr = dbUtils.dumpCursorToString(c);//tool for easily analyzing your data
        if (c.moveToFirst()) { //data?
            String title = c.getString(c.getColumnIndex("title"));//show that data is accessible
            System.out.print("title");
        }
        db.delete(BeelineContract.FoodDetails.TABLE_NAME,null,null);//delete all data
        c.close();//prevent cursor leaks
        System.out.print("finish database");
        //---------------------------------------------------------------------------------------


        myAsyncTask task_my = new myAsyncTask();
        task_my.execute("http://beeline-db.herokuapp.com/api/v1/stores");
        try {
            String response = task_my.get();
            if (!response.equals("ERROR")) {
                JSONObject json = new JSONObject(response);
                JSONArray data = json.getJSONArray("aisles");
                System.out.print("something");
            }
        }  catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }

        //sendMessage(this.findViewById(android.R.id.content));
    }

    /** Called when the user clicks the List View button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ShoppingListActivity.class);
        startActivity(intent);
    }

//    private void preGetTest(){
//        GetRequestLoadTask getTest = new GetRequestLoadTask();

//        try {
//            getTest.run("http://beeline-db.herokuapp.com/alive");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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





}
