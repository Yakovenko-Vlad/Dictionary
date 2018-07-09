package com.example.vyakovenko.dictionary.external_carriers;

import android.app.Activity;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.vyakovenko.dictionary.DB.DBAdapter;
import com.example.vyakovenko.dictionary.DB.DBHelper;
import com.example.vyakovenko.dictionary.activities.MainActivity;
import com.opencsv.CSVWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

import static android.content.Context.MODE_PRIVATE;

public class JSONadapter {
    private String folderName = "Dictionary";
    private String exportFile = folderName + "/exportDB.json";
    private String importFile = folderName + "/importDB.json";
    private Context context;

    public JSONadapter(Context context) {
        this.context = context;
    }

    public void writeAllDataToFile(ArrayList<String[]> wordsFromDB) {
        JSONObject jsonObject = new JSONObject();
        Writer output = null;
        createFolder();
        File expFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + exportFile);
        try {
            if (!expFile.exists()) {
                expFile.createNewFile();
                if (expFile.exists())
                    scanFile(expFile.getAbsolutePath());
            }
            for (String[] str : wordsFromDB) {
                try {
                    jsonObject.put(str[0], str[1]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            output = new BufferedWriter(new FileWriter(expFile));
            output.write(jsonObject.toString());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, "Dictionary exported to the file", Toast.LENGTH_SHORT).show();
    }

    public void readDataFromFile(DBHelper dbHelper) {
        File impFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + importFile);
        BufferedReader reader;
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String[]> importedData = new ArrayList<>();
        if (!impFile.exists())
            Log.i("TAG", "File is not exist");
        try {
            reader = new BufferedReader(new FileReader(impFile));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            JSONObject importedObject = new JSONObject(String.valueOf(stringBuilder));
            Iterator<String> keys = importedObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                importedData.add(new String[]{key, String.valueOf(importedObject.get(key))});
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, "Dictionary imported from the file to the data base", Toast.LENGTH_SHORT).show();
        DBAdapter dbAdapter = new DBAdapter(dbHelper);
        dbAdapter.insertWordsFromFileToDB(importedData);
    }

    public boolean createFolder() {
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folderName);
        if (!folder.exists()) {
            folder.mkdirs();
            if (!folder.exists()) {
                Toast.makeText(context, "Folder is not created!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        scanFile(folder.getAbsolutePath());
        return true;
    }

    private void scanFile(String path) {
        MediaScannerConnection.scanFile(context,
                new String[]{path}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("TAG", "Finished scanning " + path);
                    }
                });
    }
}
