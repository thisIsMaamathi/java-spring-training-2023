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
 * ScheduleRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-05T17:35:41.371367+05:30[Asia/Kolkata]")
public class ScheduleRequest   {
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

  @JsonProperty("residentId")
  private Integer residentId;

  public ScheduleRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ScheduleRequest aadhar(Long aadhar) {
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

  public ScheduleRequest date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public ScheduleRequest phone(Long phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getPhone() {
    return phone;
  }

  public void setPhone(Long phone) {
    this.phone = phone;
  }

  public ScheduleRequest additionalInformation(String additionalInformation) {
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

  public ScheduleRequest residentId(Integer residentId) {
    this.residentId = residentId;
    return this;
  }

  /**
   * Get residentId
   * @return residentId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getResidentId() {
    return residentId;
  }

  public void setResidentId(Integer residentId) {
    this.residentId = residentId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScheduleRequest scheduleRequest = (ScheduleRequest) o;
    return Objects.equals(this.name, scheduleRequest.name) &&
        Objects.equals(this.aadhar, scheduleRequest.aadhar) &&
        Objects.equals(this.date, scheduleRequest.date) &&
        Objects.equals(this.phone, scheduleRequest.phone) &&
        Objects.equals(this.additionalInformation, scheduleRequest.additionalInformation) &&
        Objects.equals(this.residentId, scheduleRequest.residentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, aadhar, date, phone, additionalInformation, residentId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScheduleRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    aadhar: ").append(toIndentedString(aadhar)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    additionalInformation: ").append(toIndentedString(additionalInformation)).append("\n");
    sb.append("    residentId: ").append(toIndentedString(residentId)).append("\n");
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

