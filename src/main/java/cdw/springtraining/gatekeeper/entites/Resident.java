package cdw.springtraining.gatekeeper.entites;

import cdw.springtraining.gatekeeper.validation.ValidAadhar;
import cdw.springtraining.gatekeeper.validation.ValidPhoneNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity that contains Resident information
 */

@Entity
@Table(name = "residents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_id")
    private int id;

    @Column(name = "resident_name")
    @NotBlank(message = "Username cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9@_-]{3,}$", message = "Enter a valid user name. A valid name must contain atleast two characters and no numbers or special characters")
    private String residentName;

    @Column(name = "aadhar")
    @ValidAadhar
    private Long aadhar;


    @Column(name = "residence_number")
    private int residenceNumber;

    @Column(name = "phone_number")
    @ValidPhoneNumber
    private Long phoneNumber;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "resident", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Visitors> visitorsList = new ArrayList<>();

    public Resident(String residentName, long aadhar, int residenceNumber, long phoneNumber, boolean isActive) {
        this.residentName = residentName;
        this.aadhar = aadhar;
        this.residenceNumber = residenceNumber;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;

    }

    public Resident(int id, String residentName, Long aadhar, int residenceNumber, Long phoneNumber, boolean isActive) {
        this.id = id;
        this.residentName = residentName;
        this.aadhar = aadhar;
        this.residenceNumber = residenceNumber;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }
}
