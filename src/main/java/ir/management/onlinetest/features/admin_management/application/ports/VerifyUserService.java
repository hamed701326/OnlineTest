package ir.management.onlinetest.features.admin_management.application.ports;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.enums.Status;
import ir.management.onlinetest.features.admin_management.application.ports.in.VerifyUserByAdminUseCase;
import ir.management.onlinetest.features.admin_management.application.ports.in.commands.VerifyUserByAdminCommand;
import ir.management.onlinetest.features.admin_management.application.ports.in.outcomes.VerifyUserByAdminOutcome;
import ir.management.onlinetest.repositories.AccountRepository;
import ir.management.onlinetest.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerifyUserService implements VerifyUserByAdminUseCase {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public VerifyUserByAdminOutcome verify(VerifyUserByAdminCommand command) throws Exception {
        //find by id;
        VerifyUserByAdminOutcome res=new VerifyUserByAdminOutcome();
        Optional<Account> account =accountRepository.findById(command.getUserId());
        account.ifPresentOrElse(
                account1 -> {
                    account1.setStatus(Status.APPROVED_ACCOUNT);
                    account1.setRole(roleRepository.findByName(command.getRole()));
                    accountRepository.saveAndFlush(account1);
                },
                ()->res.setMessage("This account doesn't Exist now."));

        return res;
    }
}
