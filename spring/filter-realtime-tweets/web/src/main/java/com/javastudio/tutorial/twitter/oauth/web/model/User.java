package com.javastudio.tutorial.twitter.oauth.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class User {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("name")
    private String name;

    @JsonProperty("screen_name")
    private String screenName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
