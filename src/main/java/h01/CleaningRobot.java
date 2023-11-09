package h01;

import fopbot.Direction;
import fopbot.Robot;
import h01.template.Cleaner;
import h01.template.GameConstants;
import h01.template.TickBased;
import org.tudalgo.algoutils.student.Student;

/**
 * A robot that can clean the floor.
 */
public class CleaningRobot extends Robot implements Cleaner, TickBased {

    /**
     * Creates a new {@link CleaningRobot}.
     *
     * @param x             the initial x coordinate of the robot
     * @param y             the initial y coordinate of the robot
     * @param direction     the initial direction of the robot
     * @param numberOfCoins the initial number of coins of the robot
     */
    public CleaningRobot(final int x, final int y, final Direction direction, final int numberOfCoins) {
        super(x, y, direction, numberOfCoins);
    }

    @Override
    public void handleKeyInput(final int direction, final boolean shouldPutCoins, final boolean shouldPickCoins) {
        // TODO: H1
        if(shouldPutCoins && hasAnyCoins()){
            putCoin();
        }
        if (shouldPickCoins && isOnACoin() && getNumberOfCoins() < GameConstants.CLEANER_CAPACITY){
            pickCoin();
        }
        if (direction >= 0 && direction < 4){
            // Direction 0
            int dx = 0;
            int dy = 1;
            // rotation of the direction by 90 degrees to right
            for (int d=0; d < direction; d++){
                final int tmp = dx;
                dx = dy;
                dy = -tmp;
            }
            while (getDirection().getDx() != dx || getDirection().getDy() != dy){
                turnLeft();
            }
            if(isFrontClear()){
                move();
            }
            if (getDirection().getDx() == dx || getDirection().getDy() == dy){
                turnOff();
            }
        }
        Student.crash("H1 - remove if implemented");
    }
}
