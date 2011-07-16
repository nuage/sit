/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */

package fr.nuage.sit.gui;

import static com.google.common.collect.Maps.newHashMap;
import com.google.inject.Singleton;
import java.util.Map;

/**
 *
 * @author nuage
 */
@Singleton
public class Mapper {

    private Map<String, Class<? extends Command>> urls = newHashMap();

    public Mapper() {
//        urls.put("projects/get", GetProjects.class);
//        urls.put("projects/new", NewProject.class);
//        urls.put("entry/get", GetEntry.class);
        urls.put("entry/add", AddEntry.class);
    }

    public Class<? extends Command> get(String path) {
        return urls.get(path);
    }
}
