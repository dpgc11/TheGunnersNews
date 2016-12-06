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
        TextView time = (TextView) rootView.findViewById(R.id.timeId);

        String dT = currentNews.getDateTime();
        String title = currentNews.getTitle();
        String titleToDisplay = "";

        if (title.contains(":")) {
            int indexOfColon = title.indexOf(':');
            titleToDisplay = title.substring(indexOfColon + 1, title.length());
        } else {
            titleToDisplay = title;
        }




        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

//        Date d3 = null;
//        String date1 = "";
//        String date2 = "";
//        try {
//            d3 = dFormat.parse(dT);
//            date1 = dateFormat.format(d3);
//            date2 = timeFormat.format(d3);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


//        String dateToDisplay = dateFormat.format(dT);
//        String timeToDisplay = timeFormat.format(dT);

        newsTitle.setText(titleToDisplay);
//        date.setText(date1);
//        time.setText(date2);

        return rootView;
    }
}
