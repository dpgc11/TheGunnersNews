package com.example.android.thegunnersnews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yogesh on 05-12-2016.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    private static String LOG_TAG = NewsAdapter.class.getSimpleName();

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
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
        ImageView image = (ImageView) rootView.findViewById(R.id.imageview);

        String dT = currentNews.getDateTime();
        String title = currentNews.getTitle();
        String titleToDisplay = "";

        if (title.contains(":")) {
            int indexOfColon = title.indexOf(':');
            titleToDisplay = title.substring(indexOfColon + 1, title.length());
        } else {
            titleToDisplay = title;
        }

        String thumbnail = currentNews.getThumbnail();
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date d1;
        String dateString = "";
        String dateOnly = "";
        String yearOnly = "";
        try {
            d1 = dFormat.parse(dT);
            dateString = d1.toString();
            dateOnly = dateString.substring(4, 10);
            yearOnly = dateString.substring(dateString.length() - 4, dateString.length());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        newsTitle.setText(titleToDisplay);
        date.setText(dateOnly + "\n" + yearOnly);
        Picasso.with(getContext()).load(thumbnail).into(image);

        return rootView;
    }
}
