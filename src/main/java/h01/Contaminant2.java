package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;
import h01.template.Contaminant;
import h01.template.TickBased;
import h01.template.Utils;
import org.tudalgo.algoutils.student.Student;

/**
 * A {@link Contaminant}-{@link Robot} that moves in a predefined way and contaminates the floor.
 */
public class Contaminant2 extends Robot implements Contaminant, TickBased {

    /**
     * Creates a new {@link Contaminant2}.
     *
     * @param x             the initial x coordinate of the robot
     * @param y             the initial y coordinate of the robot
     * @param direction     the initial direction of the robot
     * @param numberOfCoins the initial number of coins of the robot
     */
    public Contaminant2(final int x, final int y, final Direction direction, final int numberOfCoins) {
        super(x, y, direction, numberOfCoins, RobotFamily.SQUARE_AQUA);
    }

    @Override
    public int getUpdateDelay() {
        return 8;
    }

    @Override
    public void doMove() {
        // TODO: H2.2
        if (getNumberOfCoins() == 0){
            turnOff();
            return;
        }
        if (isTurnedOff()){
            return;
        }
        // lay 2 coins | legt 2 Münzen | așează 2 monezi | 2枚のコインに置く
        if (!isOnACoin() || Utils.getCoinAmount(getX(), getY()) < 2){
            for(int i = 0; i < 2; i++){
                if(!hasAnyCoins() || Utils.getCoinAmount(getX(), getY()) >= 2){
                    break;
                }
                putCoin();
            }
        }

        // get valid paths | erhält gültige Pfade | obține căi valide | 有効なパスを取得する
        Direction left = null;
        Direction back = null;
        Direction right = null;
        Direction front = null;
        int validPathAmount = 0;
        for (int i = 0; i < 4; i++){
            turnLeft();
            if (isFrontClear()){
                if (i==0){
                    left = getDirection();
                } else if (i == 1) {
                    back = getDirection();
                } else if (i == 2) {
                    right = getDirection();
                } else if (i == 3) {
                    front = getDirection();
                }
            }

            // check if there are any paths
            // prüfen, ob es Pfade gibt
            // verifică dacă există căi de acces
            // パスがあるかチェックする
            if (validPathAmount == 0) {
                return;
            }

            // orient on left wall
            // an der linken Wand orientieren
            // orientare pe peretele din stânga
            // 左の壁
            Direction direction = null;
            if (left != null){
                direction = left;
            } else if (front != null) {
                direction = front;
            } else if (right != null) {
                direction = right;
            } else if (back != null) {
                direction = back;
            }

            while (getDirection() != direction){
                turnLeft();
            }
            move();
        }
        Student.crash("H2.2 - remove if implemented");
    }
}
