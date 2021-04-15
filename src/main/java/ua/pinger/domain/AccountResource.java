package ua.pinger.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.pinger.domain.enumeration.MonitoringType;
import ua.pinger.domain.enumeration.ResourceStatus;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "account_resource", schema = "pinger")
public class AccountResource
{
    private int id;
    private String name;
    private ResourceStatus status;
    private MonitoringType type;
    private String host;
    private int monitoringInterval;
    private Timestamp created;
    private int accountId;
    private Account account;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public MonitoringType getType()
    {
        return type;
    }

    public void setType(MonitoringType type)
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

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
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
