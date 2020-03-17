package ir.management.onlinetest.features.admin_management.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.management.onlinetest.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class UserDTO {
    private Long userId;
    private String userName;
    private String role;
    private Date createDate;
    private String status;
    private boolean isActive;
    @JsonManagedReference
    private List<Course> courseList;
}
