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
@Table(name="locations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @Column(name="location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;

    @Column(name="name")
    private String name;

    @Column(name="state")
    private  String state;

    @Column(name="isActive")
    private boolean isActive;

    @Column(name="isPrime")
    private boolean isPrime;

    public Location(String name, String state, boolean isPrime) {

        this.name = name;
        this.state = state;
        this.isPrime = isPrime;
    }
    @JsonIgnore
    @OneToMany(mappedBy = "location",cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    private List<Show> regionalShowsList=new ArrayList<>();
}
