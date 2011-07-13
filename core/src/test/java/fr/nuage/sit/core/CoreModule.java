/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */

package fr.nuage.sit.core;

import com.google.inject.AbstractModule;

/**
 *
 * @author nuage
 */
public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DataManager.class).to(SimpleDataManager.class);
        bind(RightManager.class).to(SimpleRightManager.class);
    }

}
