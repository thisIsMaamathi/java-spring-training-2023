package cdw.domaintraining.spring.meetingscheduler.responseentity;

import cdw.domaintraining.spring.meetingscheduler.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class UnavailableEmployeesResponse {

    List<Employee>  employeeList=new ArrayList<>();

    public UnavailableEmployeesResponse(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
