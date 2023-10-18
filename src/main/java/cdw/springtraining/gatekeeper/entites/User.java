package cdw.springtraining.gatekeeper.entites;

import cdw.springtraining.gatekeeper.validation.ValidAadhar;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This entity contains user information
 */

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

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

    @Column(name = "password")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(name = "aadhar")
    @ValidAadhar
    private Long aadhar;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Roles> roles = new ArrayList<>();

    public User(String userName, String firstName, String lastName, String email, LocalDate dateOfBirth, String gender, String password, Long aadhar) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.password = password;
        this.aadhar = aadhar;
    }


}
