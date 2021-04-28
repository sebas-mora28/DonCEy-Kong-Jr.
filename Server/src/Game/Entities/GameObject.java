package Game.Entities;

public class GameObject {


    private Integer posX;
    private Integer posY;
    private Integer speed = 1;
    private Integer liana;
    private Integer id;
    private String type;
    private Integer facing;
    private Integer jumping;
    private Integer onLiana;
    private Integer falling;





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


    /**
     * @author Sebastian Mora
     * @brief Returns game pos x
     * @return posX
     */
    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    /**
     * @author Sebastian Mora
     * @brief Returns pos y
     * @return game posY
     */
    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }


    /**
     * @author Sebastian Mora
     * @brief Returns pos y
     * @return game posY
     */
    public Integer getFacing() {
        return facing;
    }

    /**
     * @author Sebastian Mora
     * @brief Sets facing
     * @param facing facing
     */
    public void setFacing(Integer facing) {
        this.facing = facing;
    }

    /**
     * @author Sebastian Mora
     * @brief Returns jumping
     * @return jumpin
     */
    public Integer getJumping() {
        return jumping;
    }

    public void setJumping(Integer jumping) {
        this.jumping = jumping;
    }

    /**
     * @author Sebastian Mora
     * @brief Returns onLiana
     * @return onLiana
     */
    public Integer getOnLiana() {
        return onLiana;
    }

    public void setOnLiana(Integer onLiana) {
        this.onLiana = onLiana;
    }

    /**
     * @author Sebastian Mora
     * @brief Returns falling
     * @return falling
     */
    public Integer getFalling() {
        return falling;
    }

    public void setFalling(Integer falling) {
        this.falling = falling;
    }

}