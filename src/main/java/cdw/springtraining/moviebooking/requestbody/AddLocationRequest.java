package cdw.springtraining.moviebooking.requestbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddLocationRequest {
    private int id;
    private String name;
    private String state;
    private boolean isPrime;

}
