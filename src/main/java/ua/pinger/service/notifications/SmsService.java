package ua.pinger.service.notifications;

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
    private static final String API_KEY = "Basic eb2577af93daf70afb58fc82f8d6a1e74d04ca02";
    private static final String API_URL = "https://api.turbosms.ua";

    public void sendSms(String phone, String textSms)
    {
        try
        {
            URL url = new URL(API_URL + "/message/send.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Authorization", API_KEY);
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
