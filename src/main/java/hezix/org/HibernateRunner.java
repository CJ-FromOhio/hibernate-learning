package hezix.org;


import hezix.org.converter.BirthDayConverter;
import hezix.org.entity.*;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class HibernateRunner {
    public static void main(String[] args) throws SQLException {
//        BlockingQueue<Connection> pool = null;
//        SessionFactory
//
//        var connection = DriverManager.getConnection("db.url"
//                , "db.username", "db.password");
//        Session

        var configuration = new Configuration();

//        configuration.addAnnotatedClass(User.class);
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAttributeConverter(new BirthDayConverter());
        configuration.configure();
//        var company = Company.builder()
//                .name("adidas")
//                .build();
//        var user = User.builder()
//                .username("admin6")
//                .personalInfo(PersonalInfo.builder()
//                        .firstName("Petrovka11")
//                        .lastName("admin11")
//                        .dateOfBirth(new Birthday(LocalDate.of(2000, 12, 2)))
//                        .build())
//                .role(Role.ADMIN)
//                .company(company)
//                .build();


        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

//            var company1 = session.get(Company.class, 1);
//            System.out.println(company1);
//            session.save(company);
//            var company = session.get(Company.class, 1);
//            session.save(company);
//            company.addUser(user);
            var user1 = session.get(User.class, 6L);
            System.out.println(user1);
//            System.out.println(company);
//            user.setCompany(company);
//            session.save(user);

            session.getTransaction().commit();
        }
    }
}
