package hezix.org.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
//@DiscriminatorValue("programmer")
//@PrimaryKeyJoinColumn(name = "id")
public class Programmer extends User {
    @Enumerated(EnumType.STRING)
    private Language language;

    public Programmer(Long id, String username, PersonalInfo personalInfo, Role role, Company company, Profile profile, List<UserChat> userChats, Language language) {
        this.language = language;
    }
}
