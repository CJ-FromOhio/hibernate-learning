package hezix.org;


import hezix.org.interceptor.GlobalInterceptor;
import hezix.org.util.HibernateUtil;
import hezix.org.entity.*;
import hezix.org.util.TestDataImporter;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;

public class HibernateRunner {

    @Transactional
    public static void main(String[] args) throws SQLException {
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
         Session session = sessionFactory
                 .withOptions()
                 .interceptor(new GlobalInterceptor())
                 .openSession()) {
            TestDataImporter.importData(sessionFactory);
            session.beginTransaction();

            var payment1 = session.find(Payment.class, 1);
            payment1.setAmount(payment1.getAmount() + 10);

            session.getTransaction().commit();
        }
    }
}
