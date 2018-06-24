package com.example.vyakovenko.dictionary.activities;

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
import android.widget.ListView;

import com.example.vyakovenko.dictionary.DB.DBAdapter;
import com.example.vyakovenko.dictionary.MainTemplate;
import com.example.vyakovenko.dictionary.R;
import com.example.vyakovenko.dictionary.adapter.DictionaryListAdapter;

import java.util.ArrayList;

public class ReviewWords extends MainTemplate {
    DictionaryListAdapter dictionaryListAdapter;
    DBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_words);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        dbAdapter = new DBAdapter(dbHelper);
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        dictionaryListAdapter = new DictionaryListAdapter(this, dbAdapter.getAllWordsFromDB());
        lvMain.setClickable(false);
        lvMain.setAdapter(dictionaryListAdapter);
    }
}