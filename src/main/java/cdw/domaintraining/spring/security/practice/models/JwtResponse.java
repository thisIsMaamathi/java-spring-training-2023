package cdw.domaintraining.spring.security.practice.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
    String jwtToken;
    String tokentype;
}
