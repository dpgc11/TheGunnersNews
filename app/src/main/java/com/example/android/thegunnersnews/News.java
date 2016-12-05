package com.example.android.thegunnersnews;

import java.util.Date;

/**
 * Created by Yogesh on 05-12-2016.
 */

public class News {

    private String title;
    private String url;
    private Date dateTime;

    public News(String title, Date dateTime, String url) {
        this.title = title;
        this.dateTime = dateTime;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getUrl() {
        return url;
    }

}
