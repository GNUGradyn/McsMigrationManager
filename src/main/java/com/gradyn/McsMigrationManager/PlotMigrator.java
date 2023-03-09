package com.gradyn.McsMigrationManager;

import com.gradyn.McsMigrationManager.Data.DbFactory;
import com.gradyn.McsMigrationManager.Data.Models.UserCache;
import org.hibernate.Session;

import java.util.UUID;
import java.util.logging.Logger;

public class PlotMigrator {
    public static UUID GetPsudoUUID(String username) {
        try (Session session = DbFactory.getSessionFactory().openSession()) {
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(UserCache.class);
            var root = query.from(UserCache.class);

            query.select(root);
            query.where(builder.and(
                    builder.isFalse(root.get("Transfered")),
                    builder.equal(root.get("Username"), username)
            ));

            var results = session.createQuery(query).getResultList();
            if (results.size() == 0) return null;
            return results.get(results.size() - 1).getUUID();
        }
    }

    public static boolean MigratePlots(UUID original, UUID destination) {
        try (Session session = DbFactory.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(UserCache.class);
            var root = query.from(UserCache.class);

            query.select(root);

            query.where(builder.and(
                    builder.isFalse(root.get("Transfered")),
                    builder.equal(root.get("UUID"), original)
            ));

            var results = session.createQuery(query).getResultList();

            if (results.size() == 0) {
                Logger.getGlobal().warning("Failed to migrate. source uuid not found in cache");
                return false;
            }

            var entry = results.get(results.size() - 1);

            entry.setTransfered(true);
            entry.setUUID(destination);

            transaction.commit();
            return true;
        }
    }
}
