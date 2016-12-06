package com.example.android.thegunnersnews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yogesh on 06-12-2016.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private static final String LOG_TAG = NewsLoader.class.getSimpleName();
    private String url;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {

        if (url == null)
            return null;

        List<News> news = QueryUtils.extractNews(url);

        return news;
    }
}
