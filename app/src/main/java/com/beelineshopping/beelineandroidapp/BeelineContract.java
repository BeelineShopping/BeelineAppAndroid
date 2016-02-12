package com.beelineshopping.beelineandroidapp;

import android.provider.BaseColumns;

/**
 * Created by Shelby on 2/12/2016.
 */
public class BeelineContract {
    public BeelineContract(){};

    //Inner class that defines the table contents
    public static abstract class StoreDetails implements BaseColumns {
        public static final String TABLE_NAME = "StoreDetails";
        public static final String COLUMN_NAME_ENTRY_ID = "432";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_NULLABLE = null;
    }

    public static abstract class FoodDetails implements BaseColumns {
        public static final String TABLE_NAME = "FoodDetails";
        public static final String COLUMN_NAME_ENTRY_ID = "food_id";
        public static final String COLUMN_NAME_AISLE = "aisle_number";
        public static final String COLUMN_NAME_SECTION = "section";
        public static final String COLUMN_NAME_NULLABLE = null;
    }
}
