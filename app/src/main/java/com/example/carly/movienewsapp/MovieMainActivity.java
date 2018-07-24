package com.example.carly.movienewsapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovieMainActivity extends AppCompatActivity implements LoaderCallbacks<List<Movie>> {

    private static final String LOG_TAG = MovieMainActivity.class.getName();

    /**
     * URL for movie article data from the Guardian dataset
     */
    private static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search?q=movie&api-key=93247d35-fd14-4de7-9e47-9383b623283f";

    /**
     * Constant value for the movie loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int MOVIE_LOADER_ID = 1;

    /**
     * Adapter for the list of movies
     */
    private MovieAdapter mAdapter;

    /**
     *  TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_main_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView movieListView = (ListView) findViewById(R.id.list);

        // Create an empty state view if there is no earthquake data to return.
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        movieListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new MovieAdapter(this, new ArrayList<Movie>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        movieListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Movie currentMovie = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri movieUri = Uri.parse(currentMovie.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, movieUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check the state of the network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details of the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader.
            loaderManager.initLoader(MOVIE_LOADER_ID, null, this);
        } else {
            // Otherwise display an error

            // First: hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        // LOG TAG
        Log.e(LOG_TAG, "NOTE: Loader initialized.");
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        // LOG TAG
        Log.e(LOG_TAG, "NOTE: Create a new loader for the given URL.");

        // Create a new loader for the given URL
        return new MovieLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        // LOG TAG
        Log.e(LOG_TAG, "NOTE: Clear the adapter of previous movie data & update ListView.");

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.no_movies);


        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (movies != null && !movies.isEmpty()) {
            mAdapter.addAll(movies);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        // LOG TAG
        Log.e(LOG_TAG, "NOTE: Resetting loader to clear out existing data.");

        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
