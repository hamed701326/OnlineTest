package ir.management.onlinetest.core.config;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account=accountRepository.findByUserName(userName);
        User.UserBuilder builder=null;
        if (account!=null){
            builder=User.withUsername(userName);
            builder.password(passwordEncoder().encode(account.getPassword()));
            builder.roles(account.getRole().getName());
        }else {
            throw new UsernameNotFoundException("user not found");
        }
        return builder.build();
    }
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
}
