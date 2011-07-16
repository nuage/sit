/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */

package fr.nuage.sit.core;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import static com.google.common.collect.Lists.newArrayList;
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

    public boolean newProject(Entry project, long owner) {
        dataManager.update(project);
        rightManager.grant(owner, project.getId(), RightManager.Permission.Delete);
        return true;
    }

    public boolean add(Entry entry, long parent, long owner) {
        dataManager.update(get(parent).addChild(entry.getId()));
        dataManager.update(entry.setParent(parent));
        rightManager.grant(owner, entry.getId(), RightManager.Permission.Delete);
        return true;
    }

    public boolean remove(long entry, long parent) {
        dataManager.update(get(parent).removeChild(entry));

        dataManager.remove(entry);
        rightManager.remove(entry);
        return true;
    }

    public List<Entry> getEntries() {
        return dataManager.getEntries();
    }
    
    public List<Entry> getProjects() {
        final List<Entry> entries = getEntries();
        return ImmutableList.copyOf(Collections2.filter(entries, new Predicate<Entry>() {

            @Override
            public boolean apply(Entry t) {
                return t.getParent() == null;
            }
            
        }));
    }

    public LoadedEntry getLoadedEntry(long id) {
        final Entry root = get(id);
        final List<Long> childs = root.getChildsId();
        final List<LoadedEntry> lChilds = newArrayList();
        if (childs != null && !childs.isEmpty()) {
            for (Long child : childs) {
                lChilds.add(getLoadedEntry(child));
            }
        }
        return entryFactory.make(root, lChilds);
    }
    
    public Entry get(long id) {
        return dataManager.getEntry(id);
    }

    public boolean answer(long parent, String response, long owner) {
        final Entry entry = entryFactory.make(response, Entry.Type.Note, parent);
        add(entry, parent, owner);
        return true;
    }

    public boolean move(long entry, long newParent) {
        Entry oldParent = get(get(entry).getParent());
        oldParent = oldParent.removeChild(entry);

        dataManager.update(oldParent);
        dataManager.update(get(newParent).addChild(entry));
        return true;
    }

    public boolean extract(long entryId, String textToExtract) {
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
        
        return true;
    }
}

