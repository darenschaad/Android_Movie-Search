package com.epicodus.movielookup.models;

/**
 * Created by Guest on 4/27/16.
 */
public class Movie {
    private String mTitle;
    private String mOverview;
    private String mReleaseDate; //yyyy/mm/dd
    private String mLanguage;
    private String mImageUrl;

    public Movie(String title, String overview, String releaseDate, String language, String imageUrl) {
        this.mTitle = title;
        this.mOverview = overview;
        this.mReleaseDate = releaseDate;
        this.mLanguage = language;
        this.mImageUrl = imageUrl;
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
        return mLanguage;
    }
    public String getImageUrl() {
        return mImageUrl;
    }
}
//http://image.tmdb.org/t/p/w342/qCrzofMuobtW3kkoIwgUx3zAUNz.jpg