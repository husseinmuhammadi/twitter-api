package com.javastudio.tutorial.twitter.oauth.web.controller;

import com.google.api.client.http.HttpRequestFactory;
import org.springframework.context.ApplicationEvent;

public class TwitterLoginEvent  {

    private final HttpRequestFactory httpRequestFactory;

    public TwitterLoginEvent(HttpRequestFactory httpRequestFactory) {
        this.httpRequestFactory=httpRequestFactory;
    }

    public HttpRequestFactory getHttpRequestFactory() {
        return httpRequestFactory;
    }
}
