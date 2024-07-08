package ni.factorizacion.server;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import ni.factorizacion.server.domain.entities.Terminal;
import ni.factorizacion.server.domain.entities.TerminalType;
import ni.factorizacion.server.services.AuthenticationService;
import ni.factorizacion.server.services.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Component
@NonNullApi
public class BasicAuthFilter extends OncePerRequestFilter {
    @Autowired
    TerminalService terminalService;

    @Autowired
    AuthenticationService authService;


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String tokenHeader = request.getHeader("Authorization");

        if (tokenHeader == null || !tokenHeader.startsWith("Basic ")) {
//            throw new ControlException(HttpStatus.BAD_REQUEST, "Bearer string not found");
            System.out.println("Basic string not found");
            filterChain.doFilter(request, response);
            return;
        }
        String token = tokenHeader.substring(6);

        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String decodedString = new String(decodedBytes);
        String[] split = decodedString.split(":");
        if (split.length != 2) {
            System.out.println("Invalid token");
            filterChain.doFilter(request, response);
            return;
        }
        String username = split[0];
        String password = split[1];

        TerminalType terminalType;
        try {
            terminalType = TerminalType.valueOf(username);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid TerminalType");
            filterChain.doFilter(request, response);
            return;
        }

        Optional<Terminal> terminal = terminalService.findTerminalByType(terminalType, password);
        if (terminal.isEmpty()) {
            System.out.println("Invalid terminal credentials");
            filterChain.doFilter(request, response);
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    terminal.get(),
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_TERMINAL"))
            );

            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}
