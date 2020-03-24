package ir.management.onlinetest.features.Exam_management.adapter.web;

import ir.management.onlinetest.features.Exam_management.application.ports.CreateExamService;
import ir.management.onlinetest.features.Exam_management.application.ports.DeleteExamService;
import ir.management.onlinetest.features.Exam_management.application.ports.EditExamService;
import ir.management.onlinetest.features.Exam_management.application.ports.ListExamService;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.CreateExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.DeleteExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.EditExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.ListExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.CreateExamByMasterOutcome;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.DeleteExamByMasterOutcome;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.EditExamByMasterOutcome;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.ListExamByMasterOutcome;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class ExamController {
    private final EditExamService editExamService;
    private final ListExamService listExamService;
    private final CreateExamService createExamService;
    private final DeleteExamService deleteExamService;
    public ExamController(EditExamService editExamService, ListExamService listExamService, CreateExamService createExamService, DeleteExamService deleteExamService) {
        this.editExamService = editExamService;
        this.listExamService = listExamService;
        this.createExamService = createExamService;
        this.deleteExamService = deleteExamService;
    }

    @PostMapping("/create-test-by-master")
    public CreateExamByMasterOutcome createExamByMaster(@RequestBody CreateExamByMasterCommand command,
                                                        HttpServletRequest request,
                                                        BindingResult result){
        return createExamService.createByMaster(command,request,result);
    }
    @PostMapping("/list-test-by-master")
    public ListExamByMasterOutcome listExamByMaster(@RequestBody ListExamByMasterCommand command,
                                                    HttpServletRequest request,
                                                    BindingResult result){
        return listExamService.ListByMaster(command,request,result);
    }
    @PostMapping("/edit-test-by-master")
    public EditExamByMasterOutcome editExamByMaster(@RequestBody EditExamByMasterCommand command,
                                                    HttpServletRequest request,
                                                    BindingResult result)
    {
        return editExamService.editByMaster(command,result,request);
    }
    @PostMapping("/delete-test-by-master")
    public DeleteExamByMasterOutcome deleteExamByMaster(@RequestBody DeleteExamByMasterCommand command,
                                                        HttpServletRequest request,
                                                        BindingResult result){
        return  deleteExamService.deleteByMaster(command,result,request);
    }

}
