package cdw.springtraining.moviebooking.responseobjects;

import cdw.springtraining.moviebooking.entity.Show;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowsListResponse {
    List<Show> showList=new ArrayList<>();

}
