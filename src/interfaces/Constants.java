package interfaces;

public interface Constants {
    int WIDTH_GAME = 1064;
    int HEIGHT_GAME = WIDTH_GAME;
    int W_TANK = 33 * WIDTH_GAME / 532;
    int H_TANK = 32 * WIDTH_GAME / 532;
    int W_ITEM = 19* WIDTH_GAME / 532;
    int H_ITEM = W_ITEM;
    int W_BULLET = 8 * WIDTH_GAME / 532;
    int H_BULLET = W_BULLET;

    int W_FRAME = WIDTH_GAME + 200 * WIDTH_GAME / 532;
    int H_FRAME = HEIGHT_GAME;

    int KILLTANK = 10;

    // orientation
    int LEFT = 0;
    int UP = 1;
    int RIGHT = 2;
    int DOWN = 3;
    // item
    int BRICK = 1;
    int WATER = 2;
    int TREE = 4;
    int ROCK = 5;
    int BIRD = 9;


}
