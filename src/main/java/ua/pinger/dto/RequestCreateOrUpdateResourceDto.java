package ua.pinger.dto;

import ua.pinger.domain.enumeration.MonitoringType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Min(value = 1, message = "Interval must be from 1 to 1440")
    @Max(value = 1440, message = "Interval must be from 1 to 1440")
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
