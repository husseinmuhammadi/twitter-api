package com.javastudio.tutorial.twitter.oauth.web.controller;

import com.google.api.client.http.HttpRequestFactory;
import com.javastudio.tutorial.twitter.oauth.exceptions.TwitterAuthenticationException;
import com.javastudio.tutorial.twitter.oauth.web.config.OAuthProperties;
import com.javastudio.tutorial.twitter.oauth.web.config.security.TwitterAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/twitter")
public class TwitterController {

    private Logger logger = LoggerFactory.getLogger(TwitterController.class);

    @Autowired
    private OAuthProperties oAuthProperties;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/oauth")
    public String oauth() {
        return "/twitter/oauth";
    }

    @GetMapping("/redirect")
    public RedirectView redirectWithUsingRedirectView(RedirectAttributes attributes) throws TwitterAuthenticationException {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");
        return new RedirectView("https://developer.twitter.com/en/docs/twitter-api/v1/tweets/filter-realtime/overview");
    }

    @GetMapping("/authenticate")
    public String authenticate() throws TwitterAuthenticationException {
        TwitterAuthenticator authenticator = new TwitterAuthenticator(System.out,
                oAuthProperties.getKey(), oAuthProperties.getSecret());

        HttpRequestFactory httpRequestFactory = authenticator.getAuthorizedHttpRequestFactory();
        publisher.publishEvent(new TwitterLoginEvent(httpRequestFactory));
        logger.info("Event published ...");
        return "redirect:/twitter/oauth";
    }
}
