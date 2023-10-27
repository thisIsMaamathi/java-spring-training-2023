package cdw.springtraining.gatekeeper.controller;


import cdw.springtraining.gatekeeper.api.AdminApi;
import cdw.springtraining.gatekeeper.models.UserResponse;
import cdw.springtraining.gatekeeper.models.UserAdminResponse;
import cdw.springtraining.gatekeeper.models.ResidentAdminResponse;
import cdw.springtraining.gatekeeper.models.GateKeeperAdminResponse;
import cdw.springtraining.gatekeeper.models.UpdateUserRequest;
import cdw.springtraining.gatekeeper.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Admin controller class consists of all endpoints accessible by admin.
 * This implements AdminApi from generated using OpenAPiCodegen
 */
@RestController
public class AdminController implements AdminApi {
    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Endpoint for approving a user
     *
     * @param requestId (required)
     * @return Response entity containing a Userobject
     */

    @Override
    public ResponseEntity<UserResponse> approveUser(@PathVariable Integer requestId) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.approveRequest(requestId));
    }

    /**
     * Endpoint for getting a list of all residents
     *
     * @return a Response ENtity containing List of Resident objects
     */
    @Override
    public ResponseEntity<List<ResidentAdminResponse>> getResidents() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllResidents());
    }

    /**
     * Endpoint for deleting user
     *
     * @param userId
     * @return Response entity containing String
     */
    @Override
    public ResponseEntity<Void> deleteUsers(@PathVariable Integer userId) {
        adminService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint for update user
     *
     * @param userId
     * @return userAdinResponse Response entity containing updated user
     */
    @Override
    public ResponseEntity<UserAdminResponse> updateUsers(@PathVariable Integer userId, @RequestBody UpdateUserRequest updateResident) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateUser(userId, updateResident));

    }

    /**
     * Endpoint for getting a resident by id
     *
     * @param userId
     * @return Response entity containing Resident Object
     */
    @Override
    public ResponseEntity<UserAdminResponse> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getUsersById(userId));
    }

    /**
     * Endpoint for  getting all activeGateKeepers
     *
     * @return Response entity containing a list of gatekeepers
     */
    @Override
    public ResponseEntity<List<GateKeeperAdminResponse>> getGateKeeper() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllGateKeepers());
    }


    /**
     * Endpoint for viewing all registration requests
     *
     * @return Response entity containing a list of User objects
     */
    @Override
    public ResponseEntity<List<UserResponse>> viewRegnRequest() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.viewApprovedRequests());
    }

    /**
     * Endpoint for rejecting a user
     *
     * @param requestId (required)
     * @return user response
     */
    @Override
    public ResponseEntity<UserResponse> rejectUser(@PathVariable("requestId") Integer requestId) {
        return ResponseEntity.status(200).body(adminService.rejectUser(requestId));
    }

    /**
     * Endpoint for viewling list of approved requests
     * @return List of user response
     */
    @Override
    public ResponseEntity<List<UserResponse>> viewApprovedRequest() {
        return ResponseEntity.status(200).body(adminService.viewRequestApproved());
    }

    /**
     * Endpoint for viewing rejected requests
     * @return list of user response
     */

    @Override
    public ResponseEntity<List<UserResponse>> viewRejectedRequest() {
        return ResponseEntity.status(200).body(adminService.viewRequestRejected());
    }


}
