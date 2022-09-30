package org.interview.oauth.twitter.config;

import org.interview.oauth.twitter.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@IntegrationTest
class TwitterEndpointsTest {

    @Autowired
    TwitterEndpoints endpoints;

    @Test
    void shouldInject() {
        assertNotNull(endpoints);
    }

    @Test
    void shouldLoadProperties() {
        assertNotNull(endpoints.filterRealTimeTweetsUrl);
    }
}