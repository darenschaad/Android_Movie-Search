package com.epicodus.movielookup.ui;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.movielookup.R;
import com.epicodus.movielookup.models.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.movieImageView) ImageView mImageLabel;
    @Bind(R.id.movieTitleTextView) TextView mTitleLabel;
    @Bind(R.id.dateTextView) TextView mDateLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.languageTextView) TextView mLanguageLabel;
    @Bind(R.id.googleTextView) TextView mGoogleLabel;
    @Bind(R.id.theaterTextView) TextView mTheaterLabel;
    @Bind(R.id.rentalTextView) TextView mRentalLabel;
    @Bind(R.id.overviewtextView) TextView mOverviewLabel;
    @Bind(R.id.saveMovieButton) TextView mSaveMovieButton;

    private Movie mMovie;

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", Parcels.wrap(movie));
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovie = Parcels.unwrap(getArguments().getParcelable("movie"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mMovie.getImageUrl()).into(mImageLabel);
        Log.d("MOVE IMAGE", mMovie.getImageUrl());
        mTitleLabel.setText(mMovie.getTitle());
        mDateLabel.setText(mMovie.getReleaseDate());
        mOverviewLabel.setText(mMovie.getOverview());
        mLanguageLabel.setText(mMovie.getLanguage());
        mRatingLabel.setText(Double.toString(mMovie.getRating()) + "/10");
        mGoogleLabel.setOnClickListener(this);
        mTheaterLabel.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onClick(View v) {
        if (v == mGoogleLabel) {
            Intent googleIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=" + mMovie.getTitle() + " movie info" ));
            startActivity(googleIntent);
        }
        if (v == mTheaterLabel) {
            Intent goToTheaterMapIntent = new Intent(getActivity(), DisplayMapActivity.class);
            startActivity(goToTheaterMapIntent);
        }
    }

}
