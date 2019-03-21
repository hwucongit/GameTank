package ui;

import interfaces.Constants;
import interfaces.IMenu;
import manager.GameManager;
import manager.SoundManager;
import model.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Constants,KeyListener,Runnable {
    private GameManager gameManager;
    private IMenu iMenu;
    private ScorePanel scorePanel;
    private Score scoreObj;
    private SoundManager gameOverAudio;
    long time = 0;
    boolean ok;
    public GamePanel(IMenu imenu){
        this.iMenu = imenu;
        setSize(WIDTH_GAME, HEIGHT_GAME);
        setBackground(Color.BLACK);
        setLayout(null);
        gameManager = new GameManager();
        scorePanel = new ScorePanel(iMenu);
        scoreObj = new Score(W_ITEM,2*H_ITEM,null,null);
        scoreObj.setScore(gameManager.getScore());
        scorePanel.setScore(scoreObj);
        add(scorePanel);
        requestFocus();
        setFocusable(true);
        addKeyListener(this);
        initThread();
    }
    public void initThread(){
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        gameManager.draw(g2d);

    }

    public void checkWin(){
        if (gameManager.getCountKillEnemyTank() == KILLTANK) {
            gameManager.setCountKillEnemyTank(0);
            gameOverAudio = new SoundManager("/sound/win.wav");
            gameOverAudio.init();
            gameOverAudio.play();
            JOptionPane.showMessageDialog(this, "Game Win !!!");
            iMenu.backMenu();
            gameManager.reset();
        }
    }
    public void checkLose(){
        if(ok) {
            gameOverAudio = new SoundManager("/sound/gameover.wav");
            gameOverAudio.init();
            gameOverAudio.play();
            JOptionPane.showMessageDialog(this, "Game Over !!!");
            iMenu.backMenu();
            gameManager.reset();
        }
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        gameManager.pressKey(keyEvent.getKeyCode(),time);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameManager.releaseKey(keyEvent.getKeyCode());
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ok = gameManager.actionThread(time);
            scoreObj.setScore(gameManager.getScore());
            scoreObj.setLife(gameManager.getLife());
            checkLose();
            checkWin();
            repaint();
            time++;
        }
    }

}
