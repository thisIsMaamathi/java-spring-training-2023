package cdw.springtraining.gatekeeper.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user_blacklist")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="blacklist_id")
    private int blackListId;

    @Column(name="aadhar")
    private Long aadhar;

    @Column(name="user_type")
    private String user_type;

    public Blacklist(Long aadhar, String user_type) {
        this.aadhar = aadhar;
        this.user_type = user_type;
    }
}
