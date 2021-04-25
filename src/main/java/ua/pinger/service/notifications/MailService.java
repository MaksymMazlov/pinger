package ua.pinger.service.notifications;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Service
public class MailService
{
    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    @Value("${app.service.mail.enabled:false}")
    private boolean enabled;
    @Value("${app.service.mail.email}")
    private String email;
    @Value("${app.service.mail.password}")
    private String password;

    public void sendMail(String recipient, String subject, String text)
    {
        if (!enabled)
        {
            LOG.warn("Email wasn't send to {}, as mailing service is disabled", recipient);
            return;
        }
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(email, password);
            }
        });

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    public void sendHtmlMail(String recipient, String subject, String htmlFileName)
    {
        if (!enabled)
        {
            LOG.warn("Email wasn't send to {}, as mailing service is disabled", recipient);
            return;
        }

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String content = "";

             Session session = Session.getInstance(properties, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(email, password);
            }
        });

        try
        {
            content = IOUtils.resourceToString("/email/" + htmlFileName, StandardCharsets.UTF_8);

        }
        catch (IOException e)
        {
            LOG.error("Error converted content from HTML - {}", htmlFileName);
            e.printStackTrace();
        }

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=UTF-8");
            Transport.send(message);
            LOG.info(content);
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }

    }
}
