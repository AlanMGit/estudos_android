package com.testematerialserachview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class MainActivity extends AppCompatActivity {


    private MaterialSearchView mSearchView;
    private Toolbar mToolbar;
    private ListView mListPeople;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        loadingList();

        confToolbar();

    }

    private void initViews() {
        mSearchView = (MaterialSearchView) findViewById(R.id.search_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mListPeople = (ListView) findViewById(R.id.list_people);

        mSearchView.setOnQueryTextListener(aoClicarFiltrar);
    }

    private void loadingList (){

        String[] list = {"José", "Maria", "Carla", "Adriano"};

        adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, list);

        mListPeople.setAdapter(adapter);
    }

    private void confToolbar() {
        setSupportActionBar(mToolbar);
    }

    private MaterialSearchView.OnQueryTextListener aoClicarFiltrar = new MaterialSearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            adapter.getFilter().filter(newText);
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);

        return true;
    }
}
