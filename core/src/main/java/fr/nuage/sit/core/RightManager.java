/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */
package fr.nuage.sit.core;

/**
 *
 * @author nuage
 */
public interface RightManager {

    enum Permission {

        Read,
        Modify,
        Delete
    }

    boolean can(User user, Entry entry, Permission permission);

    boolean grant(User user, Entry entry, Permission permission);

    boolean remove(Entry entry);

    boolean copy(Entry source, Entry dest);
}
