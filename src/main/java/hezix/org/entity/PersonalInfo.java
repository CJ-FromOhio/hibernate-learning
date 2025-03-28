package hezix.org.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Embeddable
public class PersonalInfo {

    private String firstName;
    private String lastName;

//    @Column(name = "berth_date")
    private LocalDate dateOfBirth;
}
