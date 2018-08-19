package com.example.android.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.myapplication.data.PetContract;
import com.example.android.myapplication.data.PetContract.PetEntry;

/**
 * Created by devansh on 19/8/18.
 */

public class PetCursorAdapter extends CursorAdapter {

    public PetCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nameTextView = view.findViewById(R.id.name);
        TextView breedTextView = view.findViewById(R.id.breed);

        String name = cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME));
        String breed = cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED));

        nameTextView.setText(name);
        breedTextView.setText(breed);

    }
}
