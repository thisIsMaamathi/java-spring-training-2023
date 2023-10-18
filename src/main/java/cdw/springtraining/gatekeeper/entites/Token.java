package cdw.springtraining.gatekeeper.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This entity contains the jwt tokens of logged-in users
 */

@Table(name = "token")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tokenId;


    @Column(name = "jwt", unique = true)
    private String jwt;

    public Token(String jwt) {
        this.jwt = jwt;
    }
}
