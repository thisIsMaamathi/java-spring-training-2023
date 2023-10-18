package cdw.springtraining.gatekeeper.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ScheduleResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-17T13:15:27.851200+05:30[Asia/Kolkata]")
public class ScheduleResponse   {
  @JsonProperty("visitorId")
  private Integer visitorId;

  @JsonProperty("name")
  private String name;

  @JsonProperty("aadhar")
  private Long aadhar;

  @JsonProperty("date")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
  private LocalDate date;

  @JsonProperty("phone")
  private Long phone;

  @JsonProperty("additionalInformation")
  private String additionalInformation;

  @JsonProperty("pass")
  private String pass;

  @JsonProperty("residenceId")
  private Integer residenceId;

  public ScheduleResponse visitorId(Integer visitorId) {
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

  public ScheduleResponse name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ScheduleResponse aadhar(Long aadhar) {
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

  public ScheduleResponse date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public ScheduleResponse phone(Long phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
  */
  @ApiModelProperty(value = "")


  public Long getPhone() {
    return phone;
  }

  public void setPhone(Long phone) {
    this.phone = phone;
  }

  public ScheduleResponse additionalInformation(String additionalInformation) {
    this.additionalInformation = additionalInformation;
    return this;
  }

  /**
   * Get additionalInformation
   * @return additionalInformation
  */
  @ApiModelProperty(value = "")


  public String getAdditionalInformation() {
    return additionalInformation;
  }

  public void setAdditionalInformation(String additionalInformation) {
    this.additionalInformation = additionalInformation;
  }

  public ScheduleResponse pass(String pass) {
    this.pass = pass;
    return this;
  }

  /**
   * Get pass
   * @return pass
  */
  @ApiModelProperty(value = "")


  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public ScheduleResponse residenceId(Integer residenceId) {
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
    ScheduleResponse scheduleResponse = (ScheduleResponse) o;
    return Objects.equals(this.visitorId, scheduleResponse.visitorId) &&
        Objects.equals(this.name, scheduleResponse.name) &&
        Objects.equals(this.aadhar, scheduleResponse.aadhar) &&
        Objects.equals(this.date, scheduleResponse.date) &&
        Objects.equals(this.phone, scheduleResponse.phone) &&
        Objects.equals(this.additionalInformation, scheduleResponse.additionalInformation) &&
        Objects.equals(this.pass, scheduleResponse.pass) &&
        Objects.equals(this.residenceId, scheduleResponse.residenceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(visitorId, name, aadhar, date, phone, additionalInformation, pass, residenceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScheduleResponse {\n");
    
    sb.append("    visitorId: ").append(toIndentedString(visitorId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    aadhar: ").append(toIndentedString(aadhar)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    additionalInformation: ").append(toIndentedString(additionalInformation)).append("\n");
    sb.append("    pass: ").append(toIndentedString(pass)).append("\n");
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

