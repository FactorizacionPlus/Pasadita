package ni.factorizacion.server.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GithubAccessToken {
    private String access_token;
    private String scope;
    private String token_type;
}
