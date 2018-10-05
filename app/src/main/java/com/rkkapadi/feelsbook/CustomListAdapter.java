package com.rkkapadi.feelsbook;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomListAdapter extends ArrayAdapter<Emotions>
{

    private Context context;
    private int id;
    private ArrayList<Emotions> emotionList;

    public CustomListAdapter(Context context, int textViewResourceId, ArrayList<Emotions> emotionList)
    {
        super(context, textViewResourceId, emotionList);
        this.context = context;
        id = textViewResourceId;
        this.emotionList = emotionList;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent){
        View view = v;
        if(view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(id, null);
        }

        TextView text = (TextView) view.findViewById(R.id.emotionListView);

        if(emotionList.get(position) != null)
        {
            text.setTextColor(Color.WHITE);
            text.setText((CharSequence) emotionList.get(position));
        }

        return view;
    }
}
