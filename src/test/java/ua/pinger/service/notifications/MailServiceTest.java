package ua.pinger.service.notifications;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MailServiceTest
{
    @InjectMocks
    private MailService mailService;

    @Test
    public void sendHtmlMail()
    {
        mailService.sendHtmlMail("mazlovmaxim@gmail.com","Test","userRegistration.html");
    }
}