package com.epicodus.movielookup.ui;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
//    private MovieListAdapter mAdapter;
    public ArrayList<com.epicodus.movielookup.models.Movie> mMovies = new ArrayList<>();
    @Bind(R.id.listView) ListView mListView;

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

                        String[] movieNames = new String[mMovies.size()];
                        for (int i = 0; i < movieNames.length; i++) {
                            movieNames[i] = mMovies.get(i).getTitle();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(MovieListActivity.this, android.R.layout.simple_expandable_list_item_1, movieNames);
                        mListView.setAdapter(adapter);

                        for (com.epicodus.movielookup.models.Movie movie : mMovies) {
                            Log.d(TAG, "Title: " + movie.getTitle());
                        }
                    }
                });
            }
        });
    }
}
