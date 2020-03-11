package ir.management.onlinetest.features.user_management;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.entities.Privilege;
import ir.management.onlinetest.entities.Role;
import ir.management.onlinetest.repositories.AccountRepository;
import ir.management.onlinetest.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestUserDetailService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MessageSource messages;
    @Autowired
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account=accountRepository.findByUserName(s);
        if(account==null)
            return new User(
                    "",
                    "",
                    true,
                    true,
                    true,
                    true,
                    getAuthorities(Arrays.asList(roleRepository.findByName("Role_User")))
            );
        return new User(
                account.getUserName(),
                account.getPassword(),
                account.isActive(),
                true,
                true,
                true,
                getAuthorities(Arrays.asList(account.getRole()))
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }
    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }
    private List<GrantedAuthority> getGrantedAuthorities
            (List<String> privileges){
        List<GrantedAuthority> authorities=new ArrayList<>();
        for (String privilege:privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
