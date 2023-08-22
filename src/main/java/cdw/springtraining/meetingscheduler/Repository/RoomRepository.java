package cdw.springtraining.meetingscheduler.Repository;

import cdw.springtraining.meetingscheduler.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
}
