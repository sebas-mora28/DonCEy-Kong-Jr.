package Game.Entities;

public class RedCrocodile extends GameObject implements Cocodrile{


    /**
     * @author Sebastian Mora
     * @brief Class constructor
     * @param row liana position
     */
    public RedCrocodile(Integer row, Integer column){
        this.setSpeed(1);
        this.updatePosition(0,0);
        this.setRow(row);
        this.setColumn(column);
        this.setType("red");
    }

    @Override
    public void move() {

    }
}
