package ua.pinger.domain;

import ua.pinger.domain.enumeration.EventType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "monitoring_events", schema = "pinger")
public class MonitoringEvents
{
    private int id;
    private EventType type;
    private Timestamp dateTime;
    private int duration;
    private String reason;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public EventType getType()
    {
        return type;
    }

    public void setType(EventType type)
    {
        this.type = type;
    }

    @Basic
    @Column(name = "date_time")
    public Timestamp getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime)
    {
        this.dateTime = dateTime;
    }

    @Basic
    @Column(name = "duration")
    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    @Basic
    @Column(name = "reason")
    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
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
        MonitoringEvents that = (MonitoringEvents) o;
        return id == that.id && duration == that.duration && Objects.equals(type, that.type) && Objects.equals(dateTime, that.dateTime) && Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, type, dateTime, duration, reason);
    }
}
