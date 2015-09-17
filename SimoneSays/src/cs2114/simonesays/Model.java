package cs2114.simonesays;

import java.util.ArrayList;

/**
 * // -------------------------------------------------------------------------
 * /** This is the model that is responsible for changing the sequence.
 *
 * @author Chase Whitt (justcw7)
 * @author Christina Olk (colk)
 * @author Ian McCormack (ianzm9)
 * @version 10.10.2013
 */
public class Model
{

    private ArrayList<Integer> sequence;
    private Random             numGen;


    // ----------------------------------------------------------
    /**
     * Create a new Model object.
     */
    public Model()
    {
        numGen = new Random();
        sequence = new ArrayList<Integer>();
    }


    // ----------------------------------------------------------
    /**
     * Gets the sequence ArrayList.
     *
     * @return The ArrayList for the model
     */
    public ArrayList<Integer> getList()
    {

        return sequence;
    }


    // ----------------------------------------------------------
    /**
     * Adds a value to the top of the ArrayList.
     *
     * @param x
     *            is one of the color identifiers
     */
    public void addSequence(int x)
    {
        sequence.add(x);
    }


    // ----------------------------------------------------------
    /**
     * Gets the last value of the ArrayList.
     *
     * @return the last value of the ArrayList.
     */
    public int getLast()
    {
        return (sequence.size() - 1);
    }


    /**
     * Creates a random number that will be used to activate a random color as
     * the next item in the sequence.
     *
     * @param temp
     *            the max number that can be generated
     * @return the number generated
     */
    public int getNextNumber(int temp)
    {
        int y = numGen.nextInt(1, temp);
        System.out.println(y);
        sequence.add(y);
        return y;

    }


    /**
     * Restarts the game by clearing the sequence.
     */
    public void restart()
    {
        sequence.clear();
    }
}
