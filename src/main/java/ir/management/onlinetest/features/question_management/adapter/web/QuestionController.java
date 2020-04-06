package ir.management.onlinetest.features.question_management.adapter.web;

import ir.management.onlinetest.features.question_management.application.ports.AddQuestionService;
import ir.management.onlinetest.features.question_management.application.ports.FindQuestionService;
import ir.management.onlinetest.features.question_management.application.ports.in.commands.AddQuestionByMasterCommand;
import ir.management.onlinetest.features.question_management.application.ports.in.commands.FindQuestionByMasterCommand;
import ir.management.onlinetest.features.question_management.application.ports.in.commands.UseQuestionByMasterCommand;
import ir.management.onlinetest.features.question_management.application.ports.in.outcomes.AddQuestionByMasterOutcome;
import ir.management.onlinetest.features.question_management.application.ports.in.outcomes.FindQuestionByMasterOutcome;
import ir.management.onlinetest.features.question_management.application.ports.in.outcomes.UseQuestionByMasterOutcome;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final AddQuestionService addQuestionService;
    private final FindQuestionService findQuestionService;
    public QuestionController(AddQuestionService addQuestionService, FindQuestionService findQuestionService) {
        this.addQuestionService = addQuestionService;
        this.findQuestionService = findQuestionService;
    }

    @PostMapping("/add-question-by-master")
    public AddQuestionByMasterOutcome addQuestionByMaster(@RequestBody AddQuestionByMasterCommand command,
                                                          BindingResult result,
                                                          HttpServletRequest request){
        return addQuestionService.addQuestionByMaster(command,result,request);

    }
    @PostMapping("/find-question-by-master")
    public FindQuestionByMasterOutcome findQuestionByMaster(@RequestBody FindQuestionByMasterCommand command,
                                                            BindingResult result,
                                                            HttpServletRequest request){
        return findQuestionService.FindByMaster(command,result,request);
    }
    @PostMapping("/use-question-by-master")
    public UseQuestionByMasterOutcome useQuestionByMaster(@RequestBody UseQuestionByMasterCommand command,
                                                          BindingResult result,
                                                          HttpServletRequest request){
        return addQuestionService.useFromQuestionBank(command,result,request);
    };

}
