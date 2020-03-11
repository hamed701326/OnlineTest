package ir.management.onlinetest.features.user_management.application.ports.in.outcomes;

import ir.management.onlinetest.features.user_management.application.ports.in.commands.SignUpCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class SignUpOutcome {
    Long idUser;
    boolean validated;
    Map<String, String> errorMessages;
}
