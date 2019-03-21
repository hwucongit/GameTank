package model;

import interfaces.Constants;

import java.awt.*;

public class MovObject extends Object2D implements Constants {
    int speed;
    int oriantitation;

    public MovObject(int x, int y, Size size, Image image, int speed) {
        super(x, y, size, image);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getOriantitation() {
        return oriantitation;
    }

    public void setOriantitation(int oriantitation) {
        this.oriantitation = oriantitation;
    }

    public void move(long time) {
        if(time % speed !=0){
            return;
        }
        switch (oriantitation) {
            case LEFT:
                x += -1;
                break;
            case UP:
                y += -1;
                break;
            case RIGHT:
                x += 1;
                break;
            case DOWN:
                y += 1;
                break;
            default:
                break;
        }

    }
}
