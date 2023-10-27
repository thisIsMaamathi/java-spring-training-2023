package cdw.springtraining.gatekeeper.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ResidentGateKeeperResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-27T12:54:09.138029+05:30[Asia/Kolkata]")
public class ResidentGateKeeperResponse   {
  @JsonProperty("residentName")
  private String residentName;

  @JsonProperty("residenceId")
  private Integer residenceId;

  @JsonProperty("phoneNumber")
  private Long phoneNumber;

  public ResidentGateKeeperResponse residentName(String residentName) {
    this.residentName = residentName;
    return this;
  }

  /**
   * Get residentName
   * @return residentName
  */
  @ApiModelProperty(value = "")


  public String getResidentName() {
    return residentName;
  }

  public void setResidentName(String residentName) {
    this.residentName = residentName;
  }

  public ResidentGateKeeperResponse residenceId(Integer residenceId) {
    this.residenceId = residenceId;
    return this;
  }

  /**
   * Get residenceId
   * @return residenceId
  */
  @ApiModelProperty(value = "")


  public Integer getResidenceId() {
    return residenceId;
  }

  public void setResidenceId(Integer residenceId) {
    this.residenceId = residenceId;
  }

  public ResidentGateKeeperResponse phoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
  */
  @ApiModelProperty(value = "")


  public Long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResidentGateKeeperResponse residentGateKeeperResponse = (ResidentGateKeeperResponse) o;
    return Objects.equals(this.residentName, residentGateKeeperResponse.residentName) &&
        Objects.equals(this.residenceId, residentGateKeeperResponse.residenceId) &&
        Objects.equals(this.phoneNumber, residentGateKeeperResponse.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(residentName, residenceId, phoneNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResidentGateKeeperResponse {\n");
    
    sb.append("    residentName: ").append(toIndentedString(residentName)).append("\n");
    sb.append("    residenceId: ").append(toIndentedString(residenceId)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

