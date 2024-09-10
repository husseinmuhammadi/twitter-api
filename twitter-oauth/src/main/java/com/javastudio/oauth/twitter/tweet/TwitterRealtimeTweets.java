package com.javastudio.oauth.twitter.tweet;

import com.google.api.client.http.HttpRequestFactory;
import com.javastudio.oauth.twitter.auth.TwitterAuthenticator;
import com.javastudio.oauth.twitter.exceptions.TwitterAuthenticationException;

public class TwitterRealtimeTweets {

    // private final
    private final TwitterAuthenticator authenticator;

    public TwitterRealtimeTweets(TwitterAuthenticator authenticator) {
        this.authenticator = authenticator;
    }


    public void start() {
        try {
            HttpRequestFactory authorizedHttpRequestFactory = authenticator.getAuthorizedHttpRequestFactory();
            // authorizedHttpRequestFactory.buildGetRequest()
        } catch (TwitterAuthenticationException e) {
            e.printStackTrace();
        }
    }
}
