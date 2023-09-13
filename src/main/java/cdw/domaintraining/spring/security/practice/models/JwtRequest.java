package cdw.domaintraining.spring.security.practice.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtRequest {
    String name;
    String password;
}
