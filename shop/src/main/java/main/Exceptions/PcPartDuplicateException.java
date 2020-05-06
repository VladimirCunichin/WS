package main.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "partId already exists")
public class PcPartDuplicateException extends RuntimeException{
    public PcPartDuplicateException(){}
}
