package com.example.android.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.myapplication.data.PetContract.PetEntry;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        //Setup FAB to open EditorActivity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void insertDummyData() {
        ContentValues dummyValues = new ContentValues();
        dummyValues.put(PetEntry.COLUMN_PET_NAME, "Toto");
        dummyValues.put(PetEntry.COLUMN_PET_BREED, "Terrier");
        dummyValues.put(PetEntry.COLUMN_PET_GENDER, 1);
        dummyValues.put(PetEntry.COLUMN_PET_WEIGHT, 7);

        getContentResolver().insert(PetEntry.CONTENT_URI, dummyValues);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertDummyData();
                displayDatabaseInfo();
                return true;
            case R.id.action_delete_all_entries:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayDatabaseInfo() {

        Cursor cursor = getContentResolver().query(PetEntry.CONTENT_URI, null, null, null,
                null);

        try {
            TextView displayView = findViewById(R.id.text_view_pet);
            displayView.setText("Number of rows in pets database table: " + cursor.getCount());
            displayView.append("\n" + PetEntry._ID + " - " + PetEntry.COLUMN_PET_NAME + " - " + PetEntry.COLUMN_PET_BREED
                + " - " + PetEntry.COLUMN_PET_GENDER + " - " + PetEntry.COLUMN_PET_WEIGHT + "\n");

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(PetEntry._ID));
                String name = cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME));
                String breed = cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED));
                int gender = cursor.getInt(cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER));
                int weight = cursor.getInt(cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT));

                displayView.append(id + " - " + name + " - " + breed + " - " + gender + " - " + weight + "\n");
            }
        } finally {
            cursor.close();
        }
    }
}
