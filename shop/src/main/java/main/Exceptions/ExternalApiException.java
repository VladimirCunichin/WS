package main.Exceptions;

public class ExternalApiException extends RuntimeException{
    private String message;

    public static ExternalApiException create() {
        return new ExternalApiException("External service could not handle your request correctly");
    }

    private ExternalApiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
