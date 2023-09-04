package cdw.domaintraining.spring.meetingscheduler.entities;

import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    int employeeId;
    @Column(name="first_name")
    String firstName;
    @Column(name="last_name")
    String lastName;
    @Column(name="email")
    String email;
    @JsonManagedReference
    @ManyToMany( mappedBy = "teamMembersList" ,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    List<Team> teamsList=new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "employee",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<TimeSlot> EmployeeBookingTimeslot=new ArrayList<>();


    public Employee(){}

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Team> getTeamsList() {
        return teamsList;
    }

    public void setTeamsList(List<Team> teamsList) {
        this.teamsList = teamsList;
    }

    public List<TimeSlot> getEmployeeBookingTimeslot() {
        return EmployeeBookingTimeslot;
    }

    public void setEmployeeBookingTimeslot(List<TimeSlot> employeeBookingTimeslot) {
        EmployeeBookingTimeslot = employeeBookingTimeslot;
    }
    public void addTeamtoEmployee(Team team){this.teamsList.add(team);}






}
