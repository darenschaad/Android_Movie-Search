package com.epicodus.movielookup.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.movielookup.models.Movie;
import com.epicodus.movielookup.ui.MovieDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 4/28/16.
 */
public class MoviePageAdapter extends FragmentPagerAdapter {
    private ArrayList<Movie> mMovies;

    public MoviePageAdapter(FragmentManager fm, ArrayList<Movie> movies) {
        super(fm);
        mMovies = movies;
    }

    @Override
    public Fragment getItem(int position) {
        return MovieDetailFragment.newInstance(mMovies.get(position));
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMovies.get(position).getTitle();
    }
}
