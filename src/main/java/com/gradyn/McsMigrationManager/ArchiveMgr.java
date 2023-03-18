package com.gradyn.McsMigrationManager;

import com.gradyn.McsMigrationManager.Data.DbFactory;
import com.gradyn.McsMigrationManager.Data.Models.Archive;
import com.gradyn.McsMigrationManager.Data.Models.UserCache;
import org.hibernate.Session;

import java.util.List;

public class ArchiveMgr {
    public static Coordinate[] LocateByUsername(String username) {
        try (Session session = DbFactory.getSessionFactory().openSession()) {
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(com.gradyn.McsMigrationManager.Data.Models.Archive.class);
            var root = query.from(com.gradyn.McsMigrationManager.Data.Models.Archive.class);

            query.select(root);
            query.where(builder.equal(root.get("Owner"), username));

            var results = session.createQuery(query).getResultStream();

            return results.map(x -> new Coordinate(x.getX(), x.getY(), x.getZ())).toArray(Coordinate[]::new);
        }
    }

    public static List<String> UsernameSearch(String usernameQuery) {
        try (Session session = DbFactory.getSessionFactory().openSession()) {
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(Archive.class);
            var root = query.from(Archive.class);

            query.select(root);

            query.where(builder.like(root.get("Owner"), usernameQuery + "%"));

            var results = session.createQuery(query).setMaxResults(200).getResultList().stream().map(x -> x.getOwner()).toList();

            return results;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
