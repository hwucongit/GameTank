package model;

import interfaces.Constants;
import interfaces.ITank;
import manager.SoundManager;
import utils.ImageUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tank extends MovObject implements Constants {
    protected List<Bullet> bullets;
    private Image bulletImg;
    private long currentShoot;
    private SoundManager shootAudio;
    private SoundManager gameOverAudio;
    private ITank inter;
    public void setInter(ITank inter) {
        this.inter = inter;
    }

    public Tank(int x, int y, Size size, Image image, int speed) {
        super(x, y, size, image, speed);
        bullets = new ArrayList<>();
        bulletImg = ImageUtil.getImage("/img/bullet.png");

    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void shoot(long time) {

        if(time - currentShoot > 100) {
            switch (oriantitation) {
                case LEFT:
                    Bullet bullet =
                            new Bullet(x - W_BULLET, y + H_TANK / 2 - H_BULLET / 2,
                                    new Size(W_BULLET, H_BULLET), bulletImg, speed / 2, 1);

                    bullet.setOriantitation(LEFT);
                    bullets.add(bullet);
                    break;
                case UP:
                    bullet = new Bullet(x + W_TANK / 2 - W_BULLET / 2, y - H_BULLET,
                            new Size(W_BULLET, H_BULLET), bulletImg, speed / 2, 1);
                    bullet.setOriantitation(UP);
                    bullets.add(bullet);
                    break;
                case RIGHT:
                    bullet = new Bullet(x + W_TANK, y + H_TANK / 2 - H_BULLET / 2,
                            new Size(W_BULLET, H_BULLET), bulletImg, speed / 2, 1);
                    bullet.setOriantitation(RIGHT);
                    bullets.add(bullet);
                    break;
                case DOWN:
                    bullet = new Bullet(x + W_TANK / 2 - W_BULLET / 2, y + H_TANK,
                            new Size(W_BULLET, H_BULLET), bulletImg, speed / 2, 1);
                    bullet.setOriantitation(DOWN);
                    bullets.add(bullet);
                    break;
                default:
                    break;
            }
            shootAudio = new SoundManager("/sound/shoot.wav");
            shootAudio.init();
            shootAudio.play();
            currentShoot = time;
        }
    }

    public void checkBulletOut() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if(bullet.checkOutFrame()) {
                bullets.remove(i);
                i--;
            }
        }

    }

    public void checkBulletIntersectItem() {
    }

    public boolean checkCollision() {
        List<Item> items = inter.getItems();
        MyTank myTank = inter.getMyTank();
        List<EnemyTank> enemyTanks= inter.getEnemyTank();
        Rectangle reT = getRect();
        for(Item item: items){
            int code = item.getCode();
            switch (code){
                case BRICK:
                case ROCK:
                case WATER:
                    if(reT.intersects(item.getRect()))
                        return true;
                case BIRD:
                    if(reT.intersects(item.getRect())) {
                        return true;
                    }
            }
        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            Tank tank = enemyTanks.get(i);
            if(tank == this)
                continue;
            if(reT.intersects(tank.getRect()))
                return true;
        }
        return false;
    }
    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g2d);
        }
    }
}
