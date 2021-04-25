package ua.pinger.service.notifications;

import java.util.Collections;
import java.util.List;

public class SmsRecipient
{
    private List<String> recipients;
    private Sms sms;

    public SmsRecipient(String sender, String textSms, String recipient)
    {
        this.recipients = Collections.singletonList(recipient);
        this.sms = new Sms();
        this.sms.sender = sender;
        this.sms.text = textSms;
    }

    public List<String> getRecipients()
    {
        return recipients;
    }

    public void setRecipients(List<String> recipients)
    {
        this.recipients = recipients;
    }

    public Sms getSms()
    {
        return sms;
    }

    public void setSms(Sms sms)
    {
        this.sms = sms;
    }

    public static class Sms
    {
        private String sender;
        private String text;

        public String getSender()
        {
            return sender;
        }

        public void setSender(String sender)
        {
            this.sender = sender;
        }

        public String getText()
        {
            return text;
        }

        public void setText(String text)
        {
            this.text = text;
        }
    }
}
