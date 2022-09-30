package com.javastudio.tutorial.twitter.oauth.web.resources;

import com.javastudio.tutorial.twitter.oauth.web.model.Tweet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TweetsResource {

    @GetMapping("/tweets")
    public List<Tweet> getTweets(){
        return List.of();
    }
}
