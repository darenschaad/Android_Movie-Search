package com.epicodus.movielookup.services;

import android.util.Log;

import com.epicodus.movielookup.Constants;
import com.epicodus.movielookup.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieService {
    public static void findMovies(String searchInfo, Callback callback) {
        String KEY = Constants.MOVIE_KEY;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MOVIE_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.MOVIE_SEARCH_QUERY_PARAMETER, searchInfo);
        urlBuilder.addQueryParameter(Constants.MOVIE_API_KEY_QUERY_PARAMETER, KEY);
        String url = urlBuilder.build().toString();
//        url += "&appid=" + KEY;
        Log.d("URL", url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Movie> processResults(Response response) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject moviedbJSON = new JSONObject(jsonData);
                JSONArray filmsJSON = moviedbJSON.getJSONArray("results");
                for (int i = 0; i < filmsJSON.length(); i++) {
                    JSONObject movieJSON = filmsJSON.getJSONObject(i);
                    String title = movieJSON.getString("title");
                    String overview = movieJSON.getString("overview");
                    String releaseDate = movieJSON.getString("release_date");
                    String language = movieJSON.getString("original_language");
                    String imageUrl = movieJSON.getString("poster_path");
                    double rating = movieJSON.getDouble("vote_average");

                    Movie movie = new Movie(title, overview, releaseDate, language, imageUrl, rating);
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

}
