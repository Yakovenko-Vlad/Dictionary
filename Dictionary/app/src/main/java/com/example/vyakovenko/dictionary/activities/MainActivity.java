package com.example.vyakovenko.dictionary.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vyakovenko.dictionary.DB.DBHelper;
import com.example.vyakovenko.dictionary.MainTemplate;
import com.example.vyakovenko.dictionary.R;

import java.util.Locale;

public class MainActivity extends MainTemplate {
    Button addNewWord;
    EditText engText, ukrText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        dbHelper = new DBHelper(this);

        engText = (EditText) findViewById(R.id.engText);
        ukrText = (EditText) findViewById(R.id.ukrText);
        addNewWord = (Button) findViewById(R.id.addNewWord);
        addNewWord.setOnClickListener(myButtonClickListener);
        engText.setOnLongClickListener(longClickListener);
        ukrText.setOnLongClickListener(longClickListener);
    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            String engWord = engText.getText().toString();
            String ukrWord = ukrText.getText().toString();
            String text = ukrWord.length() == 0 ? engWord : ukrWord;

            ClipboardManager clipboardManager = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Copied text", text);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(getApplicationContext(), "Copy + paste here " + text, Toast.LENGTH_SHORT).show();
            return false;
        }
    };

    View.OnClickListener myButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
            String lovaleStr = ims.getLocale();
            Locale locale = new Locale(lovaleStr);
            String cur = locale.getDisplayLanguage();
            System.out.println(cur);*/
            ContentValues cv = new ContentValues();

            String engWord = engText.getText().toString();
            String ukrWord = ukrText.getText().toString();

            if (engWord.length() > 0 && ukrWord.length() > 0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor, c;
                c = db.query("dictionary", null, null, null, null, null, "id");
                cursor = db.query("dictionary", new String[]{"id"}, "english = ? and ukrainian = ?", new String[]{engWord, ukrWord}, null, null, null);
                if ((cursor.getCount() == 0 && c.getCount() > 0) || c.getCount() == 0) {
                    cv.put("english", engWord);
                    cv.put("ukrainian", ukrWord);

                    // вставляем запись и получаем ее ID
                    long rowID = db.insert("dictionary", null, cv);
                    Toast.makeText(getApplicationContext(), "row inserted, ID = " + rowID, Toast.LENGTH_SHORT).show();

                    engText.setText(null);
                    ukrText.setText(null);
                } else {
                    Toast.makeText(getApplicationContext(), "Words already added", Toast.LENGTH_SHORT).show();
                    engText.setText(null);
                    ukrText.setText(null);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please, fill all required fields.", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
