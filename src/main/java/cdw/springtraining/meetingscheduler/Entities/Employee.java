package cdw.springtraining.meetingscheduler.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @Column(name="employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    String lastName;
    @Column(name="email")
    String email;

    @ManyToMany(mappedBy = "teamsEmployeeList")
    private List<Teams> employeeTeamsList;

    @OneToMany(mappedBy = "employee")
    private List<TimeSlot> employeeTimeSlotList;



    public List<Teams> getEmployeeTeamsList() {
        return employeeTeamsList;
    }

    public void setEmployeeTeamsList(List<Teams> employeeTeamsList) {
        this.employeeTeamsList = employeeTeamsList;
    }

    public List<TimeSlot> getEmployeeTimeSlotList() {
        return employeeTimeSlotList;
    }

    public void setEmployeeTimeSlotList(List<TimeSlot> employeeTimeSlotList) {
        this.employeeTimeSlotList = employeeTimeSlotList;
    }

    public Employee(int employeeId, String firstName, String lastName, String email) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    Employee(){}

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


}
