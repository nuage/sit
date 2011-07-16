/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.nuage.sit.gui.cmd;

import static com.google.common.collect.Maps.newHashMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.nuage.sit.core.Entry;
import fr.nuage.sit.core.EntryFactory;
import fr.nuage.sit.core.EntryManager;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nuage
 */
@Singleton
public class MockData extends Command {
    
    private EntryManager entryManager;

    @Inject
    public MockData(EntryManager entryManager) {
        this.entryManager = entryManager;
    }

    @Override
    protected Map<String, Object> response(Map<String, String> params, Long user) {
        final EntryFactory entryFactory = new EntryFactory();
        
        final Entry project = entryFactory.make("MyProject", Entry.Type.Project);
        final long root = project.getId();
        
        entryManager.newProject(project, user);
        final Entry issue1 = entryFactory.make("Issue 1", Entry.Type.Issue);
        entryManager.add(issue1, root, user);
        entryManager.add(entryFactory.make("Issue 1 Comment 1", Entry.Type.Note), issue1.getId(), user);
        entryManager.add(entryFactory.make("Issue 1 Comment 2", Entry.Type.Note), issue1.getId(), user);
        entryManager.add(entryFactory.make("Issue 1 Comment 3", Entry.Type.Note), issue1.getId(), user);
        final Entry issue1SubIssue1 = entryFactory.make("Issue 1 Sub Issue 1", Entry.Type.Issue);
        entryManager.add(issue1SubIssue1, issue1.getId(), user);
        entryManager.add(entryFactory.make("Issue 1 Sub Issue 1 Comment 1", Entry.Type.Note), issue1SubIssue1.getId(), user);
        entryManager.add(entryFactory.make("Issue 1 Sub Issue 1 Comment 2", Entry.Type.Note), issue1SubIssue1.getId(), user);
        
        final Entry issue2= entryFactory.make("Issue 2", Entry.Type.Issue);
        entryManager.add(issue2, root, user);
        entryManager.add(entryFactory.make("Issue 2 Comment 1", Entry.Type.Note), issue2.getId(), user);
        entryManager.add(entryFactory.make("Issue 2 Comment 2", Entry.Type.Note), issue2.getId(), user);
        
        entryManager.add(entryFactory.make("Issue 3", Entry.Type.Issue), root, user);
        
        final Entry issue4 = entryFactory.make("Issue 4", Entry.Type.Issue);
        entryManager.add(issue4, root, user);
        entryManager.add(entryFactory.make("Issue 4 Comment 1", Entry.Type.Note), issue4.getId(), user);
        entryManager.add(entryFactory.make("Issue 4 Comment 2", Entry.Type.Note), issue4.getId(), user);
        entryManager.add(entryFactory.make("Issue 4 Comment 3", Entry.Type.Note), issue4.getId(), user);
        entryManager.add(entryFactory.make("Issue 4 Comment 4", Entry.Type.Note), issue4.getId(), user);
        entryManager.add(entryFactory.make("Issue 4 Comment 5", Entry.Type.Note), issue4.getId(), user);
        entryManager.add(entryFactory.make("Issue 4 Comment 6", Entry.Type.Note), issue4.getId(), user);
        
        final List<Entry> projects = entryManager.getProjects();
        Map<String, Object> data = newHashMap();
        data.put("projects", projects);
        return data;
    }

    @Override
    protected String view() {
        return "project/get";
    }
}
