package ir.management.onlinetest.features.Exam_management.adapter.web;

import ir.management.onlinetest.features.Exam_management.application.ports.*;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.*;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@RestController
@RequestMapping("/test")
public class ExamController {
    private final TakeExamService takeExamService;
    private final EditExamService editExamService;
    private final ListExamService listExamService;
    private final CreateExamService createExamService;
    private final DeleteExamService deleteExamService;
    private final ListParticipateService listParticipateService;
    private final SubmitResponseService submitResponseService;
    public ExamController(TakeExamService takeExamService,
                          EditExamService editExamService,
                          ListExamService listExamService,
                          CreateExamService createExamService,
                          DeleteExamService deleteExamService,
                          ListParticipateService listParticipateService, SubmitResponseService submitResponseService) {
        this.takeExamService = takeExamService;
        this.editExamService = editExamService;
        this.listExamService = listExamService;
        this.createExamService = createExamService;
        this.deleteExamService = deleteExamService;
        this.listParticipateService = listParticipateService;
        this.submitResponseService = submitResponseService;
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
    @PostMapping("/start-test-by-master")
    public StartExamByMasterOutcome startByMaster(@RequestBody StartExamByMasterCommand command,
                                                  HttpServletRequest request,
                                                  BindingResult result)
    {
        return createExamService.startByMaster(command,request,result);
    }
    @PostMapping("/finish-test-by-master")
    public FinishExamByMasterOutcome finishByMaster(@RequestBody FinishExamByMasterCommand command,
                                                    HttpServletRequest request,
                                                    BindingResult result){
        return  createExamService.finishByMaster(command,request,result);
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
    @PostMapping("/submit-answer-by-student")
    public SubmitResponseByStudentOutcome submitResponseByStudent(@RequestBody SubmitResponseByStudentCommand command,
                                                                  BindingResult result,
                                                                  HttpServletRequest request){
        return  submitResponseService.submitByStudent(command,request,result);
    }

    @PostMapping("/finish-test-by-student")
    public FinishExamByStudentOutcome finishExam(HttpServletRequest request){

        return takeExamService.determineScoreAutomatically(request);
    }
    @PostMapping("/list-participator-in-exam-by-master")
    public ListParticipateInExamByMasterOutcome listParticipator(@RequestBody ListParticipateInExamByMasterCommand command,
                                                                 BindingResult result,
                                                                 HttpServletRequest request){
        return listParticipateService.listByMaster(command,request,result);
    }
    @PostMapping("/list-question-and-answer-by-master")
    public ListQuestionAndAnswerByMasterOutcome listQuestionAndAnswerByMaster(@RequestBody ListQuestionAndAnswerByMasterCommand command,
                                                                              BindingResult result,
                                                                              HttpServletRequest request){
        return takeExamService.listByMaster(command,result,request);
    }
    @PostMapping("/correct-free-question-by-master")
    public CorrectFreeQuestionByMasterOutcome correctByMaster(@RequestBody CorrectFreeQuestionByMasterCommand command,
                                                              BindingResult result,
                                                              HttpServletRequest request){
        return takeExamService.correctByMaster(command,result,request);
    }
    @PostMapping("/finish-correction-by-master")
    public FinishCorrectionByMasterOutcome finishCorrectionByMaster(@RequestBody FinishCorrectionByMasterCommand command,
                                                                       BindingResult  result ,
                                                                       HttpServletRequest request){
        return takeExamService.finishCorrectionByMaster(command,result,request);
    }


}
