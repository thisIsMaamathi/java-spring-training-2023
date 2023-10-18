package cdw.springtraining.gatekeeper.security;

import cdw.springtraining.gatekeeper.entites.User;
import cdw.springtraining.gatekeeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A custom implementation of the Spring Security UserDetailsService interface.
 * This service is responsible for loading user information from a repository and
 * converting it into a format that Spring Security can use for authentication and authorization.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    /**
     * Loads user details by the given username.
     *
     * @param username the username for which user details are to be loaded.
     * @return a UserDetails object containing user information and authorities.
     * @throws UsernameNotFoundException if no user is found with the provided username.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = (User) userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username"));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                authorities
        );

    }
}


