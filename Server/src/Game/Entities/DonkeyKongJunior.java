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
    public void increaseScore(Integer score) {
        this.score += score;
    }


    /**
     * @author Sebastian Mora
     * @brief Sets default score
     */
    public void setDefaultScore() {
        this.score = 0;
    }


    /**
     * @author Sebastian Mora
     * @brief Sets default lives
     */
    public void setDefaultLives() {
        this.lives = 3;
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
     * @brief Returns player lives
     * @return players lives
     */
    public Integer getLives() {
        return lives;
    }

    /**
     * @author Sebastian Mora
     * @brief Increment player lives
     */
    public void incrementLives() {
        this.lives++;
    }

    /**
     * @author Sebastian Mora
     * @brief Increment player lives
     */
    public void decrementLives() {
        this.lives--;
    }


}
