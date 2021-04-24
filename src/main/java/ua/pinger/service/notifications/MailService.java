package ua.pinger.service.notifications;

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
import java.util.Properties;

@Service
public class MailService {
    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    @Value("${app.service.mail.enabled:false}")
    private boolean enabled;

    public void sendMail(String recipient, String subject, String text) {
        if (!enabled) {
            LOG.debug("Email wasn't send to {}, as mailing service is disabled", recipient);
            return;
        }
        String meAccountEmail = "mazlovmaxim@gmail.com";
        String password = "osdthpilcwotlbnx";

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
                return new PasswordAuthentication(meAccountEmail, password);
            }
        });

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(meAccountEmail));
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
}
