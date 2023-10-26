package cdw.springtraining.gatekeeper.controller;


import cdw.springtraining.gatekeeper.entites.Users;
import cdw.springtraining.gatekeeper.models.*;
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
import static org.mockito.Mockito.doNothing;
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
        List<ResidentAdminResponse> residentObjects = new ArrayList<>();
        when(adminService.getAllResidents()).thenReturn(residentObjects);
        assertEquals(residentObjects, adminController.getResidents().getBody());
    }


    /**
     * Unit testing for delete user
     */
    @Test
    public void testdeleteUsers() {
        Integer id = 1;
        Users user = new Users();
        doNothing().when(adminService).deleteUser(1);
        ResponseEntity response = adminController.deleteUsers(1);
        assertEquals(204, response.getStatusCodeValue());

    }
//

    /**
     * Unit testing for updateResident
     */
    @Test
    public void testUpdateResidents() {
        Integer id = 1;
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserAdminResponse userAdminResponse = new UserAdminResponse();
        when(adminService.updateUser(id, updateUserRequest)).thenReturn(userAdminResponse);
        UserAdminResponse response = adminController.updateUsers(id, updateUserRequest).getBody();
        assertEquals(userAdminResponse, response);

    }

    /**
     * Unit testing for getResident ById
     */
    @Test
    public void testGetUsersById() {
        Integer id = 1;
        UserAdminResponse userAdminResponse = new UserAdminResponse();
        when(adminService.getUsersById(id)).thenReturn(userAdminResponse);
       UserAdminResponse response = adminController.getUserById(id).getBody();
        assertEquals(userAdminResponse, response);
    }

    /**
     * Unit testing for getAllGateKeepers
     */
    @Test
    public void testgetAllGateKeepers() {
        List<GateKeeperAdminResponse> gateKeeperObjects = new ArrayList<>();
        when(adminService.getAllGateKeepers()).thenReturn(gateKeeperObjects);
        assertEquals(gateKeeperObjects, adminController.getGateKeeper().getBody());
    }



    /**
     * Unit testing for ViewRegistration request
     */
    @Test
    public void testGetListOfRequest() {
        List<UserResponse> userObjects = new ArrayList<>();
        when(adminService.viewApprovedRequests()).thenReturn(userObjects);
        ResponseEntity<List<UserResponse>> response = adminController.viewRegnRequest();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userObjects, response.getBody());

    }

    /**
     * Unit testing for approveRequest
     */
    @Test
    public void testApproveRequest(){
        UserResponse userObject = new UserResponse();
        when(adminService.approveRequest(1)).thenReturn(userObject);
        ResponseEntity<UserResponse> response = adminController.approveUser(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userObject, response.getBody());


    }

    @Test
    public void testRejectUser(){
        UserResponse userObject = new UserResponse();
        when(adminService.rejectUser(1)).thenReturn(userObject);
        ResponseEntity<UserResponse> response = adminController.rejectUser(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userObject, response.getBody());

    }

    @Test
    public void testViewApprovedRequests(){
        List<UserResponse> userObjects = new ArrayList<>();
        when(adminService.viewRequestApproved()).thenReturn(userObjects);
        ResponseEntity<List<UserResponse>> response = adminController.viewApprovedRequest();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userObjects, response.getBody());
    }

    @Test
    public void testViewRejectedRequests(){
        List<UserResponse> userObjects = new ArrayList<>();
        when(adminService.viewRequestRejected()).thenReturn(userObjects);
        ResponseEntity<List<UserResponse>> response = adminController.viewRejectedRequest();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userObjects, response.getBody());

    }




}









