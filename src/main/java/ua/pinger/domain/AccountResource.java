package ua.pinger.domain;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ua.pinger.domain.enumeration.ResourceStatus;

@Entity
@Table(name = "account_resource", schema = "pinger")
public class AccountResource
{
    private int id;
    private String name;
    private ResourceStatus status;
    private String type;
    private String host;
    private int monitoringInterval;
    private Timestamp created;
    private int accountId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public ResourceStatus getStatus()
    {
        return status;
    }

    public void setStatus(ResourceStatus status)
    {
        this.status = status;
    }

    @Basic
    @Column(name = "type")
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Basic
    @Column(name = "host")
    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    @Basic
    @Column(name = "monitoring_interval")
    public int getInterval()
    {
        return monitoringInterval;
    }

    public void setInterval(int interval)
    {
        this.monitoringInterval = interval;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated()
    {
        return created;
    }

    public void setCreated(Timestamp created)
    {
        this.created = created;
    }

    @Basic
    @Column(name = "account_id")
    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        AccountResource resource = (AccountResource) o;
        return id == resource.id && monitoringInterval == resource.monitoringInterval && accountId == resource.accountId && Objects.equals(name, resource.name) && Objects.equals(status, resource.status) && Objects.equals(type, resource.type) && Objects.equals(host, resource.host) && Objects.equals(created, resource.created);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, status, type, host, monitoringInterval, created, accountId);
    }
}
