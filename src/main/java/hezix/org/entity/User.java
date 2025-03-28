package hezix.org.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;

import lombok.*;


import java.util.ArrayList;
import java.util.List;

import static javax.xml.stream.XMLStreamConstants.SPACE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company", "profile", "userChats"})
@Table(name = "users")
//@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "findUserByName", query = "select u from User u " +
        "left join u.company c " +
        "where u.personalInfo.firstName = :firstname and c.name = :companyName " +
        "order by u.personalInfo.firstName desc")
//@DiscriminatorColumn(name = "type")
public class User implements Comparable<User> , BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String username;
    @Embedded
    @AttributeOverride(name = "dateOfBirth", column = @Column(name = "berth_date"))
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "company_id")  // company_id
    private Company company;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "user")// колонка в которую хотим присоединить поле чат
    private List<UserChat> userChats = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy="receiver")
    private List<Payment> payments = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.username);
    }

    public String fullName() {
        return getPersonalInfo().getFirstName() + " " + getPersonalInfo().getLastName();
    }
}
