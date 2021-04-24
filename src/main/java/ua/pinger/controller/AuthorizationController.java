package ua.pinger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.pinger.domain.Account;
import ua.pinger.dto.RequestAccountRegistrationDto;
import ua.pinger.exception.RestApiException;
import ua.pinger.service.AccountService;

import java.util.UUID;

@RestController
public class AuthorizationController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/authorization")
    public ResponseEntity<String> authorize(@RequestBody @Validated RequestAccountRegistrationDto req) {
        Account account = accountService.findByEmail(req.getEmail());
        if (account == null) {
            throw new RestApiException(HttpStatus.UNAUTHORIZED, "User not exists");
        }

        if (!passwordEncoder.matches(req.getPassword(), account.getPassword())) {
            throw new RestApiException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }

        UUID token = UUID.randomUUID();
        accountService.updateToken(account, token);

        return ResponseEntity.ok()
                .header("Authorization", token.toString())
                .body(token.toString());
    }
}
