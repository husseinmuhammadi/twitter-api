package com.javastudio.tutorial.twitter.oauth.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwitterEndpoints {
    @Value("${twitter.endpoint.statuses.filter}")
    public String filterRealTimeTweetsUrl;

    @Value("${twitter.endpoint.oauth.authorize}")
    public String oAuthAuthorizeUrl;

    @Value("${twitter.endpoint.oauth.access-token}")
    public String oAuthAccessTokenUrl;

    @Value("${twitter.endpoint.oauth.request-token}")
    public String oAuthRequestTokenUrl;
}
