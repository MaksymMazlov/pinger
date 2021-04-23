package ua.pinger.exception;

import org.springframework.http.HttpStatus;

public class RestApiException extends RuntimeException
{
    private final HttpStatus status;

    public RestApiException(HttpStatus status, String message)
    {
        super(message);
        this.status = status;
    }
    public RestApiException(String message)
    {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getStatus()
    {
        return status;
    }
}
