package cdw.springtraining.gatekeeper.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="approve_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="request_id")
    private int requestId;

    @Column(name="resident_name")
    private String userName;

    @Column(name="aadhar")
    private Long aadhar;

    @Column(name="residence_number")
    private int residenceNumber;

    @Column(name="phone_number")
    private Long phoneNumber;

    @Column(name="password")
    private String password;

    @Column(name="user_type")
    private String userType;

    @Column(name="has_approved")
    private boolean hasApproved;

    public ApproveRequest(String userName, Long aadhar, int residenceNumber, Long phoneNumber, String password, String userType) {
        this.userName = userName;
        this.aadhar = aadhar;
        this.residenceNumber = residenceNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userType = userType;
    }
}
