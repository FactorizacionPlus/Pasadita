package ni.factorizacion.server;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.services.AuthenticationService;
import ni.factorizacion.server.services.RegisteredUserService;
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
@NonNullApi
public class JWTTokenFilter extends OncePerRequestFilter {
    private final RequestMatcher authMatcher = new AntPathRequestMatcher("/auth/**");
    private final RequestMatcher optionsMatcher = new AntPathRequestMatcher("**", "OPTIONS");
    private final RequestMatcher sseMatcher = new AntPathRequestMatcher("/sse/**");
    private final RequestMatcher qrMatcher = new AntPathRequestMatcher("/api/access/validate/");

    @Autowired
    RegisteredUserService userService;

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

        Optional<RegisteredUser> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            throw new ControlException(HttpStatus.UNAUTHORIZED, "No user found");
        }

        boolean tokenValidity = authService.isTokenValid(user.get(), token);

        if (!tokenValidity) {
            throw new ControlException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user.get(),
                    null,
                    user.get().getAuthorities()
            );

            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return authMatcher.matches(request) || qrMatcher.matches(request) || optionsMatcher.matches(request) || sseMatcher.matches(request);
    }
}
