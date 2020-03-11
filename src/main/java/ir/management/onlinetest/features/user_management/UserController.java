package ir.management.onlinetest.features.user_management;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.enums.Status;
import ir.management.onlinetest.features.user_management.application.ports.in.commands.SignUpCommand;
import ir.management.onlinetest.features.user_management.application.ports.in.mapper.SignUpCommandMapper;
import ir.management.onlinetest.features.user_management.application.ports.in.outcomes.SignUpOutcome;
import ir.management.onlinetest.repositories.AccountRepository;
import ir.management.onlinetest.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String getSignInPage(){
        return "../static/features/user-management/sign-in-by-user";
    }
    @GetMapping(value = "/admin/{userName}")
    @Secured("ROLE_Admin")
    public String getAdminPage(@PathVariable("userName") String userName, Model model){
        model.addAttribute("userName",userName);
        return "../static/features/admin-management/index";
    }
    @GetMapping(value = "/user/{userName}")
    public String getUserPage(@PathVariable("userName") String userName, Model model){
        model.addAttribute("userName",userName);
        return "features/user-management/index";
    }
    @GetMapping(value = "/")
    public String getHomePage(){
//        ModelAndView modelAndView=new ModelAndView("home");
        return "../static/home";
    }



}
