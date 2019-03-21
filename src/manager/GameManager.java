package manager;

import interfaces.Constants;
import interfaces.ITank;
import model.*;
import utils.ImageUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager implements Constants {
    private MyTank myTank;
    private List<EnemyTank> enemyTanks;
    private List<Item> items;
    private int score = 0;
    private int life = 3;
    private int countKillEnemyTank = 0;
    public GameManager() {
        initMap("/map/map1.txt");
        initMyTank();
        initEnemyTank();
    }

    public void initMap(String path) {
        items = new ArrayList<>();
        try {
            InputStream in = GameManager.class.getResource(path).openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String content;
            int row = 0;
            while ((content = br.readLine()) != null) {
                for (int i = 0; i < content.length(); i++) {
                    int code = Integer.parseInt(content.charAt(i) + "");
                    int x = i * W_ITEM;
                    int y = row * H_ITEM;
                    Image image = getItemImage(code);
                    if (code == 9) {
                        Item item = new Item(x, y, new Size(2 * W_ITEM, 2 * H_ITEM), image);
                        item.setCode(code);
                        items.add(item);
                    } else {
                        Item item = new Item(x, y, new Size(W_ITEM, H_ITEM), image);
                        item.setCode(code);
                        items.add(item);
                    }

                }
                row++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Image getItemImage(int code) {
        switch (code) {
            case BRICK:
                return ImageUtil.getImage("/img/brick.png");
            case WATER:
                return ImageUtil.getImage(
                        "/img/water.png"
                );
            case TREE:
                return ImageUtil.getImage(
                        "/img/tree.png"
                );
            case ROCK:
                return ImageUtil.getImage(
                        "/img/rock.png"
                );
            case BIRD:
                return ImageUtil.getImage(
                        "/img/bird.png"
                );
            default:
                return null;
        }
    }

    public void reset() {
        enemyTanks.clear();
        items.clear();
        score = 0;
        life = 3;
        countKillEnemyTank = 0;
        initMap("/map/map1.txt");
        initMyTank();
        initEnemyTank();
    }

    public void initMyTank() {
        Image tankUp = ImageUtil.getImage("/img/bossyellow_up.png");
        myTank = new MyTank(9 * W_ITEM, HEIGHT_GAME - H_TANK - H_ITEM,
                new Size(W_TANK, H_TANK), tankUp, 2);
        myTank.setOriantitation(UP);
        myTank.setInter(
                new ITank() {
                    @Override
                    public List<Item> getItems() {
                        return items;
                    }

                    @Override
                    public List<EnemyTank> getEnemyTank() {
                        return enemyTanks;
                    }

                    @Override
                    public MyTank getMyTank() {
                        return myTank;
                    }
                });
    }

    public void initEnemyTank() {
        enemyTanks = new ArrayList<>();
        Image tankDown = ImageUtil.getImage("/img/player_green_down.png");
        Random rd = new Random();
        int x = W_ITEM;
        for (int i = 0; i < 5; i++) {
            int y = W_ITEM;
            int orientation = rd.nextInt(4);
            int speed = 5;
            EnemyTank tank = new EnemyTank(x, y, new Size(W_TANK, H_TANK), tankDown, speed);
            tank.setOriantitation(orientation);
            tank.setInter(new ITank() {
                @Override
                public List<Item> getItems() {
                    return items;
                }

                @Override
                public List<EnemyTank> getEnemyTank() {
                    return enemyTanks;
                }

                @Override
                public MyTank getMyTank() {
                    return myTank;
                }
            });
            tank.setItank(new ITank() {
                @Override
                public List<Item> getItems() {
                    return null;
                }

                @Override
                public List<EnemyTank> getEnemyTank() {
                    return null;
                }

                @Override
                public MyTank getMyTank() {
                    return myTank;
                }
            });
            enemyTanks.add(tank);
            x+= 3*W_TANK;
        }

    }

    public void shootByEnemyTank(long time) {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            enemyTank.shoot(time);
        }
    }

    public void moveEnemyBullet(long time) {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            enemyTank.moveBullets(time);
        }
    }

    public void checkOutFrameEnemyBullet() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            enemyTank.checkBulletOut();
        }
    }

    public boolean checkEnemyBulletIntersectItem() {
        boolean check = false;
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            check = enemyTank.checkBulletIntersectItem(items);
        }
        return check;
    }

    public void checkEnemyBulletIntersectMyTank() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            List<Bullet> enemyBullets = enemyTank.getBullets();
            for (int j = 0; j < enemyBullets.size(); j++) {
                Bullet enemyBullet = enemyBullets.get(j);
                if (myTank.getRect().intersects(enemyBullet.getRect())) {
                    life--;
                    myTank.setX(9 * W_ITEM);
                    myTank.setY(HEIGHT_GAME - H_TANK - H_ITEM);
                    enemyBullets.remove(j--);
                    break;
                }
            }
        }
    }

    public void checkMyBulletIntersectEnemyTank() {
        List<Bullet> bullets = myTank.getBullets();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < enemyTanks.size(); j++) {
                EnemyTank enemyTank = enemyTanks.get(j);
                if (bullet.getRect().intersects(enemyTank.getRect())) {
                    SoundManager soundManager = new SoundManager("/sound/intersect.wav");
                    soundManager.init();
                    soundManager.play();
                    score += 5;
                    countKillEnemyTank++;
                    enemyTanks.remove(j--);
                    bullets.remove(i--);
                    break;
                }
            }
        }
    }

    public void checkMyBulletInterSectEnemyBullet() {
        List<Bullet> myBullets = myTank.getBullets();
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            List<Bullet> enemyBullets = enemyTank.getBullets();
            for (int j = 0; j < myBullets.size(); j++) {
                Bullet myBullet = myBullets.get(j);
                for (int k = 0; k < enemyBullets.size(); k++) {
                    Bullet enemyBullet = enemyBullets.get(k);
                    if (myBullet.getRect().intersects(enemyBullet.getRect())) {
                        myBullets.remove(j--);
                        enemyBullets.remove(k--);
                        break;
                    }
                }
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getLife(){
        return life;
    }

    public void setCountKillEnemyTank(int x){
        this.countKillEnemyTank = x;
    }

    public int getCountKillEnemyTank(){return countKillEnemyTank;}

    public void draw(Graphics2D g2d) {
        myTank.draw(g2d);


        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            enemyTank.draw(g2d);
        }

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            item.draw(g2d);
        }
    }

    public boolean actionThread(long time) {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank tankEnemy = enemyTanks.get(i);
            tankEnemy.move(time);

        }
        myTank.move(time);
        myTank.moveBullets(time);
        myTank.checkBulletOut();
        checkMyBulletIntersectEnemyTank();
        checkMyBulletInterSectEnemyBullet();
        checkEnemyBulletIntersectMyTank();
        shootByEnemyTank(time);
        moveEnemyBullet(time);
        checkOutFrameEnemyBullet();

        boolean isBirdDead = myTank.checkBulletIntersectItem(items);
        boolean isBirdDead2 = checkEnemyBulletIntersectItem();

        if(enemyTanks.size() == 0) {
            if (countKillEnemyTank < KILLTANK)
                initEnemyTank();
        }

        if (isBirdDead || isBirdDead2 || life == 0){
            life = -1;
            return true;
        }
        return false;
    }

    public void pressKey(int keyEvent, long time) {

        switch (keyEvent) {
            case KeyEvent.VK_LEFT:
                myTank.setOriantitation(LEFT);
                myTank.setMove(true);
                break;
            case KeyEvent.VK_UP:
                myTank.setOriantitation(UP);
                myTank.setMove(true);
                break;
            case KeyEvent.VK_RIGHT:
                myTank.setOriantitation(RIGHT);
                myTank.setMove(true);
                break;
            case KeyEvent.VK_DOWN:
                myTank.setOriantitation(DOWN);
                myTank.setMove(true);
                break;
            case KeyEvent.VK_SPACE:
                myTank.shoot(time);
                break;
        }
    }

    public void releaseKey(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_DOWN:
                myTank.setMove(false);
                break;
            default:
                break;
        }
    }
}
