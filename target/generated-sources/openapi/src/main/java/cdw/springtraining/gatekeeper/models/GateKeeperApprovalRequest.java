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
 * GateKeeperApprovalRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-26T16:12:55.335364+05:30[Asia/Kolkata]")
public class GateKeeperApprovalRequest   {
  @JsonProperty("pass")
  private String pass;

  public GateKeeperApprovalRequest pass(String pass) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GateKeeperApprovalRequest gateKeeperApprovalRequest = (GateKeeperApprovalRequest) o;
    return Objects.equals(this.pass, gateKeeperApprovalRequest.pass);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pass);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GateKeeperApprovalRequest {\n");
    
    sb.append("    pass: ").append(toIndentedString(pass)).append("\n");
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

