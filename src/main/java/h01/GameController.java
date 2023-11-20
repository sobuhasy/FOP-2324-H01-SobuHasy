
package h01;

import fopbot.Robot;
import h01.template.GameControllerBase;
import org.tudalgo.algoutils.student.Student;

/**
 * A {@link GameController} controls the game loop and the {@link Robot}s and checks the win condition.
 */
public class GameController extends GameControllerBase {

    /**
     * Creates a new {@link GameControllerBase}.
     */
    public GameController() {
        setup();
    }

    @Override
    public void checkWinCondition() {
        // TODO: H3
        // If all offenders are turned off, the game is won
        if(getContaminant1().isTurnedOff() && getContaminant2().isTurnedOff()){
            System.out.println("The cleaning robot already won!");
            stopGame();
        }


        // If more than 50
        Student.crash("H3 - remove if implemented");
    }
}
