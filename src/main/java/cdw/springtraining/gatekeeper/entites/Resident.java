package cdw.springtraining.gatekeeper.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="residents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="resident_id")
    private int id;

    @Column(name="resident_name")
    private String residentName;

    @Column(name="aadhar")
    private Long aadhar;


    @Column(name="residence_number")
    private int residenceNumber;

    @Column(name="phone_number")
    private Long phoneNumber;

    @Column(name="is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "resident",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    List<Visitors> visitorsList=new ArrayList<>();

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
