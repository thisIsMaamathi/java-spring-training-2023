package cdw.springtraining.gatekeeper.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    CustomUserDetailsService customUserDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/register").permitAll();
                    authorize.requestMatchers("/login").permitAll();
                    authorize.requestMatchers("/logout").permitAll();
                    authorize.requestMatchers("/requests/**").hasAuthority("admin");
                    authorize.requestMatchers("/residents/**").hasAuthority("admin");
                    authorize.requestMatchers("/gatekeepers/**").hasAuthority("admin");
                    authorize.requestMatchers("/resident/**").hasAuthority("resident");
                    authorize.requestMatchers("/gatekeeper/**").hasAuthority("gatekeeper");
                    authorize.requestMatchers("/visitor/**").permitAll();

                    authorize.anyRequest().authenticated();
                  // authorize.anyRequest().permitAll();
                });
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//                       http .logout((logout) -> logout
//                               .logoutUrl("/logout").permitAll()
//                                .addLogoutHandler(((request, response, authentication) -> SecurityContextHolder.clearContext())));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
//
//}
