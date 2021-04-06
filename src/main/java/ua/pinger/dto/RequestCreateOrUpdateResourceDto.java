package ua.pinger.dto;

import javax.validation.constraints.NotNull;

public class RequestCreateOrUpdateResourceDto
{
    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private String host;
    @NotNull
    private int monitoringInterval;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public int getMonitoringInterval()
    {
        return monitoringInterval;
    }

    public void setMonitoringInterval(int monitoringInterval)
    {
        this.monitoringInterval = monitoringInterval;
    }
}
