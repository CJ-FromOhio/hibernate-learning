package hezix.org.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
//@DiscriminatorValue("manager")
//@PrimaryKeyJoinColumn(name = "id")
public class Manager extends User{

    private String projectName;


    public Manager(Long id, String username, PersonalInfo personalInfo, Role role, Company company, Profile profile, List<UserChat> userChats, String projectName) {
        this.projectName = projectName;
    }
}
