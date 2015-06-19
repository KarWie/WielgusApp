package com.wielgus.pamoapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DictionaryActivity extends ActionBarActivity {

    private EntriesDataSource datasource;
    int entriesNo = 0;
    EditText entryField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        datasource = new EntriesDataSource(this);
        Button saveButton = (Button) findViewById(R.id.save_button);
        Button clearButton = (Button) findViewById(R.id.clear_button);
        entryField = (EditText) findViewById(R.id.entry_field);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEntry = entryField.getText().toString();
                datasource.open();
                datasource.createEntry(getEntry);
                Toast.makeText(getBaseContext(), "Data inserted", Toast.LENGTH_LONG).show();
                entriesNo = datasource.getEntriesCount();
                TextView tv = (TextView) findViewById(R.id.items_count);
                tv.setText(String.valueOf(entriesNo));
                datasource.close();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datasource.open();
                datasource.clearEntries();
                TextView tv = (TextView) findViewById(R.id.items_count);
                tv.setText("0");
                Toast.makeText(getBaseContext(), "Data cleared", Toast.LENGTH_LONG).show();
                datasource.close();
            }
        });
        entriesNo = datasource.getEntriesCount();
        if(entriesNo > 0){
            TextView tv = (TextView) findViewById(R.id.items_count);
            tv.setText(String.valueOf(entriesNo));
        }
        else {
            TextView tv = (TextView) findViewById(R.id.items_count);
            tv.setText(String.valueOf(0));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dictionary, menu);
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
