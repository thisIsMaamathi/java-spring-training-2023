package cdw.domaintraining.spring.meetingscheduler.repositories;

import cdw.domaintraining.spring.meetingscheduler.entities.Employee;
import cdw.domaintraining.spring.meetingscheduler.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
