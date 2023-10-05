package cdw.springtraining.gatekeeper.security;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
//@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

   JwtAuthenticationFilter jwtAuthenticationFilter;
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomUserDetailsService customUserDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf-> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("register").permitAll();
                    authorize.requestMatchers("login").permitAll();
                   authorize.requestMatchers("requests/**").hasAuthority("ADMIN");
                   authorize.requestMatchers("admin/**").hasAuthority("ADMIN");
                    authorize.requestMatchers("residents/**").hasAuthority("ADMIN");
                    authorize.requestMatchers("gatekeepers/**").hasAuthority("ADMIN");
                 authorize.requestMatchers("resident/**").hasAuthority("RESIDENT");
                    authorize.requestMatchers("gatekeeper/**").hasAuthority("GATEKEEPER");
                    authorize.requestMatchers("visitor/**").hasAuthority("VISITOR");

//
//                    authorize.anyRequest().authenticated();
                    //authorize.anyRequest().permitAll();
                });
       http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


}
//
//}
