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

    public void newProject(Entry project, long owner) {
        dataManager.update(project);
        rightManager.grant(owner, project.getId(), RightManager.Permission.Delete);
    }

    public void add(Entry entry, long parent, long owner) {
        dataManager.update(get(parent).addChild(entry.getId()));
        dataManager.update(entry.setParent(parent));
        rightManager.grant(owner, entry.getId(), RightManager.Permission.Delete);
    }

    public void remove(long entry, long parent) {
        dataManager.update(get(parent).removeChild(entry));

        dataManager.remove(entry);
        rightManager.remove(entry);
    }

    public List<Entry> getEntries() {
        return dataManager.getEntries();
    }

    public Entry get(long id) {
        return dataManager.getEntry(id);
    }

    public void answer(long parent, String response, long owner) {
        final Entry entry = entryFactory.make(response, Entry.Type.Note, parent);
        add(entry, parent, owner);
    }

    public void extract(long entryId, String textToExtract) {
        Entry entry = get(entryId);
        if (!entry.getText().contains(textToExtract)) {
            throw new IllegalArgumentException("Entry " + entry + " does not contain " + textToExtract);
        }
        final Long parentId = entry.getParent();
        final Entry newEntry = entryFactory.extractFrom(entry, textToExtract, parentId);
        Entry parent = get(parentId);
        parent = parent.addChild(newEntry.getId());
        
        entry = entry.updateText(entry.getText().replace(textToExtract, ""));

        dataManager.updateAll(Lists.newArrayList(parent, entry, newEntry));
        rightManager.copy(entryId, newEntry.getId());
    }
}

