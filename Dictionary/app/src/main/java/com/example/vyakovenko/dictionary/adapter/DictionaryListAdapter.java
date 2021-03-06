package com.example.vyakovenko.dictionary.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vyakovenko.dictionary.R;

import java.util.ArrayList;


public class DictionaryListAdapter extends BaseAdapter{
    LayoutInflater lInflater;
    Context context;
    ArrayList<String[]> wordsFromDB;
    public DictionaryListAdapter(Context context, ArrayList<String[]> wordsFromDB) {
        //super(context, R.layout.item, var);
        this.context = context;
        this.wordsFromDB = wordsFromDB;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return wordsFromDB.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        System.out.println("GET_VIEW");
        if (view == null) {
            view = lInflater.inflate(R.layout.item, viewGroup, false);
        }
        if (this.getCount() == 0) {
            Toast.makeText(context, "0 rows in DB", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println(i);
            ((TextView) view.findViewById(R.id.counter)).setText((i + 1) + "");
            TextView textView = ((TextView) view.findViewById(R.id.engWord));
            textView.setText(wordsFromDB.get(i)[0]);
            ((TextView) view.findViewById(R.id.uaWord)).setText(wordsFromDB.get(i)[1]);
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    TextView textView = ((TextView) view.findViewById(R.id.engWord));
                    ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("Copied text", textView.getText());
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(context, "Copy + paste here " + textView.getText(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

        }

        return view;
    }

}
