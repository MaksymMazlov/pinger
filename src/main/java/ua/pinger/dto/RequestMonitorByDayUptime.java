package ua.pinger.dto;

import java.math.BigDecimal;

public class RequestMonitorByDayUptime
{
    private BigDecimal uptime;

    public BigDecimal getUptime()
    {
        return uptime;
    }

    public void setUptime(BigDecimal uptime)
    {
        this.uptime = uptime;
    }
}
