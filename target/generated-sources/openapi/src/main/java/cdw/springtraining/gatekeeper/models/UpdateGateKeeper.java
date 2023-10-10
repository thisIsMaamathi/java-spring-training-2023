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
 * UpdateGateKeeper
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-06T18:49:48.984700+05:30[Asia/Kolkata]")
public class UpdateGateKeeper   {
  @JsonProperty("gateKeeperName")
  private String gateKeeperName;

  @JsonProperty("gateId")
  private Integer gateId;

  @JsonProperty("aadhar")
  private Long aadhar;

  @JsonProperty("phoneNumber")
  private Long phoneNumber;

  public UpdateGateKeeper gateKeeperName(String gateKeeperName) {
    this.gateKeeperName = gateKeeperName;
    return this;
  }

  /**
   * Get gateKeeperName
   * @return gateKeeperName
  */
  @ApiModelProperty(value = "")


  public String getGateKeeperName() {
    return gateKeeperName;
  }

  public void setGateKeeperName(String gateKeeperName) {
    this.gateKeeperName = gateKeeperName;
  }

  public UpdateGateKeeper gateId(Integer gateId) {
    this.gateId = gateId;
    return this;
  }

  /**
   * Get gateId
   * @return gateId
  */
  @ApiModelProperty(value = "")


  public Integer getGateId() {
    return gateId;
  }

  public void setGateId(Integer gateId) {
    this.gateId = gateId;
  }

  public UpdateGateKeeper aadhar(Long aadhar) {
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

  public UpdateGateKeeper phoneNumber(Long phoneNumber) {
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
    UpdateGateKeeper updateGateKeeper = (UpdateGateKeeper) o;
    return Objects.equals(this.gateKeeperName, updateGateKeeper.gateKeeperName) &&
        Objects.equals(this.gateId, updateGateKeeper.gateId) &&
        Objects.equals(this.aadhar, updateGateKeeper.aadhar) &&
        Objects.equals(this.phoneNumber, updateGateKeeper.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gateKeeperName, gateId, aadhar, phoneNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateGateKeeper {\n");
    
    sb.append("    gateKeeperName: ").append(toIndentedString(gateKeeperName)).append("\n");
    sb.append("    gateId: ").append(toIndentedString(gateId)).append("\n");
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

