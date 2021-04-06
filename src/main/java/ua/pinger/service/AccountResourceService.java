package ua.pinger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pinger.repository.AccountResourceRepository;

@Service
public class AccountResourceService
{
    @Autowired
    private AccountResourceRepository accountResourceRepository;
}
