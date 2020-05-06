package main.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Part not found!")
public class ExternalApiException extends RuntimeException {
    public ExternalApiException() {
    }
}
