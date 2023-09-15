package cdw.springtraining.moviebooking.requestbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddShowRequest {
    private int slot;
    private int count;
    private String location;
    private String movie;
    private boolean isPrime;


}
