package ir.management.onlinetest.features.user_management.adapter.web;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.features.user_management.application.ports.in.commands.SignUpCommand;
import ir.management.onlinetest.features.user_management.application.ports.in.mapper.SignUpCommandMapper;
import ir.management.onlinetest.features.user_management.application.ports.in.outcomes.SignUpOutcome;
import ir.management.onlinetest.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class SignUpController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private SignUpCommandMapper signUpCommandMapper;

    @PostMapping(value = "/sign-up-by-user")
    public SignUpOutcome signUp(@RequestBody SignUpCommand signUpCommand,BindingResult result){

        Account account1= accountRepository.save(
                signUpCommandMapper.map(signUpCommand)
        );
        SignUpOutcome response=new SignUpOutcome();
        if(result.hasErrors()){

            //Get error message
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );

            response.setValidated(false);
            response.setErrorMessages(errors);
        }else{
            // Implement business logic to save employee into database
            //..
            response.setValidated(true);
            response.setIdUser(account1.getId());
        }
        return response;
    }

    @PostMapping(value = "/check-username")
    public void checkUserName(@RequestBody String userName){
        String message="";
        if(accountRepository.findByUserName(userName)!=null)
            message="this username existed";
    }
}
