package com.gradyn.McsMigrationManager.Data;

import com.gradyn.McsMigrationManager.Data.Models.Archive;
import com.gradyn.McsMigrationManager.Data.Models.Plot;
import com.gradyn.McsMigrationManager.Data.Models.UserCache;
import com.gradyn.McsMigrationManager.McsMigrationManager;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

public class DbFactory {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    public static SessionFactory buildSessionFactory() {
        Configuration config = new Configuration();
        config.addAnnotatedClass(Plot.class);
        config.addAnnotatedClass(UserCache.class);
        config.addAnnotatedClass(Archive.class);
        config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        config.setProperty("hibernate.connection.url", McsMigrationManager.config.getString("database.url"));
        config.setProperty("hibernate.connection.username", McsMigrationManager.config.getString("database.username"));
        config.setProperty("hibernate.connection.password", McsMigrationManager.config.getString("database.password"));
        try {
            return config.buildSessionFactory();
        } catch (Throwable ex) {
            Logger.getAnonymousLogger().severe("Failed to create McsMigrationManager database SessionFactory");
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
