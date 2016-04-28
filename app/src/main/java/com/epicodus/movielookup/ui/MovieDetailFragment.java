package com.epicodus.movielookup.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class MovieDetailFragment extends Fragment {
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
        mTitleLabel.setText(mMovie.getTitle());
        mDateLabel.setText(mMovie.getReleaseDate());
        mOverviewLabel.setText(mMovie.getOverview());
        mLanguageLabel.setText(mMovie.getLanguage());
        mRatingLabel.setText(Double.toString(mMovie.getRating()) + "/10");
        // Inflate the layout for this fragment
        return view;
    }

}
