package cdw.springtraining.moviebooking.responseobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowResponse{


    private int slot;
    private int count;
    private String movieName;
    private String location;

}
