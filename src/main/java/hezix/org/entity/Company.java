package hezix.org.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SortComparator;
import org.hibernate.annotations.SortNatural;

import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "users")
@EqualsAndHashCode(of = "name")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
//    @OrderBy("personalInfo.firstName")
    @OrderColumn(name = "id")
    @SortNatural
    @MapKey(name = "username")
//    @SortComparator()
    private Map<String, User> users = new TreeMap<>();

    @ElementCollection
    @CollectionTable(name = "company_locale", joinColumns = @JoinColumn(name = "company_id"))
    @Builder.Default
//    @AttributeOverride(name = "language", column = @Column(name = "lang"))
//    private List<LocaleInfo> locale = new ArrayList<>();
    @MapKeyColumn(name = "lang")
    private Map<String, String> locales = new HashMap();

    public void addUser(User user) {
        users.put(user.getUsername(),user);
        user.setCompany(this);
    }
}
