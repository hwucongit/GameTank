package model;

import manager.SoundManager;
import utils.ImageUtil;

import java.awt.*;
import java.util.List;

public class MyTank extends Tank {
    private boolean isMove;
    private long currentTimeMove;
    private Image[] images;
    private SoundManager intersectAudio;

    public MyTank(int x, int y, Size size, Image image, int speed) {
        super(x, y, size, image, speed);
        Image tankUp = ImageUtil.getImage("/img/bossyellow_up.png");
        Image tankDown = ImageUtil.getImage("/img/bossyellow_down.png");
        Image tankRight = ImageUtil.getImage("/img/bossyellow_right.png");
        Image tankLeft = ImageUtil.getImage("/img/bossyellow_left.png");

        images = new Image[]{
                tankLeft,
                tankUp,
                tankRight,
                tankDown
        };
    }


    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean move) {
        isMove = move;
    }

    public void coverBunker() {

    }


    @Override
    public void setOriantitation(int oriantitation) {
        super.setOriantitation(oriantitation);
        image = images[oriantitation];
    }

    @Override
    public void move(long time) {
        if (!isMove) {
            return;
        }
        if (time - currentTimeMove < speed) {
            return;
        }
        currentTimeMove = time;
        switch (oriantitation) {
            case LEFT:
                x += -1;
                if (checkCollision()) {
                    x++;
                }

                break;
            case UP:
                y += -1;
                if (checkCollision()) {
                    y++;
                }

                break;
            case RIGHT:
                x += 1;
                if (checkCollision()) {
                    x--;
                }

                break;
            case DOWN:
                y += 1;
                if (checkCollision()) {
                    y--;
                }

                break;
            default:
                break;
        }

    }


    public void moveBullets(long time) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.move(time);
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
                            intersectAudio = new SoundManager("/sound/intersect.wav");
                            intersectAudio.init();
                            intersectAudio.play();
                            break;
                        case ROCK:
                            bullets.remove(i--);
                            intersectAudio = new SoundManager("/sound/intersect.wav");
                            intersectAudio.init();
                            intersectAudio.play();
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

