package com.example.carly.movienewsapp;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Activity context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item, parent, false);
        }

        // Get the {@link Movie} object located at this position in the list
        Movie currentMovie = getItem(position);

        // Get Movie article Title
        // Find the TextView in the movies_list_item.xml layout with the ID title
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        // Display the title of the current movie article in that TextView
        titleTextView.setText(currentMovie.getTitle());

        // Get Movie article Section
        // Find the TextView in the movies_list_item.xml layout with the ID section
        TextView sectionTextView = (TextView) listItemView.findViewById(R.id.section);
        // Get the location from the current Movie object and set this text on the section TextView
        sectionTextView.setText(currentMovie.getSection());

        // Get Movie article Date
        // Find the TextView in the movies_list_item.xml layout with the ID date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        // Get the date from the current Movie object and set this text on the date TextView
        dateTextView.setText(currentMovie.getDate());

        // Return the listItemView layout containing 3 text views
        return listItemView;

    }
}
