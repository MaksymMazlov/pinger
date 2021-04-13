package ua.pinger.dto;

import ua.pinger.domain.enumeration.ResourceStatus;

public class RequestChangeStatusDto
{
    private ResourceStatus status;

    public ResourceStatus getStatus()
    {
        return status;
    }

    public void setStatus(ResourceStatus status)
    {
        this.status = status;
    }
}