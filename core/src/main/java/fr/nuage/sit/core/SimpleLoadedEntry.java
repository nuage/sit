/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.nuage.sit.core;

import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 *
 * @author Nuage
 */
public class SimpleLoadedEntry extends SimpleEntry implements LoadedEntry {

    private List<LoadedEntry> childs;
    
    public SimpleLoadedEntry(Entry entry, List<LoadedEntry> childs) {
        super(entry);
        this.childs = ImmutableList.copyOf(childs);
    }

    @Override
    public List<LoadedEntry> getChilds() {
        return childs;
    }
    
}
