package ir.management.onlinetest.features.course_management.application.port.in.outcome;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
public class AddMemberByAdminOutcome {
    boolean validated;
    Map<String,String> errorMessage;
}
