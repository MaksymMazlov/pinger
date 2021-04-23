package ua.pinger.dto;

import ua.pinger.domain.enumeration.MonitoringType;
import ua.pinger.domain.enumeration.ResourceStatus;

public class ResponseAccountResourceDto
{
    private int id;
    private String name;
    private ResourceStatus status;
    private MonitoringType type;
    private String host;
    private int monitoringInterval;
    private String created;
    private int accountId;
    private boolean smsNotification;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ResourceStatus getStatus()
    {
        return status;
    }

    public void setStatus(ResourceStatus status)
    {
        this.status = status;
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

    public String getCreated()
    {
        return created;
    }

    public void setCreated(String created)
    {
        this.created = created;
    }

    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    public boolean isSmsNotification()
    {
        return smsNotification;
    }

    public void setSmsNotification(boolean smsNotification)
    {
        this.smsNotification = smsNotification;
    }
}
