/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.nuage.sit.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import fr.nuage.sit.core.UserRight.Permission;
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
     * Test of entryCreation method, of class SimpleRightManager.
     */
    public void testEntryCreation() {
        System.out.println("entryCreation");
        User owner = new User(1, "Quentin");
        Entry entry = null;
        SimpleRightManager instance = new SimpleRightManager();
        boolean expResult = false;
        boolean result = instance.entryCreation(owner, entry);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of entryRemoval method, of class SimpleRightManager.
     */
    public void testEntryRemoval() {
        System.out.println("entryRemoval");
        Entry entry = null;
        SimpleRightManager instance = new SimpleRightManager();
        boolean expResult = false;
        boolean result = instance.entryRemoval(entry);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copyRights method, of class SimpleRightManager.
     */
    public void testCopyRights() {
        System.out.println("copyRights");
        Entry source = null;
        Entry dest = null;
        SimpleRightManager instance = new SimpleRightManager();
        boolean expResult = false;
        boolean result = instance.copyRights(source, dest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRight method, of class SimpleRightManager.
     */
    public void testGetRight() {
        System.out.println("getRight");
        User user = null;
        Entry entry = null;
        SimpleRightManager instance = new SimpleRightManager();
        UserRight expResult = null;
        UserRight result = instance.getRight(user, entry);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of can method, of class SimpleRightManager.
     */
    public void testCan() {
        System.out.println("can");
        User user = null;
        Entry entry = null;
        Permission permission = null;
        SimpleRightManager instance = new SimpleRightManager();
        boolean expResult = false;
        boolean result = instance.can(user, entry, permission);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
