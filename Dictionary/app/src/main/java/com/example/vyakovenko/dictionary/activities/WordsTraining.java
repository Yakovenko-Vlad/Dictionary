package com.example.vyakovenko.dictionary.activities;

import android.content.Intent;
import android.graphics.Color;
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

public class WordsTraining extends MainTemplate implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4;
    TextView trainWord;
    DBAdapter dbAdapter;
    int maxID;
    String[] trainWordText;

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

        dbAdapter = new DBAdapter(dbHelper);
        maxID = dbAdapter.getWordsCount();

        dbAdapter = new DBAdapter(dbHelper);
        trainWord = (TextView) findViewById(R.id.trainWord);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        fillElementsWithData(getRandTestWords());
    }

    public void fillElementsWithData(ArrayList<String> data) {
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
        ArrayList<String> data = new ArrayList<>();
        getWords();
        data.add(trainWordText[1]);
        for (int i = 0; i < 3; i++) {
            String word = dbAdapter.selectWordFromDB(getRandIndex())[1];
            if (!word.equals(trainWordText[1])) data.add(word);
            else data.add(dbAdapter.selectWordFromDB(getRandIndex())[1]);
        }
        return data;
    }

    public void getWords() {
        int rand = getRandIndex();
        trainWordText = dbAdapter.selectWordFromDB(rand);
    }

    public void test(Button btn, String mainText) {

            if (verifyResult(mainText, String.valueOf(btn.getText()))) {
                btn.setBackgroundColor(Color.GREEN);
            } else {
                btn.setBackgroundColor(Color.RED);
            }
        finish();
        startActivity(new Intent(this, WordsTraining.class));
        overridePendingTransition(0, 0);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fillElementsWithData(getRandTestWords());

    }

    public boolean verifyResult(String mainText, String selectedText) {
        return mainText.equals(selectedText);
    }

    @Override
    public void onClick(View view) {
        String mainText = (String) trainWord.getText();
        switch (view.getId()) {

            case R.id.btn1:
                test(btn1, trainWordText[1]);
                break;

            case R.id.btn2:
                test(btn2, trainWordText[1]);
                break;

            case R.id.btn3:
                test(btn3, trainWordText[1]);
                break;

            case R.id.btn4:
                test(btn4, trainWordText[1]);
                break;
        }
    }
}
