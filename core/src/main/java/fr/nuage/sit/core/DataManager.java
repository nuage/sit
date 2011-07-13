/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */

package fr.nuage.sit.core;

import java.util.List;

/**
 *
 * @author nuage
 */
public interface DataManager {

    Entry getEntry(long id);

    List<Entry> getEntries();

    boolean add(Entry entry);

    boolean addAll(List<Entry> entries);

    boolean remove(Entry entry);
}
