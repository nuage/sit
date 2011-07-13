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

    boolean can(User user, Entry entry, UserRight.Permission permission);

    UserRight getRight(User user, Entry entry);

    boolean entryCreation(User owner, Entry entry);

    boolean entryRemoval(Entry entry);

    boolean copyRights(Entry source, Entry dest);
}
