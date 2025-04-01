package hezix.org.entity;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;


import java.util.ArrayList;
import java.util.List;

import static javax.xml.stream.XMLStreamConstants.SPACE;

@Data
@Builder
@FetchProfile(name = "withCompanyAndPayment",
        fetchOverrides ={ @FetchProfile.FetchOverride(
                entity = User.class, association = "company", mode = FetchMode.JOIN
        ),
        @FetchProfile.FetchOverride(
                entity = User.class, association = "payments", mode = FetchMode.JOIN
        )}
)

@NamedEntityGraph(
        name = "WithCompanyAndChat",
        attributeNodes = {
                @NamedAttributeNode("company"),
                @NamedAttributeNode(value = "userChats", subgraph = "chats")
        },
        subgraphs = {
                @NamedSubgraph(name = "chats", attributeNodes =
                        @NamedAttributeNode("chat"))
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company", "userChats", "payments"})
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

//    @OneToOne(mappedBy = "user",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY)
//    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)// колонка в которую хотим присоединить поле чат
    private List<UserChat> userChats = new ArrayList<>();
    @Builder.Default
//    @BatchSize(size = 3)
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy="receiver", fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.username);
    }

    public String fullName() {
        return getPersonalInfo().getFirstName() + " " + getPersonalInfo().getLastName();
    }
}
