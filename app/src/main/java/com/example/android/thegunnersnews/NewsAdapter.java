package com.example.android.thegunnersnews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.Inflater;

/**
 * Created by Yogesh on 05-12-2016.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0 , news);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = convertView;

        if (rootView == null) {
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        News currentNews = getItem(position);

        TextView newsTitle = (TextView) rootView.findViewById(R.id.newsTitleId);
        TextView date = (TextView) rootView.findViewById(R.id.dateId);
        TextView time = (TextView) rootView.findViewById(R.id.timeId);

        Date dT = currentNews.getDateTime();
        String title = currentNews.getTitle();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss'Z'");

        String dateToDisplay = dateFormat.format(dT);
        String timeToDisplay = timeFormat.format(dT);

        newsTitle.setText(title);
        date.setText(dateToDisplay);
        time.setText(timeToDisplay);

        return rootView;
    }
}
