/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */

package fr.nuage.sit.gui;

import fr.nuage.sit.gui.cmd.GetProject;
import fr.nuage.sit.gui.cmd.AddProject;
import fr.nuage.sit.gui.cmd.GetEntry;
import fr.nuage.sit.gui.cmd.AddEntry;
import fr.nuage.sit.gui.cmd.Command;
import static com.google.common.collect.Maps.newHashMap;
import com.google.inject.Singleton;
import fr.nuage.sit.gui.cmd.MockData;
import java.util.Map;

/**
 *
 * @author nuage
 */
@Singleton
public class Mapper {

    private Map<String, Class<? extends Command>> urls = newHashMap();

    public Mapper() {
        urls.put("mock", MockData.class);
        urls.put("project/get", GetProject.class);
        urls.put("project/add", AddProject.class);
        urls.put("entry/get", GetEntry.class);
        urls.put("entry/add", AddEntry.class);
    }

    public Class<? extends Command> get(String path) {
        return urls.get(path);
    }
}
