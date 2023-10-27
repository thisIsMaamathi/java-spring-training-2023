/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package cdw.springtraining.gatekeeper.api;

import cdw.springtraining.gatekeeper.models.BadRequestError;
import cdw.springtraining.gatekeeper.models.ForbiddenError;
import cdw.springtraining.gatekeeper.models.GateKeeperAdminResponse;
import cdw.springtraining.gatekeeper.models.InternalServerError;
import cdw.springtraining.gatekeeper.models.NotFoundError;
import cdw.springtraining.gatekeeper.models.ResidentAdminResponse;
import cdw.springtraining.gatekeeper.models.UnauthorizedError;
import cdw.springtraining.gatekeeper.models.UpdateUserRequest;
import cdw.springtraining.gatekeeper.models.UserAdminResponse;
import cdw.springtraining.gatekeeper.models.UserResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-27T12:54:09.138029+05:30[Asia/Kolkata]")
@Validated
@Api(value = "Admin", description = "the Admin API")
public interface AdminApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * PATCH /requests/approve/{requestId} : Approve and save a user to database
     *
     * @param requestId  (required)
     * @return 201 CREATED (status code 201)
     *         or 400 BAD REQUEST (status code 400)
     *         or 401 UNAUTHORIZED (status code 401)
     *         or 500 INTERNAL SERVER ERROR (status code 500)
     *         or 403 FORBIDDEN (status code 403)
     */
    @ApiOperation(value = "Approve and save a user to database", nickname = "approveUser", notes = "", response = UserResponse.class, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "201 CREATED", response = UserResponse.class),
        @ApiResponse(code = 400, message = "400 BAD REQUEST", response = BadRequestError.class),
        @ApiResponse(code = 401, message = "401 UNAUTHORIZED", response = UnauthorizedError.class),
        @ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR", response = InternalServerError.class),
        @ApiResponse(code = 403, message = "403 FORBIDDEN", response = ForbiddenError.class) })
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/requests/approve/{requestId}",
        produces = { "application/json" }
    )
    default ResponseEntity<UserResponse> approveUser(@ApiParam(value = "", required = true) @PathVariable("requestId") Integer requestId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"lastName\" : \"lastName\", \"gender\" : \"gender\", \"userName\" : \"userName\", \"isActive\" : true, \"userId\" : 0, \"residenceId\" : 5, \"firstName\" : \"firstName\", \"phoneNumber\" : 1, \"dob\" : \"2000-01-23\", \"aadhar\" : 6, \"userType\" : \"userType\", \"isApproved\" : \"isApproved\", \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /user/{userId} : Delete a user by ID
     *
     * @param userId  (required)
     * @return Successful Deletion (status code 204)
     *         or 400 BAD REQUEST (status code 400)
     *         or 401 UNAUTHORIZED (status code 401)
     *         or 500 INTERNAL SERVER ERROR (status code 500)
     *         or 403 FORBIDDEN (status code 403)
     *         or 404 NOT FOUND (status code 404)
     */
    @ApiOperation(value = "Delete a user by ID", nickname = "deleteUsers", notes = "", tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful Deletion"),
        @ApiResponse(code = 400, message = "400 BAD REQUEST", response = BadRequestError.class),
        @ApiResponse(code = 401, message = "401 UNAUTHORIZED", response = UnauthorizedError.class),
        @ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR", response = InternalServerError.class),
        @ApiResponse(code = 403, message = "403 FORBIDDEN", response = ForbiddenError.class),
        @ApiResponse(code = 404, message = "404 NOT FOUND", response = NotFoundError.class) })
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/user/{userId}",
        produces = { "application/json" }
    )
    default ResponseEntity<Void> deleteUsers(@ApiParam(value = "", required = true) @PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /gatekeepers : To display a List of all gatekeepers
     *
     * @return 200 OK (status code 200)
     *         or 401 UNAUTHORIZED (status code 401)
     *         or 500 INTERNAL SERVER ERROR (status code 500)
     *         or 403 FORBIDDEN (status code 403)
     */
    @ApiOperation(value = "To display a List of all gatekeepers", nickname = "getGateKeeper", notes = "", response = GateKeeperAdminResponse.class, responseContainer = "List", tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "200 OK", response = GateKeeperAdminResponse.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "401 UNAUTHORIZED", response = UnauthorizedError.class),
        @ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR", response = InternalServerError.class),
        @ApiResponse(code = 403, message = "403 FORBIDDEN", response = ForbiddenError.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/gatekeepers",
        produces = { "application/json" }
    )
    default ResponseEntity<List<GateKeeperAdminResponse>> getGateKeeper() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"phoneNumber\" : 1, \"gender\" : \"gender\", \"dob\" : \"2000-01-23\", \"name\" : \"name\", \"aadhar\" : 6, \"userName\" : \"userName\", \"isActive\" : true, \"isApproved\" : \"isApproved\", \"userId\" : 0, \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /residents : To display a List of all residents
     *
     * @return 200 OK (status code 200)
     *         or 401 UNAUTHORIZED (status code 401)
     *         or 500 INTERNAL SERVER ERROR (status code 500)
     *         or 403 FORBIDDEN (status code 403)
     */
    @ApiOperation(value = "To display a List of all residents", nickname = "getResidents", notes = "", response = ResidentAdminResponse.class, responseContainer = "List", tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "200 OK", response = ResidentAdminResponse.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "401 UNAUTHORIZED", response = UnauthorizedError.class),
        @ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR", response = InternalServerError.class),
        @ApiResponse(code = 403, message = "403 FORBIDDEN", response = ForbiddenError.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/residents",
        produces = { "application/json" }
    )
    default ResponseEntity<List<ResidentAdminResponse>> getResidents() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"phoneNumber\" : 5, \"gender\" : \"gender\", \"residentName\" : \"residentName\", \"dob\" : \"2000-01-23\", \"approvedBy\" : \"approvedBy\", \"aadhar\" : 1, \"userName\" : \"userName\", \"isActive\" : true, \"isApproved\" : \"isApproved\", \"userId\" : 0, \"residenceId\" : 6, \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /user/{userId} : Get a user by ID
     *
     * @param userId  (required)
     * @return 200 OK (status code 200)
     *         or 400 BAD REQUEST (status code 400)
     *         or 401 UNAUTHORIZED (status code 401)
     *         or 500 INTERNAL SERVER ERROR (status code 500)
     *         or 403 FORBIDDEN (status code 403)
     *         or 404 NOT FOUND (status code 404)
     */
    @ApiOperation(value = "Get a user by ID", nickname = "getUserById", notes = "", response = UserAdminResponse.class, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "200 OK", response = UserAdminResponse.class),
        @ApiResponse(code = 400, message = "400 BAD REQUEST", response = BadRequestError.class),
        @ApiResponse(code = 401, message = "401 UNAUTHORIZED", response = UnauthorizedError.class),
        @ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR", response = InternalServerError.class),
        @ApiResponse(code = 403, message = "403 FORBIDDEN", response = ForbiddenError.class),
        @ApiResponse(code = 404, message = "404 NOT FOUND", response = NotFoundError.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/user/{userId}",
        produces = { "application/json" }
    )
    default ResponseEntity<UserAdminResponse> getUserById(@ApiParam(value = "", required = true) @PathVariable("userId") Integer userId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"gender\" : \"gender\", \"approvedBy\" : \"approvedBy\", \"userName\" : \"userName\", \"isActive\" : true, \"userId\" : 0, \"residenceId\" : 6, \"phoneNumber\" : 5, \"dob\" : \"2000-01-23\", \"name\" : \"name\", \"aadhar\" : 1, \"userType\" : \"userType\", \"isApproved\" : \"isApproved\", \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PATCH /requests/reject/{requestId} : Approve and save a user to database
     *
     * @param requestId  (required)
     * @return 201 CREATED (status code 201)
     *         or 400 BAD REQUEST (status code 400)
     *         or 401 UNAUTHORIZED (status code 401)
     *         or 500 INTERNAL SERVER ERROR (status code 500)
     *         or 403 FORBIDDEN (status code 403)
     */
    @ApiOperation(value = "Approve and save a user to database", nickname = "rejectUser", notes = "", response = UserResponse.class, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "201 CREATED", response = UserResponse.class),
        @ApiResponse(code = 400, message = "400 BAD REQUEST", response = BadRequestError.class),
        @ApiResponse(code = 401, message = "401 UNAUTHORIZED", response = UnauthorizedError.class),
        @ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR", response = InternalServerError.class),
        @ApiResponse(code = 403, message = "403 FORBIDDEN", response = ForbiddenError.class) })
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/requests/reject/{requestId}",
        produces = { "application/json" }
    )
    default ResponseEntity<UserResponse> rejectUser(@ApiParam(value = "", required = true) @PathVariable("requestId") Integer requestId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"lastName\" : \"lastName\", \"gender\" : \"gender\", \"userName\" : \"userName\", \"isActive\" : true, \"userId\" : 0, \"residenceId\" : 5, \"firstName\" : \"firstName\", \"phoneNumber\" : 1, \"dob\" : \"2000-01-23\", \"aadhar\" : 6, \"userType\" : \"userType\", \"isApproved\" : \"isApproved\", \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /user/{userId} : Update a user by ID
     *
     * @param userId  (required)
     * @param updateUserRequest  (required)
     * @return The updated resident (status code 200)
     *         or 400 BAD REQUEST (status code 400)
     *         or 401 UNAUTHORIZED (status code 401)
     *         or 500 INTERNAL SERVER ERROR (status code 500)
     *         or 403 FORBIDDEN (status code 403)
     *         or 404 NOT FOUND (status code 404)
     */
    @ApiOperation(value = "Update a user by ID", nickname = "updateUsers", notes = "", response = UserAdminResponse.class, tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The updated resident", response = UserAdminResponse.class),
        @ApiResponse(code = 400, message = "400 BAD REQUEST", response = BadRequestError.class),
        @ApiResponse(code = 401, message = "401 UNAUTHORIZED", response = UnauthorizedError.class),
        @ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR", response = InternalServerError.class),
        @ApiResponse(code = 403, message = "403 FORBIDDEN", response = ForbiddenError.class),
        @ApiResponse(code = 404, message = "404 NOT FOUND", response = NotFoundError.class) })
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/user/{userId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<UserAdminResponse> updateUsers(@ApiParam(value = "", required = true) @PathVariable("userId") Integer userId,@ApiParam(value = "", required = true) @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"gender\" : \"gender\", \"approvedBy\" : \"approvedBy\", \"userName\" : \"userName\", \"isActive\" : true, \"userId\" : 0, \"residenceId\" : 6, \"phoneNumber\" : 5, \"dob\" : \"2000-01-23\", \"name\" : \"name\", \"aadhar\" : 1, \"userType\" : \"userType\", \"isApproved\" : \"isApproved\", \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /requests/view/approved : View all the register requests made by users
     *
     * @return 200 OK (status code 200)
     *         or 401 UNAUTHORIZED (status code 401)
     *         or 500 INTERNAL SERVER ERROR (status code 500)
     *         or 403 FORBIDDEN (status code 403)
     *         or 404 NOT FOUND (status code 404)
     */
    @ApiOperation(value = "View all the register requests made by users", nickname = "viewApprovedRequest", notes = "", response = UserResponse.class, responseContainer = "List", tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "200 OK", response = UserResponse.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "401 UNAUTHORIZED", response = UnauthorizedError.class),
        @ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR", response = InternalServerError.class),
        @ApiResponse(code = 403, message = "403 FORBIDDEN", response = ForbiddenError.class),
        @ApiResponse(code = 404, message = "404 NOT FOUND", response = NotFoundError.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/requests/view/approved",
        produces = { "application/json" }
    )
    default ResponseEntity<List<UserResponse>> viewApprovedRequest() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"lastName\" : \"lastName\", \"gender\" : \"gender\", \"userName\" : \"userName\", \"isActive\" : true, \"userId\" : 0, \"residenceId\" : 5, \"firstName\" : \"firstName\", \"phoneNumber\" : 1, \"dob\" : \"2000-01-23\", \"aadhar\" : 6, \"userType\" : \"userType\", \"isApproved\" : \"isApproved\", \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /requests/view : View all the register requests made by users
     *
     * @return 200 OK (status code 200)
     *         or 401 UNAUTHORIZED (status code 401)
     *         or 500 INTERNAL SERVER ERROR (status code 500)
     *         or 403 FORBIDDEN (status code 403)
     *         or 404 NOT FOUND (status code 404)
     */
    @ApiOperation(value = "View all the register requests made by users", nickname = "viewRegnRequest", notes = "", response = UserResponse.class, responseContainer = "List", tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "200 OK", response = UserResponse.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "401 UNAUTHORIZED", response = UnauthorizedError.class),
        @ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR", response = InternalServerError.class),
        @ApiResponse(code = 403, message = "403 FORBIDDEN", response = ForbiddenError.class),
        @ApiResponse(code = 404, message = "404 NOT FOUND", response = NotFoundError.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/requests/view",
        produces = { "application/json" }
    )
    default ResponseEntity<List<UserResponse>> viewRegnRequest() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"lastName\" : \"lastName\", \"gender\" : \"gender\", \"userName\" : \"userName\", \"isActive\" : true, \"userId\" : 0, \"residenceId\" : 5, \"firstName\" : \"firstName\", \"phoneNumber\" : 1, \"dob\" : \"2000-01-23\", \"aadhar\" : 6, \"userType\" : \"userType\", \"isApproved\" : \"isApproved\", \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /requests/view/rejected : View all the register requests made by users
     *
     * @return 200 OK (status code 200)
     *         or 401 UNAUTHORIZED (status code 401)
     *         or 500 INTERNAL SERVER ERROR (status code 500)
     *         or 403 FORBIDDEN (status code 403)
     *         or 404 NOT FOUND (status code 404)
     */
    @ApiOperation(value = "View all the register requests made by users", nickname = "viewRejectedRequest", notes = "", response = UserResponse.class, responseContainer = "List", tags={ "admin", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "200 OK", response = UserResponse.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "401 UNAUTHORIZED", response = UnauthorizedError.class),
        @ApiResponse(code = 500, message = "500 INTERNAL SERVER ERROR", response = InternalServerError.class),
        @ApiResponse(code = 403, message = "403 FORBIDDEN", response = ForbiddenError.class),
        @ApiResponse(code = 404, message = "404 NOT FOUND", response = NotFoundError.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/requests/view/rejected",
        produces = { "application/json" }
    )
    default ResponseEntity<List<UserResponse>> viewRejectedRequest() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"lastName\" : \"lastName\", \"gender\" : \"gender\", \"userName\" : \"userName\", \"isActive\" : true, \"userId\" : 0, \"residenceId\" : 5, \"firstName\" : \"firstName\", \"phoneNumber\" : 1, \"dob\" : \"2000-01-23\", \"aadhar\" : 6, \"userType\" : \"userType\", \"isApproved\" : \"isApproved\", \"email\" : \"email\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
