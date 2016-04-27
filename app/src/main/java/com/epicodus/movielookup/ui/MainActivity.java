package com.epicodus.movielookup.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.movielookup.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.movieSearchButton)
    Button mMovieSearchButton;
    @Bind(R.id.movieSearchEditText)
    EditText mMovieSearchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMovieSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.movieSearchButton:
                String searchInfo = mMovieSearchEditText.getText().toString();
                Intent goToMovieList = new Intent(MainActivity.this, MovieListActivity.class);
                goToMovieList.putExtra("searchInfo", searchInfo);
                startActivity(goToMovieList);
                break;
            default:
                break;
        }

    }
}
