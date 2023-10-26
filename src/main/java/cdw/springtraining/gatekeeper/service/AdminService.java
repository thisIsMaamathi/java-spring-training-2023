package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.constant.CommonConstants;
import cdw.springtraining.gatekeeper.entites.Users;
import cdw.springtraining.gatekeeper.exceptions.NoEntriesException;
import cdw.springtraining.gatekeeper.exceptions.UserAlreadyExistsException;
import cdw.springtraining.gatekeeper.exceptions.UserHasBeenRemovedException;
import cdw.springtraining.gatekeeper.exceptions.UserNotFoundException;
import cdw.springtraining.gatekeeper.models.*;
import cdw.springtraining.gatekeeper.repository.BlackListRepository;
import cdw.springtraining.gatekeeper.repository.RolesRepository;
import cdw.springtraining.gatekeeper.repository.UserRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import cdw.springtraining.gatekeeper.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AdminService {


    BlackListRepository repository;
    UserRepository userRepository;
    VisitorRepository visitorRepository;
    RolesRepository roleRepository;
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AdminService(BlackListRepository repository, UserRepository userRepository, VisitorRepository visitorRepository, RolesRepository roleRepository,CustomUserDetailsService customUserDetailsService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.visitorRepository = visitorRepository;
        this.roleRepository = roleRepository;
        this.customUserDetailsService=customUserDetailsService;
    }

    /**
     * Method to approve a user request
     *
     * @param requestId
     * @return UserObject
     */
    @Transactional
    public UserResponse approveRequest(Integer requestId) {
        Users user = userRepository.findById(requestId).orElseThrow(() -> new UserNotFoundException(CommonConstants.APPROVE_REQUEST_NOT_FOUND));
        if(user.getUserType().equalsIgnoreCase("admin")) throw new UserAlreadyExistsException(CommonConstants.USER_IS_ADMIN);
        user.setIsApproved("approved");
        user.setActive(true);
        user.setApprovedBy(customUserDetailsService.getCurrentUserName());
        userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setAadhar(user.getAadhar());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setUserType(user.getUserType());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setDob(user.getDob());
        response.setResidenceId(user.getResidenceNumber());
        response.setIsActive(user.isActive());
        response.setIsApproved(user.getIsApproved());
        return response;
    }

    /**
     * Method to get a list of all  active residents
     *
     * @return List of Resident object where each object contains information about the resident
     */
    public List<ResidentAdminResponse> getAllResidents() {
        List<Users> residents = userRepository.findByUserTypeAndIsActive("resident", true);
        if (residents.size() == 0) throw new NoEntriesException(CommonConstants.NO_ACTIVE_RESIDENTS);
        return residents.stream().map(resident -> {
            ResidentAdminResponse response = new ResidentAdminResponse();
            response.setUserId(resident.getUserId());
            response.setResidentName(resident.getFirstName() + " " + resident.getLastName());
            response.setResidenceId(resident.getResidenceNumber());
            response.setUserName(resident.getUserName());
            response.setDob(resident.getDob());
            response.setGender(resident.getGender());
            response.setEmail(resident.getEmail());
            response.setPhoneNumber(resident.getPhoneNumber());
            response.setAadhar(resident.getAadhar());
            response.setIsActive(resident.isActive());
            response.setIsApproved(resident.getIsApproved());
            response.setApprovedBy(resident.getApprovedBy());

            return response;

        }).collect(Collectors.toList());
    }


    /**
     * View a list of approved requests
     * @return list of user response
     */

    public List<UserResponse> viewApprovedRequests() {
        List<Users> requests = userRepository.findByIsActive(true);
        if (requests.size() == 0) throw new NoEntriesException(CommonConstants.NO_REQUEST_TO_APPROVE);
        return requests.stream()
                .map(user -> {
                    UserResponse response = new UserResponse();
                    response.setUserId(user.getUserId());
                    response.setUserName(user.getUserName());
                    response.setAadhar(user.getAadhar());
                    response.setPhoneNumber(user.getPhoneNumber());
                    response.setUserType(user.getUserType());
                    response.setFirstName(user.getFirstName());
                    response.setLastName(user.getLastName());
                    response.setEmail(user.getEmail());
                    response.setDob(user.getDob());
                    response.setResidenceId(user.getResidenceNumber());
                    response.setIsActive(user.isActive());
                    response.setIsApproved(user.getIsApproved());
                    return response;
                }).toList();
    }

    /**
     * Method to soft delete a ruser
     *
     * @param userId
     */
    public void deleteUser(Integer userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(CommonConstants.USER_NOT_FOUND));
        if (!user.isActive()) throw new RuntimeException(CommonConstants.USER_ALREADY_DELETED);
        user.setActive(false);
        userRepository.save(user);
    }

    /**
     * Method to update user details
     *
     * @param userId
     * @param updateUserRequest
     * @return User object
     */

    public UserAdminResponse updateUser(Integer userId, UpdateUserRequest updateUserRequest) {
        Optional<Users> userOptional = userRepository.findById(userId);
        if (!userOptional.get().isActive())
            throw new UserHasBeenRemovedException(CommonConstants.RESIDENT_WAS_DELETED);
        Users user = userOptional.get();
        if(updateUserRequest.getFirstName()!=null){
            user.setFirstName(updateUserRequest.getFirstName());
        }
        if(updateUserRequest.getLastName()!=null){
            user.setLastName(updateUserRequest.getLastName());
        }
        if (updateUserRequest.getUserName() != null) {
            user.setUserName(updateUserRequest.getUserName());
        }
        if (updateUserRequest.getAadhar() != null) {
            user.setAadhar(updateUserRequest.getAadhar());
        }
        if (updateUserRequest.getPhoneNumber() != null) {
            user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        }
        if (updateUserRequest.getEmail() != null) {
            user.setEmail(updateUserRequest.getEmail());
        }
        if (updateUserRequest.getDob() != null) {
            user.setDob(updateUserRequest.getDob());
        }
        if (updateUserRequest.getGender() != null) {
            user.setGender(updateUserRequest.getGender());
        }
        userRepository.save(user);
        UserAdminResponse response = new UserAdminResponse();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setAadhar(user.getAadhar());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setEmail(user.getEmail());
        response.setDob(user.getDob());
        response.setResidenceId(user.getResidenceNumber());
        response.setIsActive(user.isActive());
        response.setIsApproved(user.getIsApproved());
        return response;

    }

    /**
     * Method to get a user by Id
     *
     * @param userId
     * @return UserAdminResponse
     */
    public UserAdminResponse getUsersById(Integer userId) {
      Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(CommonConstants.USER_NOT_FOUND));
        if (user.isActive()) {
           UserAdminResponse response = new UserAdminResponse();
            response.setUserId(user.getUserId());
            response.setName(user.getFirstName() + " " + user.getLastName());
            response.setResidenceId(user.getResidenceNumber());
            response.setUserName(user.getUserName());
            response.setDob(user.getDob());
            response.setGender(user.getGender());
            response.setEmail(user.getEmail());
            response.setPhoneNumber(user.getPhoneNumber());
            response.setAadhar(user.getAadhar());
            response.setIsActive(user.isActive());
            response.setIsApproved(user.getIsApproved());
            response.setUserType(user.getUserType());
            return response;
        } else throw new UserHasBeenRemovedException(CommonConstants.USER_HAD_BEEN_REMOVED);

    }


    /**
     * Method to get a list of gatekeepers
     *
     * @return List containing Gatekeeper objects
     */
    public List<GateKeeperAdminResponse> getAllGateKeepers() {
        List<Users> gateKeepers = userRepository.findByUserTypeAndIsActive("gatekeeper", true);
        if (gateKeepers.size() == 0) throw new NoEntriesException(CommonConstants.NO_ACTIVE_GATE_KEEPERS);
        return gateKeepers.stream()
                .map(gateKeeper -> {
                   GateKeeperAdminResponse response=new GateKeeperAdminResponse();
                   response.setUserId(gateKeeper.getUserId());
                   response.setName(gateKeeper.getFirstName()+" "+gateKeeper.getLastName());
                   response.setUserName(gateKeeper.getUserName());
                   response.setEmail(gateKeeper.getEmail());
                   response.setDob(gateKeeper.getDob());
                   response.setGender(gateKeeper.getGender());
                   response.setAadhar(gateKeeper.getAadhar());
                   response.setPhoneNumber(gateKeeper.getPhoneNumber());
                   response.setIsActive(gateKeeper.isActive());
                   response.setIsApproved(gateKeeper.getIsApproved());
                   return response;
                }).collect(Collectors.toList());
    }

    public UserResponse rejectUser(Integer requestId) {
            Users user = userRepository.findById(requestId).orElseThrow(() -> new UserNotFoundException(CommonConstants.APPROVE_REQUEST_NOT_FOUND));
            if(user.getUserType().equalsIgnoreCase("admin")) throw new UserAlreadyExistsException(CommonConstants.USER_IS_ADMIN);
            user.setIsApproved("rejected");
            user.setActive(true);
            user.setApprovedBy(customUserDetailsService.getCurrentUserName());
            userRepository.save(user);

            UserResponse response = new UserResponse();
            response.setUserId(user.getUserId());
            response.setUserName(user.getUserName());
            response.setAadhar(user.getAadhar());
            response.setPhoneNumber(user.getPhoneNumber());
            response.setUserType(user.getUserType());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
            response.setDob(user.getDob());
            response.setResidenceId(user.getResidenceNumber());
            response.setIsActive(user.isActive());
            response.setIsApproved(user.getIsApproved());
            return response;

    }

    public List<UserResponse> viewRequestApproved() {
        List<Users> requests = userRepository.findByIsApproved("approved");
        if (requests.size() == 0) throw new NoEntriesException(CommonConstants.NO_REQUEST_TO_APPROVE);
        return requests.stream()
                .map(user -> {
                    UserResponse response = new UserResponse();
                    response.setUserId(user.getUserId());
                    response.setUserName(user.getUserName());
                    response.setAadhar(user.getAadhar());
                    response.setPhoneNumber(user.getPhoneNumber());
                    response.setUserType(user.getUserType());
                    response.setFirstName(user.getFirstName());
                    response.setLastName(user.getLastName());
                    response.setEmail(user.getEmail());
                    response.setDob(user.getDob());
                    response.setResidenceId(user.getResidenceNumber());
                    response.setIsActive(user.isActive());
                    response.setIsApproved(user.getIsApproved());
                    return response;
                }).toList();
    }

    public List<UserResponse> viewRequestRejected() {
        List<Users> requests = userRepository.findByIsApproved("rejected");
        if (requests.size() == 0) throw new NoEntriesException(CommonConstants.NO_REQUEST_TO_APPROVE);
        return requests.stream()
                .map(user -> {
                    UserResponse response = new UserResponse();
                    response.setUserId(user.getUserId());
                    response.setUserName(user.getUserName());
                    response.setAadhar(user.getAadhar());
                    response.setPhoneNumber(user.getPhoneNumber());
                    response.setUserType(user.getUserType());
                    response.setFirstName(user.getFirstName());
                    response.setLastName(user.getLastName());
                    response.setEmail(user.getEmail());
                    response.setDob(user.getDob());
                    response.setResidenceId(user.getResidenceNumber());
                    response.setIsActive(user.isActive());
                    response.setIsApproved(user.getIsApproved());
                    return response;
                }).toList();
    }
}
