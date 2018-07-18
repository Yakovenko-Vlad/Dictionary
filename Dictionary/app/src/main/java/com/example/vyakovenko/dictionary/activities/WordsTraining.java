package com.example.vyakovenko.dictionary.activities;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vyakovenko.dictionary.DB.DBAdapter;
import com.example.vyakovenko.dictionary.R;
import com.example.vyakovenko.dictionary.MainTemplate;

import java.util.ArrayList;

import static java.util.Collections.sort;

public class WordsTraining extends MainTemplate {

    Button btn1, btn2, btn3, btn4;
    TextView trainWord;
    DBAdapter dbAdapter;
    int maxID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_training);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        maxID = dbAdapter.getWordsCount();

        dbAdapter = new DBAdapter(dbHelper);
        trainWord = (TextView) findViewById(R.id.trainWord);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        fillElementsWithData(getRandTestWords(), getWords());
    }

    public void fillElementsWithData(ArrayList<String> data, String[] trainWordText) {
        trainWord.setText(trainWordText[0]);
        sort(data);
        btn1.setText(data.get(0));
        btn2.setText(data.get(1));
        btn3.setText(data.get(2));
        btn4.setText(data.get(3));
    }

    public int getRandIndex() {
        return (int) (Math.random() * maxID);
    }

    public ArrayList<String> getRandTestWords() {
        String[] trainWordText = getWords();
        ArrayList<String> data = new ArrayList<>();
        data.add(trainWordText[1]);
        for(int i = 0; i < 3; i++ ){
            String word = dbAdapter.selectWordFromDB(getRandIndex())[1];
            if(!word.equals(trainWordText[1])) data.add(word);
            else data.add(dbAdapter.selectWordFromDB(getRandIndex())[1]);
        }
        return data;
    }
    public String[] getWords() {
        int rand = getRandIndex();
        return dbAdapter.selectWordFromDB(rand);
    }
}
