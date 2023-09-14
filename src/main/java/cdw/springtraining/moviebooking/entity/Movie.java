package cdw.springtraining.moviebooking.entity;

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

    @ManyToMany(mappedBy = "moviesList",cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    private List<Show> movieShowsList=new ArrayList<>();

}
