/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */
package fr.nuage.sit.core;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author nuage
 */
public class SimpleRightManager implements RightManager {

    /**
     * Row: User
     * Col: Entry
     */
    private Table<Long, Long, Permission> rights;

    public SimpleRightManager() {
        rights = HashBasedTable.create();
    }

    @Override
    public boolean grant(long user, long entry, Permission permission) {
        rights.put(user, entry, permission);
        return true;
    }

    @Override
    public boolean remove(long entry) {
        final Set<Long> users = rights.column(entry).keySet();
        for (Long user : users) {
            rights.remove(user, entry);
        }
        return true;
    }

    @Override
    public boolean copy(long source, long dest) {
        boolean allSuccess = true;
        final Map<Long, Permission> column = rights.column(source);
        for (Long user : column.keySet()) {
            Permission perm = column.get(user);
            grant(user, dest, perm);
        }
        return allSuccess;
    }

    @Override
    public boolean can(long user, long entry, Permission permission) {
        final Permission perm = rights.get(user, entry);
        return perm != null && perm.ordinal() >= permission.ordinal();
    }
}
