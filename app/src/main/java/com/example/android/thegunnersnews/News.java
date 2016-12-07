package com.example.android.thegunnersnews;

/**
 * Created by Yogesh on 05-12-2016.
 */

public class News {

    private String title;
    private String url;
    private String dateTime;
    private String thumbnail;

    public News(String title, String url, String dateTime) {
        this.title = title;
        this.url = url;
        this.dateTime = dateTime;
    }

    public News(String title, String url, String dateTime, String thumbnail) {
        this.title = title;
        this.url = url;
        this.dateTime = dateTime;
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getUrl() {
        return url;
    }

}
