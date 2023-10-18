package cdw.springtraining.gatekeeper.entites;

import cdw.springtraining.gatekeeper.validation.ValidAadhar;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity that holds the Blaclisted users ,both gateKeepers and visitors
 */
@Entity
@Table(name = "user_blacklist")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blacklist_id")
    private int blackListId;

    @Column(name = "aadhar")
    @ValidAadhar
    private Long aadhar;

    @Column(name = "user_type")
    @NotNull(message = "UserType cannot be blank")
    private String user_type;

    public Blacklist(Long aadhar, String user_type) {
        this.aadhar = aadhar;
        this.user_type = user_type;
    }
}
