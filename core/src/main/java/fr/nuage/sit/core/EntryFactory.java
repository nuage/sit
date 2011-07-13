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

    public Entry addChild(Entry parent, Entry child) {
        return ((SimpleEntry)parent).addChild(child);
    }

    public Entry make(Entry entry, String text) {
        SimpleEntry newEntry = (SimpleEntry)make(text, entry.getType());
        final List<Entry> childs = entry.getChilds();
        for (Entry child : childs) {
            newEntry = newEntry.addChild(child);
        }
        return newEntry;
    }

    private long getNextId() {
        return Math.round(Math.random() * 10000);
    }

    public Entry extractFrom(Entry entry, String textToExtract) {
        return make(textToExtract, entry.getType());
    }
    
    public Entry removeChild(Entry parent, Entry child) {
        return ((SimpleEntry)parent).removeChild(child);
    }
}
