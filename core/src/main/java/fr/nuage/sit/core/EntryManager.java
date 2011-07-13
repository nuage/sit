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
            dataManager.update(parent.addChild(entry.getId()));
        }
        dataManager.update(entry);
        rightManager.entryCreation(owner, entry);
    }

    public void removeEntry(Entry entry, Entry parent) {
        dataManager.update(parent.removeChild(entry.getId()));

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
        final Entry entry = entryFactory.make(response, Entry.Type.Note, parent == null ? null : parent.getId());
        addEntry(entry, parent, owner);
    }

    public void extractFromEntry(Entry entry, String textToExtract, Entry parent) {
        if (!entry.getText().contains(textToExtract)) {
            throw new IllegalArgumentException("Entry " + entry + " does not contain " + textToExtract);
        }
        final Long parentId = parent == null ? null : parent.getId();
        final Entry newEntry = entryFactory.extractFrom(entry, textToExtract, parentId);
        parent = parent.addChild(newEntry.getId());
        
        entry = entry.updateText(entry.getText().replace(textToExtract, ""));

        dataManager.updateAll(Lists.newArrayList(parent, entry, newEntry));
        rightManager.copyRights(entry, newEntry);
    }
}

