package cdw.springtraining.moviebooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_id")
    private int movieId;

    @Column(name="movie_name")
    private String movieName;

    @Column(name="description")
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "movie",cascade ={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Show> movieShowsList=new ArrayList<>();

}
