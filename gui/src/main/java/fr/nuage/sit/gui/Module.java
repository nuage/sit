/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */
package fr.nuage.sit.gui;

import com.google.inject.AbstractModule;
import fr.nuage.sit.core.DataManager;
import fr.nuage.sit.core.RightManager;
import fr.nuage.sit.core.SimpleDataManager;
import fr.nuage.sit.core.SimpleRightManager;

/**
 *
 * @author nuage
 */
public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(DataManager.class).to(SimpleDataManager.class);
        bind(RightManager.class).to(SimpleRightManager.class);
    }
}
