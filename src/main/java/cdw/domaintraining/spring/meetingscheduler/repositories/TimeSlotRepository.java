package cdw.domaintraining.spring.meetingscheduler.repositories;

import cdw.domaintraining.spring.meetingscheduler.entities.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot,Integer> {


    List<TimeSlot> findAllByDate(LocalDate date);

    List<TimeSlot> findAllByRoomIdAndDate(int roomId, LocalDate date);


    List<TimeSlot> findAllByRoomId(int roomId);
}
