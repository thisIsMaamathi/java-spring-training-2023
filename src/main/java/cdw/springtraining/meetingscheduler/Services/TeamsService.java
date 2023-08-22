package cdw.springtraining.meetingscheduler.Services;

import cdw.springtraining.meetingscheduler.Entities.Teams;
import cdw.springtraining.meetingscheduler.Repository.EmployeeRepository;
import cdw.springtraining.meetingscheduler.Repository.RoomRepository;
import cdw.springtraining.meetingscheduler.Repository.TeamRepository;
import cdw.springtraining.meetingscheduler.Repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamsService {
    private EmployeeRepository employeeRepository;
    private RoomRepository roomRepository;
    private TeamRepository teamRepository;
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    public TeamsService(EmployeeRepository employeeRepository, RoomRepository roomRepository, TeamRepository teamRepository, TimeSlotRepository timeSlotRepository) {
        this.employeeRepository = employeeRepository;
        this.roomRepository = roomRepository;
        this.teamRepository = teamRepository;
        this.timeSlotRepository = timeSlotRepository;
    }
    public TeamsService(){}

    public void addTeams(Teams team){
        teamRepository.save(team);
    }

    public void deleteTeamsByTeamID(int teamId){
        teamRepository.deleteById(teamId);
    }



}
