package cdw.springtraining.moviebooking.entity;

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
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="state")
    private  String state;

    @Column(name="isActive")
    private boolean isActive;


    @ManyToMany(mappedBy = "locationList",cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    private List<Show> regionalShowsList=new ArrayList<>();
}
