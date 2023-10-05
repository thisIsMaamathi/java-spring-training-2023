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
 * ResidentObject
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-05T17:35:41.371367+05:30[Asia/Kolkata]")
public class ResidentObject   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("residentName")
  private String residentName;

  @JsonProperty("residenceId")
  private Integer residenceId;

  @JsonProperty("aadhar")
  private Long aadhar;

  @JsonProperty("phoneNumber")
  private Long phoneNumber;

  public ResidentObject id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ResidentObject residentName(String residentName) {
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

  public ResidentObject residenceId(Integer residenceId) {
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

  public ResidentObject aadhar(Long aadhar) {
    this.aadhar = aadhar;
    return this;
  }

  /**
   * Get aadhar
   * @return aadhar
  */
  @ApiModelProperty(value = "")


  public Long getAadhar() {
    return aadhar;
  }

  public void setAadhar(Long aadhar) {
    this.aadhar = aadhar;
  }

  public ResidentObject phoneNumber(Long phoneNumber) {
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
    ResidentObject residentObject = (ResidentObject) o;
    return Objects.equals(this.id, residentObject.id) &&
        Objects.equals(this.residentName, residentObject.residentName) &&
        Objects.equals(this.residenceId, residentObject.residenceId) &&
        Objects.equals(this.aadhar, residentObject.aadhar) &&
        Objects.equals(this.phoneNumber, residentObject.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, residentName, residenceId, aadhar, phoneNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResidentObject {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    residentName: ").append(toIndentedString(residentName)).append("\n");
    sb.append("    residenceId: ").append(toIndentedString(residenceId)).append("\n");
    sb.append("    aadhar: ").append(toIndentedString(aadhar)).append("\n");
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

