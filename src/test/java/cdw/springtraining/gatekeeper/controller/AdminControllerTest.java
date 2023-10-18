package cdw.springtraining.gatekeeper.controller;


import cdw.springtraining.gatekeeper.entites.GateKeeper;
import cdw.springtraining.gatekeeper.entites.Resident;
import cdw.springtraining.gatekeeper.models.ResidentObject;
import cdw.springtraining.gatekeeper.models.CreateResident;
import cdw.springtraining.gatekeeper.models.GateKeeperObject;
import cdw.springtraining.gatekeeper.models.UpdateResident;
import cdw.springtraining.gatekeeper.models.CreateGateKeeper;
import cdw.springtraining.gatekeeper.models.UpdateGateKeeper;
import cdw.springtraining.gatekeeper.models.UserObject;
import cdw.springtraining.gatekeeper.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit test class for AdminController
 */

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @InjectMocks
    AdminController adminController;

    @Mock
    AdminService adminService;

    /**
     * Unit test for getAllResidents
     */
    @Test
    public void testgetAllResidents() {
        List<ResidentObject> residentObjects = new ArrayList<>();
        when(adminService.getAllResidents()).thenReturn(residentObjects);
        assertEquals(residentObjects, adminController.getResidents().getBody());
    }

    /**
     * Unit testing for createResident
     */
    @Test
    public void testCreatResident() {
        CreateResident createResidentRequest = new CreateResident();
        ResidentObject residentObject = new ResidentObject();

        residentObject.setResidenceId(10);
        residentObject.setResidentName("Ram");
        residentObject.setPhoneNumber(3212455L);
        residentObject.setAadhar(4567890322756789L);
        residentObject.setId(1);

        when(adminService.createNewResident(createResidentRequest)).thenReturn(residentObject);
        ResidentObject residentObjectresponse = adminController.createResidents(createResidentRequest).getBody();
        assertEquals(residentObject, residentObjectresponse);


    }

    /**
     * Unit testing for delete residents
     */
    @Test
    public void testdeleteResidents() {
        Integer id = 1;
        Resident resident=new Resident();
        resident.setResidentName("Raj");
        resident.setResidenceNumber(10);
        when(adminService.deleteResident(1)).thenReturn("Deleted Resident Raj of residenceId 10");
        ResponseEntity response = adminController.deleteResidents(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Deleted Resident Raj of residenceId 10",response.getBody());

    }


    /**
     * Unit testing for updateResident
     */
    @Test
    public void testUpdateResidents() {
        Integer id = 1;
        UpdateResident updateResident = new UpdateResident();
        ResidentObject residentObject = new ResidentObject();

        residentObject.setResidenceId(10);
        residentObject.setResidentName("Ram");
        residentObject.setPhoneNumber(3212455L);
        residentObject.setAadhar(4567890322756789L);
        residentObject.setId(1);

        when(adminService.updateResident(id, updateResident)).thenReturn(residentObject);
        ResidentObject response = adminController.updateResidents(id, updateResident).getBody();
        assertEquals(residentObject, response);

    }

    /**
     * Unit testing for getResident ById
     */
    @Test
    public void testGetResidentById() {
        Integer id = 1;
        ResidentObject residentObject = new ResidentObject();
        residentObject.setResidenceId(10);
        residentObject.setResidentName("Ram");
        residentObject.setPhoneNumber(3212455L);
        residentObject.setAadhar(4567890322756789L);
        residentObject.setId(1);

        when(adminService.getResidentById(id)).thenReturn(residentObject);
        ResidentObject response = adminController.getResidentsById(id).getBody();
        assertEquals(residentObject, response);
    }

    /**
     * Unit testing for getAllGateKeepers
     */
    @Test
    public void testgetAllGateKeepers() {
        List<GateKeeperObject> gateKeeperObjects = new ArrayList<>();
        when(adminService.getAllGateKeepers()).thenReturn(gateKeeperObjects);
        assertEquals(gateKeeperObjects, adminController.getGateKeeper().getBody());
    }

    /**
     * Unit testing for create Gatekeeper
     */
    @Test
    public void testCreateGatekeeper() {
        CreateGateKeeper request = new CreateGateKeeper();
        GateKeeperObject gateKeeperObject = new GateKeeperObject();
        gateKeeperObject.setGateId(1);
        gateKeeperObject.setGateKeeperName("Raju");
        gateKeeperObject.setAadhar(34567890L);
        gateKeeperObject.setPhoneNumber(234567890L);
        gateKeeperObject.setId(1);

        when(adminService.createNewGateKeeper(request)).thenReturn(gateKeeperObject);
        GateKeeperObject response = adminController.createGateKeeper(request).getBody();
        assertEquals(gateKeeperObject, response);
    }

    /**
     * Unit testing for deleteGateKeeper
     */
    @Test
    public void testdeleteGateKeeper()  {
        GateKeeper gateKeeper = new GateKeeper();
        gateKeeper.setGatekeeper_id(1);
        gateKeeper.setGatekeeperName("Raju");

        when(adminService.deleteAGatekeeper(1)).thenReturn("The gateKeeper Raju has been deleted");
        ResponseEntity response = adminController.deleteGateKeeper(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("The gateKeeper Raju has been deleted",response.getBody());

    }

    /**
     * Unit testing for updateGatekeeper
     */
    @Test
    public void testUpdateGateKeeper() {
        Integer id = 1;
        UpdateGateKeeper request = new UpdateGateKeeper();
        GateKeeperObject gateKeeperObject = new GateKeeperObject();
        gateKeeperObject.setGateId(1);
        gateKeeperObject.setGateKeeperName("Raju");
        gateKeeperObject.setAadhar(34567890L);
        gateKeeperObject.setPhoneNumber(234567890L);
        gateKeeperObject.setId(1);

        when(adminService.updateGateKeeper(id, request)).thenReturn(gateKeeperObject);
        GateKeeperObject response = adminController.updateGateKeeper(id, request).getBody();
        assertEquals(gateKeeperObject, response);

    }

    /**
     * Unit testing for getGatekeeperById
     */
    @Test
    public void testGetGateKeeperById() {
        Integer id = 1;
        GateKeeperObject gateKeeperObject = new GateKeeperObject();
        gateKeeperObject.setGateId(1);
        gateKeeperObject.setGateKeeperName("Raju");
        gateKeeperObject.setAadhar(34567890L);
        gateKeeperObject.setPhoneNumber(234567890L);
        gateKeeperObject.setId(1);

        when(adminService.getAGateKeeper(id)).thenReturn(gateKeeperObject);
        GateKeeperObject response = adminController.getgateKeeperById(id).getBody();
        assertEquals(gateKeeperObject, response);

    }

    /**
     * Unit testing for ViewRegistration request
     */
    @Test
    public void testGetListOfRequest() {
        List<UserObject> userObjects = new ArrayList<>();
        when(adminService.viewRegistrationRequests()).thenReturn(userObjects);
        ResponseEntity<List<UserObject>> response = adminController.viewRegnRequest();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userObjects, response.getBody());

    }

    /**
     * Unit testing for approveRequest
     */
    @Test
    public void testApproveRequest(){
        UserObject userObject = new UserObject();
        when(adminService.approveRequest(1)).thenReturn(userObject);
        ResponseEntity<UserObject> response = adminController.approveUser(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userObject, response.getBody());


    }

}









