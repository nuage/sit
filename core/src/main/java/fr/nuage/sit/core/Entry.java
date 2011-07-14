/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */

package fr.nuage.sit.core;

import java.util.List;

/**
 *
 * @author nuage
 */
public interface Entry {

    public enum Type {
        Note,
        Issue,
        Project
    }

    long getId();

    String getText();

    Type getType();

    List<Long> getChilds();

    Long getParent();

    Entry addChild(long child);

    Entry removeChild(long child);

    Entry updateText(String text);

    Entry setParent(long parent);
}
