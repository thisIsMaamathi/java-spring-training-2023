package cdw.springtraining.gatekeeper.constant;

/**
 * This class contains all common constants used throughout the application
 */
public class CommonConstants {

    public static final String APPROVE_REQUEST_NOT_FOUND="The request is not present";
    public static final String USER_NOT_FOUND="No user of the requested type exists";
    public static final String USER_ALREADY_REGISTERED="This user have already registered";
    public static final String RESIDENCE_HAD_BEEN_REGISTERED="This residence has already been registered";
    public static final String BLACKLISTED_GATEKEEPER="This gatekeeper had been blacklisted";
    public static final String NO_ENTRIES = "No entries have been made here yet";
    public static final String RESIDENT_WAS_DELETED = "The resident had been deleted";
    public static final String USER_HAD_BEEN_REMOVED = "The requested user was deleted";
    public static final String NO_ACTIVE_RESIDENTS = "Currently there are no active residents" ;
    public static final String NO_ACTIVE_GATE_KEEPERS ="Currently there are no active gatekeepers" ;
    public static final String GATEKEEPER_NOT_FOUND ="The requested gate keeper was not found" ;
    public static final String BLACKLISTED_VISITOR = "Vistor had been blacklisted....please contact admin";
    public static final String RESIDENT_NOT_FOUND ="Resident not found" ;
    public static final String VISIT_ALREADY_SCHEDULED = "Your visit was already scheduled for this day";
    public static final String RESIDENT_HAD_BEEN_DELETED = "The requested resident was deleted" ;
    public static final String VISITOR_NOT_FOUND = "Visitor Not Found" ;
    public static final String USER_TYPE_MISMATCH = "Enter a valid user type";
    public static final String ADDED_TO_BLACKLIST = "Added user to blacklist" ;
    public static final String APPROVED_VISITOR = "Approved the visitor";
    public static final String REJECTED_VISITOR = "Rejected the visitor" ;
    public static final String BLACKLISTED_EXCEPTION = "Sorry, you were blacklisted.....contact admin for more details";
    public static final String APPENDED_REQUEST = "Appended Request";
    public static final String CANCELLED_VISIT = "Cancelled this scheduled visit";
    public static final String GATEKEPER_CANNOT_HAVE_RESIDENCE_NUMBER = "Gatekeeper cannot have a residence id" ;
    public static final String RESIDENCE_NUMBER_NOT_MENTIONED = "Please enter your residence number";
    public static final String NO_REQUEST_TO_APPROVE = "No requests for approval";
    public static final String USER_ALREADY_DELETED = "The requested user had already been deleted";
    public static final String ALREADY_BLACKLISTED = "user was already blacklisted";
    public static final String USER_ALREADY_APPROVED = "User had already been approved";
    public static final String USER_IS_ADMIN = "The user id you are trying to enter belongs to an admin. You cannot approve an admin" ;
    public static final String YET_TO_BE_APPROVED = "The admin has not yet approved your request";
    public static final String VISITOR_ALREADY_ENTERED = "The visitor had already entered the apartment";
    public static final String LOGGED_OUT = "Logged out";
    public static final String NO_REQUEST_HAVEBEEN_APPROVED = "No requests have been approved";
    public static final String MAL_INFORMED_TOKEN = "Invalid JWT token: {}";
    public static final String EXPIRED_JWT = "JWT token is expired: {}";
    public static final String UNSUPPORTED_JWT = "JWT token is unsupported: {}";
    public static final String ILLEGAL_ARGUMENT ="JWT token is empty: {}" ;
    public static final String ADMIN ="admin" ;
    public static final String APPROVED = "approved";
    public static final String RESIDENT = "resident";
    public static final String GATEKEEPER = "gatekeeper";
    public static final String REJECTED = "rejected";
    public static final String REGISTERED_SUCCESSFULLY = "Registered successfully";
    public static final String VISITOR = "visitor";
    public static final  String NO_VISITS_SCHEDULED = "No visits scheduled for this day" ;
    public static final String ENTER_VALID_AADHAR = "Enter valid aadhar number";
    public static final String ENTER_VALID_PHONE_NUMBER = "Enter valid indian phone number";
    public static final String NO_REJECTED_REQUESTS = "No requests have been rejected";
    public static final String USER_HAD_BEEN_REJECTED = "You cannot login, you have been rejected. Please contact admin" ;
    public static final String LOGGED_OUT_USER = "You have logged out, please sign in again";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer " ;
    public static final String SOME_PROBLEM_OCCURED = "Some problem occurred";
}
