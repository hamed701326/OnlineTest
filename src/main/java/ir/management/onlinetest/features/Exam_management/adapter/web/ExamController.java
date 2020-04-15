package ir.management.onlinetest.features.Exam_management.adapter.web;

import ir.management.onlinetest.features.Exam_management.application.ports.*;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.*;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class ExamController {
    private final TakeExamService takeExamService;
    private final EditExamService editExamService;
    private final ListExamService listExamService;
    private final CreateExamService createExamService;
    private final DeleteExamService deleteExamService;
    public ExamController(TakeExamService takeExamService,
                          EditExamService editExamService,
                          ListExamService listExamService,
                          CreateExamService createExamService,
                          DeleteExamService deleteExamService) {
        this.takeExamService = takeExamService;
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
        return listExamService.listByMaster(command,request,result);
    }
    @PostMapping("/list-test-by-student")
    public ListExamByStudentOutcome listExamByStudent(@RequestBody ListExamByStudentCommand command,
                                                     HttpServletRequest request,
                                                     BindingResult result){
        Long id= (Long) request.getSession().getAttribute("accountId");
        return listExamService.listByStudent(command, result, request);
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
    @PostMapping("/take-test-by-student")
    public TakeExamByStudentOutcome takeExamByStudent(@RequestBody TakeExamByStudentCommand command,
                                                      HttpServletRequest request,
                                                      BindingResult result){
        return  takeExamService.takeByStudent(command,request,result);
    }
    @PostMapping("/set-leftTime-for-exam")
    public SetLeftTimeForTakeExamOutcome setLeftTime(@RequestBody SetLeftTimeForTakeExamCommand command,
                                                     HttpServletRequest request,
                                                     BindingResult result){
        return takeExamService.setLeftTime(command,result,request);
    }
    @GetMapping("/test-page")
    public ModelAndView getExamPage(){
        return new ModelAndView("../static/features/exam-management/take-exam-by-student/take-exam-by-student");
    }
    @PostMapping("/finish-test-by-student")
    public FinishExamByStudentOutcome finishExam(HttpServletRequest request,BindingResult result){

        return takeExamService.determineScoreAutomatically(request,result);


    }


}
