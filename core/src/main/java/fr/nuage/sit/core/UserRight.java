/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */
package fr.nuage.sit.core;

/**
 *
 * @author nuage
 */
public class UserRight {

    public enum Permission {

        Read,
        Modify,
        Delete
    }
    private final User user;
    private final Permission permission;
    private final Entry entry;

    public UserRight(User user, Permission permission, Entry entry) {
        this.user = user;
        this.permission = permission;
        this.entry = entry;
    }

    public Entry getEntry() {
        return entry;
    }

    public Permission getPermission() {
        return permission;
    }

    public User getUser() {
        return user;
    }
}
