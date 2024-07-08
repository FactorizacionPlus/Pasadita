package ni.factorizacion.server;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.services.AuthenticationService;
import ni.factorizacion.server.services.RegisteredUserService;
import ni.factorizacion.server.utils.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Optional;

@Component
@NonNullApi
public class JWTTokenFilter extends OncePerRequestFilter {
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
//            throw new ControlException(HttpStatus.BAD_REQUEST, "Bearer string not found");
            System.out.println("Bearer string not found");
            filterChain.doFilter(request, response);
            return;
        }
        String token = tokenHeader.substring(7);

        String email = jwtTools.getEmailFrom(token);
        if (email == null) {
//            throw new ControlException(HttpStatus.UNAUTHORIZED, "Invalid token");
            System.out.println("Invalid token");
            filterChain.doFilter(request, response);
            return;
        }

        Optional<RegisteredUser> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            // throw new ControlException(HttpStatus.UNAUTHORIZED, "No user found");
            System.out.println("No user found");
            filterChain.doFilter(request, response);
            return;
        }

        boolean tokenValidity = authService.isTokenValid(user.get(), token);

        if (!tokenValidity) {
//            throw new ControlException(HttpStatus.UNAUTHORIZED, "Invalid token");
            System.out.println("Invalid token");
            filterChain.doFilter(request, response);
            return;
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
}
