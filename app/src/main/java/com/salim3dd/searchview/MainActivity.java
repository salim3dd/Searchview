package com.salim3dd.searchview;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<String> listIndex = new ArrayList<String>();
    ArrayList<String> listIndex_Search = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        String[] itme = getResources().getStringArray(R.array.index);

        for (String i : itme) {
            listIndex.add(i);
        }
        All_Names();

    }

    public void All_Names() {
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listIndex);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_searchable));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.All_Names) {
            All_Names();
            return true;
        }
        if (id == R.id.Close) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        search(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        search(newText);
        return false;
    }

    public void search(String word) {
       // if (word.length() > 1) {
        listIndex_Search.clear();
        for (int i = 0; i < listIndex.size(); i++) {
            String name = listIndex.get(i);
            if (name.contains(word)) {
                listIndex_Search.add(name);
            }
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listIndex_Search);
        listView.setAdapter(arrayAdapter);

       // }

    }
}
