package Game.Entities;

public class GameObject {


    private Integer posX;
    private Integer posY;
    private Integer speed;
    private Integer liana;
    private Integer id;
    private String type;





    /**
     * @author Sebastia Mora
     * @brief Returns game object type position
     * @return gmae object type
     */
    public String getType() {
        return type;
    }

    /**
     * @author Sebastian Mora
     * @brief Sets game object row position
     * @param type game object type
     */
    public void setType(String type) {
        this.type = type;
    }




    /**
     * @author Sebastian Mora Godinez
     * @brief Update game object position.
     * @param posX position in X axis
     * @param posY position in Y axis
     */
    public void updatePosition(Integer posX, Integer posY){
        this.setPosX(posX);
        this.setPosY(posY);

    }

    /**
     * @author Sebastia Mora
     * @brief Returns game object row position
     * @return row number
     */
    public Integer getLiana() {
        return this.liana;
    }



    /**
     * @author Sebastian Mora
     * @brief Sets game object row position
     * @param liana
     */
    public void setLiana(Integer liana) {
        this.liana = liana;
    }


    /**
     * @atuhor Sebastian Mora
     * @brief Returns game object speed
     * @return Integer that stand for game object speed
     */
    public Integer getSpeed() {
        return speed;
    }


    /**
     * @author Sebastian Mora
     * @brief Sets game object speed
     * @param speed Integer that stand for new speed
     */
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    /**
     * @author Sebastian Mora
     * @brief Returns game object id
     * @return game object id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @author Sebastian Mora
     * @brief Sets game object id
     * @param id game object id
     */
    public void setId(Integer id) {
        this.id = id;
    }


    private Integer getPosX() {
        return posX;
    }

    private void setPosX(Integer posX) {
        this.posX = posX;
    }

    private Integer getPosY() {
        return posY;
    }

    private void setPosY(Integer posY) {
        this.posY = posY;
    }
}