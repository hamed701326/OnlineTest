package ir.management.onlinetest.features.admin_management.application.ports;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.enums.Status;
import ir.management.onlinetest.features.admin_management.application.ports.in.ListUserByAdminUseCase;
import ir.management.onlinetest.features.admin_management.application.ports.in.commands.ListUserByAdminCommand;
import ir.management.onlinetest.features.admin_management.application.ports.in.mapper.UserMapper;
import ir.management.onlinetest.features.admin_management.application.ports.in.outcomes.ListUserByAdminOutcome;
import ir.management.onlinetest.repositories.AccountRepository;
import ir.management.onlinetest.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListUserService implements ListUserByAdminUseCase {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public ListUserByAdminOutcome list(ListUserByAdminCommand command) throws Exception {
        List<String> a=command.getSearchAttribute();
        List<Account> accounts;
        if(a.contains("role") && a.contains("userName") && a.contains("status")) {
            accounts = accountRepository.findAllByStatusAndUserNameContainingAndRoleOrderByRole(
                    Status.valueOf(command.getStatus()),
                    command.getUserName(),
                    roleRepository.findByName(command.getRole())
            );
        }else if(a.size()==1 && a.contains("status"))
            accounts=accountRepository.findAllByStatus(Status.valueOf(command.getStatus()));
        else {
            accounts=accountRepository.findAll();
        }
        return new ListUserByAdminOutcome(
                accounts
                .stream()
                .map(UserMapper::map).collect(Collectors.toList())
        );
    }
}
