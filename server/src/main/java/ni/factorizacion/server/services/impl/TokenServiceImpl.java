package ni.factorizacion.server.services.impl;

import jakarta.transaction.Transactional;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Token;
import ni.factorizacion.server.domain.entities.TokenType;
import ni.factorizacion.server.repositories.TokenRepository;
import ni.factorizacion.server.services.TokenService;
import ni.factorizacion.server.utils.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    private JWTTools jwtTools;

    @Override
    @Transactional
    public void cleanTokens(RegisteredUser user) {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token -> {
            if (token.getType() != TokenType.AUTH) {
                return;
            }
            boolean valid = jwtTools.verifyToken(token.getContent());

            if (!valid) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        });
    }

    @Override
    @Transactional
    public void invalidateQrTokensByUser(RegisteredUser user) {
        tokenRepository.invalidateQrTokensByUser(user);
    }
}
