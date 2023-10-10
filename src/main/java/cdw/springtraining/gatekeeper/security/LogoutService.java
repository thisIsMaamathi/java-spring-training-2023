//package cdw.springtraining.gatekeeper.security;
//
//import cdw.springtraining.gatekeeper.repository.TokenRepository;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//
//@Service
//@RequiredArgsConstructor
//public class LogoutService implements LogoutHandler {
//    @Autowired
//    JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Autowired
//    TokenRepository tokenRepository;
//    @Override
//    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
//        tokenRepository.deleteByToken(token);
//
//    }
//}
