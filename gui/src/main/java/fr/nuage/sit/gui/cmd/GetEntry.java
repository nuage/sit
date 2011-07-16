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
import java.util.List;
import java.util.Map;

/**
 *
 * @author nuage
 */
@Singleton
public class GetEntry extends Command {

    private EntryManager entryManager;

    @Inject
    public GetEntry(EntryManager entryManager) {
        this.entryManager = entryManager;
    }

    @Override
    protected Map<String, Object> response(Map<String, String> params, Long user) {
        final List<Entry> entries = entryManager.getEntries();

        Map<String, Object> data = newHashMap();
        data.put("entries", entries);

        return data;
    }

    @Override
    protected String view() {
        return "entry/get.ftl";
    }
}
