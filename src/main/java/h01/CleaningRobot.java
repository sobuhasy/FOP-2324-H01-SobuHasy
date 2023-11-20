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
        if(shouldPutCoins && !isOnACoin() && hasAnyCoins()){
            putCoin();
        }
        if (shouldPickCoins && isOnACoin() && getNumberOfCoins() < GameConstants.CLEANER_CAPACITY){
            pickCoin();
        }
        if (direction >= 0 && direction < 4){
            // Direction 0 | Richtung 0 | Direcția 0 | 方向０
            int dx = 0;
            int dy = 1;
            // rotation of the direction by 90 degrees to right
            // Drehung der Richtung um 90 Grad nach rechts
            // rotirea direcției cu 90 de grade spre dreapta
            // 右90度回転
            for (int i=0; i < direction; i++){
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

        }
        Student.crash("H1 - remove if implemented");
    }
}
