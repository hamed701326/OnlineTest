package ir.management.onlinetest.features.admin_management.application.ports.in.outcomes;

import ir.management.onlinetest.features.admin_management.domain.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ListUserByAdminOutcome {
  List<UserDTO> userDTOS;
}
