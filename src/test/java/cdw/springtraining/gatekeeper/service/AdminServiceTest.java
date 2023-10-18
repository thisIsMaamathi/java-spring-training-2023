package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.entites.*;
import cdw.springtraining.gatekeeper.exceptions.UserAlreadyExistsException;
import cdw.springtraining.gatekeeper.models.*;
import cdw.springtraining.gatekeeper.repository.*;
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
import static org.mockito.Mockito.when;

/**
 * Unit test for Admin service
 */
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    AdminService adminService;
    @Mock
    ResidentRepository residentRepository;
    @Mock
    BlacklistRepository blacklistRepository;
    @Mock
    GateKeeperRepository gateKeeperRepository;
    @Mock
    ApproveRequestRepository approveRequestRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;

    /**
     * Unit testing for getAllResidents
     */

    @Test
    public void testGetAllResidents() {
        List<Resident> residentList = new ArrayList<>();
        List<ResidentObject> residentObject = new ArrayList<>();

        Resident resident1 = new Resident();
        resident1.setId(1);
        resident1.setResidentName("Mathi");
        resident1.setResidenceNumber(10);
        resident1.setPhoneNumber(123456789L);
        resident1.setActive(true);
        resident1.setAadhar(23456789L);
        resident1.setVisitorsList(new ArrayList<>());

        residentList.add(resident1);


        ResidentObject residentObject1 = new ResidentObject();
        residentObject1.setId(1);
        residentObject1.setResidenceId(10);
        residentObject1.setResidentName("Mathi");
        residentObject1.setPhoneNumber(123456789L);
        residentObject1.setAadhar(23456789L);

        residentObject.add(residentObject1);


        when(residentRepository.findByIsActive(true)).thenReturn(residentList);
        List<ResidentObject> response = adminService.getAllResidents();
        assertEquals(residentObject, response);

    }

    /**
     * Unit test for create resident
     */
    @Test
    public void testCreateNewResident() {
        CreateResident request = new CreateResident();
        request.setAadhar(3456789L);
        request.setResidenceId(10);
        request.setPhoneNumber(1324567890L);
        request.setResidentName("Rose");
        when(residentRepository.existsByResidenceNumber(request.getResidenceId())).thenReturn(false);
        Resident resident = new Resident();
        resident.setId(1);
        resident.setResidenceNumber(10);
        resident.setActive(true);
        resident.setResidentName("Rose");
        resident.setAadhar(3456789L);
        resident.setPhoneNumber(1324567890L);
        resident.setVisitorsList(new ArrayList<>());

        ResidentObject residentObject = new ResidentObject();
        residentObject.setId(1);
        residentObject.setResidenceId(10);
        residentObject.setResidentName("Rose");
        residentObject.setPhoneNumber(1324567890L);
        residentObject.setAadhar(3456789L);

        when(residentRepository.findByAadharAndResidenceNumber(request.getAadhar(),request.getResidenceId())).thenReturn(resident);
        ResidentObject response = adminService.createNewResident(request);
        assertEquals(residentObject, response);
    }

    /**
     * Unit test for exceptin in create new resident
     */
    @Test
    public void testUserExceptionForCreateResident() {
        CreateResident request = new CreateResident();
        request.setAadhar(3456789L);
        request.setResidenceId(10);
        request.setPhoneNumber(1324567890L);
        request.setResidentName("Rose");
        when(residentRepository.existsByResidenceNumber(10)).thenReturn(true);
        assertThrows(UserAlreadyExistsException.class, () -> adminService.createNewResident(request));
    }

    /**
     * Unit testing for deleteResident
     */
    @Test
    public void testDeleteResident() {
        Resident resident = new Resident();
        resident.setId(1);
        resident.setResidenceNumber(10);
        resident.setActive(true);
        resident.setResidentName("Rose");
        resident.setAadhar(3456789L);
        resident.setPhoneNumber(1324567890L);
        resident.setVisitorsList(new ArrayList<>());

        when(residentRepository.findById(1)).thenReturn(Optional.of(resident));
        String b = adminService.deleteResident(1);
        assertEquals("Deleted Resident Rose of residenceId 10" ,b);

    }

    /**
     * Unit tes for updateResident
     */
    @Test
    public void testUpdateResident() {
        UpdateResident request = new UpdateResident();
        request.setAadhar(3456789L);
        request.setResidentName("Rose");
        request.setPhoneNumber(1324567890L);

        Resident resident = new Resident();
        resident.setId(1);
        resident.setResidenceNumber(10);
        resident.setActive(true);
        resident.setResidentName("Rose");
        resident.setAadhar(3456789L);
        resident.setPhoneNumber(1324567890L);
        resident.setVisitorsList(new ArrayList<>());
        when(residentRepository.findById(1)).thenReturn(Optional.of(resident));

        ResidentObject residentObject = new ResidentObject();
        residentObject.setId(1);
        residentObject.setResidenceId(10);
        residentObject.setResidentName("Rose");
        residentObject.setPhoneNumber(1324567890L);
        residentObject.setAadhar(3456789L);

        ResidentObject response = adminService.updateResident(1, request);
        assertEquals(residentObject, response);

    }

    /**
     * Unit test for getByResidentId
     */
    @Test
    public void testGetResidentById() {

        Resident resident = new Resident();
        resident.setId(1);
        resident.setResidenceNumber(10);
        resident.setActive(true);
        resident.setResidentName("Rose");
        resident.setAadhar(3456789L);
        resident.setPhoneNumber(1324567890L);
        resident.setVisitorsList(new ArrayList<>());

        when(residentRepository.findById(1)).thenReturn(Optional.of(resident));
        ResidentObject residentObject = new ResidentObject();
        residentObject.setId(1);
        residentObject.setResidenceId(10);
        residentObject.setResidentName("Rose");
        residentObject.setPhoneNumber(1324567890L);
        residentObject.setAadhar(3456789L);

        ResidentObject response = adminService.getResidentById(1);
        assertEquals(residentObject, response);

    }


    /**
     * Unit test for createNewGateKeeper
     */
    @Test
    public void testCreateNewGateKeepers() {
        CreateGateKeeper request = new CreateGateKeeper();
        request.setAadhar(3456789L);
        request.setGateKeeperName("Babu");
        request.setPhoneNumber(1324567890L);
        when(gateKeeperRepository.existsByAadhar(request.getAadhar())).thenReturn(false);

        GateKeeper gateKeeper = new GateKeeper();
        gateKeeper.setGatekeeper_id(1);
        gateKeeper.setGateId(1);
        gateKeeper.setActive(true);
        gateKeeper.setGatekeeperName("Babu");
        gateKeeper.setAadhar(3456789L);
        gateKeeper.setPhone_number(1324567890L);
        gateKeeper.setVisitorsList(new ArrayList<>());

        GateKeeperObject gateKeeperObject = new GateKeeperObject();
        gateKeeperObject.setId(1);
        gateKeeperObject.setGateId(1);
        gateKeeperObject.setGateKeeperName("Babu");
        gateKeeperObject.setPhoneNumber(1324567890L);
        gateKeeperObject.setAadhar(3456789L);

        when(gateKeeperRepository.findByAadhar(request.getAadhar())).thenReturn(gateKeeper);

        GateKeeperObject response = adminService.createNewGateKeeper(request);
        assertEquals(gateKeeperObject, response);


    }

    /**
     * Unit testing for deleteAGatekeeper
     */

    @Test
    public void testDeleteGateKeeper() {
        GateKeeper gateKeeper = new GateKeeper();
        gateKeeper.setGatekeeper_id(1);
        gateKeeper.setGateId(1);
        gateKeeper.setActive(true);
        gateKeeper.setGatekeeperName("Babu");
        gateKeeper.setAadhar(3456789L);
        gateKeeper.setPhone_number(1324567890L);
        gateKeeper.setVisitorsList(new ArrayList<>());

        when(gateKeeperRepository.findById(1)).thenReturn(Optional.of(gateKeeper));
        String b = adminService.deleteAGatekeeper(1);
        assertEquals("The gatekeeper Babu has been deleted", b);

    }

    /**
     * Unit testing for getAllGateKeepers
     */
    @Test
    public void testGetAllGateKeepers() {
        List<GateKeeper> gateKeepersList = new ArrayList<>();
        List<GateKeeperObject> gateKeeperObjects = new ArrayList<>();

        GateKeeper gateKeeper = new GateKeeper();
        gateKeeper.setGatekeeper_id(1);
        gateKeeper.setGateId(1);
        gateKeeper.setActive(true);
        gateKeeper.setGatekeeperName("Babu");
        gateKeeper.setAadhar(3456789L);
        gateKeeper.setPhone_number(1324567890L);
        gateKeeper.setVisitorsList(new ArrayList<>());


        gateKeepersList.add(gateKeeper);

        GateKeeperObject gateKeeperObject = new GateKeeperObject();
        gateKeeperObject.setId(1);
        gateKeeperObject.setGateId(1);
        gateKeeperObject.setGateKeeperName("Babu");
        gateKeeperObject.setPhoneNumber(1324567890L);
        gateKeeperObject.setAadhar(3456789L);


        gateKeeperObjects.add(gateKeeperObject);


        when(gateKeeperRepository.findByIsActive(true)).thenReturn(gateKeepersList);
        List<GateKeeperObject> response = adminService.getAllGateKeepers();
        assertEquals(gateKeeperObjects, response);

    }

    /**
     * Unit test for  getAGateKeeper
     */

    @Test
    public void testGetGateKeeperById() {
        GateKeeper gateKeeper = new GateKeeper();
        gateKeeper.setGatekeeper_id(1);
        gateKeeper.setGateId(1);
        gateKeeper.setActive(true);
        gateKeeper.setGatekeeperName("Babu");
        gateKeeper.setAadhar(3456789L);
        gateKeeper.setPhone_number(1324567890L);
        gateKeeper.setVisitorsList(new ArrayList<>());

        when(gateKeeperRepository.findById(1)).thenReturn(Optional.of(gateKeeper));
        GateKeeperObject gateKeeperObject = new GateKeeperObject();
        gateKeeperObject.setId(1);
        gateKeeperObject.setGateId(1);
        gateKeeperObject.setGateKeeperName("Babu");
        gateKeeperObject.setPhoneNumber(1324567890L);
        gateKeeperObject.setAadhar(3456789L);

        GateKeeperObject response = adminService.getAGateKeeper(1);
        assertEquals(gateKeeperObject, response);

    }

    /**
     * Unit for updateGateKeeper
     */

    @Test
    public void testUpdateGateKeeper() {
        UpdateGateKeeper request = new UpdateGateKeeper();
        request.setAadhar(3456789L);
        request.setGateKeeperName("Babu");
        request.setPhoneNumber(1324567890L);

        GateKeeper gateKeeper = new GateKeeper();
        gateKeeper.setGatekeeper_id(1);
        gateKeeper.setGateId(1);
        gateKeeper.setActive(true);
        gateKeeper.setGatekeeperName("Babu");
        gateKeeper.setAadhar(3456789L);
        gateKeeper.setPhone_number(1324567890L);
        gateKeeper.setVisitorsList(new ArrayList<>());
        when(gateKeeperRepository.findById(1)).thenReturn(Optional.of(gateKeeper));

        ResidentObject residentObject = new ResidentObject();
        GateKeeperObject gateKeeperObject = new GateKeeperObject();
        gateKeeperObject.setId(1);
        gateKeeperObject.setGateId(1);
        gateKeeperObject.setGateKeeperName("Babu");
        gateKeeperObject.setPhoneNumber(1324567890L);
        gateKeeperObject.setAadhar(3456789L);

        GateKeeperObject response = adminService.updateGateKeeper(1, request);
        assertEquals(gateKeeperObject, response);

    }

    /**
     * Unit test for approveRequest
     */

    @Test
    public void testApproveRequestResident() {
        UserObject userObject = new UserObject();
        userObject.setUserType("resident");
        userObject.setAadhar(3456789L);
        userObject.setUserId(1);
        userObject.setPhoneNumber(345678890L);
        userObject.setUserName("Maya");

        ApproveRequest approveRequest = new ApproveRequest();
        approveRequest.setRequestId(1);
        approveRequest.setPhoneNumber(345678890L);
        approveRequest.setHasApproved(true);
        approveRequest.setAadhar(3456789L);
        approveRequest.setResidenceNumber(10);
        approveRequest.setUserName("Maya");
        approveRequest.setPassword("pass");
        approveRequest.setUserType("resident");

        when(approveRequestRepository.findById(1)).thenReturn(Optional.of(approveRequest));


        Roles role = new Roles();
        role.setRoleId(1);
        role.setRoleName("resident");
        role.setUserList(new ArrayList<>());

        User user = new User();
        user.setUser_id(1);
        user.setUserName("Maya");
        user.setRoles(new ArrayList<>());

        when(roleRepository.findByRoleName(approveRequest.getUserType())).thenReturn(role);
        when(residentRepository.existsByResidenceNumber(approveRequest.getResidenceNumber())).thenReturn(false);
        when(userRepository.findByUserName(approveRequest.getUserName())).thenReturn(Optional.of(user));
        Resident resident = new Resident();
        resident.setId(1);
        resident.setResidenceNumber(10);
        resident.setActive(true);
        resident.setResidentName("Rose");
        resident.setAadhar(3456789L);
        resident.setPhoneNumber(1324567890L);
        resident.setVisitorsList(new ArrayList<>());

        UserObject userObjectResponse = adminService.approveRequest(1);
        assertEquals(userObject, userObjectResponse);

    }

    /**
     * Unit tes for approveRequest
     */
    @Test
    public void testApproveRequestGateKeeper() {
        UserObject userObject = new UserObject();
        userObject.setUserType("gatekeeper");
        userObject.setAadhar(3456789L);
        userObject.setUserId(1);
        userObject.setPhoneNumber(345678890L);
        userObject.setUserName("Maya");

        ApproveRequest approveRequest = new ApproveRequest();
        approveRequest.setRequestId(1);
        approveRequest.setPhoneNumber(345678890L);
        approveRequest.setHasApproved(true);
        approveRequest.setAadhar(3456789L);
        approveRequest.setResidenceNumber(10);
        approveRequest.setUserName("Maya");
        approveRequest.setPassword("pass");
        approveRequest.setUserType("gatekeeper");

        when(approveRequestRepository.findById(1)).thenReturn(Optional.of(approveRequest));


        Roles role = new Roles();
        role.setRoleId(1);
        role.setRoleName("gatekeeper");
        role.setUserList(new ArrayList<>());

        User user = new User();
        user.setUser_id(1);
        user.setUserName("Maya");
        user.setRoles(new ArrayList<>());

        when(roleRepository.findByRoleName(approveRequest.getUserType())).thenReturn(role);
        when(gateKeeperRepository.existsByAadhar(approveRequest.getAadhar())).thenReturn(false);
        //when(residentRepository.existsByResidenceNumber(approveRequest.getResidenceNumber())).thenReturn(false);
        when(userRepository.findByUserName(approveRequest.getUserName())).thenReturn(Optional.of(user));
        GateKeeper gateKeeper = new GateKeeper();
        gateKeeper.setGatekeeper_id(1);
        gateKeeper.setGateId(1);
        gateKeeper.setActive(true);
        gateKeeper.setGatekeeperName("Babu");
        gateKeeper.setAadhar(3456789L);
        gateKeeper.setPhone_number(1324567890L);
        gateKeeper.setVisitorsList(new ArrayList<>());

        UserObject userObjectResponse = adminService.approveRequest(1);
        assertEquals(userObject, userObjectResponse);

    }

    /**
     * Unit test for viewRegistrationRequests
     */
    @Test
    public void viewRegnReqTest() {

        List<ApproveRequest> approveRequestList = new ArrayList<>();
        ApproveRequest approveRequest = new ApproveRequest();
        approveRequest.setRequestId(1);
        approveRequest.setPhoneNumber(345678890L);
        approveRequest.setHasApproved(true);
        approveRequest.setAadhar(3456789L);
        approveRequest.setResidenceNumber(10);
        approveRequest.setUserName("Maya");
        approveRequest.setPassword("pass");
        approveRequest.setUserType("gatekeeper");
        approveRequest.setHasApproved(false);
        approveRequestList.add(approveRequest);

        List<UserObject> userObjectsList = new ArrayList<>();
        UserObject userObject = new UserObject();
        userObject.setUserType("gatekeeper");
        userObject.setAadhar(3456789L);
        userObject.setUserId(1);
        userObject.setPhoneNumber(345678890L);
        userObject.setUserName("Maya");
        userObjectsList.add(userObject);

        when(approveRequestRepository.findAll()).thenReturn(approveRequestList);
        List<UserObject> response = adminService.viewRegistrationRequests();
        assertEquals(response, userObjectsList);


    }


}
