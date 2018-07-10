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
        Cursor cursor, c;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int counter = 0;
        for (String[] string : arrayList) {
            c = db.query("dictionary", null, null, null, null, null, "id");
            cursor = db.query("dictionary", new String[]{"id"}, "english = ? and ukrainian = ?", new String[]{string[0], string[1]}, null, null, null);
            if ((cursor.getCount() == 0 && c.getCount() > 0) || c.getCount() == 0) {
                cv.put("english", string[0]);
                cv.put("ukrainian", string[1]);
                db.insert("dictionary", null, cv);
                counter++;
            } else continue;
        }
        Log.d("insertCounter", String.valueOf(counter) + " columns added");
    }
}
