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
    public boolean grant(User user, Entry entry, Permission permission) {
        return addRight(user.getId(), entry.getId(), permission);
    }

    private boolean addRight(long user, long entry, Permission permission) {
        rights.put(user, entry, permission);
        return true;
    }

    @Override
    public boolean remove(Entry entry) {
        final Set<Long> users = rights.column(entry.getId()).keySet();
        for (Long user : users) {
            rights.remove(user, entry.getId());
        }
        return true;
    }

    @Override
    public boolean copy(Entry source, Entry dest) {
        boolean allSuccess = true;
        final Map<Long, Permission> column = rights.column(source.getId());
        for (Long user : column.keySet()) {
            Permission perm = column.get(user);
            addRight(user, dest.getId(), perm);
        }
        return allSuccess;
    }

    @Override
    public boolean can(User user, Entry entry, Permission permission) {
        final Permission perm = rights.get(user.getId(), entry.getId());
        return perm != null && perm.ordinal() >= permission.ordinal();
    }
}
