package cdw.springtraining.gatekeeper.entites;

import jakarta.persistence.*;
import lombok.*;

@Table(name="tokens")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tokenId;

    @Column(name="tokens")
    private String token;


    public Token(String token) {
        this.token=token;
    }
}
