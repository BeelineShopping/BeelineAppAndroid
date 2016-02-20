package com.beelineshopping.beelineandroidapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.beelineshopping.beelineandroidapp.tasks.Ingredient;

import java.util.ArrayList;

public class Aisles extends AppCompatActivity {

    // Hold all the ingredient objects
    private ArrayList<Ingredient> ingredients;

    // Variables to bind to the three listViews
    private ListView sectionOneListView;
    private ListView sectionTwoListView;
    private ListView sectionThreeListView;

    // Hold the lists that are binded to the listView
    private ArrayAdapter<String> sectionOneListAdapter;
    private ArrayAdapter<String> sectionTwoListAdapter;
    private ArrayAdapter<String> sectionThreeListAdapter;

    // Need to create String objects to hold the ingredient names
    private ArrayList<String> sectionOneItems;
    private ArrayList<String> sectionTwoItems;
    private ArrayList<String> sectionThreeItems;

    // TextView for the top of the screen
    private TextView title;

    // Button for the button
    private Button nextButton;

    private String[] aisleOrder;
    private int currentAisleIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aisles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ingredients = new ArrayList<Ingredient>();
        fillList();

         aisleOrder = new String[] {"Aisle 1", "Aisle 2", "Aisle 3"};

        sectionOneListView = (ListView)findViewById(R.id.sectionOne);
        sectionTwoListView = (ListView)findViewById(R.id.sectionTwo);
        sectionThreeListView = (ListView)findViewById(R.id.sectionThree);

        currentAisleIndex = 0;

        // Initial setting up of aisle items
        updateAisleItems();


        View.OnClickListener nextAisleListener = new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                currentAisleIndex++;

                // If we have reached the last aisle
                if (currentAisleIndex >= aisleOrder.length - 1)
                {
                    nextButton = (Button) findViewById(R.id.nextButton);
                    nextButton.setText("Done");
                }

                // If there are no more aisles go back to the parent activity
                if (currentAisleIndex >= aisleOrder.length)
                {
                    finish();
                }
                else
                {
                    updateAisleItems();
                }
            }
        };

        final Button nextButton = (Button) findViewById(R.id.nextButton);

        nextButton.setOnClickListener(nextAisleListener);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void fillList()
    {
        ingredients.add(new Ingredient("Milk", "Aisle 1", "Section 1"));
        ingredients.add(new Ingredient("Eggs",  "Aisle 1", "Section 2"));
        ingredients.add(new Ingredient("Cheese", "Aisle 1", "Section 3"));
        ingredients.add(new Ingredient("Celery", "Aisle 2", "Section 1"));
        ingredients.add(new Ingredient("Tomato", "Aisle 2", "Section 1"));
        ingredients.add(new Ingredient("Peanuts", "Aisle 3", "Section 1"));
        ingredients.add(new Ingredient("Chips", "Aisle 3", "Section 1"));
        ingredients.add(new Ingredient("Potatoes", "Aisle 3", "Section 3"));
        ingredients.add(new Ingredient("Salt", "Aisle 3", "Section 3"));
    }

    public void updateAisleItems()
    {
        sectionOneItems = new ArrayList<String>();
        sectionTwoItems = new ArrayList<String>();
        sectionThreeItems = new ArrayList<String>();

        for (int i = 0; i < ingredients.size(); i++)
        {   // Check the aisle
            if (ingredients.get(i).getAisle() == aisleOrder[currentAisleIndex]) {
                //Check the section
                if (ingredients.get(i).getSection() == "Section 1") {
                    sectionOneItems.add(ingredients.get(i).getName());
                }
                else if (ingredients.get(i).getSection() == "Section 2") {
                    sectionTwoItems.add(ingredients.get(i).getName());
                }
                else if (ingredients.get(i).getSection() == "Section 3") {
                    sectionThreeItems.add(ingredients.get(i).getName());
                }
            }
        }

        // Update the ListAdapters
        sectionOneListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, sectionOneItems);
        sectionTwoListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, sectionTwoItems);
        sectionThreeListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, sectionThreeItems);

        // Update the ListViews
        sectionOneListView.setAdapter(sectionOneListAdapter);
        sectionTwoListView.setAdapter(sectionTwoListAdapter);
        sectionThreeListView.setAdapter(sectionThreeListAdapter);

        // Make ListViews grey if a section has no items
        if (sectionOneItems.size() == 0)
            sectionOneListView.setBackgroundColor(Color.parseColor("#EDEDED"));
        else
            sectionOneListView.setBackgroundColor(Color.WHITE);

        if (sectionTwoItems.size() == 0)
            sectionTwoListView.setBackgroundColor(Color.parseColor("#EDEDED"));
        else
            sectionTwoListView.setBackgroundColor(Color.WHITE);

        if (sectionThreeItems.size() == 0)
            sectionThreeListView.setBackgroundColor(Color.parseColor("#EDEDED"));
        else
            sectionThreeListView.setBackgroundColor(Color.WHITE);

            // Update the text at the top of the screen
        title = (TextView) findViewById(R.id.title);

        // Set the text to the next Aisle
        title.setText(aisleOrder[currentAisleIndex]);
    }

}
