package ir.management.onlinetest.features.user_management.application.ports.in.mapper;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.enums.Status;
import ir.management.onlinetest.features.user_management.application.ports.in.commands.SignUpCommand;
import ir.management.onlinetest.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
@Service
public class SignUpCommandMapper {
    @Autowired
    private RoleRepository roleRepository;
    public  Account map(SignUpCommand command){
        return new Account(
          null,
          command.getUserName(),
          command.getPassword(),
          true,
          null,
          new Date()
        , Status.WAITING_FOR_CONFIRM
        ,roleRepository
                .findByName(
                command.getRole())
        ,
        new ArrayList<>()
        );
    }
}
