package com.example.vyakovenko.dictionary.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "english text NOT NULL,"
                + "ukrainian text NOT NULL,"
                + "status text,"
                + "level integer DEFAULT 0"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
