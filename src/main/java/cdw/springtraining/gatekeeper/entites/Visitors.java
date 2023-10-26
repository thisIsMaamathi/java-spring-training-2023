package cdw.springtraining.gatekeeper.entites;

import cdw.springtraining.gatekeeper.validation.ValidAadhar;
import cdw.springtraining.gatekeeper.validation.ValidPhoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "visitor_name")
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[A-Za-z ]{2,}$", message = "Enter a valid first name. A valid name must contain atleast two characters and no numbers or special characters")
    private String visitorName;

    @Column(name = "date")
    @FutureOrPresent(message = "Enter a date after this day")
    private LocalDate date;

    @Column(name = "aadhar")
    @ValidAadhar
    private Long aadhar;

    @Column(name = "phone")
    @ValidPhoneNumber
    private Long phone;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "pass")
    @NotBlank(message = "Without pass,you cannot schedule a visit")
    private String pass;

    @Column(name = "is_approved")
    private String isApproved;

    @Column(name="residence_id")
    private int residenceId;

    @Column(name = "has_checked_in")
    private boolean hasCheckedIn;

    @Column(name="approved_by")
    @Pattern(regexp = "approved|rejected", message = "invalid response")
    private String approvedBy;

    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "visitor_users_list",
            joinColumns = @JoinColumn(name="visitor_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    List<Users> usersList=new ArrayList<>();

    public Visitors(String visitorName, LocalDate date, Long aadhar, Long phone, String additionalInfo,int residenceId) {
        this.visitorName = visitorName;
        this.date = date;
        this.aadhar = aadhar;
        this.phone = phone;
        this.additionalInfo = additionalInfo;
        this.residenceId=residenceId;

    }
}
