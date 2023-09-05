package cdw.domaintraining.spring.meetingscheduler.exceptions;

import java.util.function.Supplier;

public class NoSuchRoomFoundException extends Exception {
   public NoSuchRoomFoundException(String msg){
       super(msg);
   }



}
