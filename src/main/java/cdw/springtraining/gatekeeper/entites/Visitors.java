package cdw.springtraining.gatekeeper.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="visitors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitors {

    @Id
    @Column(name="visitor_id")
    private int visitorId;

    @Column(name = "visitor_name")
    private String visitorName;

    @Column(name="aadhar")
    private long aadhar;

    @Column(name="isPermitted")
    private boolean isPermitted;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name="resident")
    Resident resident;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name="gatekeeper")
    GateKeeper gateKeeper;


}
