/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.nuage.sit.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import fr.nuage.sit.core.RightManager.Permission;
import junit.framework.TestCase;

/**
 *
 * @author nuage
 */
public class SimpleRightManagerTest extends TestCase {

    private SimpleRightManager srm;

    public SimpleRightManagerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        Injector injector = Guice.createInjector(new CoreModule());
        srm = injector.getInstance(SimpleRightManager.class);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of grant method, of class SimpleRightManager.
     */
    public void testGrant() {
        System.out.println("grant");
        User owner = new User(1, "Quentin");
        final EntryFactory ef = new EntryFactory();
        Entry entry = ef.make("aa", Entry.Type.Note, 1l);
        boolean result = srm.grant(owner, entry, Permission.Delete);
        assertEquals(true, result);
        assertTrue(srm.can(owner, entry, Permission.Delete));
        assertTrue(srm.can(owner, entry, Permission.Modify));
        assertTrue(srm.can(owner, entry, Permission.Read));

        result = srm.grant(owner, entry, Permission.Modify);
        assertEquals(true, result);
        assertFalse(srm.can(owner, entry, Permission.Delete));
        assertTrue(srm.can(owner, entry, Permission.Modify));
        assertTrue(srm.can(owner, entry, Permission.Read));

        result = srm.grant(owner, entry, Permission.Read);
        assertEquals(true, result);
        assertFalse(srm.can(owner, entry, Permission.Delete));
        assertFalse(srm.can(owner, entry, Permission.Modify));
        assertTrue(srm.can(owner, entry, Permission.Read));
    }

    /**
     * Test of remove method, of class SimpleRightManager.
     */
    public void testRemove() {
        System.out.println("remove");
        User owner = new User(1, "Quentin");
        final EntryFactory ef = new EntryFactory();
        Entry entry = ef.make("aa", Entry.Type.Note, 1l);
        boolean result = srm.grant(owner, entry, Permission.Delete);
        assertEquals(true, result);

        result = srm.remove(entry);
        assertEquals(true, result);

        assertFalse(srm.can(owner, entry, Permission.Delete));
        assertFalse(srm.can(owner, entry, Permission.Modify));
        assertFalse(srm.can(owner, entry, Permission.Read));
    }

    /**
     * Test of copyRights method, of class SimpleRightManager.
     */
    public void testCopyRights() {
        System.out.println("copyRights");
        User owner = new User(1, "Quentin");
        final EntryFactory ef = new EntryFactory();
        Entry source = ef.make("aa", Entry.Type.Note, 1l);
        boolean result = srm.grant(owner, source, Permission.Delete);
        assertEquals(true, result);

        Entry dest = ef.make("bb", Entry.Type.Note, 1l);

        result = srm.copy(source, dest);
        assertEquals(true, result);

        assertTrue(srm.can(owner, dest, Permission.Delete));
        assertTrue(srm.can(owner, dest, Permission.Modify));
        assertTrue(srm.can(owner, dest, Permission.Read));
    }
}
