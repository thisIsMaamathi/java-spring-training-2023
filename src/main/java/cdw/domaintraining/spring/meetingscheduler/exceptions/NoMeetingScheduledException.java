package cdw.domaintraining.spring.meetingscheduler.exceptions;

public class NoMeetingScheduledException extends Exception{

    public NoMeetingScheduledException(String msg){
        super(msg);
    }

}
