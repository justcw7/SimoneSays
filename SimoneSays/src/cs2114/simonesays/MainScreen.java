package cs2114.simonesays;

import android.widget.CheckBox;
import sofia.widget.ImageView;
import java.util.ArrayList;
import android.widget.TextView;
import sofia.graphics.*;
import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * This class uses an ArrayList with type int objects that represents the
 * sequence of colors that should be repeated by the user. This class uses the
 * GUI design and implements the MVC pattern to interact with the user in
 * representing the game Simone Says.
 *
 * @author Christina Olk (colk)
 * @author Chase Whitt (justcw7)
 * @author Ian McCormack (ianzm9)
 * @version 2013.10.15
 */

public class MainScreen
    extends ShapeScreen
{
    private ShapeView          shapeView;
    private TextView           stat1;
    private TextView           stat2;
    private TextView           name;
    private TextView           stats;
    private SimoneBlock[][]    grid;
    private Model              model;
    private int                highScore;
    private int                currentScore;
    private ArrayList<Integer> inputNeed;
    private ImageView          imageView1;
    private CheckBox           hardMode;
    private boolean            disableShapeView;


    /**
     * Initializes the class and sets up the screen.
     */
    public void initialize()
    {
        // initialize all of the fields
        inputNeed = new ArrayList<Integer>();
        model = new Model();
        highScore = 0;
        currentScore = 0;
        disableShapeView = false;
        stat1.setText("Current Score: " + currentScore);
        stat2.setText("High Score: " + highScore);
        stats.setText("Statistics");
        stats.setEnabled(false);
        stat1.setEnabled(false);
        stat2.setEnabled(false);
        name.setText("Simone Says");

        // makes the four blocks appear on the shapeView
        grid = new SimoneBlock[2][2];
        float w = getShapeView().getWidth() / 2;
        float h = getShapeView().getHeight() / 2;
        int locationCounter = 0;
        for (int i = 0; i < 2; i++)
        {
            for (int j = 1; j < 3; j++)
            {
                locationCounter++;
                SimoneBlock tile =
                    new SimoneBlock((i) * w, (j - 1) * h, (i) * w + w, (j - 1)
                        * h + h, (locationCounter));

                if (i + j == 1)
                {
                    tile.setFillColor(Color.yellow);
                }
                if (i == 0 && j == 2)
                {
                    tile.setFillColor(Color.blue);
                }
                if (i == 1 && j == 1)
                {
                    tile.setFillColor(Color.red);
                }
                if (i + j == 3)
                {
                    tile.setFillColor(Color.green);
                }
                shapeView.add(tile);
                grid[i][j - 1] = tile;
            }
        }

        // sets the image in the center of the shapeView to a custom image
        imageView1.setImageResource(R.drawable.simonesaysimage);

        // has the application begin automatically when initialized
        inputNeed.add(model.getNextNumber(4));
        this.playSequence();

    }


    // ----------------------------------------------------------
    /**
     * Starts a new game for the user.
     */
    public void startGameClicked()
    {
        disableShapeView = false;
        // adjusts the difficulty of the game when hardMode is checked
        if (hardMode.isChecked() && grid[0][0].getSpeed() == 300)
        {
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    grid[i][j].setSpeed(100);
                    grid[i][j].setDuration(100);
                }
            }
        }
        if (!hardMode.isChecked() && grid[0][0].getSpeed() == 100)
        {
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    grid[i][j].setSpeed(300);
                    grid[i][j].setDuration(300);
                }
            }
        }

        // starts the game by adding the first number to the sequence
        inputNeed.add(model.getNextNumber(4));
        // and then lighting up the number in the model
        this.playSequence();
    }


    // ----------------------------------------------------------
    /**
     * Restarts the current game. ((******Keep in mind that we should basically
     * just make a new game because if we restart the current game that means it
     * has the same sequence... we don't want that...****))
     */
    public void restartClicked()
    {
        model.restart();
        inputNeed.clear();
        currentScore = 0;
        name.setText("Simone Says");
        this.update();
        this.startGameClicked();
    }


    // ----------------------------------------------------------
    /**
     * Performs certain actions when a position on the screen is touched.
     *
     * @param x
     *            the x-coordinate of the position touched
     * @param y
     *            the y-coordinate of the position touched
     */
    public void onTouchDown(float x, float y)
    {
        // makes it so the user cannot hit the shapeView while the sequence is
        // going
        shapeView.setEnabled(false);
        int needed = 0;

        // gets the block that is being clicked
        SimoneBlock block =
            getShapes().locatedAt(x, y).withClass(SimoneBlock.class).front();

        int location = block.getLoc();

        // lights up the block that the user presses by darkening the color
        if (!disableShapeView)
        {
            block.lightUp1();
        }

        // checks if the block pressed is null
        if (block != null)
        {
            // if the sequence needed is not empty then removed and stores the
            // input needed
            if (!inputNeed.isEmpty())
            {
                needed = inputNeed.remove(0);
            }

            // checks if the location pressed is what needed to be pressed
            if (location == needed)
            {
                // if the sequence is done with no error, advances to the next
                // level
                if (inputNeed.isEmpty())
                {
                    nextLevel();
                }
            }
            // otherwise the user got a game over
            else
            {
                gameOver();
            }
        }
        // re-enables the shapeView for the user to interact again
        shapeView.setEnabled(true);
    }


    // ----------------------------------------------------------
    /**
     * Goes to the next level by adding one more color to the sequence.
     */
    public void nextLevel()
    {
        model.getNextNumber(4);
        inputNeed.clear();
        for (int i = 0; i < model.getList().size(); i++)
        {
            inputNeed.add(model.getList().get(i));
        }
        currentScore++;
        update();
        playSequence();

    }


    /**
     * Updates the screen when the game is over; the player loses.
     */
    public void gameOver()
    {
        name.setText("Game Over");
        disableShapeView = true;
        shapeView.setEnabled(false);
    }


    /**
     * Updates the screen accordingly.
     */
    public void update()
    {
        // updates the current score
        stat1.setText("Current Score: " + currentScore);

        // updates the highscore if it is greater than the currentscore
        if (currentScore > highScore)
        {
            highScore = currentScore;
        }

        // sets the highscore to the new highscore
        stat2.setText("High Score: " + highScore);
    }


    /**
     * plays the current sequence in the model
     */
    public void playSequence()
    {
        shapeView.setEnabled(false);

        // lights up the color that corresponds to the sequence
        for (int i = 0; i < model.getList().size(); i++)
        {
            int played = model.getList().get(i);
            if (played == 1)
            {
                grid[0][0].lightUp();
            }
            if (played == 2)
            {
                grid[0][1].lightUp();
            }
            if (played == 3)
            {
                grid[1][0].lightUp();
            }
            if (played == 4)
            {
                grid[1][1].lightUp();
            }

        }

        // re-enables the shapeView for the user to interact with
        shapeView.setEnabled(true);
    }


    // ----------------------------------------------------------
    /**
     * Gets the model.
     *
     * @return the model.
     */
    public Model getModel()
    {
        return model;
    }

}
