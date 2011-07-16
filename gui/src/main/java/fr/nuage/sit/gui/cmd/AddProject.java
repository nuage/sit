/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */
package fr.nuage.sit.gui.cmd;

import static com.google.common.collect.Maps.newHashMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.nuage.sit.core.Entry;
import fr.nuage.sit.core.Entry.Type;
import fr.nuage.sit.core.EntryFactory;
import fr.nuage.sit.core.EntryManager;
import java.util.Map;

/**
 *
 * @author nuage
 */
@Singleton
public class AddProject extends Command {

    private EntryManager entryManager;

    @Inject
    public AddProject(EntryManager entryManager) {
        this.entryManager = entryManager;
    }

    @Override
    protected Map<String, Object> response(Map<String, String> params, Long user) {
        final EntryFactory entryFactory = new EntryFactory();
        final String text = getS(params, "text");
        final Entry entry = entryFactory.make(text, Type.Project);
        boolean success = entryManager.newProject(entry, user);

        Map<String, Object> data = newHashMap();
        data.put("success", success);
        data.put("project", entry);

        return data;
    }

    @Override
    protected String view() {
        return "project/add";
    }
}
