package cdw.springtraining.moviebooking.responseobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookedShowResponse {
        private int showId;
        private int slot;
        private String movieName;
        private String location;
        private String bookedBy;

}
