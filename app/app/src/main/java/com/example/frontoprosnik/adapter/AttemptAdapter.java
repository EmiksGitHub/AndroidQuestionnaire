package com.example.frontoprosnik.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.frontoprosnik.R;
import com.example.frontoprosnik.json.JSONResult;

import java.util.List;

public class AttemptAdapter extends ArrayAdapter<JSONResult> {
    private LayoutInflater inflater;
    private int layout;
    private List<JSONResult> resultList;
    private Context mContext;

    public AttemptAdapter(Context context, int textViewResourceId, List<JSONResult> results) {
        super(context, textViewResourceId, results);
        this.resultList = results;
        this.layout = textViewResourceId;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }
    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewListAttemptName.setText(resultList.get(position).getData_attempt() +
                " (" + resultList.get(position).getPoints_general() + ")");
        return convertView;
    }

    public JSONResult getItem(int position) {
        return resultList.get(position);
    }

    private class ViewHolder {
        /*final Button ButtonListAttemptShow;*/
        final TextView textViewListAttemptName;
        ViewHolder(View view){
            /*ButtonListAttemptShow = view.findViewById(R.id.ButtonListAttemptShow);*/
            textViewListAttemptName = view.findViewById(R.id.textViewListAttemptName);
        }
    }
}
