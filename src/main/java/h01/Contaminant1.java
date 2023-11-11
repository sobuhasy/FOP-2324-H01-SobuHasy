package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;
import h01.template.Contaminant;
import h01.template.GameConstants;
import h01.template.TickBased;
import h01.template.Utils;
import org.tudalgo.algoutils.student.Student;

/**
 * A {@link Contaminant}-{@link Robot} that moves randomly and contaminates the floor.
 */
public class Contaminant1 extends Robot implements Contaminant, TickBased {

    /**
     * Creates a new {@link Contaminant1}.
     *
     * @param x             the initial x coordinate of the robot
     * @param y             the initial y coordinate of the robot
     * @param direction     the initial direction of the robot
     * @param numberOfCoins the initial number of coins of the robot
     */
    public Contaminant1(final int x, final int y, final Direction direction, final int numberOfCoins) {
        super(x, y, direction, numberOfCoins, RobotFamily.SQUARE_ORANGE);
    }

    @Override
    public int getUpdateDelay() {
        return 10;
    }

    @Override
    public void doMove() {
        // TODO: H2.1
        if (getNumberOfCoins() == 0){
            turnOff();
            return;
        }
        if (isTurnedOff()){
            return;
        }

        // Lay random amount of coins between 1 and 5
        // Legt eine zufällige Anzahl von Münzen zwischen 1 und 5
        // Așează o sumă aleatorie de monede între 1 și 5
        // 1～5枚のコインをランダムに置く
        if (!isOnACoin() || Utils.getCoinAmount(getX(), getY()) < 20){
            final int quantity = Utils.getRandomInteger(
                GameConstants.CONTAMINANT_ONE_MIN_PUT_COINS,
                GameConstants.CONTAMINANT_ONE_MAX_PUT_COINS
            );
            for (int i = 0; i < quantity; i++){
                if (!hasAnyCoins() || Utils.getCoinAmount(getX(), getY()) >= 20){
                    break;
                }
                putCoin();
            }
        }


        // get valid paths | erhält gültige Pfade | obține căi valide | 有効なパスを取得する
        Direction path0 = null;
        Direction path1 = null;
        Direction path2 = null;
        Direction path3 = null;
        int validPathAmount = 0;

        for (int i = 0; i < 4; i++){
            turnLeft();
            if (isFrontClear()){
                validPathAmount = validPathAmount + 1;
                if (path0 == null){
                    path0 = getDirection();
                } else if (path1 == null){
                    path1 = getDirection();
                } else if (path2 == null){
                    path2 = getDirection();
                } else if (path3 == null){
                    path3 = getDirection();
                }
            }
        }

        // get random path | erhält ein zufälliger Pfad | obține o cale aleatorie | ランダムなパスを取得する
        if (path0 == null && path1 == null && path2 == null && path3 == null){
            return;
        }

        final int randomPathfinder = Utils.getRandomInteger(0, validPathAmount - 1);
        Direction path = null;
        if (randomPathfinder == 0){
            path = path0;
        } else if (randomPathfinder == 1){
            path = path1;
        } else if (randomPathfinder == 2) {
            path = path2;
        } else if (randomPathfinder == 3) {
            path = path3;
        }
        while (getDirection() != path) {
            turnLeft();
        }

        move();

        Student.crash("H2.1 - remove if implemented");
    }
}
