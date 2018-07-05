package com.example.vyakovenko.dictionary.external_carriers;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class CSVAdapter {
    private String folderName = "Dictionary";
    private String exportFile = "/exportDB.json";
    private String importFile = folderName + "/importDB.json";



    public void writeAllDataToFile(ArrayList<String[]> wordsFromDB){

        createFolder();
        File expFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + exportFile);
        if(!expFile.exists()) {
            try {
                expFile.createNewFile();
                if(!expFile.exists()) {
                    Log.d("createFile", "File is not created");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JSONObject jsonObject = new JSONObject() ;
        for(String[] str : wordsFromDB) {
            try {
                jsonObject.put(str[0], str[1]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(expFile));
            output.write(jsonObject.toString());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean createFolder() {
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folderName);
        if (!folder.exists()) {
            folder.mkdirs();
            if (!folder.exists())
                return false;
        }
        return true;
    }
}
