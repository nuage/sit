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

    boolean can(long user, long entry, Permission permission);

    boolean grant(long user, long entry, Permission permission);

    boolean remove(long entry);

    boolean copy(long source, long dest);
}
