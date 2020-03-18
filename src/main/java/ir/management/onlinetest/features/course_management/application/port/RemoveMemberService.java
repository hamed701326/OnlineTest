package ir.management.onlinetest.features.course_management.application.port;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.RemoveMemberByAdminOutcome;
import ir.management.onlinetest.features.course_management.application.port.in.RemoveMemberByAdminUseCase;
import ir.management.onlinetest.features.course_management.application.port.in.command.RemoveMemberByAdminCommand;
import ir.management.onlinetest.repositories.AccountRepository;
import ir.management.onlinetest.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RemoveMemberService implements RemoveMemberByAdminUseCase {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    AccountRepository accountRepository;
    @Override
    public RemoveMemberByAdminOutcome remove(RemoveMemberByAdminCommand command) {
        RemoveMemberByAdminOutcome result=new RemoveMemberByAdminOutcome();
        courseRepository.findById(command.getCourseId()).ifPresentOrElse(
                course ->
                {
                    Optional<Account> account=accountRepository.findById(command.getUserId());
                    account.ifPresentOrElse(
                           account1 -> {
                               if (course.getMembers().contains(account1)) {
                                   course.getMembers().remove(account1);
                                   courseRepository.saveAndFlush(course);
                                   result.setValidated(true);
                               }
                               else
                                   result.setValidated(false);
                           }
                                    ,

                            ()->  result.setValidated(false)
                    );

                }
                ,
                ()->result.setValidated(false)
        );
        return result;
    }
}
