package ua.pinger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.pinger.domain.Account;
import ua.pinger.repository.AccountRepository;


public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Account account = accountRepository.findByEmail(username);

        return account;
    }

}
