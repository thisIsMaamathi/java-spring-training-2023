package cdw.domaintraining.spring.meetingscheduler;

import cdw.domaintraining.spring.meetingscheduler.entities.Employee;
import cdw.domaintraining.spring.meetingscheduler.entities.Team;
import cdw.domaintraining.spring.meetingscheduler.repositories.TeamRepository;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.serviceimpl.BookingServices;

import cdw.domaintraining.spring.meetingscheduler.serviceimpl.UpdatingService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class MeetingschedulerApplication {

	@Autowired
	BookingServices bookingServices;

	@Autowired
	UpdatingService updatingService;

private static final Logger logger=LogManager.getLogger(MeetingschedulerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MeetingschedulerApplication.class, args);
	}
     @Bean
	 public CommandLineRunner runnerclass(String args[]){
		 return runner->{
			 System.out.println("hi");
//			 Employee e=new Employee("Maamathi","Krish","KM@CDW");
//			 TimeSlotRequest t=new TimeSlotRequest(LocalDate.of(2023,8,31), LocalTime.of(12,0),LocalTime.of(13,0));
////			 List<Integer> l=new ArrayList<>();
////			 l.add(101);
////			 l.add(1003);
////			 l.add(2001);
//
//              //bookingServices.bookForTeam(2001,4,t,101,"Scrum");
//               // bookingServices.bookOutsideTeam(2001,1,t,l,"JustAMeet");
//             //teamServices.addTeamMembers(e,2);
//			 //System.out.println(bookingServices.isSlotAvailable(t,101));
////			Employee employee=new Employee("Maamathi","Krishnan","Maamathi@cdw");
////			Team team=new Team("teamDummy",3);
// bookingServices.cancelMeeting(10);
//			 bookingServices.cancelMeeting(11);
//			 bookingServices.cancelMeeting(12);
			 //bookingServices.cancelMeeting(8);
//
//
//
//
//            // updatingService.findTimeSlotId(t,4);
//			// updatingService.changeTime(19,t);
//			 updatingService.changeMeetingRoom(19,1);
//
////
////		    team.getTeamMembersList().add(employee);
////			teamRepository.save(team);

		 };
	 }


}
