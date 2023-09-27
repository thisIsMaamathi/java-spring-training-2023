package cdw.springtraining.gatekeeper.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="gate_keepers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class GateKeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gatekeeper_id")
    private int gatekeeper_id;

    @Column(name="aadhar")
    private long aadhar;

    @Column(name="gatekeeper_name")
    private String gatekeeper_name;

    @Column(name="phone_number")
    private int phone_number;

    @OneToMany(mappedBy = "gateKeeper",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    List<Visitors> visitorsList=new ArrayList<>();


}
