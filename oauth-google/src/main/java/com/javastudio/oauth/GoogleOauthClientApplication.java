package com.javastudio.oauth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class GoogleOauthClientApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleOauthClientApplication.class);

    /**
     * Directory to store user credentials.
     */
    private static final File DATA_STORE_DIR =
            new File(System.getProperty("user.home"), ".store/dailymotion_sample");

    /**
     * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
     * globally shared instance across your application.
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * OAuth 2 scope.
     */
    private static final String SCOPE = "read";

    /**
     * Global instance of the HTTP transport.
     */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /**
     * Global instance of the JSON factory.
     */
    static final JsonFactory JSON_FACTORY = new GsonFactory();

    private static final String TOKEN_SERVER_URL = "https://api.dailymotion.com/oauth/token";
    private static final String AUTHORIZATION_SERVER_URL = "https://api.dailymotion.com/oauth/authorize";

    /**
     * Authorizes the installed application to access user's protected data.
     */
    private static Credential authorize() throws IOException {
        OAuth2ClientCredentials.errorIfNotSpecified();
        // set up authorization code flow
        AuthorizationCodeFlow flow =
                new AuthorizationCodeFlow.Builder(
                        BearerToken.authorizationHeaderAccessMethod(),
                        HTTP_TRANSPORT,
                        JSON_FACTORY,
                        new GenericUrl(TOKEN_SERVER_URL),
                        new ClientParametersAuthentication(
                                OAuth2ClientCredentials.API_KEY, OAuth2ClientCredentials.API_SECRET),
                        OAuth2ClientCredentials.API_KEY,
                        AUTHORIZATION_SERVER_URL)
                        .setScopes(Arrays.asList(SCOPE))
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .build();
        // authorize
        LocalServerReceiver receiver =
                new LocalServerReceiver.Builder()
                        .setHost(OAuth2ClientCredentials.DOMAIN)
                        .setPort(OAuth2ClientCredentials.PORT)
                        .build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static void main(String[] args) {
        try {
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
            final Credential credential = authorize();
            HttpRequestFactory requestFactory =
                    HTTP_TRANSPORT.createRequestFactory(
                            request -> {
                                credential.initialize(request);
                                request.setParser(new JsonObjectParser(JSON_FACTORY));
                            });
            // run(requestFactory);
            // Success!
            return;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Throwable t) {
            LOGGER.error("Error", t);
        }
        System.exit(1);
    }
}
