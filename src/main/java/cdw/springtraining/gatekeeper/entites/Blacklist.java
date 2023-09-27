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
    private long aadhar;
    

}
