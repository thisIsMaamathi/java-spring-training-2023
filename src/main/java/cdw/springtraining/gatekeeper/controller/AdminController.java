package cdw.springtraining.gatekeeper.controller;


import cdw.springtraining.gatekeeper.api.AdminApi;

import cdw.springtraining.gatekeeper.models.CreateResident;
import cdw.springtraining.gatekeeper.models.GateKeeperObject;
import cdw.springtraining.gatekeeper.models.ResidentObject;
import cdw.springtraining.gatekeeper.models.UpdateResident;
import cdw.springtraining.gatekeeper.models.CreateGateKeeper;
import cdw.springtraining.gatekeeper.models.UpdateGateKeeper;
import cdw.springtraining.gatekeeper.models.UserObject;
import cdw.springtraining.gatekeeper.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Endpoint for getting a l;ist of all residents
     * @return a Response ENtity containing List of Resident objects
     */
    @Override
    public ResponseEntity<List<ResidentObject>> getResidents() {
        return ResponseEntity.ok(adminService.getAllResidents());

    }

    /**
     * Endpoint for creating a new resident
     * @param request
     * @return a Response Entity containing Resident object containing information of the resident created
     */
    @Override
    public ResponseEntity<ResidentObject> createResidents(@RequestBody CreateResident request) {
        return ResponseEntity.ok(adminService.createNewResident(request));
    }

    /**
     * Endpoint for deleting residents
     * @param residentId
     * @return Response entity containing String
     */
    @Override
    public ResponseEntity<String> deleteResidents(@PathVariable Integer residentId) {
        return ResponseEntity.status(200).body(adminService.deleteResident(residentId));
    }

    /**
     * Endpoint for update resident
     * @param residentId
     * @param updateResident
     * @return Response entity containing update Resident
     */
    @Override
    public ResponseEntity<ResidentObject> updateResidents(@PathVariable Integer residentId, @RequestBody UpdateResident updateResident) {
        return ResponseEntity.ok(adminService.updateResident(residentId, updateResident));

    }

    /**
     * Endpoint for getting a resident by id
     * @param residentId
     * @return Response entity containing Resident Object
     */
    @Override
    public ResponseEntity<ResidentObject> getResidentsById(@PathVariable Integer residentId) {
        return ResponseEntity.ok(adminService.getResidentById(residentId));
    }

    /**
     * Endpoint for creating a gatekeeper
     * @param request
     * @return Response entity containing Gatekeeper Object
     */
    @Override
    public ResponseEntity<GateKeeperObject> createGateKeeper(@RequestBody CreateGateKeeper request) {
        return ResponseEntity.ok(adminService.createNewGateKeeper(request));
    }

    /**
     * Endpoint for deleting a gatekeeper
     * @param gatekeeperId
     * @return Response entity containing String
     */
    @Override
    public ResponseEntity<String> deleteGateKeeper(@PathVariable Integer gatekeeperId) {

        return ResponseEntity.status(200).body(adminService.deleteAGatekeeper(gatekeeperId));


    }

    /**
     * Endpoint for  getting all activeGateKeepers
     * @return Response entity containing a list of gatekeepers
     */
    @Override
    public ResponseEntity<List<GateKeeperObject>> getGateKeeper() {
        return ResponseEntity.ok(adminService.getAllGateKeepers());
    }

    /**
     * Endpoint for getting a gatekeeper by Id
     * @param gatekeeperId
     * @return Response entity containing Gatekeeper object
     */
    @Override
    public ResponseEntity<GateKeeperObject> getgateKeeperById(@PathVariable Integer gatekeeperId) {
        return ResponseEntity.ok(adminService.getAGateKeeper(gatekeeperId));
    }

    /**
     * Endpoint for updating a gatekeeper
     * @param gatekeeperId  (required)
     * @param updateGateKeeper  (required)
     * @return  Response entity containing GateKeeperObject
     */
    @Override
    public ResponseEntity<GateKeeperObject> updateGateKeeper(@PathVariable Integer gatekeeperId, @RequestBody UpdateGateKeeper updateGateKeeper) {
        return ResponseEntity.ok(adminService.updateGateKeeper(gatekeeperId, updateGateKeeper));
    }

    /**
     * Endpoint for viewing all registration requests
     * @return Response entity containing a list of User objects
     */
    @Override
    public ResponseEntity<List<UserObject>> viewRegnRequest() {
        return ResponseEntity.ok(adminService.viewRegistrationRequests());
    }

    /**
     * Endpoint for approving a user
     * @param requestId  (required)
     * @return Response entity containing a Userobject
     */

    @Override
    public ResponseEntity<UserObject> approveUser(@PathVariable Integer requestId) {
        return ResponseEntity.ok(adminService.approveRequest(requestId));
    }


}
