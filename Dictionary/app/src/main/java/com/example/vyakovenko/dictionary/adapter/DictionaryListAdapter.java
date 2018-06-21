package com.example.vyakovenko.dictionary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vyakovenko.dictionary.R;


public class DictionaryListAdapter extends ArrayAdapter {
    LayoutInflater lInflater;
    Context context;
    Integer[] var;
    public DictionaryListAdapter(Context context, Integer[] var) {
        super(context, R.layout.item, var);
        this.context = context;
        this.var = var;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }
        ((TextView) view.findViewById(R.id.engWord)).setText("ENG" + var[position]);
        ((TextView) view.findViewById(R.id.uaWord)).setText("UA" + var[position]);
        return view;
    }
}