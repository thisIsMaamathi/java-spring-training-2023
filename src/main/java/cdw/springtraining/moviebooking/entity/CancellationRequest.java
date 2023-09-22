package cdw.springtraining.moviebooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cancel_req")
@Getter
@Setter
@NoArgsConstructor

public class CancellationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user_id;
    private int show_id;

    public CancellationRequest(int user_id,int show_id){
        this.show_id=show_id;
        this.user_id=user_id;
    }


}
