package com.example.android.thegunnersnews.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.thegunnersnews.R;
import com.example.android.thegunnersnews.adapter.NewsAdapter;
import com.example.android.thegunnersnews.model.News1;
import com.example.android.thegunnersnews.model.Result;
import com.example.android.thegunnersnews.rest.ApiClient;
import com.example.android.thegunnersnews.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    private static final String LOG_TAG = NewsActivity.class.getSimpleName();
    private static final String API_KEY = "b29db22e-a97f-4c16-abc6-ca85fec2cde0";
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ApiInterface apiService;
    private ProgressBar progressBar;
    private List<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        apiService = ApiClient.getClient().create(ApiInterface.class);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        performSearch();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                performSearch();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        setRecyclerViewItemTouchListener();

    }

    private void performSearch() {

        Call<News1> call = apiService.getNewsAboutArsenal("football/arsenal", "football", "football/arsenal",
                "newest", "thumbnail", API_KEY);
        call.enqueue(new Callback<News1>() {
            @Override
            public void onResponse(Call<News1> call, Response<News1> response) {
                int statusCode = response.code();
                progressBar.setVisibility(View.INVISIBLE);
                results = response.body().getResponse().getResults();
                recyclerView.setAdapter(new NewsAdapter(results, R.layout.list_item, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<News1> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void setRecyclerViewItemTouchListener() {

        //1
        ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                //2
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //3
                int position = viewHolder.getAdapterPosition();
                results.remove(position);
                recyclerView.getAdapter().notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


}
