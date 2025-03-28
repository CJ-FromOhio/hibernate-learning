package hezix.org.converter.util;

import hezix.org.converter.BirthDayConverter;
import hezix.org.entity.*;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;


@UtilityClass
public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        return buildConfiguration().configure().buildSessionFactory();
    }

    public static Configuration buildConfiguration() {
        Configuration config = new Configuration();

        config.addAnnotatedClass(User.class);
        config.addAnnotatedClass(Profile.class);
        config.addAnnotatedClass(Chat.class);
        config.addAnnotatedClass(UserChat.class);
        config.addAnnotatedClass(Company.class);
        config.addAttributeConverter(new BirthDayConverter());
        config.configure("hibernate.cfg.xml");

        return config;
    }
}
