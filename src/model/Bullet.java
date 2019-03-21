package model;

import java.awt.*;

public class Bullet extends MovObject {
    int dame;


    public Bullet(int x, int y, Size size, Image image, int speed, int dame) {
        super(x, y, size, image, speed);
        this.dame = dame;
    }

    public int getDame() {
        return dame;
    }

    public void setDame(int dame) {
        this.dame = dame;
    }

    public void fire() {
    }


    public boolean checkOutFrame() {
        if (x >= WIDTH_GAME - W_ITEM || x <= -W_ITEM || y >= HEIGHT_GAME || y <= - H_ITEM)
            return true;
        return false;
    }

}
