package com.example.vyakovenko.dictionary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vyakovenko.dictionary.R;

import java.util.ArrayList;


public class DictionaryListAdapter extends BaseAdapter{
    LayoutInflater lInflater;
    Context context;
    ArrayList<String> var;
    public DictionaryListAdapter(Context context, ArrayList<String> var) {
        //super(context, R.layout.item, var);
        this.context = context;
        this.var = var;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("constructor " + var.get(1));
    }


    @Override
    public int getCount() {
        return var.size();
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
        ((TextView) view.findViewById(R.id.engWord)).setText("ENG" + var.get(i));
        ((TextView) view.findViewById(R.id.uaWord)).setText("UA" + var.get(i));
        return view;
    }
}

/*
       System.out.println("GET_VIEW");
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }
        ((TextView) view.findViewById(R.id.engWord)).setText("ENG" + var[position]);
        ((TextView) view.findViewById(R.id.uaWord)).setText("UA" + var[position]);
*/