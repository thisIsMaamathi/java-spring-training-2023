package cdw.springtraining.moviebooking.responseobjects;

import cdw.springtraining.moviebooking.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private String movieName;
    private String description;

    public MovieResponse(Movie movie){
        this.movieName=movie.getMovieName();
        this.description=movie.getDescription();
    }


}
