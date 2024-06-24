package ni.factorizacion.server.services.impl;

import jakarta.transaction.Transactional;
import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.repositories.TokenRepository;
import ni.factorizacion.server.services.AccessService;
import ni.factorizacion.server.services.PermissionService;
import ni.factorizacion.server.utils.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessServiceImpl implements AccessService {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PermissionService permissionService;

    @Override
    public Token getQrToken(RegisteredUser user) {
        cleanTokens(user);

        String tokenString = jwtTools.generateTokenWithExpTime(user.getEmail(), 10 * 60 * 1000);
        Token token = new Token(tokenString, user, TokenType.QR);
        tokenRepository.save(token);
        return token;
    }

    @Override
    public boolean validateQrToken(Token token) {
        RegisteredUser user = token.getUser();
        if (user == null) {
            return false;
        }
        // User must not be an Administrator
        if (user instanceof Administrator) {
            return false;
        }

        // InvitedUser must have a permission to make the validation
        if (user instanceof InvitedUser) {
            Optional<Permission> permission = permissionService.findByUserNow((InvitedUser) user);

            // There must be a permission for the InvitedUser
            if (permission.isEmpty()) {
                return false;
            }

            // The permission must be authorized
            if (!permission.get().getAuthorized()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Optional<Token> findQrToken(String content) {
        return tokenRepository.findByContent(content);
    }

    @Override
    @Transactional
    public void cleanTokens(RegisteredUser user) {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token -> {
            if (!jwtTools.verifyToken(token.getContent())) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        });
    }
}
