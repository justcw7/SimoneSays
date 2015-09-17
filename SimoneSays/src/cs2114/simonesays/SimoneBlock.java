package cs2114.simonesays;

import android.media.ToneGenerator;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;

/**
 * The SimoneBlock we will use for the graphical layout.
 *
 * @author Chase Whitt (justcw7)
 * @author Christina Olk (colk)
 * @author Ian McCormack (ianzm9)
 * @version 10.10.2013
 */
public class SimoneBlock
    extends RectangleShape
{
    private int                  location;
    private static ToneGenerator sound2;
    private int                  duration;
    private int                  speed;


    /**
     * Creates a Simone block.
     *
     * @param a
     *            top left x-coordinate
     * @param b
     *            top-left y-coordinate
     * @param c
     *            bottom-right x-coordinate
     * @param d
     *            bottom-right y-coordinate
     * @param loc
     *            the numbered location
     */
    public SimoneBlock(float a, float b, float c, float d, int loc)
    {
        super(a, b, c, d);
        sound2 = new ToneGenerator(0, 99);
        location = loc;
        speed = 300;
        duration = 300;
    }


    /**
     * Plays a lighten animation for when the application presses a color in the
     * sequence. It also adds sounds to the corresponding color.
     */
    public void lightUp()
    {

        // adds sound to the corresponding color
        sounds();

        Color original = this.getFillColor();

        // brightens the color to white
        this.setFillColor(Color.white);

        // waits
        try
        {
            Thread.sleep(speed);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        // darkens the color back to its original color
        this.setFillColor(original);

        // waits
        try
        {
            Thread.sleep(speed);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

    }


    /**
     * Plays a lighten animation for when the user presses a color in the
     * sequence. It also adds sounds to the corresponding color.
     */
    public void lightUp1()
    {

        // adds sound to the corresponding color
        sounds();

        Color original = this.getFillColor();

        // darkens the color to gray to indicate the color being pressed
        this.setFillColor(Color.gray);

        // waits
        try
        {
            Thread.sleep(speed);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        // brightens the color back to its original color
        this.setFillColor(original);

        // waits
        try
        {
            Thread.sleep(speed);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }


    /**
     * Gets the location.
     *
     * @return returns the location of the object
     */
    public int getLoc()
    {
        return location;
    }


    /**
     * Sets the speed
     *
     * @param x
     *            the number of the speed to be changed.
     */
    public void setSpeed(int x)
    {
        speed = x;
    }


    /**
     * Returns the speed
     *
     * @return the speed.
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * Sets the duration of the sound
     *
     * @param x
     *            the number of the speed to be changed.
     */
    public void setDuration(int x)
    {
        duration = x;
    }


    /**
     * Returns the speed
     *
     * @return the speed.
     */
    public int getDuration()
    {
        return duration;
    }



    /**
     * Creates a sound for each block
     *
     * @return the numbered sound it returned
     */
    public int sounds()
    {
        switch (location)
        {
            case 1:
                sound2.startTone(ToneGenerator.TONE_DTMF_0, duration);
                return 1;
            case 2:
                sound2.startTone(ToneGenerator.TONE_DTMF_1, duration);
                return 2;
            case 3:
                sound2.startTone(ToneGenerator.TONE_DTMF_2, duration);
                return 3;
            case 4:
                sound2.startTone(ToneGenerator.TONE_DTMF_3, duration);
                return 4;
            default:
                sound2.startTone(ToneGenerator.TONE_DTMF_0, duration);
                return 0;
        }
    }

}
