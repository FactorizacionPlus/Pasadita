package ni.factorizacion.server;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.services.AuthenticationService;
import ni.factorizacion.server.services.InvitedUserService;
import ni.factorizacion.server.types.ControlException;
import ni.factorizacion.server.utils.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Optional;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {

    private final RequestMatcher uriMatcher = new AntPathRequestMatcher("/auth/login/**");
    @Autowired
    InvitedUserService userService;

    @Autowired
    AuthenticationService authService;

    @Autowired
    JWTTools jwtTools;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String tokenHeader = request.getHeader("Authorization");

        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            throw new ControlException(HttpStatus.BAD_REQUEST, "Bearer string not found");
        }
        String token = tokenHeader.substring(7);

        String email = jwtTools.getEmailFrom(token);
        if (email == null) {
            throw new ControlException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        Optional<InvitedUser> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            throw new ControlException(HttpStatus.UNAUTHORIZED, "No user found");
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user.get(),
                    null
            );

            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return uriMatcher.matches(request);
    }
}
