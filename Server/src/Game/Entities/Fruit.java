package Game.Entities;

public class Fruit extends GameObject {

    private String type;


    /**
     * @author Sebastian Mora
     * @brief Class constructor
     * @param type fruit type
     * @param row row position
     * @param column row position
     */
    public Fruit( String type, Integer row, Integer column){
        this.setRow(row);
        this.setColumn(column);
        this.type = type;
    }


    /**
     * @author Sebastian Mora
     * @brief Returns fruit type
     * @return fruit type
     */
    public String getType() {
        return type;
    }


    /**
     * @author Sebastian Mora
     * @bbrief Sets fruit type
     * @param type gruit type
     */
    public void setType(String type) {
        this.type = type;
    }

}
