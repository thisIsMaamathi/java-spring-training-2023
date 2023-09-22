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
public class UserRegistrationRequest {

    private String userName;


    private LocalDate dob;


    private String gender;

    private int age;


    private String mailId;


    private String password;

}
