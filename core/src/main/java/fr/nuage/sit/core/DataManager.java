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

    boolean update(Entry entry);

    boolean updateAll(List<Entry> entries);

    boolean remove(long entry);
}
