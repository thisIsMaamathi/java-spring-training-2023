package cdw.springtraining.gatekeeper.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for setting up security in the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    CustomUserDetailsService customUserDetailsService;

    /**
     * Defines the security filter chain and related security configurations.
     * @param http the HttpSecurity object for configuring security settings.
     * @return a SecurityFilterChain with configured security settings.
     * @throws Exception if there is an issue configuring security.
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults())
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
         
                });
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
