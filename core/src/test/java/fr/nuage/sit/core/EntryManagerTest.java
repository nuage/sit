/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.nuage.sit.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.List;
import junit.framework.TestCase;
import org.mockito.Mockito;

/**
 *
 * @author nuage
 */
public class EntryManagerTest extends TestCase {

    private User user;
    private EntryManager entryManager;
    private static final long ROOT_ID = 1;

    public EntryManagerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        Injector injector = Guice.createInjector(new CoreModule());

        user = Mockito.mock(User.class);
        Mockito.when(user.getId()).thenReturn(100l);
        Mockito.when(user.getName()).thenReturn("Quentin");

        entryManager = injector.getInstance(EntryManager.class);
        Entry project = new SimpleEntry(ROOT_ID, "ProjetTest", Entry.Type.Project, null);
        entryManager.newProject(project, user.getId());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of addEntry method, of class EntryManager.
     */
    public void testAddEntry() {
        System.out.println("addEntry");
        Entry entry = new SimpleEntry(2, "text", Entry.Type.Note, null);
        entryManager.add(entry, ROOT_ID, user.getId());
        assertTrue(entryManager.get(ROOT_ID).getChilds().size() == 1);
    }

    /**
     * Test of removeEntry method, of class EntryManager.
     */
    public void testRemoveEntry() {
        System.out.println("removeEntry");
        Entry entry = new SimpleEntry(2, "text", Entry.Type.Note, null);
        entryManager.add(entry, ROOT_ID, user.getId());
        entryManager.remove(entry.getId(), ROOT_ID);
        assertTrue(entryManager.get(ROOT_ID).getChilds().isEmpty());
    }

    /**
     * Test of extract method, of class EntryManager.
     */
    public void testExtractFromEntry() {
        System.out.println("extractFromEntry");
        String text1 = "[Issue 1]";
        String text2 = "[Issue 2]";
        Entry entry = new SimpleEntry(2, text1 + text2, Entry.Type.Note, null);
        entryManager.add(entry, ROOT_ID, user.getId());
        entryManager.extract(entry.getId(), text2);
        assertEquals(2, entryManager.get(ROOT_ID).getChilds().size());
        
        List<Long> childs = entryManager.get(ROOT_ID).getChilds();
        Entry child1 = entryManager.get(childs.get(0));
        assertEquals(text1, child1.getText());
        Entry child2 = entryManager.get(childs.get(1));
        assertEquals(text2, child2.getText());
    }

}

