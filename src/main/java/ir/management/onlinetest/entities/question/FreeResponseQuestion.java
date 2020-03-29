package ir.management.onlinetest.entities.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("freeResponse-question")
public class FreeResponseQuestion extends Question {


}
