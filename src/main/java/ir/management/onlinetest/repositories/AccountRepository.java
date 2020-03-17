package ir.management.onlinetest.repositories;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.entities.Role;
import ir.management.onlinetest.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByUserName(String userName);

    List<Account> findAllByStatus(Status status);
    List<Account> findAllByStatusAndUserNameContainingAndRoleOrderByRole(Status status, String userName, Role role);
}
