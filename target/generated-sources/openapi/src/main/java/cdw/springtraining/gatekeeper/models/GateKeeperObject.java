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
 * GateKeeperObject
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-05T17:35:41.371367+05:30[Asia/Kolkata]")
public class GateKeeperObject   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("gateKeeperName")
  private String gateKeeperName;

  @JsonProperty("gateId")
  private Integer gateId;

  @JsonProperty("aadhar")
  private Long aadhar;

  @JsonProperty("phoneNumber")
  private Long phoneNumber;

  public GateKeeperObject id(Integer id) {
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

  public GateKeeperObject gateKeeperName(String gateKeeperName) {
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

  public GateKeeperObject gateId(Integer gateId) {
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

  public GateKeeperObject aadhar(Long aadhar) {
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

  public GateKeeperObject phoneNumber(Long phoneNumber) {
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
    GateKeeperObject gateKeeperObject = (GateKeeperObject) o;
    return Objects.equals(this.id, gateKeeperObject.id) &&
        Objects.equals(this.gateKeeperName, gateKeeperObject.gateKeeperName) &&
        Objects.equals(this.gateId, gateKeeperObject.gateId) &&
        Objects.equals(this.aadhar, gateKeeperObject.aadhar) &&
        Objects.equals(this.phoneNumber, gateKeeperObject.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, gateKeeperName, gateId, aadhar, phoneNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GateKeeperObject {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

