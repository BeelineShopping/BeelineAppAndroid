package com.beelineshopping.beelineandroidapp.cursor_adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.beelineshopping.beelineandroidapp.R;

/**
 * Created by Shelby on 2/5/2016.
 */
public class ShopCursorAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;

    Context mContext;
    public ShopCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

        cursorInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(R.layout.row_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
