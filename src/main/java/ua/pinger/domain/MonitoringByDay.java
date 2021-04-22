package ua.pinger.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "monitoring_by_day")
public class MonitoringByDay
{
    private int id;
    private Date date;
    private Time time;
    private String status;
    private int resourceId;
    private boolean isAvailable;
    private AccountResource accountResource;

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

    @Column(name = "date")
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Column(name = "time")
    public Time getTime()
    {
        return time;
    }

    public void setTime(Time time)
    {
        this.time = time;
    }

    @Column(name = "status")
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Column(name = "account_resource_id")
    public int getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(int resourceId)
    {
        this.resourceId = resourceId;
    }

    @Column(name = "available")
    public boolean isAvailable()
    {
        return isAvailable;
    }

    public void setAvailable(boolean available)
    {
        isAvailable = available;
    }

    @ManyToOne
    @JoinColumn(name = "account_resource_id", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonIgnore
    public AccountResource getAccountResource()
    {
        return accountResource;
    }

    public void setAccountResource(AccountResource accountResource)
    {
        this.accountResource = accountResource;
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
        MonitoringByDay that = (MonitoringByDay) o;
        return id == that.id && Objects.equals(date, that.date) && Objects.equals(time, that.time) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, date, time, status);
    }
}
