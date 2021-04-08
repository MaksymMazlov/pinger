package ua.pinger.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "monitoring_by_week", schema = "pinger")
public class MonitoringByWeek
{
    private int id;
    private Date date;
    private String status;
    private boolean isAvailable;
    private int accountResourceId;

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
    @Column(name = "available")
    public boolean isAvailable()
    {
        return isAvailable;
    }

    @Basic
    @Column(name = "account_resource_id")
    public int getAccountResourceId()
    {
        return accountResourceId;
    }

    public void setAccountResourceId(int accountResourceId)
    {
        this.accountResourceId = accountResourceId;
    }

    public void setAvailable(boolean available)
    {
        isAvailable = available;
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
        MonitoringByWeek that = (MonitoringByWeek) o;
        return id == that.id && Objects.equals(date, that.date) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, date, status);
    }
}
