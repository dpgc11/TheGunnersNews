package com.example.android.thegunnersnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.thegunnersnews.R;
import com.example.android.thegunnersnews.model.Result;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Yogesh on 10-04-2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Result> newsList;
    private int rowLayout;
    private Context context;

    public NewsAdapter(List<Result> news, int rowLayout, Context context) {
        this.newsList = news;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.NewsViewHolder holder, int position) {
        Result currentResult = newsList.get(position);

        String dT = currentResult.getWebPublicationDate();
        String title = currentResult.getWebTitle();
        String titleToDisplay = "";

        if (title.contains(":")) {
            int indexOfColon = title.indexOf(':');
            titleToDisplay = title.substring(indexOfColon + 1, title.length());
        } else {
            titleToDisplay = title;
        }

        String thumbnail = currentResult.getFields().getThumbnail();
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

        holder.newsTitle.setText(titleToDisplay);
        holder.newsDate.setText(dateOnly + "\n" + yearOnly);
        Picasso.with(context).load(thumbnail).into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;
        private TextView newsTitle;
        private TextView newsDate;
        private ImageView newsImage;

        public NewsViewHolder(View v) {
            super(v);
            linearLayout = (LinearLayout) v.findViewById(R.id.linear_layout);
            newsTitle = (TextView) v.findViewById(R.id.newsTitleId);
            newsDate = (TextView) v.findViewById(R.id.dateId);
            newsImage = (ImageView) v.findViewById(R.id.imageview);
        }
    }
}
