/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */

package fr.nuage.sit.core;

import com.google.common.collect.ImmutableList;
import java.util.List;
import com.google.common.collect.Lists;
/**
 *
 * @author nuage
 */
public class SimpleEntry implements Entry {

    private long id;
    private List<Entry> childs;
    private String text;
    private Type type;
    private Entry parent;

    public SimpleEntry(long id, String text, Type type, Entry parent) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.parent = parent;
        this.childs = Lists.newArrayList();
    }

    private SimpleEntry(Entry e) {
        id = e.getId();
        text = e.getText();
        type = e.getType();
        childs = Lists.newArrayList(e.getChilds());
    }

    public SimpleEntry addChild(Entry child) {
        SimpleEntry se = new SimpleEntry(this);
        se.childs.add(child);
        return se;
    }

    public SimpleEntry removeChild(Entry child) {
        SimpleEntry se = new SimpleEntry(this);
        se.childs.remove(child);
        return se;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Entry getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "[" + id  + " : " + text + " => " + childs + "]";
    }

    @Override
    public List<Entry> getChilds() {
        return ImmutableList.copyOf(childs);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleEntry other = (SimpleEntry) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
}
