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
 * RegistrationRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-05T17:35:41.371367+05:30[Asia/Kolkata]")
public class RegistrationRequest   {
  @JsonProperty("userName")
  private String userName;

  @JsonProperty("aadhar")
  private Long aadhar;

  @JsonProperty("phoneNumber")
  private Long phoneNumber;

  @JsonProperty("userType")
  private String userType;

  @JsonProperty("password")
  private String password;

  @JsonProperty("residenceId")
  private Integer residenceId;

  public RegistrationRequest userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Get userName
   * @return userName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public RegistrationRequest aadhar(Long aadhar) {
    this.aadhar = aadhar;
    return this;
  }

  /**
   * Get aadhar
   * @return aadhar
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getAadhar() {
    return aadhar;
  }

  public void setAadhar(Long aadhar) {
    this.aadhar = aadhar;
  }

  public RegistrationRequest phoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public RegistrationRequest userType(String userType) {
    this.userType = userType;
    return this;
  }

  /**
   * Get userType
   * @return userType
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public RegistrationRequest password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RegistrationRequest residenceId(Integer residenceId) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistrationRequest registrationRequest = (RegistrationRequest) o;
    return Objects.equals(this.userName, registrationRequest.userName) &&
        Objects.equals(this.aadhar, registrationRequest.aadhar) &&
        Objects.equals(this.phoneNumber, registrationRequest.phoneNumber) &&
        Objects.equals(this.userType, registrationRequest.userType) &&
        Objects.equals(this.password, registrationRequest.password) &&
        Objects.equals(this.residenceId, registrationRequest.residenceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, aadhar, phoneNumber, userType, password, residenceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegistrationRequest {\n");
    
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    aadhar: ").append(toIndentedString(aadhar)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    userType: ").append(toIndentedString(userType)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    residenceId: ").append(toIndentedString(residenceId)).append("\n");
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

