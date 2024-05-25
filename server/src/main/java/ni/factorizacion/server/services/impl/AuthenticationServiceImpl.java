package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.services.AuthenticationService;
import ni.factorizacion.server.types.GithubAccessToken;
import ni.factorizacion.server.types.GoogleAccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import java.util.AbstractMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${github.client_id}")
    private String githubClientId;
    @Value("${github.client_secret}")
    private String githubClientSecret;

    @Value("${google.client_id}")
    private String googleClientId;
    @Value("${google.client_secret}")
    private String googleClientSecret;

    @Override
    public String getGithubToken(String code) {
        WebClient client = WebClient.builder()
                .baseUrl("https://oauth2.googleapis.com/token")
                .defaultUriVariables(Map.ofEntries(
                        new AbstractMap.SimpleEntry<>("client_id", githubClientId),
                        new AbstractMap.SimpleEntry<>("client_secret", githubClientSecret),
                        new AbstractMap.SimpleEntry<>("code", code)
                ))
                .build();

        ResponseSpec response = client.post().retrieve();
        GithubAccessToken token = response.bodyToFlux(GithubAccessToken.class).blockLast();

        return token.getAccess_token();
    }

    @Override
    public String getGoogleToken(String code) {
        // TODO: Validate errors

        WebClient client = WebClient.builder()
                .baseUrl("https://oauth2.googleapis.com/token")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();

        ResponseSpec response = client.post()
                .body(BodyInserters
                        .fromFormData("client_id", googleClientId)
                        .with("client_secret", googleClientSecret)
                        .with("code", code)
                        .with("grant_type", "authorization_code")
                        .with("redirect_uri", "http://localhost:8080/auth/login/google")
                )
                .retrieve();

        GoogleAccessToken token = response.bodyToFlux(GoogleAccessToken.class).blockLast();
        return token.getAccess_token();
    }
}
