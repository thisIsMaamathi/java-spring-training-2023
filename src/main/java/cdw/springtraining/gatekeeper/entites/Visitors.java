package cdw.springtraining.gatekeeper.entites;

import cdw.springtraining.gatekeeper.validation.ValidAadhar;
import cdw.springtraining.gatekeeper.validation.ValidPhoneNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * This entity contains Visitor information
 */
@Entity
@Table(name = "visitors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visitors {

    @Id
    @Column(name = "visitor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int visitorId;

    @Column(name = "name")
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[A-Za-z ]{2,}$", message = "Enter a valid first name. A valid name must contain atleast two characters and no numbers or special characters")
    private String name;

    @Column(name = "date")
    @FutureOrPresent(message = "Enter a date after this day")
    private LocalDate date;

    @Column(name = "aadhar")
    @ValidAadhar
    private Long aadhar;

    @Column(name = "phone")
    @ValidPhoneNumber
    private Long phone;

    @Column(name = "house_number")
    private int houseNumber;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "pass")
    @NotBlank(message = "Without pass,you cannot schedule a visit")
    private String pass;

    @Column(name = "is_approved")
    private boolean isApproved;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "resident")
    Resident resident;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "gatekeeper")
    GateKeeper gateKeeper;

    public Visitors(String name, LocalDate date, Long aadhar, Long phone, String additionalInfo) {
        this.name = name;
        this.date = date;
        this.aadhar = aadhar;
        this.phone = phone;
        this.additionalInfo = additionalInfo;

    }
}
