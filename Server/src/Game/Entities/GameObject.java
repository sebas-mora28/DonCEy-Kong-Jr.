package Game.Entities;

public class GameObject {


    private Integer posX;
    private Integer posY;
    private static Integer speed;
    private Integer row;
    private Integer column;
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
    public Integer getRow() {
        return this.row;
    }


    /**
     * @author Sebastia Mora
     * @brief Returns game object column position
     * @return column number
     */
    public Integer getColumn() {
        return this.column;
    }



    /**
     * @author Sebastian Mora
     * @brief Sets game object row position
     * @param row
     */
    public void setRow(Integer row) {
        this.row = row;
    }


    /**
     * @author Sebastian Mora
     * @brief Sets game object column position
     * @param column
     */
    public void setColumn(Integer column) {
        this.column = column;
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