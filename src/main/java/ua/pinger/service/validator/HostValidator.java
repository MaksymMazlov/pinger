package ua.pinger.service.validator;

import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class HostValidator implements IValidator<String> {

    public boolean isValid(String host) {
        if (host == null || host.isEmpty()) {
            return false;
        }

        if (host.startsWith("http")) {
            return false;
        }

        try {
            InetAddress.getByName(host);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }
}
