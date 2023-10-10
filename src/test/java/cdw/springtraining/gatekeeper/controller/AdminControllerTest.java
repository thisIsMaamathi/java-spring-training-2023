package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.models.*;
import cdw.springtraining.gatekeeper.service.AdminService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @InjectMocks
    AdminController adminController;

    @Mock
    AdminService adminService;

    @Test
    public void testgetAllResidents(){
        List<ResidentObject> residentObjects=new ArrayList<>();
        when(adminService.getAllResidents()).thenReturn(residentObjects);
        assertEquals(residentObjects,adminController.getResidents().getBody());
    }

    @SneakyThrows
    @Test
    public void testCreatResident()  {
        CreateResident createResidentRequest=new CreateResident();
        ResidentObject residentObject=new ResidentObject();

        residentObject.setResidenceId(10);
        residentObject.setResidentName("Ram");
        residentObject.setPhoneNumber(3212455L);
        residentObject.setAadhar(4567890322756789L);
        residentObject.setId(1);

       when(adminService.createNewResident(createResidentRequest)).thenReturn(residentObject);
       ResidentObject residentObjectresponse= adminController.createResidents(createResidentRequest).getBody();
       assertEquals(residentObject,residentObjectresponse);


    }
    @SneakyThrows
    @Test
    public void testdeleteResidents(){
        Integer id=1;
        when(adminService.deleteResident(1)).thenReturn(true);
        ResponseEntity response = adminController.deleteResidents(1);
        assertEquals(204, response.getStatusCodeValue());

    }

    @SneakyThrows
    @Test
    public void testUpdateResidents(){
        Integer id=1;
        UpdateResident updateResident=new UpdateResident();
        ResidentObject residentObject=new ResidentObject();

        residentObject.setResidenceId(10);
        residentObject.setResidentName("Ram");
        residentObject.setPhoneNumber(3212455L);
        residentObject.setAadhar(4567890322756789L);
        residentObject.setId(1);

        when(adminService.updateResident(id,updateResident)).thenReturn(residentObject);
        ResidentObject response= adminController.updateResidents(id,updateResident).getBody();
        assertEquals(residentObject,response);

    }
  @Test
   public void testGetResidentById() throws Exception {
      Integer id=1;
      ResidentObject residentObject=new ResidentObject();

      residentObject.setResidenceId(10);
      residentObject.setResidentName("Ram");
      residentObject.setPhoneNumber(3212455L);
      residentObject.setAadhar(4567890322756789L);
      residentObject.setId(1);

      when(adminService.getResidentById(id)).thenReturn(residentObject);
      ResidentObject response=adminController.getResidentsById(id).getBody();
      assertEquals(residentObject,response);

  }

    @Test
    public void testgetAllGateKeepers(){
        List<GateKeeperObject> gateKeeperObjects=new ArrayList<>();
        when(adminService.getAllGateKeepers()).thenReturn(gateKeeperObjects);
        assertEquals(gateKeeperObjects,adminController.getGateKeeper().getBody());
    }


    @Test
    public void testCreateGatekeeper() throws Exception {
        CreateGateKeeper request=new CreateGateKeeper();
        GateKeeperObject gateKeeperObject=new GateKeeperObject();
        gateKeeperObject.setGateId(1);
        gateKeeperObject.setGateKeeperName("Raju");
        gateKeeperObject.setAadhar(34567890L);
        gateKeeperObject.setPhoneNumber(234567890L);
        gateKeeperObject.setId(1);

        when(adminService.createNewGateKeeper(request)).thenReturn(gateKeeperObject);
        GateKeeperObject response= adminController.createGateKeeper(request).getBody();
        assertEquals(gateKeeperObject,response);


    }

    @Test
    public void testdeleteGateKeeper() throws Exception {
        Integer id=1;
        when(adminService.deleteAGatekeeper(1)).thenReturn(true);
        ResponseEntity response = adminController.deleteGateKeeper(1);
        assertEquals(204, response.getStatusCodeValue());

    }

    @SneakyThrows
    @Test
    public void testUpdateGateKeeper(){
        Integer id=1;
        UpdateGateKeeper request=new UpdateGateKeeper();
        GateKeeperObject gateKeeperObject=new GateKeeperObject();
        gateKeeperObject.setGateId(1);
        gateKeeperObject.setGateKeeperName("Raju");
        gateKeeperObject.setAadhar(34567890L);
        gateKeeperObject.setPhoneNumber(234567890L);
        gateKeeperObject.setId(1);

        when(adminService.updateGateKeeper(id,request)).thenReturn(gateKeeperObject);
        GateKeeperObject response= adminController.updateGateKeeper(id,request).getBody();
        assertEquals(gateKeeperObject,response);

    }
    @Test
    public void testGetGateKeeperById() throws Exception {
        Integer id=1;
        GateKeeperObject gateKeeperObject=new GateKeeperObject();
        gateKeeperObject.setGateId(1);
        gateKeeperObject.setGateKeeperName("Raju");
        gateKeeperObject.setAadhar(34567890L);
        gateKeeperObject.setPhoneNumber(234567890L);
        gateKeeperObject.setId(1);

        when(adminService.getAGateKeeper(id)).thenReturn(gateKeeperObject);
        GateKeeperObject response=adminController.getgateKeeperById(id).getBody();
        assertEquals(gateKeeperObject,response);

    }

    @Test
    public void testGetListOfRequest(){
    List<UserObject> userObjects = new ArrayList<>();
    when(adminService.viewRegistrationRequests()).thenReturn(userObjects);
    ResponseEntity<List<UserObject>> response = adminController.viewRegnRequest();
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(userObjects,response.getBody());

}

    @Test
    public void testApproveRequest() throws Exception {
        UserObject userObject = new UserObject();
        when(adminService.approveRequest(1)).thenReturn(userObject);
        ResponseEntity<UserObject> response = adminController.approveUser(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userObject,response.getBody());


    }

}









