package hezix.org.util;

import hezix.org.converter.util.HibernateUtil;
import hezix.org.entity.User;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

import static hezix.org.converter.util.HibernateUtil.buildConfiguration;

@UtilityClass
public class HibernateTestUtil {

    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine");

    static {
        postgres.start();
    }

    public static SessionFactory buildSessionFactory() {
        Configuration config = HibernateUtil.buildConfiguration();

        config.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        config.setProperty("hibernate.connection.username", postgres.getUsername());
        config.setProperty("hibernate.connection.password", postgres.getPassword());

        config.configure();

        return config.buildSessionFactory();

    }
}
