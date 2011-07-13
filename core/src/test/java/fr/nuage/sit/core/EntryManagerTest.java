/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.nuage.sit.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
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
        Entry project = new SimpleEntry(ROOT_ID, "ProjetTest", Entry.Type.Project);
        entryManager.addEntry(project, null, user);
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
        Entry entry = new SimpleEntry(1, "text", Entry.Type.Note);
        entryManager.addEntry(entry, entryManager.getEntry(ROOT_ID), user);
        assertTrue(entryManager.getEntries().size() == 1);
    }

    /**
     * Test of removeEntry method, of class EntryManager.
     */
    public void testRemoveEntry() {
        System.out.println("removeEntry");
        Entry entry = new SimpleEntry(2, "text", Entry.Type.Note);
        entryManager.addEntry(entry, entryManager.getEntry(ROOT_ID), user);
        entryManager.removeEntry(entry, entryManager.getEntry(ROOT_ID));
        assertTrue(entryManager.getEntry(ROOT_ID).getChilds().isEmpty());
    }

    /**
     * Test of extractFromEntry method, of class EntryManager.
     */
    public void testExtractFromEntry() {
        System.out.println("extractFromEntry");
        String text1 = "[Issue 1]";
        String text2 = "[Issue 2]";
        Entry entry = new SimpleEntry(2, text1 + text2, Entry.Type.Note);
        entryManager.addEntry(entry, entryManager.getEntry(ROOT_ID), user);
        entryManager.extractFromEntry(entry, text2, entryManager.getEntry(ROOT_ID));
        assertEquals(2, entryManager.getEntry(ROOT_ID).getChilds().size());
        assertEquals(text1, entryManager.getEntry(ROOT_ID).getChilds().get(0).getText());
        assertEquals(text2, entryManager.getEntry(ROOT_ID).getChilds().get(1).getText());
    }

}

