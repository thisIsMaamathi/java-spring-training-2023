package cdw.springtraining.meetingscheduler;

import cdw.springtraining.meetingscheduler.Services.BookingService;
import cdw.springtraining.meetingscheduler.Services.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class MeetingschedulerApplication {

@Autowired
BookingService service;
	public static void main(String[] args) {
		SpringApplication.run(MeetingschedulerApplication.class, args);

	}
	@Bean
public CommandLineRunner commandLineRunner(String [] args){
		return runner->{
			//service.bookForTeam(2001,101, LocalDate.of(2023,11,1), LocalTime.of(2,30),LocalTime.of(3,30),02);
   			//service.cancelMeeting(2);
			//service.viewAllMeetings();
			//service.viewMeetingByDate(LocalDate.of(2023,8,21));
			//service.cancelMeeting(6);
		};
}


}
