package cdw.springtraining.moviebooking.requestbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowRequest {
    private Integer slot;
    private Integer count;
    private LocalDate date;
    private String location;
    private String movie;



}
