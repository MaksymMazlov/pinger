package ua.pinger.service.notifications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service

public class SmsService
{
    private static final Logger LOG = LoggerFactory.getLogger(SmsService.class);
    @Value("${app.service.sms.enabled:false}")
    private boolean enabled;
    @Value("${app.service.sms.api_key}")
    private String apiKey;
    @Value("${app.service.sms.api_url}")
    private String apiUrl;

    public void sendSms(String phone, String textSms)
    {
        if (enabled)
        {
            LOG.warn("Sms wasn't send, as sms send service is disabled");
            return;
        }
        try
        {
            URL url = new URL(apiUrl + "/message/send.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Authorization", apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            OutputStream os = connection.getOutputStream();
            String json = "{\n" +
                    "   \"recipients\":[\n" +
                    "      \"" + phone + "\"\n" +
                    "   ],\n" +
                    "   \"sms\":{\n" +
                    "      \"sender\": \"TAXI\",\n" +
                    "      \"text\": \"" + textSms + "\"\n" +
                    "   }\n" +
                    "}";
            os.write(json.getBytes());
            os.close();
            InputStream io = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(io));
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                System.out.println(line);
            }
            io.close();
        }
        catch (IOException e)
        {
            System.out.println("Не удалось отправить СМС" + e.getLocalizedMessage());
        }
    }
}
