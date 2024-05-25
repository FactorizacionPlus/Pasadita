package ni.factorizacion.server.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleAccessToken {
    private String access_token;
    private Integer expires_in;
    private String token_type;
    private String scope;
    private String refresh_token;
}
