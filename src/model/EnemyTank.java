package model;

import interfaces.ITank;
import manager.SoundManager;
import utils.ImageUtil;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class EnemyTank extends Tank {
    private int tankKind;
    private long currentTimeMove;
    private Image[] images;
    private Image bulletImg;
    private long currentShoot;
    private ITank itank;
    public void setItank(ITank iTank){
        this.itank = iTank;
    };
    public EnemyTank(int x, int y, Size size, Image image, int speed) {
        super(x, y, size, image, speed);
        Image tankUp = ImageUtil.getImage("/img/player_green_up.png");
        Image tankDown = ImageUtil.getImage("/img/player_green_down.png");
        Image tankRight = ImageUtil.getImage("/img/player_green_right.png");
        Image tankLeft = ImageUtil.getImage("/img/player_green_left.png");
        bulletImg = ImageUtil.getImage("/img/bullet.png");
        images = new Image[]{
                tankLeft,
                tankUp,
                tankRight,
                tankDown
        };
    }
    public void randomMove(){
        Random rd = new Random();
        int orient = rd.nextInt(4);
        if(orient == oriantitation){
            setOriantitation((orient+1)%4);
        }
        else
            setOriantitation(orient);

    }
    @Override
    public void setOriantitation(int oriantitation) {
        super.setOriantitation(oriantitation);
        image = images[oriantitation];
    }

    public int getTankKind() {
        return tankKind;
    }

    public void setTankKind(int tankKind) {
        this.tankKind = tankKind;
    }

    public void attackBunker() {

    }

    public boolean checkInterSectMytank(){
        Tank myTank = itank.getMyTank();
        if (this.getRect().intersects(myTank.getRect()))
            return true;
        return false;
    }
    public void moveBullets(long time) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.move(time);
        }
    }

    @Override
    public void move(long time) {
        if (time - currentTimeMove < speed) {
            return;
        }
        currentTimeMove = time;
        switch (oriantitation) {
            case LEFT:
                x += -1;
                if(checkCollision()){
                    x++;
                    randomMove();
                }
                if(checkInterSectMytank())
                    x++;
                if(new Random().nextInt(1000000) > 999000)
                    randomMove();
                break;
            case UP:
                y += -1;
                if(checkCollision()){
                    y++;
                    randomMove();
                }
                if(checkInterSectMytank())
                    y++;
                if(new Random().nextInt(1000000) > 999000)
                    randomMove();
                break;
            case RIGHT:
                x += 1;
                if(checkCollision()){
                    x--;
                    randomMove();
                }
                if(checkInterSectMytank())
                    x--;
                if(new Random().nextInt(1000000) > 999000)
                    randomMove();
                break;
            case DOWN:
                y += 1;
                if(checkCollision()){
                    y--;
                    randomMove();
                }
                if(checkInterSectMytank())
                    y--;
                if(new Random().nextInt(1000000) > 999000)
                    randomMove();
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.draw(g2d);
        }
    }

    @Override
    public void shoot(long time) {
        if(time - currentShoot > 1000) {
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
            currentShoot = time;
        }
    }
    public boolean checkBulletIntersectItem(List<Item> items) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Item item;
            for (int j = 0; j < items.size(); j++) {
                item = items.get(j);
                if (bullet.getRect().intersects(item.getRect())) {
                    switch (item.getCode()) {
                        case BRICK:
                            bullets.remove(i--);
                            items.remove(j--);
                            break;
                        case ROCK:
                            bullets.remove(i--);
                            break;
                        case BIRD:
                            bullets.remove(i--);
                            return true;
                        default:
                            break;
                    }
                    break;
                }
            }
        }
        return false;
    }
}
