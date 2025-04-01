package hezix.org.util;

import hezix.org.converter.BirthDayConverter;
import hezix.org.entity.*;
import hezix.org.interceptor.GlobalInterceptor;
import hezix.org.listener.AuditTableListener;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        var config = buildConfiguration();
        config.configure();

        var sessionFactory = config.buildSessionFactory();
//        registryListeners(sessionFactory);

        return sessionFactory;
    }

    private static void registryListeners(SessionFactory sessionFactory) {
        var sessionFactoryUnwrap = sessionFactory.unwrap(SessionFactoryImpl.class);
        var serviceRegistry = sessionFactoryUnwrap.getServiceRegistry().getService(EventListenerRegistry.class);
        var auditTableListener = new AuditTableListener();
        serviceRegistry.appendListeners(EventType.PRE_INSERT,auditTableListener);
        serviceRegistry.appendListeners(EventType.PRE_DELETE,auditTableListener);
    }

    public static Configuration buildConfiguration() {
        Configuration config = new Configuration();

        // Сначала загружаем hibernate.cfg.xml
        config.configure();

        // Затем добавляем аннотированные классы и конвертеры
        config.addAnnotatedClass(User.class);
        config.addAnnotatedClass(Profile.class);
        config.addAnnotatedClass(Chat.class);
        config.addAnnotatedClass(UserChat.class);
        config.addAnnotatedClass(Company.class);
        config.addAttributeConverter(new BirthDayConverter());
        config.setInterceptor(new GlobalInterceptor());
        return config;
    }
}
