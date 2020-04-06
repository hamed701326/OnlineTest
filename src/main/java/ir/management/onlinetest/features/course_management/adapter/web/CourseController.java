package ir.management.onlinetest.features.course_management.adapter.web;

import ir.management.onlinetest.features.course_management.application.port.ListCourseService;
import ir.management.onlinetest.features.course_management.application.port.in.*;
import ir.management.onlinetest.features.course_management.application.port.in.command.*;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ListCourseService listCourseService;
    @Autowired
    private AddCourseByAdminUseCase addCourseService;
    @Autowired
    private AddMemberByAdminUseCase addMemberService;
    @Autowired
    private ListMemberByAdminUseCase listMemberService;
    @Autowired
    private RemoveMemberByAdminUseCase removeMemberService;
    @PostMapping("/list")
    public ListCourseByAdminOutcome getCourses(ListCourseByAdminCommand command){
        return listCourseService.list(command);
    }
    @PostMapping("/list-course-by-master")
    public ListCourseByMasterOutcome getCourses(@RequestBody ListCourseByMasterCommand command, BindingResult result
    , HttpServletRequest request){
        return listCourseService.listByMaster(command,result,request);
    }
    @PostMapping("/list-course-by-student")
    public ListCourseByStudentOutcome getCourses(@RequestBody ListCourseByStudentCommand command,
                                                 BindingResult result,
                                                 HttpServletRequest request){
        return listCourseService.listByStudent(command,result,request);
    }
    @PostMapping("/add-course-by-admin")
    public AddCourseByAdminOutcome addCourse(@RequestBody AddCourseByAdminCommand command, BindingResult result){
        return addCourseService.addCourse(command,result);
    }
    @PostMapping("/add-member-by-admin")
    public AddMemberByAdminOutcome addMemberByAdmin(@RequestBody AddMemberByAdminCommand command,
                                                    BindingResult result){
        return addMemberService.addMember(command,result);
    }
    @PostMapping("/list-member-by-admin")
    public ListMemberByAdminOutcome listMemberByAdmin(@RequestBody ListMemberByAdminCommand command){
        return listMemberService.list(command);
    }
    @PostMapping("/remove-member-by-admin")
    public RemoveMemberByAdminOutcome removeMemberByAdmin(@RequestBody RemoveMemberByAdminCommand command){
        return removeMemberService.remove(command);
    }
    @GetMapping()
    public ModelAndView getCoursePage(){
        return new ModelAndView(
                "../static/features/course-management/CoursePanel");
    }
    @GetMapping("/course-panel-for-student")
    public ModelAndView getCoursePageForStudent(){
        return new ModelAndView(
                "../static/features/course-management/course-panel-for-student/course-panel-for-student"
        );
    }
}
