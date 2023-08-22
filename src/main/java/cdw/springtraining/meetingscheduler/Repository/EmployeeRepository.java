package cdw.springtraining.meetingscheduler.Repository;

import cdw.springtraining.meetingscheduler.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {


}
