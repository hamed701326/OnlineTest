package ir.management.onlinetest.features.admin_management.application.ports.in.mapper;

import ir.management.onlinetest.core.annotation.Mapper;
import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.features.admin_management.domain.UserDTO;

@Mapper
public class UserMapper {
    public static UserDTO map(Account account)
    {
        return new UserDTO(
            account.getId(),
            account.getUserName(),
            account.getRole().getName(),
            account.getCreateDate(),
            account.getStatus().toString(),
            account.isActive(),
            account.getCourseList()
        );
    }
}
