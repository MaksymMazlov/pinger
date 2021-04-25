package ua.pinger.service.notifications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pinger.domain.AccountResource;
import ua.pinger.domain.MonitoringEvents;
import ua.pinger.domain.enumeration.EventType;
import ua.pinger.repository.AccountSettingsRepository;

@Service
public class NotificationService
{
    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);
    @Autowired
    private AccountSettingsRepository settingsRepository;
    @Autowired
    private SmsService smsService;
    @Autowired
    private MailService mailService;

    public void sendSms(MonitoringEvents event)
    {
        AccountResource resource = event.getAccountResource();
        String text = "Pinger - " + resource.getName() + " : " + event.getReason();
        String phone = settingsRepository.findPhoneByAccountId(resource.getAccountId());
        if (phone != null)
        {
            smsService.sendSms(phone, text);
            LOG.info("NotificationService->sendSms: send SMS to {}", phone);
        }
    }

    public void sendMail(MonitoringEvents event)
    {
        AccountResource resource = event.getAccountResource();
        String subjectUp = "\uD83D\uDE01 Pinger - " + resource.getName() + " : " + event.getReason();
        String subjectDown = "\uD83D\uDE31 Pinger - " + resource.getName() + " : " + event.getReason();

        String email = resource.getAccount().getEmail();
        EventType type = event.getType();
        mailService.sendHtmlMail(email, (type == EventType.UP ? subjectUp : subjectDown), "eventStatus.html");
        LOG.info("HttpResourceTask->createEvent: sendMail to {}", email);
    }
}
