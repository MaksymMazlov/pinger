package ua.pinger.dto;

import ua.pinger.exception.RestApiException;

public class ErrorDto
{
    private final int status;
    private final String message;

    public ErrorDto(RestApiException e)
    {
        this.status = e.getStatus().value();
        this.message = e.getMessage();
    }

    public int getStatus()
    {
        return status;
    }
    public String getMessage()
    {
        return message;
    }
}
