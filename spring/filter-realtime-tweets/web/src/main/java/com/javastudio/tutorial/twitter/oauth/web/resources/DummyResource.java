package com.javastudio.tutorial.twitter.oauth.web.resources;

import com.javastudio.tutorial.twitter.oauth.web.config.OAuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cfg")
public class DummyResource {

    @Autowired
    private OAuthProperties oAuthProperties;

    @GetMapping("/oauth")
    public OAuthProperties getOAuthProperties(){
        return oAuthProperties;
    }
}
