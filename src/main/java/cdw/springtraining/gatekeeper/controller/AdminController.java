package cdw.springtraining.gatekeeper.controller;


import cdw.springtraining.gatekeeper.api.AdminApi;

import cdw.springtraining.gatekeeper.models.*;
import cdw.springtraining.gatekeeper.service.AdminService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController implements AdminApi {
    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
       this.adminService=adminService;
    }

    @Override
    public ResponseEntity<List<ResidentObject>> getResidents(){
      return ResponseEntity.ok(adminService.getAllResidents());

    }
//
    @Override
    public ResponseEntity<ResidentObject> createResidents(@RequestBody CreateResident request) {
        return ResponseEntity.ok(adminService.createNewResident(request));
    }

    @Override
    public ResponseEntity deleteResidents(@PathVariable Integer residentId)  {
     return ResponseEntity.status(204).body(adminService.deleteResident(residentId));
    }

    @Override
    public ResponseEntity<ResidentObject> updateResidents(@PathVariable Integer residentId, @RequestBody UpdateResident updateResident){
        return ResponseEntity.ok(adminService.updateResident(residentId,updateResident));

    }

    @Override
    public ResponseEntity<ResidentObject> getResidentsById(@PathVariable Integer residentId) {
        return ResponseEntity.ok(adminService.getResidentById(residentId));
    }

    @Override
    public ResponseEntity<GateKeeperObject> createGateKeeper(@RequestBody CreateGateKeeper request)  {
        return  ResponseEntity.ok(adminService.createNewGateKeeper(request));
    }

    @Override
    public ResponseEntity deleteGateKeeper(@PathVariable Integer gatekeeperId)  {

            return ResponseEntity.status(204).body(adminService.deleteAGatekeeper(gatekeeperId));



    }

   @Override
   public ResponseEntity<List<GateKeeperObject>> getGateKeeper(){
        return  ResponseEntity.ok(adminService.getAllGateKeepers());
   }

   @Override
   public ResponseEntity<GateKeeperObject> getgateKeeperById(@PathVariable Integer gatekeeperId){
        return ResponseEntity.ok(adminService.getAGateKeeper(gatekeeperId));
   }

   @Override
   public ResponseEntity<GateKeeperObject> updateGateKeeper( @PathVariable Integer gatekeeperId, @RequestBody UpdateGateKeeper updateGateKeeper) {
        return ResponseEntity.ok(adminService.updateGateKeeper(gatekeeperId,updateGateKeeper));
   }

     @Override
     public ResponseEntity<List<UserObject>> viewRegnRequest(){
         return ResponseEntity.ok(adminService.viewRegistrationRequests());
     }

     @Override
     public ResponseEntity<UserObject> approveUser( @PathVariable Integer requestId)  {
          return ResponseEntity.ok(adminService.approveRequest(requestId));
     }




}
