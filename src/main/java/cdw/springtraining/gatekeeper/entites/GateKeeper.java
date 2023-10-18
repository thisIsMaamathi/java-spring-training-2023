package cdw.springtraining.gatekeeper.entites;

import cdw.springtraining.gatekeeper.validation.ValidAadhar;
import cdw.springtraining.gatekeeper.validation.ValidPhoneNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity that contains Gatekeepers information
 */
@Entity
@Table(name = "gate_keepers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class GateKeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gatekeeper_id")
    private int gatekeeper_id;

    @Column(name = "gate_id")
    private int gateId;

    @Column(name = "aadhar")
    @ValidAadhar
    private Long aadhar;

    @Column(name = "gatekeeper_name")
    @NotBlank(message = "Username cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9@_-]{3,}$", message = "Enter a valid user name. A valid name must contain atleast two characters and no numbers or special characters")
    private String gatekeeperName;


    @Column(name = "phone_number")
    @ValidPhoneNumber
    private Long phone_number;

    @Column(name = "is_active")
    private boolean isActive;


    @OneToMany(mappedBy = "gateKeeper", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<Visitors> visitorsList = new ArrayList<>();


    public GateKeeper(int gateId, Long aadhar, String gatekeeperName, Long phone_number, boolean isActive) {
        this.gateId = gateId;
        this.aadhar = aadhar;
        this.gatekeeperName = gatekeeperName;
        this.phone_number = phone_number;
        this.isActive = isActive;
    }

    public GateKeeper(int gatekeeper_id, int gateId, Long aadhar, String gatekeeperName, Long phone_number, boolean isActive) {
        this.gatekeeper_id = gatekeeper_id;
        this.gateId = gateId;
        this.aadhar = aadhar;
        this.gatekeeperName = gatekeeperName;
        this.phone_number = phone_number;
        this.isActive = isActive;
    }
}
