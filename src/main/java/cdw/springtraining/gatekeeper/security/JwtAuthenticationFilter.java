package cdw.springtraining.gatekeeper.security;

import cdw.springtraining.gatekeeper.constant.CommonConstants;
import cdw.springtraining.gatekeeper.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


/**
 * A custom filter for handling JWT authentication in a Spring Security application.
 * This filter intercepts incoming HTTP requests and checks for a valid JWT token in the "Authorization" header.
 * If a valid token is found, it authenticates the user and sets the user's security context.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    TokenRepository tokenRepository;

    /**
     * Intercepts incoming HTTP requests to check for a valid JWT token and sets the user's security context.
     * @param request     the incoming HttpServletRequest.
     * @param response    the HttpServletResponse for the request.
     * @param filterChain the filter chain to continue processing the request.
     * @throws ServletException if a servlet exception occurs.
     * @throws IOException      if an IO exception occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = getTokenFromRequest(request);
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {

            if (!tokenRepository.existsByJwt(token))
                throw new RuntimeException(CommonConstants.LOGGED_OUT_USER);
            String username = jwtTokenProvider.getUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Extracts the JWT token from the "Authorization" header of an HTTP request.
     * @param request the HttpServletRequest from which to extract the token.
     * @return the JWT token as a string, or null if not found.
     */
    public String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(CommonConstants.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(CommonConstants.BEARER)) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

}

