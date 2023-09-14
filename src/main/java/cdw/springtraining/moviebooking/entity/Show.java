package cdw.springtraining.moviebooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="shows")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="show_id")
    private int show_id;

    @Column(name="slot")
    private int slot;

    @Column(name = "count")
    private int count;

    
    @ManyToMany(cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "shows_movie",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> moviesList=new ArrayList<>();

    @ManyToMany(cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "shows_location",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private List<Location> locationList=new ArrayList<>();


    @ManyToMany(cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "tickets",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> ticketsList=new ArrayList<>();



}
