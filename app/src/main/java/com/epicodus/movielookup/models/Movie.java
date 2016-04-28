package com.epicodus.movielookup.models;

import org.parceler.Parcel;

@Parcel
public class Movie {
    private String mTitle;
    private String mOverview;
    private String mReleaseDate; //yyyy/mm/dd
    private String mLanguage;
    private String mImageUrl;
    private double mRating;

    public Movie() {}

    public Movie(String title, String overview, String releaseDate, String language, String imageUrl, double rating) {
        this.mTitle = title;
        this.mOverview = overview;
        this.mReleaseDate = releaseDate;
        this.mLanguage = language;
        this.mImageUrl = imageUrl;
        this.mRating = rating;
    }
    public String getTitle() {
        return mTitle;
    }
    public String getOverview() {
        return mOverview;
    }
    public String getReleaseDate() {
        return mReleaseDate;
    }
    public String getLanguage() {
        if(mLanguage.equalsIgnoreCase("en")) {
            mLanguage = "English";
        } else if(mLanguage.equalsIgnoreCase("fr")) {
            mLanguage = "French";
        }else if(mLanguage.equalsIgnoreCase("sp")) {
            mLanguage = "Spanish";
        }else if(mLanguage.equalsIgnoreCase("zh")) {
            mLanguage = "Chinese";
        }else if(mLanguage.equalsIgnoreCase("it")) {
            mLanguage = "Italian";
        }else if(mLanguage.equalsIgnoreCase("de")) {
            mLanguage = "German";
        }else if(mLanguage.equalsIgnoreCase("ko")) {
            mLanguage = "Korean";
        }else if(mLanguage.equalsIgnoreCase("ru")) {
            mLanguage = "Russian";
        }else {
            mLanguage = "Not English";
        }
        return mLanguage;
    }
    public String getImageUrl() {
        mImageUrl =  "http://image.tmdb.org/t/p/w342" + mImageUrl;
        return mImageUrl;
    }
    public double getRating() { return mRating; }
}
