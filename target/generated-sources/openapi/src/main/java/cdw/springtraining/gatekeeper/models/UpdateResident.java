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
 * UpdateResident
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-17T13:15:27.851200+05:30[Asia/Kolkata]")
public class UpdateResident   {
  @JsonProperty("residentName")
  private String residentName;

  @JsonProperty("aadhar")
  private Long aadhar;

  @JsonProperty("phoneNumber")
  private Long phoneNumber;

  public UpdateResident residentName(String residentName) {
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

  public UpdateResident aadhar(Long aadhar) {
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

  public UpdateResident phoneNumber(Long phoneNumber) {
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
    UpdateResident updateResident = (UpdateResident) o;
    return Objects.equals(this.residentName, updateResident.residentName) &&
        Objects.equals(this.aadhar, updateResident.aadhar) &&
        Objects.equals(this.phoneNumber, updateResident.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(residentName, aadhar, phoneNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateResident {\n");
    
    sb.append("    residentName: ").append(toIndentedString(residentName)).append("\n");
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

