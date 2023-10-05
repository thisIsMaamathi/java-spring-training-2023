package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.entites.*;
import cdw.springtraining.gatekeeper.exceptions.BlackListedUserException;
import cdw.springtraining.gatekeeper.exceptions.UserAlreadyExistsException;
import cdw.springtraining.gatekeeper.exceptions.UserHasBeenRemovedException;
import cdw.springtraining.gatekeeper.exceptions.UserNotFoundException;
import cdw.springtraining.gatekeeper.models.*;
import cdw.springtraining.gatekeeper.repository.*;
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
    public AdminService(ResidentRepository residentRepository, BlacklistRepository blacklistRepository, GateKeeperRepository gateKeeperRepository, ApproveRequestRepository approveRequestRepository,UserRepository userRepository,RoleRepository roleRepository) {
        this.residentRepository = residentRepository;
        this.blacklistRepository = blacklistRepository;
        this.gateKeeperRepository = gateKeeperRepository;
        this.approveRequestRepository = approveRequestRepository;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }

    public List<ResidentObject> getAllResidents() {
        List<Resident> residents = residentRepository.findAll();
        List<ResidentObject> residentList = residents.stream().filter(resident -> resident.isActive())
                .map(resident -> {
                    ResidentObject residentObject = new ResidentObject();
                    residentObject.setId(resident.getId());
                    residentObject.setResidentName(resident.getResidentName());
                    residentObject.setAadhar(resident.getAadhar());
                    residentObject.setPhoneNumber(resident.getPhoneNumber());
                    residentObject.setResidenceId(resident.getResidenceNumber());
                    return residentObject;

                }).collect(Collectors.toList());
        return residentList;
    }

    public ResidentObject createNewResident(CreateResident request) throws Exception {

        if (residentRepository.existsByAadhar(request.getAadhar()))
            throw new UserAlreadyExistsException("You have already registered");

        Resident resident = new Resident(request.getResidentName(), request.getAadhar(), request.getResidenceId(), request.getPhoneNumber(), true);
        residentRepository.save(resident);

        Resident response = residentRepository.findByAadhar(request.getAadhar());
        ResidentObject residentObject = new ResidentObject();
        residentObject.setId(response.getId());
        residentObject.setResidentName(response.getResidentName());
        residentObject.setAadhar(response.getAadhar());
        residentObject.setPhoneNumber(response.getPhoneNumber());
        residentObject.setResidenceId(response.getResidenceNumber());
        return residentObject;

    }


    public boolean deleteResident(Integer residentId) throws Exception {
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new UserNotFoundException("Resident not found"));
        resident.setActive(false);
        residentRepository.save(resident);
        return true;
    }

    public ResidentObject updateResident(Integer residentId, UpdateResident updateResident) throws Exception {
        Optional<Resident> residentOptional = residentRepository.findById(residentId);
        if (!residentOptional.get().isActive()) throw new UserHasBeenRemovedException("The resident had been deleted");
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

        } else throw new UserNotFoundException("Resident not found");

    }

    public ResidentObject getResidentById(Integer residentId) throws Exception {
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new UserNotFoundException("Resident Not found"));
        if (resident.isActive()) {
            ResidentObject residentObject = new ResidentObject();
            residentObject.setId(resident.getId());
            residentObject.setResidentName(resident.getResidentName());
            residentObject.setAadhar(resident.getAadhar());
            residentObject.setPhoneNumber(resident.getPhoneNumber());
            residentObject.setResidenceId(resident.getResidenceNumber());

            return residentObject;
        } else throw new UserHasBeenRemovedException("The resident had been deleted");

    }

    public GateKeeperObject createNewGateKeeper(CreateGateKeeper request) throws Exception {
        if (blacklistRepository.existsByAadhar(request.getAadhar()))
            throw new BlackListedUserException("Sorry,you have been blacklisted");

        if (gateKeeperRepository.existsByAadhar(request.getAadhar()))
            throw new UserAlreadyExistsException("You have already registered");
        GateKeeper gateKeeper = new GateKeeper(1, request.getAadhar(), request.getGateKeeperName(), request.getPhoneNumber(), true);
        gateKeeperRepository.save(gateKeeper);

        GateKeeper response = gateKeeperRepository.findByAadhar(request.getAadhar());
        GateKeeperObject gateKeeperObject = new GateKeeperObject();
        gateKeeperObject.setId(response.getGatekeeper_id());
        gateKeeperObject.setGateKeeperName(response.getGatekeeper_name());
        gateKeeperObject.setAadhar(response.getAadhar());
        gateKeeperObject.setPhoneNumber(response.getPhone_number());
        gateKeeperObject.setGateId(response.getGateId());
        return gateKeeperObject;
    }

    public boolean deleteAGatekeeper(Integer gatekeeperId) throws Exception {
        GateKeeper gateKeeper = gateKeeperRepository.findById(gatekeeperId).orElseThrow(() -> new UserNotFoundException("gateKeeper not found"));
        gateKeeper.setActive(false);
        gateKeeperRepository.save(gateKeeper);
        return true;
    }

    public List<GateKeeperObject> getAllGateKeepers() {
        List<GateKeeper> gateKeepers = gateKeeperRepository.findAll();
        List<GateKeeperObject> gateKeeperList = gateKeepers.stream().filter(GateKeeper::isActive)
                .map(gateKeeper -> {
                    GateKeeperObject gateKeeperObject = new GateKeeperObject();
                    gateKeeperObject.setId(gateKeeper.getGatekeeper_id());
                    gateKeeperObject.setGateKeeperName(gateKeeper.getGatekeeper_name());
                    gateKeeperObject.setAadhar(gateKeeper.getAadhar());
                    gateKeeperObject.setPhoneNumber(gateKeeper.getPhone_number());
                    gateKeeperObject.setGateId(gateKeeper.getGateId());
                    return gateKeeperObject;

                }).collect(Collectors.toList());
        return gateKeeperList;
    }

    public GateKeeperObject getAGateKeeper(Integer gateKeeperId) throws Exception {
        GateKeeper gateKeeper = gateKeeperRepository.findById(gateKeeperId).orElseThrow(() -> new UserNotFoundException("GateKeeper Not found"));
        if (gateKeeper.isActive()) {
            GateKeeperObject gateKeeperObject = new GateKeeperObject();
            gateKeeperObject.setId(gateKeeper.getGatekeeper_id());
            gateKeeperObject.setGateKeeperName(gateKeeper.getGatekeeper_name());
            gateKeeperObject.setAadhar(gateKeeper.getAadhar());
            gateKeeperObject.setPhoneNumber(gateKeeper.getPhone_number());
            gateKeeperObject.setGateId(gateKeeper.getGateId());

            return gateKeeperObject;
        } else throw new UserHasBeenRemovedException("The resident had been deleted");

    }

    public GateKeeperObject updateGateKeeper(Integer gatekeeperId, UpdateGateKeeper updateGateKeeper) throws Exception {
        Optional<GateKeeper> gateKeeperOptional = gateKeeperRepository.findById(gatekeeperId);
        if (!gateKeeperOptional.get().isActive())
            throw new UserHasBeenRemovedException("The resident had been deleted");
        if (gateKeeperOptional.isPresent()) {
            GateKeeper gateKeeper = gateKeeperOptional.get();

            if (updateGateKeeper.getGateKeeperName() != null) {
                gateKeeper.setGatekeeper_name(updateGateKeeper.getGateKeeperName());
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
            gateKeeperObject.setGateKeeperName(gateKeeper.getGatekeeper_name());
            gateKeeperObject.setAadhar(gateKeeper.getAadhar());
            gateKeeperObject.setPhoneNumber(gateKeeper.getPhone_number());
            gateKeeperObject.setGateId(gateKeeper.getGateId());

            return gateKeeperObject;

        } else throw new UserNotFoundException("Gate keeper not found");


    }

    public List<UserObject> viewRegistrationRequests() {
        List<ApproveRequest> requests = approveRequestRepository.findAll();
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

    public UserObject approveRequest(Integer requestId) throws Exception {
        ApproveRequest request=approveRequestRepository.findById(requestId).orElseThrow(()-> new UserNotFoundException("The request is not present"));
        request.setHasApproved(true);

        User user=new User(request.getUserName(), request.getPassword());
        Roles role=roleRepository.findByRoleName(request.getUserType());
        user.getRoles().add(role);
        userRepository.save(user);

        Optional<User> current_user=userRepository.findByUserName(request.getUserName());

        if(request.getUserType().equalsIgnoreCase("resident")){
            Resident resident=new Resident(current_user.get().getUser_id(), request.getUserName(), request.getAadhar(), request.getResidenceNumber(), request.getPhoneNumber(),true);
            residentRepository.save(resident);
            UserObject userObject=new UserObject();
            userObject.setUserId(current_user.get().getUser_id());
            userObject.userName(user.getUserName());
            userObject.setPhoneNumber(resident.getPhoneNumber());
            userObject.setAadhar(resident.getAadhar());
            userObject.setUserType(request.getUserType());
           return userObject;
        }

        else if(request.getUserType().equalsIgnoreCase("gatekeeper")){
            if(gateKeeperRepository.existsByAadhar(request.getAadhar())) throw new BlackListedUserException("This gatekeeper had been blacklisted");
            GateKeeper gateKeeper=new GateKeeper(user.getUser_id(),1, request.getAadhar(), request.getUserName(), request.getPhoneNumber(), true);
            gateKeeperRepository.save(gateKeeper);
            UserObject userObject=new UserObject();
            userObject.setUserId(user.getUser_id());
            userObject.userName(user.getUserName());
            userObject.setPhoneNumber(gateKeeper.getPhone_number());
            userObject.setAadhar(gateKeeper.getAadhar());
            userObject.setUserType(request.getUserType());
            return userObject;
        }

        else return null;


    }
}
