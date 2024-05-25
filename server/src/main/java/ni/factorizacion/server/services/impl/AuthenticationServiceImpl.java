package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.services.AuthenticationService;
import ni.factorizacion.server.types.GithubAccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RestTemplate restTemplate;
    @Value("${github.client_id}")
    private String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;

    public AuthenticationServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String getAuthToken(String code) {
        String url = String.format(
                "https://github.com/login/oauth/access_token" +
                        "?client_id=%s" +
                        "&client_secret=%s" +
                        "&code=%s",
                clientId,
                clientSecret,
                code
        );
        GithubAccessToken response = restTemplate.getForObject(url, GithubAccessToken.class);
        return response.getAccess_token();
    }
}
