package ir.management.onlinetest.features.course_management.application.port;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.features.admin_management.application.ports.in.mapper.UserMapper;
import ir.management.onlinetest.features.course_management.application.port.in.ListMemberByAdminUseCase;
import ir.management.onlinetest.features.course_management.application.port.in.command.ListMemberByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.ListMemberByAdminOutcome;
import ir.management.onlinetest.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
@Service
public class ListMemberService implements ListMemberByAdminUseCase {
    @Autowired
    CourseRepository courseRepository;
    @Override
    public ListMemberByAdminOutcome list(ListMemberByAdminCommand command) {
        List<Account> accounts=courseRepository.findById(command.getCourseId()).get().getMembers();
        return new ListMemberByAdminOutcome(
                accounts.stream()
                .map(UserMapper::map).collect(Collectors.toList())
        );
    }
}
