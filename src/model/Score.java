package model;

import interfaces.Constants;
import utils.ImageUtil;

import java.awt.*;

public class Score extends Object2D implements Constants {
    int score,life;
    Image enemyTankImg;
    public Score(int x, int y, Size size, Image image) {
        super(x, y, size, image);
        enemyTankImg = ImageUtil.getImage("/img/bossyellow_right.png");
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setFont(new Font("Tahoma",Font.BOLD,25*WIDTH_GAME/532));
        g2d.drawString("Score: " + getScore(),x,y);
        g2d.setColor(Color.RED);
        g2d.drawString("Life: "+ getLife(),x,y + 50*WIDTH_GAME/532 );
        g2d.drawImage(enemyTankImg, x, y + 75*WIDTH_GAME/532,W_TANK,H_TANK,null);
        g2d.setColor(Color.BLUE);
        g2d.drawString(": " + getScore()/5,x + W_TANK + 10*WIDTH_GAME/532, y + 75*WIDTH_GAME/532 + H_TANK);
    }
}
