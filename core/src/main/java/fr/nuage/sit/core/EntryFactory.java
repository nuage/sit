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

    public Entry make(String text, Entry.Type type, Long parent) {
        final SimpleEntry newEntry = new SimpleEntry(getNextId(), text, type, parent);
        return newEntry;
    }

    public Entry make(Entry entry, String text, Long parent) {
        SimpleEntry newEntry = (SimpleEntry) make(text, entry.getType(), parent);
        final List<Long> childs = entry.getChilds();
        for (Long child : childs) {
            newEntry = newEntry.addChild(child);
        }
        return newEntry;
    }

    private long getNextId() {
        return Math.round(Math.random() * 10000);
    }

    public Entry extractFrom(Entry entry, String textToExtract, Long parent) {
        return make(textToExtract, entry.getType(), parent);
    }

}
