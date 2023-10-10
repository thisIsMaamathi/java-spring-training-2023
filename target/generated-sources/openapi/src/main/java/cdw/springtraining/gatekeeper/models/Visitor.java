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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-06T18:49:48.984700+05:30[Asia/Kolkata]")
public class Visitor   {
  @JsonProperty("visitorId")
  private Integer visitorId;

  @JsonProperty("visitorName")
  private String visitorName;

  @JsonProperty("residenceId")
  private Integer residenceId;

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
        Objects.equals(this.residenceId, visitor.residenceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(visitorId, visitorName, residenceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Visitor {\n");
    
    sb.append("    visitorId: ").append(toIndentedString(visitorId)).append("\n");
    sb.append("    visitorName: ").append(toIndentedString(visitorName)).append("\n");
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

