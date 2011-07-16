/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.nuage.sit.core;

import java.util.List;

/**
 *
 * @author Nuage
 */
public interface LoadedEntry extends Entry {
    
    List<LoadedEntry> getChilds();
}
