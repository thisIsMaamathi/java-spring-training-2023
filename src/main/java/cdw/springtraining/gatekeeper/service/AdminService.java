package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.constant.CommonConstants;

import cdw.springtraining.gatekeeper.entites.ApproveRequest;
import cdw.springtraining.gatekeeper.entites.GateKeeper;
import cdw.springtraining.gatekeeper.entites.Resident;
import cdw.springtraining.gatekeeper.entites.User;
import cdw.springtraining.gatekeeper.entites.Roles;
import cdw.springtraining.gatekeeper.exceptions.BlackListedUserException;
import cdw.springtraining.gatekeeper.exceptions.NoEntriesException;
import cdw.springtraining.gatekeeper.exceptions.UserAlreadyExistsException;
import cdw.springtraining.gatekeeper.exceptions.UserHasBeenRemovedException;
import cdw.springtraining.gatekeeper.exceptions.UserNotFoundException;

import cdw.springtraining.gatekeeper.models.CreateGateKeeper;
import cdw.springtraining.gatekeeper.models.CreateResident;
import cdw.springtraining.gatekeeper.models.ResidentObject;
import cdw.springtraining.gatekeeper.models.UpdateResident;
import cdw.springtraining.gatekeeper.models.GateKeeperObject;
import cdw.springtraining.gatekeeper.models.UserObject;
import cdw.springtraining.gatekeeper.models.UpdateGateKeeper;
import cdw.springtraining.gatekeeper.repository.RoleRepository;
import cdw.springtraining.gatekeeper.repository.UserRepository;
import cdw.springtraining.gatekeeper.repository.ApproveRequestRepository;
import cdw.springtraining.gatekeeper.repository.BlacklistRepository;
import cdw.springtraining.gatekeeper.repository.GateKeeperRepository;
import cdw.springtraining.gatekeeper.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    ResidentRepository residentRepository;
    BlacklistRepository blacklistRepository;
    GateKeeperRepository gateKeeperRepository;
    ApproveRequestRepository approveRequestRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public AdminService(ResidentRepository residentRepository, BlacklistRepository blacklistRepository, GateKeeperRepository gateKeeperRepository, ApproveRequestRepository approveRequestRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.residentRepository = residentRepository;
        this.blacklistRepository = blacklistRepository;
        this.gateKeeperRepository = gateKeeperRepository;
        this.approveRequestRepository = approveRequestRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Method to get a list of all  active residents
     *
     * @return List of Resident object where each object contains information about the resident
     */
    public List<ResidentObject> getAllResidents() {
        List<Resident> residents = residentRepository.findByIsActive(true);
        if (residents.size() == 0) throw new NoEntriesException(CommonConstants.NO_ACTIVE_RESIDENTS);
        return residents.stream().filter(Resident::isActive)
                .map(resident -> {
                    ResidentObject residentObject = new ResidentObject();
                    residentObject.setId(resident.getId());
                    residentObject.setResidentName(resident.getResidentName());
                    residentObject.setAadhar(resident.getAadhar());
                    residentObject.setPhoneNumber(resident.getPhoneNumber());
                    residentObject.setResidenceId(resident.getResidenceNumber());
                    return residentObject;

                }).collect(Collectors.toList());
    }

    /**
     * Method to create new resident
     *
     * @param request
     * @return Resident object containing information about the resident
     */
    public ResidentObject createNewResident(CreateResident request) {
        if (residentRepository.existsByResidenceNumber(request.getResidenceId()))
            throw new UserAlreadyExistsException(CommonConstants.RESIDENCE_HAD_BEEN_REGISTERED);
        Resident resident = new Resident(request.getResidentName(), request.getAadhar(), request.getResidenceId(), request.getPhoneNumber(), true);
        residentRepository.save(resident);
        Resident response = residentRepository.findByAadharAndResidenceNumber(request.getAadhar(), request.getResidenceId());
        ResidentObject residentObject = new ResidentObject();
        residentObject.setId(response.getId());
        residentObject.setResidentName(response.getResidentName());
        residentObject.setAadhar(response.getAadhar());
        residentObject.setPhoneNumber(response.getPhoneNumber());
        residentObject.setResidenceId(response.getResidenceNumber());
        return residentObject;

    }

    /**
     * Method to soft delete a resident
     *
     * @param residentId
     * @return String containg appropriate message
     */
    public String deleteResident(Integer residentId) {
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new UserNotFoundException(CommonConstants.USER_NOT_FOUND));
        if (!resident.isActive()) return CommonConstants.RESIDENT_HAD_BEEN_DELETED;
        resident.setActive(false);
        residentRepository.save(resident);
        return "Deleted Resident " + resident.getResidentName() + " of residenceId " + resident.getResidenceNumber();
    }

    /**
     * Method to update resident details
     *
     * @param residentId
     * @param updateResident
     * @return Resident object
     */

    public ResidentObject updateResident(Integer residentId, UpdateResident updateResident) {
        Optional<Resident> residentOptional = residentRepository.findById(residentId);
        if (!residentOptional.get().isActive())
            throw new UserHasBeenRemovedException(CommonConstants.RESIDENT_WAS_DELETED);
        if (residentOptional.isPresent()) {
            Resident resident = residentOptional.get();

            if (updateResident.getResidentName() != null) {
                resident.setResidentName(updateResident.getResidentName());
            }
            if (updateResident.getAadhar() != null) {
                resident.setAadhar(updateResident.getAadhar());
            }
            if (updateResident.getPhoneNumber() != null) {
                resident.setPhoneNumber(updateResident.getPhoneNumber());
            }
            residentRepository.save(resident);

            ResidentObject residentObject = new ResidentObject();
            residentObject.setId(resident.getId());
            residentObject.setResidentName(resident.getResidentName());
            residentObject.setAadhar(resident.getAadhar());
            residentObject.setPhoneNumber(resident.getPhoneNumber());
            residentObject.setResidenceId(resident.getResidenceNumber());

            return residentObject;

        } else throw new UserNotFoundException(CommonConstants.USER_NOT_FOUND);

    }

    /**
     * Method to get a resident by Id
     *
     * @param residentId
     * @return Resident Object
     */
    public ResidentObject getResidentById(Integer residentId) {
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new UserNotFoundException(CommonConstants.USER_NOT_FOUND));
        if (resident.isActive()) {
            ResidentObject residentObject = new ResidentObject();
            residentObject.setId(resident.getId());
            residentObject.setResidentName(resident.getResidentName());
            residentObject.setAadhar(resident.getAadhar());
            residentObject.setPhoneNumber(resident.getPhoneNumber());
            residentObject.setResidenceId(resident.getResidenceNumber());

            return residentObject;
        } else throw new UserHasBeenRemovedException(CommonConstants.USER_HAD_BEEN_REMOVED);

    }

    /**
     * Method to create a new gatekeeper
     *
     * @param request
     * @return GateKeeper object containing information about gatekeeper
     */
    public GateKeeperObject createNewGateKeeper(CreateGateKeeper request) {
        if (blacklistRepository.existsByAadhar(request.getAadhar()))
            throw new BlackListedUserException(CommonConstants.BLACKLISTED_GATEKEEPER);

        if (gateKeeperRepository.existsByAadhar(request.getAadhar()))
            throw new UserAlreadyExistsException(CommonConstants.USER_ALREADY_REGISTERED);
        GateKeeper gateKeeper = new GateKeeper(1, request.getAadhar(), request.getGateKeeperName(), request.getPhoneNumber(), true);
        gateKeeperRepository.save(gateKeeper);

        GateKeeper response = gateKeeperRepository.findByAadhar(request.getAadhar());
        GateKeeperObject gateKeeperObject = new GateKeeperObject();
        gateKeeperObject.setId(response.getGatekeeper_id());
        gateKeeperObject.setGateKeeperName(response.getGatekeeperName());
        gateKeeperObject.setAadhar(response.getAadhar());
        gateKeeperObject.setPhoneNumber(response.getPhone_number());
        gateKeeperObject.setGateId(response.getGateId());
        return gateKeeperObject;
    }

    /**
     * Method to soft delete a gateKeeper
     *
     * @param gatekeeperId
     * @return String containing appropriate information
     */
    public String deleteAGatekeeper(Integer gatekeeperId) {
        GateKeeper gateKeeper = gateKeeperRepository.findById(gatekeeperId).orElseThrow(() -> new UserNotFoundException(CommonConstants.GATEKEEPER_NOT_FOUND));
        if (blacklistRepository.existsByAadhar(gateKeeper.getAadhar()))
            throw new BlackListedUserException(CommonConstants.BLACKLISTED_GATEKEEPER);
        if (!gateKeeper.isActive()) return CommonConstants.GATE_KEEPER_ALREADY_DELETED;
        gateKeeper.setActive(false);
        gateKeeperRepository.save(gateKeeper);
        return "The gatekeeper " + gateKeeper.getGatekeeperName() + " has been deleted";
    }

    /**
     * Method to get a list of gatekeepers
     *
     * @return List containing Gatekeeper objects
     */
    public List<GateKeeperObject> getAllGateKeepers() {
        List<GateKeeper> gateKeepers = gateKeeperRepository.findByIsActive(true);
        if (gateKeepers.size() == 0) throw new NoEntriesException(CommonConstants.NO_ACTIVE_GATE_KEEPERS);
        return gateKeepers.stream()
                .map(gateKeeper -> {
                    GateKeeperObject gateKeeperObject = new GateKeeperObject();
                    gateKeeperObject.setId(gateKeeper.getGatekeeper_id());
                    gateKeeperObject.setGateKeeperName(gateKeeper.getGatekeeperName());
                    gateKeeperObject.setAadhar(gateKeeper.getAadhar());
                    gateKeeperObject.setPhoneNumber(gateKeeper.getPhone_number());
                    gateKeeperObject.setGateId(gateKeeper.getGateId());
                    return gateKeeperObject;

                }).collect(Collectors.toList());
    }

    /**
     * Method to get a gatekeeper by Id
     *
     * @param gateKeeperId
     * @return Gatekeeper Object
     */

    public GateKeeperObject getAGateKeeper(Integer gateKeeperId) {
        GateKeeper gateKeeper = gateKeeperRepository.findById(gateKeeperId).orElseThrow(() -> new UserNotFoundException(CommonConstants.GATEKEEPER_NOT_FOUND));
        if (gateKeeper.isActive()) {
            GateKeeperObject gateKeeperObject = new GateKeeperObject();
            gateKeeperObject.setId(gateKeeper.getGatekeeper_id());
            gateKeeperObject.setGateKeeperName(gateKeeper.getGatekeeperName());
            gateKeeperObject.setAadhar(gateKeeper.getAadhar());
            gateKeeperObject.setPhoneNumber(gateKeeper.getPhone_number());
            gateKeeperObject.setGateId(gateKeeper.getGateId());

            return gateKeeperObject;
        } else throw new UserHasBeenRemovedException(CommonConstants.USER_HAD_BEEN_REMOVED);

    }

    /**
     * Method to update Gatekeeper details
     *
     * @param gatekeeperId
     * @param updateGateKeeper
     * @return Gatekeeper Objects
     */
    public GateKeeperObject updateGateKeeper(Integer gatekeeperId, UpdateGateKeeper updateGateKeeper) {
        GateKeeper gateKeeper = gateKeeperRepository.findById(gatekeeperId).orElseThrow(() -> new UserNotFoundException(CommonConstants.GATEKEEPER_NOT_FOUND));

        if (!gateKeeper.isActive())
            throw new UserHasBeenRemovedException(CommonConstants.USER_HAD_BEEN_REMOVED);

        if (updateGateKeeper.getGateKeeperName() != null) {
            gateKeeper.setGatekeeperName(updateGateKeeper.getGateKeeperName());
        }
        if (updateGateKeeper.getAadhar() != null) {
            gateKeeper.setAadhar(updateGateKeeper.getAadhar());
        }
        if (updateGateKeeper.getPhoneNumber() != null) {
            gateKeeper.setPhone_number(updateGateKeeper.getPhoneNumber());
        }
        gateKeeperRepository.save(gateKeeper);
        GateKeeperObject gateKeeperObject = new GateKeeperObject();
        gateKeeperObject.setId(gateKeeper.getGatekeeper_id());
        gateKeeperObject.setGateKeeperName(gateKeeper.getGatekeeperName());
        gateKeeperObject.setAadhar(gateKeeper.getAadhar());
        gateKeeperObject.setPhoneNumber(gateKeeper.getPhone_number());
        gateKeeperObject.setGateId(gateKeeper.getGateId());
        return gateKeeperObject;
    }

    /**
     * Method to view a list of Registration requests
     *
     * @return List of UserObject where each object contains  userdetails
     */
    public List<UserObject> viewRegistrationRequests() {
        List<ApproveRequest> requests = approveRequestRepository.findAll();
        if (requests.size() == 0) throw new NoEntriesException(CommonConstants.NO_ENTRIES);
        return requests.stream().filter(approveRequest -> !approveRequest.isHasApproved())
                .map(approveRequest -> {
                    UserObject userObject = new UserObject();
                    userObject.setUserId(approveRequest.getRequestId());
                    userObject.setUserName(approveRequest.getUserName());
                    userObject.setUserType(approveRequest.getUserType());
                    userObject.setAadhar(approveRequest.getAadhar());
                    userObject.setPhoneNumber(approveRequest.getPhoneNumber());
                    return userObject;
                }).toList();
    }

    /**
     * Method to approve a user request
     *
     * @param requestId
     * @return UserObject
     */
    public UserObject approveRequest(Integer requestId) {
        ApproveRequest request = approveRequestRepository.findById(requestId).orElseThrow(() -> new UserNotFoundException(CommonConstants.APPROVE_REQUEST_NOT_FOUND));
        request.setHasApproved(true);

        if (request.getResidenceNumber() != 0 && residentRepository.existsByResidenceNumber(request.getResidenceNumber()))
            throw new UserAlreadyExistsException(CommonConstants.RESIDENCE_HAD_BEEN_REGISTERED);

        User user = new User(request.getUserName(), request.getFirstName(), request.getLastName(), request.getEmail(), request.getDateOfBirth(), request.getGender(), request.getPassword(), request.getAadhar());
        Roles role = roleRepository.findByRoleName(request.getUserType());
        if (role == null) throw new UserNotFoundException(CommonConstants.ROLE_NOT_FOUND);

        user.getRoles().add(role);
        userRepository.save(user);

        User current_user = userRepository.findByUserName(request.getUserName()).orElseThrow(() -> new UserNotFoundException(CommonConstants.USER_NOT_FOUND));
        if (request.getUserType().equalsIgnoreCase("resident")) {
            Resident resident = new Resident(current_user.getUser_id(), request.getUserName(), request.getAadhar(), request.getResidenceNumber(), request.getPhoneNumber(), true);
            residentRepository.save(resident);
            UserObject userObject = new UserObject();
            userObject.setUserId(current_user.getUser_id());
            userObject.userName(user.getUserName());
            userObject.setPhoneNumber(resident.getPhoneNumber());
            userObject.setAadhar(resident.getAadhar());
            userObject.setUserType(request.getUserType());
            return userObject;
        }

        else if (request.getUserType().equalsIgnoreCase("gatekeeper")) {
            if (residentRepository.existsByAadhar(request.getAadhar()))
                throw new UserAlreadyExistsException(CommonConstants.USER_ALREADY_REGISTERED);
            if (gateKeeperRepository.existsByAadhar(request.getAadhar()))
                throw new BlackListedUserException(CommonConstants.BLACKLISTED_GATEKEEPER);

            GateKeeper gateKeeper = new GateKeeper(current_user.getUser_id(), 1, request.getAadhar(), request.getUserName(), request.getPhoneNumber(), true);
            gateKeeperRepository.save(gateKeeper);

            UserObject userObject = new UserObject();
            userObject.setUserId(current_user.getUser_id());
            userObject.userName(user.getUserName());
            userObject.setPhoneNumber(gateKeeper.getPhone_number());
            userObject.setAadhar(gateKeeper.getAadhar());
            userObject.setUserType(request.getUserType());
            return userObject;
        }
        else
            throw new RuntimeException(CommonConstants.ROLE_DOES_NOT_MATCH);
    }
}
