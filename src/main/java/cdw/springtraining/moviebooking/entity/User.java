package cdw.springtraining.moviebooking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="dob")
    private LocalDate dob;

    @Column(name="gender")
    private String gender;

    @Column(name="age")
    private int age;

    @Column(name="mail_id")
    private String mailId;

    @Column(name="password")
    private String password;
    @JsonManagedReference
    @ManyToMany(cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList=new ArrayList<>();
    @JsonBackReference
    @ManyToMany(mappedBy = "ticketsList",cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    private List<Show> bookedShows=new ArrayList<>();

}
