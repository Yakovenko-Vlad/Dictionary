package com.example.vyakovenko.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vyakovenko.dictionary.DB.DBAdapter;
import com.example.vyakovenko.dictionary.DB.DBHelper;
import com.example.vyakovenko.dictionary.activities.MainActivity;
import com.example.vyakovenko.dictionary.activities.ReviewWords;
import com.example.vyakovenko.dictionary.external_carriers.CSVAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MainTemplate extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public DBHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(getApplicationContext(), ReviewWords.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            /*DBAdapter dbAdapter = new DBAdapter(dbHelper);
            CSVAdapter csvAdapter = new CSVAdapter();
            try {
                csvAdapter.writeAllDataToFile(dbAdapter.getAllWordsFromDB());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }*/

            /*String filePath = getApplicationContext().getFilesDir().getPath().toString() + "/export.txt";
            File f = new File(filePath);
            if(!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
            String filePath = getApplicationContext().getFilesDir().getPath().toString() + "/export";
            File f = new File(filePath);
            f.mkdirs();
            File folder = new File(Environment.getExternalStorageDirectory() + "/AMJ");
            if (folder.exists() && folder.isDirectory()) {

                Toast.makeText(getApplicationContext(), "folder " + folder + " exists", Toast.LENGTH_LONG).show(); //##1
            }              else {
            Toast.makeText(getApplicationContext(), "folder " + folder + " does not exist", Toast.LENGTH_LONG).show(); //##2
            folder.mkdirs();

        }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
