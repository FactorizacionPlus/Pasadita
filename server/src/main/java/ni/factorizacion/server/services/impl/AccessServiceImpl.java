package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.repositories.TokenRepository;
import ni.factorizacion.server.services.AccessService;
import ni.factorizacion.server.services.ConfigurationService;
import ni.factorizacion.server.services.PermissionService;
import ni.factorizacion.server.services.TokenService;
import ni.factorizacion.server.utils.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccessServiceImpl implements AccessService {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private TokenService tokenService;

    private long getExpTime() {
        Configuration configuration = configurationService.getConfiguration();
        return configuration.getQrExpiration();
    }

    @Override
    public Token getQrToken(RegisteredUser user) {
        tokenService.invalidateQrTokensByUser(user);
        String tokenString = jwtTools.generateTokenWithExpTime("", getExpTime() * 1000);
        Token token = new Token(tokenString, user, TokenType.QR);
        tokenRepository.save(token);
        return token;
    }

    @Override
    public boolean validateQrToken(Token token) {
        if (!jwtTools.verifyToken(token.getContent())) {
            return false;
        }
        RegisteredUser user = token.getUser();

        // User must not be an Administrator
        if (user.getClass().equals(Administrator.class)) {
            return false;
        }

        // InvitedUser must have a permission to make the validation
        if (user.getClass().equals(InvitedUser.class)) {
            Optional<Permission> permission = permissionService.findByUserNow((InvitedUser) user);

            // There must be a permission for the InvitedUser
            if (permission.isEmpty()) {
                return false;
            }

            // The permission must be authorized
            return permission.get().getAuthorized();
        }

        return true;
    }

    @Override
    public Optional<Token> findQrToken(UUID id) {
        Optional<Token> token = tokenRepository.findById(id);
        if (token.isPresent() && !token.get().getActive()) {
            return Optional.empty();
        }
        return token;
    }
}
