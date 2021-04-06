package ua.pinger.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.pinger.domain.Account;
import ua.pinger.dto.RequestAccountRegistrationDto;
import ua.pinger.repository.AccountRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AccountService
{
    private static final Logger LOG = LoggerFactory.getLogger(AccountRepository.class);
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder encoder;


    public Account register(RequestAccountRegistrationDto dto)
    {
        Account account = new Account();
        account.setEmail(dto.getEmail());

        account.setPassword(encoder.encode(dto.getPassword()));
        account.setCreated(Timestamp.valueOf(LocalDateTime.now()));

        LOG.info("IN register - account email: {} successfully registered", account.getEmail());
        return accountRepository.save(account);
    }

}
