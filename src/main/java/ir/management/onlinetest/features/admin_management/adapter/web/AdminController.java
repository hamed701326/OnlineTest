package ir.management.onlinetest.features.admin_management.adapter.web;

import ir.management.onlinetest.features.admin_management.application.ports.in.ListUserByAdminUseCase;
import ir.management.onlinetest.features.admin_management.application.ports.in.VerifyUserByAdminUseCase;
import ir.management.onlinetest.features.admin_management.application.ports.in.commands.ListUserByAdminCommand;
import ir.management.onlinetest.features.admin_management.application.ports.in.commands.VerifyUserByAdminCommand;
import ir.management.onlinetest.features.admin_management.application.ports.in.outcomes.ListUserByAdminOutcome;
import ir.management.onlinetest.features.admin_management.application.ports.in.outcomes.VerifyUserByAdminOutcome;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ListUserByAdminUseCase listUserByAdminUseCase;
    private final VerifyUserByAdminUseCase verifyUserByAdminUseCase;
    public AdminController(ListUserByAdminUseCase listUserByAdminUseCase, VerifyUserByAdminUseCase verifyUserByAdminUseCase) {
        this.listUserByAdminUseCase = listUserByAdminUseCase;
        this.verifyUserByAdminUseCase = verifyUserByAdminUseCase;
    }

    @PostMapping("/list-user-by-admin")
    public ListUserByAdminOutcome listUserByAdmin(@RequestBody ListUserByAdminCommand command) throws Exception {
        return listUserByAdminUseCase.list(command);
    }
    @PostMapping("/verify-user-by-admin")
    public VerifyUserByAdminOutcome verifyUserByAdmin(@RequestBody VerifyUserByAdminCommand command) throws Exception {
        return verifyUserByAdminUseCase.verify(command);
    }

}
