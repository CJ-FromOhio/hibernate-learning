package hezix.org.dao;

import hezix.org.dto.CompanyDto;
import hezix.org.entity.*;
import jakarta.persistence.Tuple;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    public List<User> findAll(Session session) {
//        return session.createQuery("select u from User u", User.class)
//                .getResultList();
//        var cb = session.getCriteriaBuilder();
//        var query = cb.createQuery(User.class);
//
//        var user = query.from(User.class);
//        query.select(user);
//
//        return session.createQuery(query)
//                .getResultList();



        return null;
    }

    /**
     * Возвращает всех сотрудников с указанным именем
     */
    public List<User> findAllByFirstName(Session session, String firstName) {
//        return session.createQuery("select u from User u where u.personalInfo.firstName = ?1", User.class)
//                .setParameter(1, firstName)
//                .getResultList();

//        var cb = session.getCriteriaBuilder();
//
//        var query = cb.createQuery(User.class);
//
//        var from = query.from(User.class);
//        query.select(from).where(
//                cb.equal(from.get(User_.personalInfo).get(PersonalInfo_.firstName), firstName));
//
//        return session.createQuery(query)
//                .getResultList();

        return null;
    }

    /**
     * Возвращает первые {limit} сотрудников, упорядоченных по дате рождения (в порядке возрастания)
     */
    public List<User> findLimitedUsersOrderedByBirthday(Session session, int limit) {
//        return session.createQuery("select u from User u order by u.personalInfo.dateOfBirth", User.class  )
//                .setMaxResults(limit)
//                .getResultList();
//        var builder = session.getCriteriaBuilder();
//        var query = builder.createQuery(User.class);
//
//        var user = query.from(User.class);
//        query.select(user).orderBy(
//                builder.asc(user.get(User_.personalInfo).get(PersonalInfo_.dateOfBirth)));
//
//        return session.createQuery(query)
//                .setMaxResults(limit)
//                .getResultList();
        return null;
    }

    /**
     * Возвращает всех сотрудников компании с указанным названием
     */
    public List<User> findAllByCompanyName(Session session, String companyName) {
//        return session.createQuery("select u from Company c " +
//                "join c.users u where c.name = :companyName", User.class)
//                .setParameter("companyName", companyName)
//                .getResultList();

//        var builder = session.getCriteriaBuilder();
//        var query = builder.createQuery(User.class);
//
//        var company = query.from(Company.class);
//        var users = company.join(Company_.users);
//        query.select(users)
//                .where(builder.equal(company.get(Company_.name), companyName));
//
//        return session.createQuery(query)
//                .getResultList();
        return null;
    }

    /**
     * Возвращает все выплаты, полученные сотрудниками компании с указанными именем,
     * упорядоченные по имени сотрудника, а затем по размеру выплаты
     */
    public List<Payment> findAllPaymentsByCompanyName(Session session, String companyName) {
//        return session.createQuery("select p from Payment p " +
//                        "join p.receiver u " +
//                        "join u.company c " +
//                        "where c.name = :companyName order by u.personalInfo.firstName, p.amount", Payment.class)
//                .setParameter("companyName", companyName)
//                .getResultList();


//        var builder = session.getCriteriaBuilder();
//        var query = builder.createQuery(Payment.class);
//
//        var payment = query.from(Payment.class);
//        var users = payment.join(Payment_.receiver);
//        var company = users.join(User_.company);
//        query.select(payment)
//                .where(builder.equal(company.get(Company_.name), companyName))
//                .orderBy(
//                        builder.asc(users.get(User_.personalInfo).get(PersonalInfo_.firstName)),
//                        builder.asc(payment.get(Payment_.amount))
//                );
//
//
//        return session.createQuery(query)
//                .getResultList();

        return null;
    }

    /**
     * Возвращает среднюю зарплату сотрудника с указанными именем и фамилией
     */
    public Double findAveragePaymentAmountByFirstAndLastNames(Session session, String firstName, String lastName) {
//        return session.createQuery("select avg(p.amount) from Payment p " +
//                        "join p.receiver u " +
//                        "where u.personalInfo.firstName = :firstName and" +
//                        " u.personalInfo.lastName = :lastname", Double.class)
//                .setParameter("firstName", firstName)
//                .setParameter("lastname", lastName)
//                .uniqueResult();

//        var builder = session.getCriteriaBuilder();
//        var query = builder.createQuery(Double.class);
//
//        var payment = query.from(Payment.class);
//        var user = payment.join(Payment_.receiver);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (firstName != null) {
//            predicates.add(builder.equal(user.get(User_.personalInfo).get(PersonalInfo_.firstName), firstName));
//        }
//        if (lastName != null) {
//            predicates.add(builder.equal(user.get(User_.personalInfo).get(PersonalInfo_.lastName), lastName));
//        }
//
//        query.select(builder.avg(payment.get(Payment_.amount))).where(
//                predicates.toArray(Predicate[]::new)
//        );
//
//
//        return session.createQuery(query)
//                .uniqueResult();
        return null;
    }

    /**
     * Возвращает для каждой компании: название, среднюю зарплату всех её сотрудников. Компании упорядочены по названию.
     */
    public List<CompanyDto> findCompanyNamesWithAvgUserPaymentsOrderedByCompanyName(Session session) {
//        return session.createQuery("select c.name, avg(p.amount) from Company c " +
//                        "join c.users u " +
//                        "join u.payments p " +
//                        "group by c.name " +
//                        "order by c.name", Object[].class)
//                .getResultList();

//        var builder = session.getCriteriaBuilder();
//        var query = builder.createQuery(CompanyDto.class);
//
//        var company = query.from(Company.class);
//        var users = company.join(Company_.users, JoinType.INNER);
//        var payments = users.join(User_.payments);
//
//        query.select(
//                        builder.construct(CompanyDto.class,
//                                company.get(Company_.name),
//                                builder.avg(payments.get(Payment_.amount)))
//
//                )
//                .groupBy(company.get(Company_.name))
//                .orderBy(builder.asc(company.get(Company_.name)));
//
//
//        return session.createQuery(query)
//                .getResultList();

        return null;

    }

    /**
     * Возвращает список: сотрудник (объект User), средний размер выплат, но только для тех сотрудников, чей средний размер выплат
     * больше среднего размера выплат всех сотрудников
     * Упорядочить по имени сотрудника
     */
    public List<Tuple> isItPossible(Session session) {
//        return session.createQuery("select u, avg(p.amount) from User u " +
//                        "join u.payments p " +
//                        "group by u " +
//                        "having avg(p.amount) > (select avg(p.amount) from Payment p) " +
//                        "order by u.personalInfo.firstName", Object[].class)
//                .getResultList();
//        var builder = session.getCriteriaBuilder();
//        var query = builder.createQuery(Tuple.class);
//
//        var user = query.from(User.class);
//        var payments = user.join(User_.payments);
//
//        var subquery = query.subquery(Double.class);
//        var paymentSubQuery = subquery.from(Payment.class);
//
//        query.select(
//                builder.tuple(
//                        user,
//                        builder.avg(payments.get(Payment_.amount))
//                )
//        ).groupBy(user.get(User_.id))
//                .having(builder.gt(
//                        builder.avg(payments.get(Payment_.amount)),
//                        subquery.select(builder.avg(paymentSubQuery.get(Payment_.amount)))
//
//                )).orderBy(builder.asc(user.get(User_.personalInfo).get(PersonalInfo_.firstName)));
//
//        return session.createQuery(query)
//                .getResultList();

        return null;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
