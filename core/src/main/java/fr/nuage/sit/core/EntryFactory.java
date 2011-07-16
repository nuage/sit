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
public class EntryFactory {

    public Entry make(String text, Entry.Type type) {
        final SimpleEntry newEntry = new SimpleEntry(getNextId(), text, type);
        return newEntry;
    }

    public Entry make(Entry entry, String text) {
        SimpleEntry newEntry = (SimpleEntry) make(text, entry.getType());
        final List<Long> childs = entry.getChildsId();
        for (Long child : childs) {
            newEntry = newEntry.addChild(child);
        }
        return newEntry;
    }

    public LoadedEntry make(Entry entry, List<LoadedEntry> childs) {
        return new SimpleLoadedEntry(entry, childs);
    }
    
    private long getNextId() {
        return Math.round(Math.random() * 10000);
    }

    public Entry extractFrom(Entry entry, String textToExtract) {
        return make(textToExtract, entry.getType());
    }

}
