package ua.pinger.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "account_resource", schema = "pinger")
public class AccountResource
{
    private int id;
    private String name;
    private String status;
    private String type;
    private String host;
    private int interval;
    private Timestamp created;

    @Id
    @Column(name = "id")
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
    @Column(name = "status")
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
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
    @Column(name = "interval")
    public int getInterval()
    {
        return interval;
    }

    public void setInterval(int interval)
    {
        this.interval = interval;
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
        AccountResource that = (AccountResource) o;
        return id == that.id && interval == that.interval && Objects.equals(name, that.name) && Objects.equals(status, that.status) && Objects.equals(type, that.type) && Objects.equals(host, that.host) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, status, type, host, interval, created);
    }
}
