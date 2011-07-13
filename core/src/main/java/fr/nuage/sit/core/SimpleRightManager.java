/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */
package fr.nuage.sit.core;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import fr.nuage.sit.core.UserRight.Permission;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author nuage
 */
public class SimpleRightManager implements RightManager {

    private List<UserRight> rights;
    private Multimap<User, UserRight> rightsByUser;
    private Multimap<Entry, UserRight> rightsByEntry;

    public SimpleRightManager() {
        rights = Lists.newArrayList();
        rightsByUser = HashMultimap.create();
        rightsByEntry = HashMultimap.create();
    }

    private boolean addRight(User user, Entry entry, Permission permission) {
        final UserRight userRight = new UserRight(user, permission, entry);
        rights.add(userRight);
        rightsByUser.put(user, userRight);
        rightsByEntry.put(entry, userRight);
        return true;
    }

    @Override
    public boolean entryCreation(User owner, Entry entry) {
        return addRight(owner, entry, Permission.Delete);
    }

    @Override
    public boolean entryRemoval(Entry entry) {
        final Collection<UserRight> entryRights = rightsByEntry.get(entry);
        rights.removeAll(entryRights);
        rightsByEntry.removeAll(entry);
        return true;
    }

    @Override
    public boolean copyRights(Entry source, Entry dest) {
        final Collection<UserRight> entryRights = rightsByEntry.get(source);
        boolean allSuccess = true;
        for (UserRight userRight : entryRights) {
            allSuccess = addRight(userRight.getUser(), dest, userRight.getPermission());
            if (!allSuccess) {
                break;
            }
        }
        return allSuccess;
    }

    @Override
    public UserRight getRight(User user, Entry entry) {
        UserRight result = null;
        final Collection<UserRight> entryRights = rightsByEntry.get(entry);
        for (UserRight right : entryRights) {
            if (right.getUser().equals(user)) {
                result = right;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean can(User user, Entry entry, Permission permission) {
        final UserRight right = getRight(user, entry);
        if (right == null) {
            return false;
        }
        return right.getPermission().ordinal() <= permission.ordinal();
    }

}
