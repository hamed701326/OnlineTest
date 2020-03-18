package ir.management.onlinetest;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.entities.Role;
import ir.management.onlinetest.repositories.AccountRepository;
import ir.management.onlinetest.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup=false;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event){
        if (alreadySetup)
            return;
        createRoleIfNotFound("Admin");
        createRoleIfNotFound("User");
        createRoleIfNotFound("Master");
        createRoleIfNotFound("Student");
        if(accountRepository.findByUserName("Admin")==null)
        {
            Role adminRole = roleRepository.findByName("ADMIN");
            Account user = new Account();
            user.setUserName("Admin");
            user.setPassword("Admin");
            user.setCreateDate(new Date());
            user.setRole(adminRole);
            user.setActive(true);
            accountRepository.save(user);
        }

        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}
