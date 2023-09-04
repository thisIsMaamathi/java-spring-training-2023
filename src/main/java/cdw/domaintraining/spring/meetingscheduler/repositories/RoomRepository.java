package cdw.domaintraining.spring.meetingscheduler.repositories;

import cdw.domaintraining.spring.meetingscheduler.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {

}
