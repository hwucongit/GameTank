package model;

import java.awt.*;

public class Item extends Object2D{
    private int code;
    public Item(int x, int y, Size size, Image image) {
        super(x, y, size, image);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
