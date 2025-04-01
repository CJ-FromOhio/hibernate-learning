package hezix.org;

import hezix.org.util.HibernateUtil;
import hezix.org.entity.*;
import hezix.org.util.HibernateTestUtil;
import jakarta.persistence.FlushModeType;
import lombok.Cleanup;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;

class HibernateRunnerTest {


    @Test
    void checkHQL(){
        try (var sessionFactory = HibernateTestUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var res = session.createQuery("select u from User u join u.company c  where u.personalInfo.firstName = ?1 and c.name = ?2", User.class)
                    .setParameter(1, "Ivan")
                    .setParameter(2, "Google")
                    .setFlushMode(FlushModeType.COMMIT)
                    .getResultList();
            System.out.println(res);

            var countRows = session.createQuery("update User u set u.role = 'ADMIN'").executeUpdate();

//            var nativeQuery = session.createNativeQuery("select u.* from users u where u.firstname = 'Ivan' ", User.class);

            session.getTransaction().commit();
        }
    }

    @Test
    void checkTestContainer(){
        try (var sessionFactory = HibernateTestUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var company = Company.builder()
                    .name("Google")
                    .build();
            session.save(company);

            var programmer = Programmer.builder()
                    .username("goprogrammer")
//                    .language(Language.GO)
                    .company(company)
                    .build();
            session.save(programmer);

            var manager = Manager.builder()
                    .username("manager")
//                    .projectName("name")
                    .company(company)
                    .build();
            session.save(manager);
            session.flush();

            session.clear();

            var programmer1 = session.get(Programmer.class, 1L);
            var manager1 = session.get(User.class, 2L);
            System.out.println(programmer1);
            System.out.println(manager1);

            session.getTransaction().commit();
        }
    }

    @Test
    void checkUsersOrdering(){
        try ( var sessionFactory = HibernateUtil.buildSessionFactory();
              var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var company = session.get(Company.class, 1);
            company.getUsers().forEach((k,v) -> System.out.println(v));
            session.getTransaction().commit();
        }
    }

    @Test
    void checkLocaleInfo(){
        try ( var sessionFactory = HibernateUtil.buildSessionFactory();
              var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var company = session.get(Company.class, 2);
//            company.getLocales().add(LocaleInfo.of("ru", "описание"));
//            company.getLocales().add(LocaleInfo.of("en", "description"));
            System.out.println(company.getLocales());
            session.getTransaction().commit();
        }
    }

    @Test
    void checkManyToMany(){
        try ( var sessionFactory = HibernateUtil.buildSessionFactory();
              var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var user = session.get(User.class, 11L);
            var chat = session.get(Chat.class, 2L);

            var userChat = UserChat.builder()
//                    .createdAt(Instant.now())
//                    .createdBy(user.getUsername())
                    .build();

            userChat.setUser(user);
            userChat.setChat(chat);

            session.save(userChat);

//            user.addChat(testChat);
//            session.save(testChat);

            session.getTransaction().commit();
        }
    }

    @Test
    void checkOneToOne() {
        try ( var sessionFactory = HibernateUtil.buildSessionFactory();
              var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var user = session.get(User.class, 11L);
            System.out.println(user.getProfile());


//            var user = User.builder()
//                    .username("admin2131")
//                    .build();
//            var profile = Profile.builder()
//                    .language("ru")
//                    .street("Z")
//                    .build();

//            profile.setUser(user);
//
//            session.save(user);
//            profile.setUser(user);
//            session.save(profile);

            session.getTransaction().commit();
        }
    }

    @Test
    void orphanRemoval() {
        try ( var sessionFactory = HibernateUtil.buildSessionFactory();
              var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var company = session.get(Company.class, 1);

//            System.out.println(company.getUsers().removeIf(u -> u.getId() == 1));

            session.getTransaction().commit();
        }
    }

    @Test
    void checkLazyInitialization() {
        Company company = null;
        try ( var sessionFactory = HibernateUtil.buildSessionFactory();
         var session = sessionFactory.openSession()) {
            session.beginTransaction();

            company = session.getReference(Company.class, 1);

            System.out.println();

            session.getTransaction().commit();
        }
        var users = company.getUsers();
        System.out.println(users.size());
    }

    @Test
    void getCompanyById(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = session.get(Company.class, 1);
        var user = session.get(User.class, 7L);
        company.addUser(user);
        Hibernate.initialize(company.getUsers());
        System.out.println();
//        session.save(company);

        session.getTransaction().commit();
    }

}