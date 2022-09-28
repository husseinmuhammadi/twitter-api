package org.digilinq.twitter.service;

import org.digilinq.twitter.api.TweetService;
import org.digilinq.twitter.web.model.Tweet;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TweetServiceImpl implements TweetService {
    @Override
    public List<Tweet> findAllTweets() {
        return List.of(new Tweet(10));
    }
}
