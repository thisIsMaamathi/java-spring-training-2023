package cdw.springtraining.gatekeeper.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * This entity contains different roles that a user can take
 */

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> userList = new ArrayList<>();
}
