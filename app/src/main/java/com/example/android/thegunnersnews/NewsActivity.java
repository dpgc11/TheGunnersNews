package com.example.android.thegunnersnews;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String LOG_TAG = NewsActivity.class.getSimpleName();

    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?q=football/arsenal&api-key=b29db22e-a97f-4c16-abc6-ca85fec2cde0";
//    private static final String API_KEY = "https://newsapi.org/v1/articles?source=bbc-sport&sortBy=latest&apiKey=fcb8258e278145ed97b20068862e2ae3";

    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        ListView newsListView = (ListView) findViewById(R.id.list);

        adapter = new NewsAdapter(this, new ArrayList<News>());

        newsListView.setAdapter(adapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News currentNews = adapter.getItem(i);
                Uri newsUri = Uri.parse(currentNews.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(1, null, this);
        } else {
            Toast.makeText(this, "No internet Connection!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {

        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);

        return new NewsLoader(this, baseUri.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {

        adapter.clear();

        if (news != null && !news.isEmpty())
            adapter.addAll(news);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        adapter.clear();
    }


}
