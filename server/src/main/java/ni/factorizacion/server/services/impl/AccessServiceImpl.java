package ni.factorizacion.server.services.impl;

import jakarta.transaction.Transactional;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Token;
import ni.factorizacion.server.repositories.TokenRepository;
import ni.factorizacion.server.services.AccessService;
import ni.factorizacion.server.utils.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessServiceImpl implements AccessService {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token getQrToken(RegisteredUser user) {
        cleanTokens(user);

        String tokenString = jwtTools.generateTokenWithExpTime(user.getEmail(), 10 * 60 * 1000);
        Token token = new Token(tokenString, user);
        tokenRepository.save(token);
        return token;
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
