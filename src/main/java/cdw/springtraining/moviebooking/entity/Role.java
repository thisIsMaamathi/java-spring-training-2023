package cdw.springtraining.moviebooking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private int roleId;

    @Column(name="role_name")
    private String roleName;

    @JsonBackReference
    @ManyToMany(mappedBy = "roleList",cascade ={CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    private List<User> usersList=new ArrayList<>();
}
