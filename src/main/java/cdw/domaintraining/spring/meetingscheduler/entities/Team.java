package cdw.domaintraining.spring.meetingscheduler.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamId;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "team_count")
    private int teamCount;
    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(
            name = "team_employee",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> teamMembersList = new ArrayList<>();
    @JsonManagedReference
    @ManyToMany(mappedBy = "teamsOfATimeslot", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<TimeSlot> teamsTimeSlotList=new ArrayList<>();

    public Team(){}


    public Team(String teamName, int teamCount) {
        this.teamName = teamName;
        this.teamCount = teamCount;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(int teamCount) {
        this.teamCount = teamCount;
    }

    public List<Employee> getTeamMembersList() {
        return teamMembersList;
    }

    public void setTeamMembersList(List<Employee> teamMembers) {
        this.teamMembersList = teamMembers;
    }

    public List<TimeSlot> getTeamsTimeSlotList() {
        return teamsTimeSlotList;
    }

    public void setTeamsTimeSlotList(List<TimeSlot> teamsTimeSlotList) {
        this.teamsTimeSlotList = teamsTimeSlotList;
    }

    public void addTimeSlotToTeams(TimeSlot timeSlot){this.teamsTimeSlotList.add(timeSlot);}

    public  void addMembersToTeams(Employee employee){this.teamMembersList.add(employee);}

}
