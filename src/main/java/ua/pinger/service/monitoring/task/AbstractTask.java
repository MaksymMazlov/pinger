package ua.pinger.service.monitoring.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTask implements Runnable
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTask.class);

    @Override
    public void run()
    {
        try
        {
            runImpl();
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
        }
    }

    protected abstract void runImpl();
}
