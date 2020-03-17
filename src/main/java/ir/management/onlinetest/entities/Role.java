package ir.management.onlinetest.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    @OneToMany(mappedBy = "role")
//    private Collection<Account> accounts;
    public Role(String name) {
        this.name=name;
    }
}
