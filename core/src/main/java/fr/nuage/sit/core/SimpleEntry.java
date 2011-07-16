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
    private List<Long> childs;
    private String text;
    private Type type;
    private Long parent;

    public SimpleEntry(long id, String text, Type type, Long parent) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.parent = parent;
        this.childs = Lists.newArrayList();
    }

    protected SimpleEntry(Entry e) {
        id = e.getId();
        text = e.getText();
        type = e.getType();
        parent = e.getParent();
        childs = Lists.newArrayList(e.getChildsId());
    }

    @Override
    public SimpleEntry addChild(long child) {
        SimpleEntry se = new SimpleEntry(this);
        se.childs.add(child);
        return se;
    }

    @Override
    public SimpleEntry removeChild(long child) {
        SimpleEntry se = new SimpleEntry(this);
        se.childs.remove(child);
        return se;
    }

    @Override
    public Entry updateText(String text) {
        SimpleEntry se = new SimpleEntry(this);
        se.text = text;
        return se;
    }

    @Override
    public Entry setParent(long parent) {
        SimpleEntry se = new SimpleEntry(this);
        se.parent = parent;
        return se;
    }

    @Override
    public Entry moveChild(long child, int index) {
        SimpleEntry se = new SimpleEntry(this);
        se.childs.remove(child);
        se.childs.add(index, child);
        return se;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Long getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "[" + id  + " : " + text + " => " + childs + "]";
    }

    @Override
    public List<Long> getChildsId() {
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
