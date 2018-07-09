package com.example.vyakovenko.dictionary.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "DictionaryDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("create table dictionary ("
                + "id integer primary key autoincrement,"
                + "english text NOT NULL,"
                + "ukrainian text NOT NULL,"
                + "status text,"
//                + "date text DEFAULT " + Calendar.getInstance().getTime() + ","
                + "level integer DEFAULT 0"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
