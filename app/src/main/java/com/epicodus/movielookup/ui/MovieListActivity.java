package com.epicodus.movielookup.ui;

import android.content.Intent;

import com.epicodus.movielookup.adapters.MovieListAdapter;
import com.epicodus.movielookup.models.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.epicodus.movielookup.R;
import com.epicodus.movielookup.services.MovieService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieListActivity extends AppCompatActivity {
    public static final String TAG = MovieListActivity.class.getSimpleName();
    private MovieListAdapter mAdapter;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    public ArrayList<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String searchInfo = intent.getStringExtra("searchInfo");
        getMovies(searchInfo);
    }

    private void getMovies(String searchInfo) {
        final MovieService movieService = new MovieService();

        MovieService.findMovies(searchInfo, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mMovies = movieService.processResults(response);

                MovieListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new MovieListAdapter(getApplicationContext(), mMovies);

                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MovieListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
