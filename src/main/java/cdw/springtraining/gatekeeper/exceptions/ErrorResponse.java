package cdw.springtraining.gatekeeper.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The `ErrorResponse` class represents an error response object to encapsulate error information, such as an error code and message.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}
