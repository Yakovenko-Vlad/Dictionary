package com.example.vyakovenko.dictionary.external_carriers;

import android.content.Context;
import android.support.annotation.NonNull;

import com.opencsv.CSVWriter;

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
    private String exportFile = "exportDB.csv";
    private String importFile = "importDB.csv";
    final String FILENAME_SD = "fileSD";
    public void writeAllDataToFile(ArrayList<String[]> wordsFromDB) throws FileNotFoundException, UnsupportedEncodingException {
       /* File expFile = new File(exportFile);
        if(!expFile.exists()) {
            try {
                expFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(exportFile),"UTF-8"),';'); // windows-1251
        for (String[] words : wordsFromDB) {
            writer.writeNext(words);
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }
}
