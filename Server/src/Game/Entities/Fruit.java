package Game.Entities;

import Game.Entities.GameObject;

public class Fruit extends GameObject {

    private String type;


    /**
     * @author Sebastian Mora
     * @brief Class constructor
     * @param type fruit type
     * @param liana row position
     */
    public Fruit( String type, Integer liana){
        this.setLiana(liana);
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
