package Game;

public class GameObject {


    public Integer posX;
    public Integer posY;
    public static Integer speed;
    public Integer liana;
    public Integer id;


    /**
     * @author Sebastian Mora Godinez
     * @brief Update game object position.
     * @param posX position in X axis
     * @param posY position in Y axis
     */
    public void updatePosition(Integer posX, Integer posY){
        this.posX = posX;
        this.posY = posY;

    }

    /**
     * @author Sebastia Mora
     * @brief Returns game object liana position
     * @return liana number
     */
    public Integer getLiana() {
        return liana;
    }


    /**
     * @author Sebastian Mora
     * @brief Sets game object liana position
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




}