package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.entites.*;
import cdw.springtraining.gatekeeper.exceptions.UserAlreadyExistsException;
import cdw.springtraining.gatekeeper.models.*;
import cdw.springtraining.gatekeeper.repository.*;
import cdw.springtraining.gatekeeper.security.CustomUserDetailsService;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for Admin service
 */
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    AdminService adminService;

    @Mock
    BlackListRepository blacklistRepository;

    @Mock
    UserRepository userRepository;
    @Mock
    RolesRepository roleRepository;

    @Mock
    CustomUserDetailsService customUserDetailsService;

    /**
     * Unit testing for getAllResidents
     */

    @Test
    public void testGetAllResidents() {
        List<Users> residentList = new ArrayList<>();
        List<ResidentAdminResponse> residentObject = new ArrayList<>();

       Users resident1 = new Users();
       resident1.setUserId(1);
        resident1.setUserName("itsMathi");
        resident1.setFirstName("Mathi");
        resident1.setLastName("mathi");
        resident1.setResidenceNumber(10);
        resident1.setPhoneNumber(123456789L);
        resident1.setActive(true);
        resident1.setAadhar(23456789L);
        resident1.setActive(true);
        resident1.setIsApproved("approved");
        resident1.setVisitorsList(new ArrayList<>());

        residentList.add(resident1);


       ResidentAdminResponse residentObject1 = new ResidentAdminResponse();
        residentObject1.setUserId(1);
        residentObject1.setResidenceId(10);
        residentObject1.setResidentName("Mathi mathi");
        residentObject1.setUserName("itsMathi");
        residentObject1.setPhoneNumber(123456789L);
        residentObject1.setAadhar(23456789L);
        residentObject1.setIsActive(true);
        residentObject1.setIsApproved("approved");
        residentObject.add(residentObject1);


        when(userRepository.findByUserTypeAndIsActive("resident",true)).thenReturn(residentList);
        List<ResidentAdminResponse> response = adminService.getAllResidents();
        assertEquals(residentObject, response);

    }


    /**
     * Unit testing for deleteResident
     */
    @Test
    public void testDeleteUser() {
        Users resident=new Users();
        resident.setUserId(1);
        resident.setResidenceNumber(10);
        resident.setActive(true);
        resident.setUserName("Rose");
        resident.setAadhar(3456789L);
        resident.setPhoneNumber(1324567890L);
        resident.setVisitorsList(new ArrayList<>());

        when(userRepository.findById(1)).thenReturn(Optional.of(resident));
         adminService.deleteUser(1);
        verify(userRepository).findById(1);

    }

    /**
     * Unit tes for updateResident
     */
    @Test
    public void testUpdateUser() {
        UpdateUserRequest request=new UpdateUserRequest();
        request.setAadhar(3456789L);
        request.setUserName("Rose");
        request.setPhoneNumber(1324567890L);

        Users resident = new Users();
        resident.setUserId(1);
        resident.setResidenceNumber(10);
        resident.setActive(true);
        resident.setUserName("Rose");
        resident.setAadhar(3456789L);
        resident.setPhoneNumber(1324567890L);
        resident.setIsApproved("approved");
        resident.setVisitorsList(new ArrayList<>());
        when(userRepository.findById(1)).thenReturn(Optional.of(resident));

        UserAdminResponse residentObject = new UserAdminResponse();
        residentObject.setUserId(1);
        residentObject.setResidenceId(10);
        residentObject.setUserName("Rose");
        residentObject.setPhoneNumber(1324567890L);
        residentObject.setAadhar(3456789L);
        residentObject.setIsActive(true);
        residentObject.setIsApproved("approved");

       UserAdminResponse response = adminService.updateUser(1, request);
        assertEquals(residentObject, response);

    }

    /**
     * Unit test for getByResidentId
     */
    @Test
    public void testUsersById() {

        Users resident = new Users();
        resident.setUserId(1);
        resident.setResidenceNumber(10);
        resident.setActive(true);
        resident.setUserName("Rose");
        resident.setFirstName("Rosie");
        resident.setLastName("mary");
        resident.setAadhar(3456789L);
        resident.setPhoneNumber(1324567890L);
        resident.setIsApproved("approved");
        resident.setVisitorsList(new ArrayList<>());

        when(userRepository.findById(1)).thenReturn(Optional.of(resident));
        UserAdminResponse residentObject = new UserAdminResponse();
        residentObject.setUserId(1);
        residentObject.setResidenceId(10);
        residentObject.setUserName("Rose");
        residentObject.setName("Rosie mary");
        residentObject.setPhoneNumber(1324567890L);
        residentObject.setAadhar(3456789L);
        residentObject.setIsActive(true);
        residentObject.setIsApproved("approved");

        UserAdminResponse response = adminService.getUsersById(1);
        assertEquals(residentObject, response);

    }




    /**
     * Unit testing for getAllGateKeepers
     */
    @Test
    public void testGetAllGateKeepers() {
        List<Users> gateKeepersList = new ArrayList<>();
        List<GateKeeperAdminResponse> gateKeeperObjects = new ArrayList<>();

        Users gateKeeper = new Users();
        gateKeeper.setUserId(1);
        gateKeeper.setActive(true);
        gateKeeper.setUserName("Babu");
        gateKeeper.setFirstName("Babu");
        gateKeeper.setLastName("lal");
        gateKeeper.setUserType("gatekeeper");
        gateKeeper.setIsApproved("approved");
        gateKeeper.setActive(true);
        gateKeeper.setAadhar(3456789L);
        gateKeeper.setPhoneNumber(1324567890L);
        gateKeeper.setVisitorsList(new ArrayList<>());
        gateKeepersList.add(gateKeeper);

       GateKeeperAdminResponse gateKeeperObject = new GateKeeperAdminResponse();
        gateKeeperObject.setUserId(1);
        gateKeeperObject.setUserName("Babu");
        gateKeeperObject.setName("Babu lal");
        gateKeeperObject.setPhoneNumber(1324567890L);
        gateKeeperObject.setAadhar(3456789L);
        gateKeeperObject.setIsApproved("approved");
        gateKeeperObject.setIsActive(true);


        gateKeeperObjects.add(gateKeeperObject);


        when(userRepository.findByUserTypeAndIsActive("gatekeeper",true)).thenReturn(gateKeepersList);
        List<GateKeeperAdminResponse> response = adminService.getAllGateKeepers();
        assertEquals(gateKeeperObjects, response);

    }


    /**
     * Unit test for approveRequest
     */

    @Test
    public void testApproveRequest() {
        UserResponse userObject = new UserResponse();
        userObject.setUserType("resident");
        userObject.setAadhar(3456789L);
        userObject.setUserId(1);
        userObject.setPhoneNumber(345678890L);
        userObject.setUserName("Maya");
        userObject.setIsApproved("approved");
        userObject.setIsActive(true);
        userObject.setResidenceId(10);

        Users approveRequest = new Users();
        approveRequest.setUserId(1);
        approveRequest.setPhoneNumber(345678890L);
        approveRequest.setAadhar(3456789L);
        approveRequest.setResidenceNumber(10);
        approveRequest.setUserName("Maya");
        approveRequest.setPassword("pass");
        approveRequest.setUserType("resident");

        when(userRepository.findById(1)).thenReturn(Optional.of(approveRequest));
        when(customUserDetailsService.getCurrentUserName()).thenReturn("itsMathi");

        UserResponse userObjectResponse = adminService.approveRequest(1);
        assertEquals(userObject, userObjectResponse);

    }


    /**
     * Unit test for viewRegistrationRequests
     */
    @Test
    public void viewRegnReqTest() {

        List<Users> approveRequestList = new ArrayList<>();
        Users approveRequest = new Users();
        approveRequest.setUserId(1);
        approveRequest.setPhoneNumber(345678890L);
        approveRequest.setIsApproved("approved");
        approveRequest.setAadhar(3456789L);
        approveRequest.setUserName("Maya");
        approveRequest.setPassword("pass");
        approveRequest.setResidenceNumber(10);
        approveRequest.setUserType("gatekeeper");
        approveRequest.setActive(true);
        approveRequestList.add(approveRequest);

        List<UserResponse> userObjectsList = new ArrayList<>();
        UserResponse userObject = new UserResponse();
        userObject.setUserType("gatekeeper");
        userObject.setAadhar(3456789L);
        userObject.setUserId(1);
        userObject.setPhoneNumber(345678890L);
        userObject.setUserName("Maya");
        userObject.setIsActive(true);
        userObject.setIsApproved("approved");
        userObject.setResidenceId(10);
        userObjectsList.add(userObject);

        when(userRepository.findByIsActive(true)).thenReturn(approveRequestList);
        List<UserResponse> response = adminService.viewApprovedRequests();
        assertEquals(response, userObjectsList);


    }

    @Test
    public void viewRequestRejectedTest() {

        List<Users> approveRequestList = new ArrayList<>();
        Users approveRequest = new Users();
        approveRequest.setUserId(1);
        approveRequest.setPhoneNumber(345678890L);
        approveRequest.setIsApproved("rejected");
        approveRequest.setAadhar(3456789L);
        approveRequest.setUserName("Maya");
        approveRequest.setPassword("pass");
        approveRequest.setResidenceNumber(10);
        approveRequest.setUserType("gatekeeper");
        approveRequest.setActive(true);
        approveRequestList.add(approveRequest);

        List<UserResponse> userObjectsList = new ArrayList<>();
        UserResponse userObject = new UserResponse();
        userObject.setUserType("gatekeeper");
        userObject.setAadhar(3456789L);
        userObject.setUserId(1);
        userObject.setPhoneNumber(345678890L);
        userObject.setUserName("Maya");
        userObject.setIsActive(true);
        userObject.setIsApproved("rejected");
        userObject.setResidenceId(10);
        userObjectsList.add(userObject);

        when(userRepository.findByIsApproved("rejected")).thenReturn(approveRequestList);
        List<UserResponse> response = adminService.viewRequestRejected();
        assertEquals(response, userObjectsList);


    }

    @Test
    public void viewRequestApprovedTest() {

        List<Users> approveRequestList = new ArrayList<>();
        Users approveRequest = new Users();
        approveRequest.setUserId(1);
        approveRequest.setPhoneNumber(345678890L);
        approveRequest.setIsApproved("approved");
        approveRequest.setAadhar(3456789L);
        approveRequest.setUserName("Maya");
        approveRequest.setPassword("pass");
        approveRequest.setResidenceNumber(10);
        approveRequest.setUserType("gatekeeper");
        approveRequest.setActive(true);
        approveRequestList.add(approveRequest);

        List<UserResponse> userObjectsList = new ArrayList<>();
        UserResponse userObject = new UserResponse();
        userObject.setUserType("gatekeeper");
        userObject.setAadhar(3456789L);
        userObject.setUserId(1);
        userObject.setPhoneNumber(345678890L);
        userObject.setUserName("Maya");
        userObject.setIsActive(true);
        userObject.setIsApproved("approved");
        userObject.setResidenceId(10);
        userObjectsList.add(userObject);

        when(userRepository.findByIsApproved("approved")).thenReturn(approveRequestList);
        List<UserResponse> response = adminService.viewRequestApproved();
        assertEquals(response, userObjectsList);


    }

    @Test
    public void testRejectRequest() {
        UserResponse userObject = new UserResponse();
        userObject.setUserType("resident");
        userObject.setAadhar(3456789L);
        userObject.setUserId(1);
        userObject.setPhoneNumber(345678890L);
        userObject.setUserName("Maya");
        userObject.setIsApproved("rejected");
        userObject.setIsActive(true);
        userObject.setResidenceId(10);

        Users approveRequest = new Users();
        approveRequest.setUserId(1);
        approveRequest.setPhoneNumber(345678890L);
        approveRequest.setAadhar(3456789L);
        approveRequest.setResidenceNumber(10);
        approveRequest.setUserName("Maya");
        approveRequest.setPassword("pass");
        approveRequest.setUserType("resident");

        when(userRepository.findById(1)).thenReturn(Optional.of(approveRequest));
        when(customUserDetailsService.getCurrentUserName()).thenReturn("itsMathi");

        UserResponse userObjectResponse = adminService.rejectUser(1);
        assertEquals(userObject, userObjectResponse);

    }


}
