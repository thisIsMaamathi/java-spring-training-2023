/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package cdw.springtraining.gatekeeper.api;

import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.ScheduleRequest;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-06T18:49:48.984700+05:30[Asia/Kolkata]")
@Validated
@Api(value = "Resident", description = "the Resident API")
public interface ResidentApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /resident/cancel/{visitorId} : To remove a visitor
     *
     * @param visitorId  (required)
     * @return No Content (successful deletion) (status code 204)
     *         or Resident not found (status code 404)
     *         or Internal Server Error (status code 500)
     */
    @ApiOperation(value = "To remove a visitor", nickname = "cancelVisitor", notes = "", tags={ "Resident", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "No Content (successful deletion)"),
        @ApiResponse(code = 404, message = "Resident not found"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/resident/cancel/{visitorId}"
    )
    default ResponseEntity<Void> cancelVisitor(@ApiParam(value = "", required = true) @PathVariable("visitorId") Integer visitorId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /resident/blacklist : Blacklist a visitor or a gatekeeper
     *
     * @param blackListRequest  (required)
     * @return Added to blacklist (status code 200)
     *         or Visitor not found (status code 404)
     *         or Internal Server Error (status code 500)
     */
    @ApiOperation(value = "Blacklist a visitor or a gatekeeper", nickname = "residentBlacklist", notes = "", response = String.class, tags={ "Resident", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Added to blacklist", response = String.class),
        @ApiResponse(code = 404, message = "Visitor not found"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/resident/blacklist",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<String> residentBlacklist(@ApiParam(value = "", required = true) @Valid @RequestBody BlackListRequest blackListRequest) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /resident/schedule : Allow the resident to schedule a visitors entry and generate a pass
     *
     * @param scheduleRequest  (required)
     * @return Approved the user (status code 200)
     *         or Internal Server Error (status code 500)
     */
    @ApiOperation(value = "Allow the resident to schedule a visitors entry and generate a pass", nickname = "schedule", notes = "", response = ScheduleResponse.class, tags={ "Resident", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Approved the user", response = ScheduleResponse.class),
        @ApiResponse(code = 500, message = "Internal Server Error") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/resident/schedule",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<ScheduleResponse> schedule(@ApiParam(value = "", required = true) @Valid @RequestBody ScheduleRequest scheduleRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"date\" : \"2000-01-23\", \"additionalInformation\" : \"additionalInformation\", \"phone\" : 1, \"pass\" : \"pass\", \"name\" : \"name\", \"aadhar\" : 6, \"residenceId\" : 5, \"visitorId\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
