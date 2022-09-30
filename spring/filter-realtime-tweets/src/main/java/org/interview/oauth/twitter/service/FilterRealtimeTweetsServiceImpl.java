package org.interview.oauth.twitter.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.interview.oauth.twitter.authenticator.TwitterAuthenticationException;
import org.interview.oauth.twitter.authenticator.TwitterAuthenticator;
import org.interview.oauth.twitter.config.TempFileConfig;
import org.interview.oauth.twitter.config.TwitterEndpoints;
import org.interview.oauth.twitter.model.Tweet;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class FilterRealtimeTweetsServiceImpl implements FilterRealtimeTweetsService {

    private final Logger logger;
    private final TwitterAuthenticator authenticator;
    private final AuthenticatorPinRetrieval pinRetrieval;
    private final TwitterEndpoints endpoints;
    private final ObjectMapper objectMapper;

    @Value("${twitter.realtime-tweets.timeout}")
    private String timeout;

    public FilterRealtimeTweetsServiceImpl(Logger logger, TwitterAuthenticator authenticator, AuthenticatorPinRetrieval pinRetrieval, TwitterEndpoints endpoints, ObjectMapper objectMapper) {
        this.logger = logger;
        this.authenticator = authenticator;
        this.pinRetrieval = pinRetrieval;
        this.endpoints = endpoints;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Tweet> getTweets(String track) throws TwitterAuthenticationException {
        try {
            URIBuilder uriBuilder = new URIBuilder(endpoints.filterRealTimeTweetsUrl);
            List<NameValuePair> queryParameters = new ArrayList<>();
            queryParameters.add(new BasicNameValuePair("track", track));
            uriBuilder.addParameters(queryParameters);
            HttpResponse httpResponse = authenticator.getAuthorizedHttpRequestFactory(pinRetrieval).buildGetRequest(
                    new GenericUrl(uriBuilder.build())
            ).setConnectTimeout(60000).setReadTimeout(60000).execute();
            logger.info("Collecting tweets for {} seconds", timeout);
            return extractTweets(httpResponse.getContent(), Integer.parseInt(this.timeout));
        } catch (URISyntaxException | IOException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }



    private List<Tweet> extractTweets(InputStream in, int timeout) throws IOException, InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<List<Tweet>> tweetExtractor = () -> {
            List<Tweet> tweets = new ArrayList<>();

            try {
                JsonFactory jf = new JsonFactory();
                JsonParser jp = jf.createParser(in);
                jp.setCodec(new ObjectMapper());
                jp.nextToken();

                int count = 0;
                while (jp.hasCurrentToken()) {
                    TreeNode treeNode = jp.readValueAsTree();
                    Tweet tweet = objectMapper.treeToValue(treeNode, Tweet.class);
                    tweets.add(tweet);
                    if (++count == 100)
                        break;
                    TimeUnit.MILLISECONDS.sleep(10);
                    jp.nextToken();
                }
            } catch (InterruptedException e) {
                logger.warn("Task interrupted");
            }
            return tweets;
        };

        Future<List<Tweet>> tweets = executorService.submit(tweetExtractor);
        TimeUnit.SECONDS.sleep(timeout);

        logger.info("Shutting down the executor service");
        executorService.shutdownNow();

        return tweets.get();
    }

    private void writeJsonToTempFile(InputStream is) throws IOException {
        File file = File.createTempFile(TempFileConfig.TEMP_FILE_PREFIX, TempFileConfig.TEMP_FILE_SUFFIX);
        logger.info(file.getAbsolutePath());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            JsonFactory jf = new JsonFactory();
            JsonParser jp = jf.createParser(is);
            jp.setCodec(new ObjectMapper());
            jp.nextToken();

            while (jp.hasCurrentToken()) {
                TreeNode treeNode = jp.readValueAsTree();
                Tweet tweet = objectMapper.treeToValue(treeNode, Tweet.class);

                System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(tweet));

                writer.write(treeNode.toString());
                jp.nextToken();
            }
        }
    }

}
