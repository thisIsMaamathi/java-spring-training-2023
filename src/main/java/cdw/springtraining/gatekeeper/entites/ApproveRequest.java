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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Entity for Storing the registration requests by the user
 */

@Entity
@Table(name = "approve_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestId;

    @Column(name = "user_name")
    @NotBlank(message = "Username cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9@_-]{3,}$", message = "Enter a valid user name. A valid name must contain atleast two characters and no numbers or special characters")
    private String userName;

    @Column(name = "first_name")
    @NotBlank(message = "Firstname cannot be blank")
    @Pattern(regexp = "^[A-Za-z ]{2,}$", message = "Enter a valid first name. A valid name must contain atleast two characters and no numbers or special characters")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^[A-Za-z ]{2,}$", message = "Enter a valid last name. A valid name must contain atleast two characters and no numbers or special characters")
    private String lastName;


    @Column(name = "email")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Enter a valid email id")
    private String email;

    @Column(name = "dob")
    @NotNull(message = "Date of birth cannot be blank")
    @Past(message = "Enter a proper date of birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "aadhar")
    @NotNull(message = "Aadhar cannot be blank")
    @ValidAadhar
    private Long aadhar;

    @Column(name = "house_number")
    private int residenceNumber;

    @Column(name = "phone_number")
    @NotNull(message = "Phone number cannot be blank")
    @ValidPhoneNumber
    private Long phoneNumber;

    @Column(name = "password")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(name = "user_type")
    @NotBlank(message = "Usertype cannot be blank")
    private String userType;

    @Column(name = "has_approved")
    private boolean hasApproved;

    public ApproveRequest(String userName, String firstName, String lastName, String email, LocalDate dateOfBirth, String gender, Long aadhar, int residenceNumber, Long phoneNumber, String password, String userType) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.aadhar = aadhar;
        this.residenceNumber = residenceNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userType = userType;
    }


}
