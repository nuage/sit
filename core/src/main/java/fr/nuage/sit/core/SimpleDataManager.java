/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */

package fr.nuage.sit.core;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nuage
 */
public class SimpleDataManager implements DataManager {

    private Map<Long, Entry> entries;

    public SimpleDataManager() {
        entries = Maps.newHashMap();
    }

    @Override
    public Entry getEntry(long id) {
        return entries.get(id);
    }

    @Override
    public boolean update(Entry entry) {
        entries.put(entry.getId(), entry);
        return true;
    }

    @Override
    public boolean updateAll(List<Entry> entries) {
        for (Entry entry : entries) {
            this.entries.put(entry.getId(), entry);
        }
        return true;
    }

    @Override
    public List<Entry> getEntries() {
        return ImmutableList.copyOf(entries.values());
    }

    @Override
    public boolean remove(long entry) {
        entries.remove(entry);
        return true;
    }
}
