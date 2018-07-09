package com.example.vyakovenko.dictionary.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DBAdapter {
    public DBHelper dbHelper;
    public DBAdapter(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public ArrayList<String[]> getAllWordsFromDB() {
        ArrayList<String[]> arrayList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("dictionary", null, null, null, null, null, "id");
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int engColIndex = c.getColumnIndex("english");
            int ukrColIndex = c.getColumnIndex("ukrainian");
            do {
                arrayList.add(new String[]{c.getString(engColIndex), c.getString(ukrColIndex)});
            } while (c.moveToNext());
        }
        c.close();
        return arrayList;
    }

    public void insertWordsFromFileToDB(ArrayList<String[]> arrayList){
        ContentValues cv = new ContentValues();
        Cursor cursor;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int counter = 0;
        for (String[] string : arrayList) {
            cursor = db.query("dictionary", null, "english = '" + string[0]+"'", null, null, null, null);
            if (cursor.getColumnCount() > 0) {
                cv.put("english", string[0]);
                cv.put("ukrainian", string[1]);
                db.insert("dictionary", null, cv);
                counter++;
            } else continue;
        }
        Log.d("insertCounter", String.valueOf(counter) + " columns added");
    }
}
