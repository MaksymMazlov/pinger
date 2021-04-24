package ua.pinger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ua.pinger.domain.Account;
import ua.pinger.exception.RestApiException;

@Service
public class AuthorizationService {

    @Autowired
    private AccountService accountService;

    public Account currentAccount() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String email = extractPrincipal(authentication);
        if (email == null) {
            throw new RestApiException(HttpStatus.UNAUTHORIZED, "Not authorized");
        }

        Account account = accountService.findByEmail(email);
        if (account == null) {
            throw new RestApiException(HttpStatus.UNAUTHORIZED, "Not authorized 2");
        }

        return account;
    }

    private String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        }

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            return springSecurityUser.getUsername();
        }

        if (authentication.getPrincipal() instanceof String) {
            return (String) authentication.getPrincipal();
        }

        return null;
    }
}
