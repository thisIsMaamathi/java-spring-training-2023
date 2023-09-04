package cdw.domaintraining.spring.meetingscheduler.repositories;

import cdw.domaintraining.spring.meetingscheduler.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Integer> {
}
