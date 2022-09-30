package com.javastudio.tutorial.twitter.oauth.web.listener;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.javastudio.tutorial.twitter.oauth.api.TwitterService;
import com.javastudio.tutorial.twitter.oauth.web.config.TwitterEndpoints;
import com.javastudio.tutorial.twitter.oauth.web.controller.TwitterLoginEvent;
import com.javastudio.tutorial.twitter.oauth.web.mapper.TweetModelMapper;
import com.javastudio.tutorial.twitter.oauth.web.model.Tweet;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilterRealtimeTweet {

    private final TweetModelMapper mapper;
    private final ObjectMapper objectMapper;
    private final TwitterEndpoints endpoints;
    private final TwitterService twitterService;

    public FilterRealtimeTweet(TweetModelMapper mapper, ObjectMapper objectMapper, TwitterEndpoints endpoints, TwitterService twitterService) {
        this.mapper = mapper;
        this.objectMapper = objectMapper;
        this.endpoints = endpoints;
        this.twitterService = twitterService;
    }

    @EventListener(TwitterLoginEvent.class)
    public void filterTweets(TwitterLoginEvent event) throws URISyntaxException, IOException {
        HttpRequestFactory httpRequestFactory = event.getHttpRequestFactory();

        final String track = "twitter";

        URIBuilder uriBuilder = new URIBuilder(endpoints.filterRealTimeTweetsUrl);
        List<NameValuePair> queryParameters = new ArrayList<>();
        queryParameters.add(new BasicNameValuePair("track", track));
        uriBuilder.addParameters(queryParameters);
        HttpResponse httpResponse = httpRequestFactory.buildGetRequest(
                new GenericUrl(uriBuilder.build())
        ).setConnectTimeout(60000).setReadTimeout(60000).execute();
        extractTweets(httpResponse.getContent());
    }

    private void extractTweets(InputStream in) throws IOException {
        JsonFactory jf = new JsonFactory();
        JsonParser jp = jf.createParser(in);
        jp.setCodec(new ObjectMapper());
        jp.nextToken();

        while (jp.hasCurrentToken()) {
            TreeNode treeNode = jp.readValueAsTree();
            Tweet tweet = objectMapper.treeToValue(treeNode, Tweet.class);
            twitterService.save(mapper.map(tweet));
            jp.nextToken();
        }
    }
}
