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
    @Column(name="gatekeeper_id")
    private int gatekeeper_id;

    @Column(name="gate_id")
    private int gateId;

    @Column(name="aadhar")
    private Long aadhar;

    @Column(name="gatekeeper_name")
    private String gatekeeper_name;

    @Column(name="phone_number")
    private Long phone_number;

    @Column(name="is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "gateKeeper",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    List<Visitors> visitorsList=new ArrayList<>();


    public GateKeeper(int gateId, Long aadhar, String gatekeeper_name, Long phone_number, boolean isActive) {
        this.gateId = gateId;
        this.aadhar = aadhar;
        this.gatekeeper_name = gatekeeper_name;
        this.phone_number = phone_number;
        this.isActive = isActive;
    }

    public GateKeeper(int gatekeeper_id, int gateId, Long aadhar, String gatekeeper_name, Long phone_number, boolean isActive) {
        this.gatekeeper_id = gatekeeper_id;
        this.gateId = gateId;
        this.aadhar = aadhar;
        this.gatekeeper_name = gatekeeper_name;
        this.phone_number = phone_number;
        this.isActive = isActive;
    }
}
