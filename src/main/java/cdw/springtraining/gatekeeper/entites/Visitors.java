package cdw.springtraining.gatekeeper.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="visitors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitors {

    @Id
    @Column(name="visitor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int visitorId;

    @Column(name = "name")
    private String name;

    @Column(name="date")
    private LocalDate date;

    @Column(name="aadhar")
    private Long aadhar;

    @Column(name="phone")
    private Long phone;

    @Column(name="house_number")
    private int houseNumber;

    @Column(name="additional_info")
    private String additionalInfo;

    @Column(name="pass")
    private String pass;

    @Column(name="is_approved")
    private boolean isApproved;



    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name="resident")
    Resident resident;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name="gatekeeper")
    GateKeeper gateKeeper;

    public Visitors(String name, LocalDate date, Long aadhar, Long phone, String additionalInfo) {
        this.name = name;
        this.date = date;
        this.aadhar = aadhar;
        this.phone = phone;
        this.additionalInfo = additionalInfo;

    }
}
