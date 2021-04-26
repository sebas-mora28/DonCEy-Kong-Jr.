package Game.Entities;

public class DonkeyKongJunior extends GameObject {


    private Integer posX_inital;

    private Integer posY_inital;

    private Integer score;

    private Integer lives;
    /**
     * @author Sebastian Mora
     * @brief Class constructor
     */
    public DonkeyKongJunior(){
            this.posX_inital  = 120;
            this.posY_inital = 600;
            this.score = 0;
            this.lives = 3;
            this.updatePosition(posX_inital,posY_inital);
    }


    /**
     * @author Sebastia Mora
     * @brief Returns x axis initial position
     * @return x initial position
     */
    public Integer getPosX_inital() {
        return this.posX_inital;
    }

    public void setPosX_inital(Integer posX_inital) {
        this.posX_inital = posX_inital;
    }

    /**
     * @author Sebastia Mora
     * @brief Returns y axis initial position
     * @return y initial position
     */
    public Integer getPosY_inital() {
        return this.posY_inital;
    }

    public void setPosY_inital(Integer posY_inital) {
        this.posY_inital = posY_inital;
    }

    /**
     * @author Sebastia Mora
     * @brief Returns score
     * @return player score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @author Sebastian Mora
     * @brief increases score
     */
    public void increaseScore() {
        this.score += 100;
    }

    /**
     * @author Sebastian Mora
     * @brief increases score
     */
    public void increaseScoreWin() {
        this.score += 300;
    }

    /**
     * @author Sebastia Mora
     * @brief Returns player lifes
     * @return players lifes
     */
    public Integer getLifes() {
        return lives;
    }

    /**
     * @author Sebastian Mora
     * @brief Increment player lifes
     */
    public void incrementLives() {
        this.lives++;
    }

    /**
     * @author Sebastian Mora
     * @brief Increment player lifes
     */
    public void decrementLives() {
        this.lives--;
    }


}
