package ua.pinger;

public class RestApiException extends RuntimeException
{
    public RestApiException(String message)
    {
        super(message);
    }
}
