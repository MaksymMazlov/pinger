package ua.pinger.dto;

import ua.pinger.domain.enumeration.MonitoringType;

import javax.validation.constraints.NotNull;

public class RequestCreateOrUpdateResourceDto
{
    @NotNull
    private String name;
    @NotNull
    private MonitoringType type;
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

    public MonitoringType getType()
    {
        return type;
    }

    public void setType(MonitoringType type)
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
