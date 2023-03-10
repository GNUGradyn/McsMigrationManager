package com.gradyn.McsMigrationManager;

import com.gradyn.McsMigrationManager.Data.DbFactory;
import com.gradyn.McsMigrationManager.Data.Models.Plot;
import com.gradyn.McsMigrationManager.Data.Models.UserCache;
import com.plotsquared.core.PlotSquared;
import com.plotsquared.core.plot.PlotId;
import org.hibernate.Session;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class PlotMigrator {
    public static List<String> UsernameSearch(String usernameQuery) {
        try (Session session = DbFactory.getSessionFactory().openSession()) {
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(UserCache.class);
            var root = query.from(UserCache.class);

            query.select(root);

            query.where(builder.and(
                    builder.isFalse(root.get("Transfered")),
                    builder.like(root.get("Username"), usernameQuery + "%")
            ));

            var results = session.createQuery(query).setMaxResults(200).getResultList().stream().map(x -> x.getUsername()).toList();

            return results;
        } catch (Exception ex) {
            throw ex;
        }
    }
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
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static boolean MigratePlots(UUID original, UUID destination) {
        try (Session session = DbFactory.getSessionFactory().openSession()) {
            // step 1: Locate fake GUID
            var transaction = session.beginTransaction();
            var builder = session.getCriteriaBuilder();
            var query = builder.createQuery(UserCache.class);
            var root = query.from(UserCache.class);

            query.select(root);

            query.where(builder.and(
                    builder.isFalse(root.get("Transfered")),
                    builder.equal(root.get("RawUUID"), original.toString())
            ));

            var results = session.createQuery(query).getResultList();

            if (results.size() == 0) {
                Logger.getGlobal().warning("Failed to migrate. source uuid not found in cache");
                return false;
            }

            var entry = results.get(results.size() - 1);

            // Step 2: Mark user transferred
            entry.setTransfered(true);

            // Step 3: Update database with real GUI and update PS ABS
            var builder2 = session.getCriteriaBuilder();
            var query2 = builder2.createQuery(Plot.class);
            var root2 = query2.from(Plot.class);

            query2.where(builder2.equal(root2.get("Owner"), original.toString()));

            var results2 = session.createQuery(query2).getResultList();

            results2.forEach(x -> {
                x.setOwner(destination.toString());
                PlotSquared.get().getPlotAreaManager().getPlotAreasSet("world").iterator().next().getPlot(PlotId.of(x.getPlotIdX(), x.getPlotIdZ())).setOwner(destination);
            });


            transaction.commit();
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }
}

