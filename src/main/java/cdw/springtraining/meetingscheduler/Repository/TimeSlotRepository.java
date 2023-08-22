package cdw.springtraining.meetingscheduler.Repository;

import cdw.springtraining.meetingscheduler.Entities.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot,Integer> {

    List<TimeSlot> findAllByRoomId(int roomId);
    List<TimeSlot> findAllByDate(LocalDate date);


}
