package Game.Entities;

public class BlueCrocodile extends GameObject implements Cocodrile {


    /**
     * @author Sebastian Mora
     * @brief Class constructor
     * @param row liana position
     */
    public BlueCrocodile(Integer row, Integer column){
        this.setSpeed(1);
        this.updatePosition(0,0);
        this.setRow(row);
        this.setColumn(column);
        this.setType("blue");
    }

    @Override
    public void move() {

    }
}
