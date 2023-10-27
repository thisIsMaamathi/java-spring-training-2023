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
 * Visitor
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-27T12:54:09.138029+05:30[Asia/Kolkata]")
public class Visitor   {
  @JsonProperty("visitorId")
  private Integer visitorId;

  @JsonProperty("visitorName")
  private String visitorName;

  @JsonProperty("residenceId")
  private Integer residenceId;

  @JsonProperty("phoneNumber")
  private Long phoneNumber;

  @JsonProperty("isApproved")
  private String isApproved;

  @JsonProperty("hasCheckedIn")
  private Boolean hasCheckedIn;

  public Visitor visitorId(Integer visitorId) {
    this.visitorId = visitorId;
    return this;
  }

  /**
   * Get visitorId
   * @return visitorId
  */
  @ApiModelProperty(value = "")


  public Integer getVisitorId() {
    return visitorId;
  }

  public void setVisitorId(Integer visitorId) {
    this.visitorId = visitorId;
  }

  public Visitor visitorName(String visitorName) {
    this.visitorName = visitorName;
    return this;
  }

  /**
   * Get visitorName
   * @return visitorName
  */
  @ApiModelProperty(value = "")


  public String getVisitorName() {
    return visitorName;
  }

  public void setVisitorName(String visitorName) {
    this.visitorName = visitorName;
  }

  public Visitor residenceId(Integer residenceId) {
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

  public Visitor phoneNumber(Long phoneNumber) {
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

  public Visitor isApproved(String isApproved) {
    this.isApproved = isApproved;
    return this;
  }

  /**
   * Get isApproved
   * @return isApproved
  */
  @ApiModelProperty(value = "")


  public String getIsApproved() {
    return isApproved;
  }

  public void setIsApproved(String isApproved) {
    this.isApproved = isApproved;
  }

  public Visitor hasCheckedIn(Boolean hasCheckedIn) {
    this.hasCheckedIn = hasCheckedIn;
    return this;
  }

  /**
   * Get hasCheckedIn
   * @return hasCheckedIn
  */
  @ApiModelProperty(value = "")


  public Boolean getHasCheckedIn() {
    return hasCheckedIn;
  }

  public void setHasCheckedIn(Boolean hasCheckedIn) {
    this.hasCheckedIn = hasCheckedIn;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Visitor visitor = (Visitor) o;
    return Objects.equals(this.visitorId, visitor.visitorId) &&
        Objects.equals(this.visitorName, visitor.visitorName) &&
        Objects.equals(this.residenceId, visitor.residenceId) &&
        Objects.equals(this.phoneNumber, visitor.phoneNumber) &&
        Objects.equals(this.isApproved, visitor.isApproved) &&
        Objects.equals(this.hasCheckedIn, visitor.hasCheckedIn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(visitorId, visitorName, residenceId, phoneNumber, isApproved, hasCheckedIn);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Visitor {\n");
    
    sb.append("    visitorId: ").append(toIndentedString(visitorId)).append("\n");
    sb.append("    visitorName: ").append(toIndentedString(visitorName)).append("\n");
    sb.append("    residenceId: ").append(toIndentedString(residenceId)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    isApproved: ").append(toIndentedString(isApproved)).append("\n");
    sb.append("    hasCheckedIn: ").append(toIndentedString(hasCheckedIn)).append("\n");
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

