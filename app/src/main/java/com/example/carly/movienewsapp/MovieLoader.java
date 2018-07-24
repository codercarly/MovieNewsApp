package com.example.carly.movienewsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import java.util.List;

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {

    /** Tag for log messages */
    private static final String LOG_TAG = MovieLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link MovieLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public MovieLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        // LOG TAG
        Log.e(LOG_TAG, "NOTE: Begin Start Loading method.");

        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        // LOG TAG
        Log.e(LOG_TAG, "NOTE: Load in Background.");

        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Movie> movies = QueryUtility.fetchMovieData(mUrl);
        return movies;
    }

}
