package ir.management.onlinetest.features.admin_management.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

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
}
