package com.example.vyakovenko.dictionary.DB;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBAdapter {
    DBHelper dbHelper;
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
}
