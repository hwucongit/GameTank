package model;

import java.awt.*;

public class Object2D {
    protected int x, y;
    protected Size size;
    protected Image image;

    public Object2D(int x, int y, Size size, Image image) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public void draw(Graphics2D g2d){
        g2d.drawImage(image, x, y, size.getW(),size.getH(),null);
    }
    public Rectangle getRect(){
        return new Rectangle(x,y,size.getW()  ,size.getH());
    }
}
