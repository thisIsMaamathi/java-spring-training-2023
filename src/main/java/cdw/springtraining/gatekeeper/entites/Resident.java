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
    private long aadhar;


    @Column(name="residence_number")
    private int residenceNumber;

    @Column(name="phone_number")
    private int phoneNumber;

    @OneToMany(mappedBy = "resident",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    List<Visitors> visitorsList=new ArrayList<>();

}
