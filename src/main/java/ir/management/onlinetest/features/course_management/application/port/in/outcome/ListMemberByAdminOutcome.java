package ir.management.onlinetest.features.course_management.application.port.in.outcome;

import ir.management.onlinetest.features.admin_management.domain.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ListMemberByAdminOutcome {
    List<UserDTO> userList;
}
