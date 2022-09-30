package com.javastudio.tutorial.twitter.oauth.service;

import com.javastudio.tutorial.twitter.oauth.api.TwitterService;
import com.javastudio.tutorial.twitter.oauth.dto.TweetDTO;
import com.javastudio.tutorial.twitter.oauth.entity.Tweet;
import com.javastudio.tutorial.twitter.oauth.repository.TweetRepository;
import com.javastudio.tutorial.twitter.oauth.service.mapper.TweetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwitterServiceImpl implements TwitterService {

    @Autowired
    private TweetMapper mapper;

    @Autowired
    private TweetRepository repository;

    // todo correct this
    @Override
    public TweetDTO save(TweetDTO tweetDTO) {
//        return Optional.of(tweetDTO)
//                .map(mapper::map)
//                .map(tweetRepository::save)
//                .map(mapper::map)
//                .orElse(null);

        Tweet tweet = mapper.map(tweetDTO);
        Tweet savedTweet = repository.save(tweet);
        return mapper.map(savedTweet);
    }
}
