package ir.management.onlinetest.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.management.onlinetest.enums.Status;
import ir.management.onlinetest.features.user_management.application.ports.in.commands.SignUpCommand;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ToString(exclude = {"role","courseList"})
@EqualsAndHashCode(exclude = {"role","courseList"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private boolean isActive;
    @Temporal(TemporalType.DATE)
    private Date lastLogin;
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    @ManyToMany(mappedBy ="members",fetch = FetchType.LAZY)
    private List<Course> courseList;
}
