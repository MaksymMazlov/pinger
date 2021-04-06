package ua.pinger.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "monitoring_by_day", schema = "pinger")
public class MonitoringByDay
{
    private int id;
    private Date date;
    private Time time;
    private String status;
    private int resourceId;

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
    @Column(name = "date")
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Basic
    @Column(name = "time")
    public Time getTime()
    {
        return time;
    }

    public void setTime(Time time)
    {
        this.time = time;
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
    @Column(name = "account_resource_id")
    public int getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(int resourceId)
    {
        this.resourceId = resourceId;
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
