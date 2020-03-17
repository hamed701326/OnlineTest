package ir.management.onlinetest.repositories;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.enums.Status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    @Test
    void findAllByStatus() {

        List<Account> accounts=accountRepository.findAllByStatusAndUserNameContainingAndRoleOrderByRole(
                Status.APPROVED_ACCOUNT,
                "ali",
                roleRepository.findByName("Master")
                );

    }
    @Test
    void findByUserName(){
        Account account=accountRepository.findByUserName("akbar.hossaini");
//        assertNull(account);
    }

    @Test
    void findById() {
        Account account=accountRepository.findById(43L).get();
        account.getCourseList().forEach(System.out::println);
    }
}