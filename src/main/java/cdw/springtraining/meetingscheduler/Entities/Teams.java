package cdw.springtraining.meetingscheduler.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teams")
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="team_id")
    private int teamId;
    @Column(name="team_name")
    private String teamName;
    @Column(name="team_count")
    private int teamCount;

    @ManyToMany
    @JoinTable(name="team_employee",
            joinColumns= @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> teamsEmployeeList=new ArrayList<>();

    @ManyToMany(mappedBy = "timeSlotTeamList")
    private List<TimeSlot> teamsTimeSlotList;




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

    public List<Employee> getTeamsEmployeeList() {
        return teamsEmployeeList;
    }

    public void setTeamsEmployeeList(List<Employee> teamsEmployeeList) {
        this.teamsEmployeeList = teamsEmployeeList;
    }

    public List<TimeSlot> getTeamsTimeSlotList() {
        return teamsTimeSlotList;
    }

    public void setTeamsTimeSlotList(List<TimeSlot> teamsTimeSlotList) {
        this.teamsTimeSlotList = teamsTimeSlotList;
    }

    public void setTeamCount(int teamCount) {
        this.teamCount = teamCount;
    }
    public Teams(int teamId, String teamName, int teamCount) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCount = teamCount;
    }
   Teams(){}

    public void addEmployeeToTeams(Employee employee){
        this.teamsEmployeeList.add(employee);
    }


}
