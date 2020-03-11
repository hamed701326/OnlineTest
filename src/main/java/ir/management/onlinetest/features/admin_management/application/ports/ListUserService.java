package ir.management.onlinetest.features.admin_management.application.ports;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.features.admin_management.application.ports.in.ListUserByAdminUseCase;
import ir.management.onlinetest.features.admin_management.application.ports.in.commands.ListUserByAdminCommand;
import ir.management.onlinetest.features.admin_management.application.ports.in.mapper.UserMapper;
import ir.management.onlinetest.features.admin_management.application.ports.in.outcomes.ListUserByAdminOutcome;
import ir.management.onlinetest.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListUserService implements ListUserByAdminUseCase {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public ListUserByAdminOutcome list(ListUserByAdminCommand command) throws Exception {
        List<Account> accounts=accountRepository.findAllByStatus(command.getStatus());

        return new ListUserByAdminOutcome(
                accounts
                .stream()
                .map(UserMapper::map).collect(Collectors.toList())
        );
    }
}
