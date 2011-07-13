/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */

package fr.nuage.sit.core;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;

/**
 *
 * @author nuage
 */
@Singleton
public class EntryManager {

    private DataManager dataManager;
    private RightManager rightManager;
    private EntryFactory entryFactory;

    @Inject
    public EntryManager(DataManager dataManager, RightManager rightManager, EntryFactory entryFactory) {
        this.dataManager = dataManager;
        this.rightManager = rightManager;
        this.entryFactory = entryFactory;
    }

    public void addEntry(Entry entry, Entry parent, User owner) {
        if (parent == null && entry.getType() != Entry.Type.Project) {
            throw new IllegalArgumentException();
        }
        if (parent != null) {
            dataManager.add(entryFactory.addChild(parent, entry));
        }
        dataManager.add(entry);
        rightManager.entryCreation(owner, entry);
    }

    public void removeEntry(Entry entry, Entry parent) {
        dataManager.add(entryFactory.removeChild(parent, entry));

        dataManager.remove(entry);
        rightManager.entryRemoval(entry);
    }

    public List<Entry> getEntries() {
        return dataManager.getEntries();
    }

    public Entry getEntry(long id) {
        return dataManager.getEntry(id);
    }

    public void answer(Entry parent, String response, User owner) {
        final Entry entry = entryFactory.make(response, Entry.Type.Note);
        addEntry(entry, parent, owner);
    }

    public void extractFromEntry(Entry entry, String textToExtract, Entry parent) {
        if (!entry.getText().contains(textToExtract)) {
            throw new IllegalArgumentException("Entry " + entry + " does not contain " + textToExtract);
        }
        final Entry newEntry = entryFactory.extractFrom(entry, textToExtract);
        Entry oldEntry = entryFactory.make(entry, entry.getText().replace(textToExtract, ""));
        final List<Entry> childs = entry.getChilds();
        for (Entry child : childs) {
            oldEntry = entryFactory.addChild(oldEntry, child);
        }

        Entry newParent = entryFactory.addChild(parent, oldEntry);
        newParent = entryFactory.addChild(newParent, newEntry);

        dataManager.addAll(Lists.newArrayList(newParent, oldEntry, newEntry));
        rightManager.copyRights(entry, newEntry);
        rightManager.copyRights(entry, oldEntry);
        removeEntry(entry, newParent);
    }
}

