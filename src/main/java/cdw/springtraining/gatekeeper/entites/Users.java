package cdw.springtraining.gatekeeper.entites;

import cdw.springtraining.gatekeeper.validation.ValidAadhar;
import cdw.springtraining.gatekeeper.validation.ValidPhoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * This entity contains user information
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "user_name",unique = true)
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
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "Enter valid mailId")
    private String email;

    @Column(name = "dob")
    @NotNull(message = "Date of birth cannot be blank")
    @Past(message = "Enter a proper date of birth")
    private LocalDate dob;

    @Column(name = "gender")
    @Pattern(regexp = "male|female|others", message = "invalid gender")
    private String gender;

    @Column(name = "password")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(name = "aadhar")
    @ValidAadhar
    private Long aadhar;

    @Column(name = "phone_number")
    @NotNull(message = "Phone number cannot be blank")
    @ValidPhoneNumber
    private Long phoneNumber;

    @Column(name = "residence_number")
    private int residenceNumber;

    @Column(name="user_type")
    @Pattern(regexp = "resident|gatekeeper|admin", message = "Enter a valid userType")
    private String userType;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name="is_approved")
    @Pattern(regexp = "approved|rejected", message = "invalid response")
    private String isApproved;

    @Column(name="approved_by")
    private String approvedBy;

    public Users(String userName, String firstName, String lastName, String email, LocalDate dob, String gender, Long aadhar, Long phoneNumber, int residenceNumber, String userType) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.aadhar = aadhar;
        this.phoneNumber = phoneNumber;
        this.residenceNumber = residenceNumber;
        this.userType = userType;
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Roles> rolesList = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, mappedBy = "usersList")
    List<Visitors> visitorsList = new ArrayList<>();


    public Users(String username, String password, List<GrantedAuthority> authorities) {
        this.userName=username;
        this.password=password;

    }
}
