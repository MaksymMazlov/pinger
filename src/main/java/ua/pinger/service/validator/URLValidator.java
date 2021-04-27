package ua.pinger.service.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class URLValidator implements IValidator<String> {

    private static final Pattern PATTERN = Pattern.compile("^https?://.*\\..*$");

    @Override
    public boolean isValid(String url) {
        if (url == null || url.isEmpty()) {
            return false;
        }

        return PATTERN.matcher(url).matches();
    }
}
