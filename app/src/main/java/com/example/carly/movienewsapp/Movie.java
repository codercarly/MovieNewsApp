package com.example.carly.movienewsapp;

public class Movie {

    // The title of the article
    private String mTitle;

    // The section name the article is assigned
    private String mSection;

    // The date the article was published
    private String mDate;

    // The URL of the article
    private String mUrl;

    /**
     * Constructor - create a new movie object
     * @param title is the title of the article
     * @param section is the section the article is assigned
     * @param date is the date the article was published
     * @param url is the website URL to read the article
     */
    public Movie(String title, String section, String date, String url) {
        mTitle = title;
        mSection = section;
        mDate = date;
        mUrl = url;
    }

    /*
     * Get the title of the article
     */
    public String getTitle() {return mTitle;}

    /*
     * Get the section of the article
     */
    public String getSection() {return mSection;}

    /*
     * Get the date of the article
     */
    public String getDate() {return mDate;}

    /*
     * Get the URL link of the article
     */
    public String getUrl() {return mUrl;}
}
