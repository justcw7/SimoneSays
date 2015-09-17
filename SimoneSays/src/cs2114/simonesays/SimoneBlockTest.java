package cs2114.simonesays;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the methods in the SimoneBlock class.
 *
 * @author Ian McCormack (ianzm9)
 * @author Christina Olk (colk)
 * @author Chase Whitt (justcw7)
 * @version 2013.10.15
 */
public class SimoneBlockTest
    extends TestCase
{

    /**
     * Creates a SimoneBlock for testing
     */
    public SimoneBlock testBlock;


    /**
     * Sets up the test class by initializing a SimoneBlock.
     */
    protected void setUp()

    {
        testBlock = new SimoneBlock(2, 2, 2, 2, 1);
    }


// ----------------------------------------------------------
    /**
     * Tests the lightUp() method
     */
// public SimoneBlock(float a, float b, float c, float d, int loc)

    public final void testLightUp()
    {
        testBlock.lightUp();
        assertEquals(testBlock.getLoc(), 1);
    }


    // ----------------------------------------------------------
    /**
     * Tests the lightUp1() method
     */
    public final void testLightUp1()
    {
        testBlock.lightUp1();
        assertEquals(testBlock.getLoc(), 1);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getLoc() method
     */
    public final void testGetLoc()
    {
        assertEquals(testBlock.getLoc(), 1);
    }


    // ----------------------------------------------------------
    /**
     * Tests the getSpeed() method
     */
    public final void testGetSpeed()
    {
        testBlock.setSpeed(2);
        assertEquals(testBlock.getSpeed(), 2);
    }


    // ----------------------------------------------------------
    /**
     * Tests the setSpeed() method.
     */
    public final void testSetSpeed()
    {
        testBlock.setSpeed(2);
        assertEquals(testBlock.getSpeed(), 2);
        testBlock.setSpeed(3);
        assertEquals(testBlock.getSpeed(), 3);
    }


    // ----------------------------------------------------------
    /**
     * Tests the sounds() method.
     */
    public final void testSounds()
    {
        SimoneBlock testBlock1 = new SimoneBlock(2, 2, 2, 2, 1);
        assertEquals(testBlock1.sounds(), 1);
        SimoneBlock testBlock2 = new SimoneBlock(2, 2, 2, 2, 2);
        assertEquals(testBlock2.sounds(), 2);
        SimoneBlock testBlock3 = new SimoneBlock(2, 2, 2, 2, 3);
        assertEquals(testBlock3.sounds(), 3);
        SimoneBlock testBlock4 = new SimoneBlock(2, 2, 2, 2, 4);
        assertEquals(testBlock4.sounds(), 4);
        SimoneBlock testBlockDefault = new SimoneBlock(2, 2, 2, 2, 5);
        assertEquals(testBlockDefault.sounds(), 0);
    }

}
