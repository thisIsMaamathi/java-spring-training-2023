package cdw.springtraining.moviebooking.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    //

//
////    @Bean
////    public static PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
////    @Bean
////    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////
////        http.csrf(csrf->csrf.disable())
////                .authorizeHttpRequests((authorize) -> {
////                    authorize.requestMatchers("/**").permitAll();
////                    authorize.anyRequest().authenticated();
////                });
////        return http.build();
////    }
//    private static List<UserDetails> APPLICATION_USERS = Arrays.asList(
//            new User(
//                    "maamathi",
//                    "mathi",
//                    Collections.singleton(new SimpleGrantedAuthority("END_USER"))
//            ),
//            new User(
//                    "naveen_nk",
//                    "naveensnr",
//                    Collections.singleton(new SimpleGrantedAuthority("BUSINESS_USER"))
//            ), new User(
//                    "nijin",
//                    "nijinmngr",
//                    Collections.singleton(new SimpleGrantedAuthority("ADMIN"))
//            )
//    );
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf-> csrf.disable())
        .authorizeHttpRequests((authorize) -> {
//            authorize.requestMatchers("auth/**").permitAll();
//            authorize.requestMatchers("bu/**").hasAuthority("BUSINESS_USER");
//            authorize.requestMatchers("admin/**").hasAuthority("ADMIN");
//            authorize.requestMatchers("user/**").hasAnyAuthority("BUSINESS_USER","END_USER","ADMIN");
//
//            authorize.anyRequest().authenticated();
            authorize.anyRequest().permitAll();
        });
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                return APPLICATION_USERS.stream().filter(userDetails -> userDetails.getUsername().equals(username))
//                        .findFirst().orElseThrow(() -> new UsernameNotFoundException("user not found"));
//            }
//        };
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(customUserDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
     @Bean
public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


}
//
//}
