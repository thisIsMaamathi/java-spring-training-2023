package cdw.springtraining.moviebooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name="cancel_req")
public class CancellationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int user_id;
    private int show_id;


}
