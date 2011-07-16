/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */

package fr.nuage.sit.gui;

import fr.nuage.sit.gui.cmd.Command;
import com.google.common.base.Strings;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 *
 * @author nuage
 */
public class CmdFactory {

    private static Injector injector = null;

    public Command get(String path) {
        Command cmd = null;
        if (!Strings.isNullOrEmpty(path)) {
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            Mapper mapper = get().getInstance(Mapper.class);
            Class<? extends Command> cmdClass = mapper.get(path);
            if (cmdClass != null) {
                cmd = get().getInstance(cmdClass);
            }
        }
        return cmd;
    }

    private static Injector get() {
        if (injector == null) {
            injector = Guice.createInjector(new Module());
        }
        return injector;
    }
}
