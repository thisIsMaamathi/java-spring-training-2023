package cdw.springtraining.gatekeeper.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private int roleId;

    @Column(name="role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<User> userList=new ArrayList<>();
}
