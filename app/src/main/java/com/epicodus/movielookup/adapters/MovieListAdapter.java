package com.epicodus.movielookup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.epicodus.movielookup.R;
import com.epicodus.movielookup.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.movieImageView) ImageView mMovieImageView;
        @Bind(R.id.movieTitleTextView) TextView mMovieTitleTextView;
        @Bind(R.id.releaseDateTextView) TextView mReleaseDateTextView;
        @Bind(R.id.languageTextView) TextView mLanguageTextView;
        @Bind(R.id.ratingBar)
        RatingBar mRatingBar;
        private Context mContext;


        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindMovie(Movie movie) {
            if (movie.getImageUrl() != "null") {
                Picasso.with(mContext).load("http://image.tmdb.org/t/p/w342" + movie.getImageUrl()).into(mMovieImageView);
            } else {
                Picasso.with(mContext).load("https://cdn.amctheatres.com/Media/Default/Images/noposter.jpg").into(mMovieImageView);
            }
            mMovieTitleTextView.setText(movie.getTitle());
            mReleaseDateTextView.setText(movie.getReleaseDate());
            if(movie.getLanguage().equalsIgnoreCase("en")) {
                mLanguageTextView.setText("English");
            } else if(movie.getLanguage().equalsIgnoreCase("fr")) {
                mLanguageTextView.setText("French");
            }else if(movie.getLanguage().equalsIgnoreCase("sp")) {
                mLanguageTextView.setText("Spanish");
            }else if(movie.getLanguage().equalsIgnoreCase("zh")) {
                mLanguageTextView.setText("Chinese");
            }else if(movie.getLanguage().equalsIgnoreCase("it")) {
                mLanguageTextView.setText("Italian");
            }else if(movie.getLanguage().equalsIgnoreCase("de")) {
                mLanguageTextView.setText("German");
            }else if(movie.getLanguage().equalsIgnoreCase("ko")) {
                mLanguageTextView.setText("Korean");
            }else if(movie.getLanguage().equalsIgnoreCase("ru")) {
                mLanguageTextView.setText("Russian");
            }else {
                mLanguageTextView.setText("Not English");
            }
            mRatingBar.setRating((float) movie.getRating());

        }
    }


}
