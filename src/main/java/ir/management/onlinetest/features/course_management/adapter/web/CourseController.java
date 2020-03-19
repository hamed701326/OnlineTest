package ir.management.onlinetest.features.course_management.adapter.web;

import ir.management.onlinetest.features.course_management.application.port.ListCourseService;
import ir.management.onlinetest.features.course_management.application.port.in.*;
import ir.management.onlinetest.features.course_management.application.port.in.command.*;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.*;
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
    public ListCourseByMasterOutcome getCourses(@RequestBody ListCourseByMasterCommand command,BindingResult result){
        return listCourseService.listByMaster(command,result);
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
}
