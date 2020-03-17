package ir.management.onlinetest.features.course_management.adapter.web;

import ir.management.onlinetest.features.course_management.application.port.AddCourseService;
import ir.management.onlinetest.features.course_management.application.port.AddMemberService;
import ir.management.onlinetest.features.course_management.application.port.ListCourseService;
import ir.management.onlinetest.features.course_management.application.port.in.AddCourseByAdminUseCase;
import ir.management.onlinetest.features.course_management.application.port.in.AddMemberByAdminUseCase;
import ir.management.onlinetest.features.course_management.application.port.in.ListCourseByAdminUseCase;
import ir.management.onlinetest.features.course_management.application.port.in.command.AddCourseByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.command.AddMemberByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.command.ListCourseByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.AddCourseByAdminOutcome;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.AddMemberByAdminOutcome;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.ListCourseByAdminOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ListCourseByAdminUseCase listCourseService;
    @Autowired
    private AddCourseByAdminUseCase addCourseService;
    @Autowired
    private AddMemberByAdminUseCase addMemberService;
    @PostMapping("/list")
    public ListCourseByAdminOutcome getCourses(ListCourseByAdminCommand command){
        return listCourseService.list(command);
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
}
