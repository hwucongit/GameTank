package ui;

import interfaces.Constants;
import interfaces.IMenu;
import model.Object2D;
import model.Size;
import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel implements Constants  {
    Object2D bg, logo;
    JButton startBt, helpBt, exitBt, scoreBt;
    IMenu iMenu;
    CardLayout cardLayout;

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public MenuPanel(IMenu iMenu) {
        this.iMenu = iMenu;
        setSize(W_FRAME, H_FRAME);
        setLayout(null);
        initBg();
        initStartBt();
        initHelpBt();
        initExitBt();
        initScoreBt();
    }

    public void initBg() {
        Image bgImg = ImageUtil.getImage("/img/menubg.jpg");
        Image logoImg = ImageUtil.getImage("/img/logo.png");
        bg = new Object2D(0, 0, new Size(getWidth(), getHeight()), bgImg);
        logo = new Object2D(getWidth()/2 - 250*W_FRAME/532/2,30*W_FRAME/532,
                new Size(250*W_FRAME/532,100*H_FRAME/532),logoImg);
    }

    public void initStartBt() {
        Image startImg = ImageUtil.getImage("/img/start.png");
        Image start2Img = ImageUtil.getImage("/img/start2.png");
        startBt = new JButton();
        startBt.setIcon(new ImageIcon(startImg));
        startBt.setSize(125*WIDTH_GAME/532, 35*WIDTH_GAME/532);
        startBt.setLocation(getWidth() / 2 - 125*WIDTH_GAME/532/2, 200*H_FRAME/532);
        add(startBt);
        startBt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                iMenu.start();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                startBt.setIcon(new ImageIcon(start2Img));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                startBt.setIcon(new ImageIcon(startImg));
            }
        });
    }

    public void initScoreBt() {
        Image scoreImg = ImageUtil.getImage("/img/highscore.png");
        Image score2Img = ImageUtil.getImage("/img/highscore2.png");
        scoreBt = new JButton();
        scoreBt.setIcon(new ImageIcon(scoreImg));
        scoreBt.setSize(125*WIDTH_GAME/532, 35*WIDTH_GAME/532);
        scoreBt.setLocation(getWidth() / 2 - 125*WIDTH_GAME/532/2, 250*H_FRAME/532);
        add(scoreBt);
        scoreBt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                iMenu.showHightScore();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                scoreBt.setIcon(new ImageIcon(score2Img));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                scoreBt.setIcon(new ImageIcon(scoreImg));
            }
        });
    }

    public void initHelpBt() {
        Image helpImg = ImageUtil.getImage("/img/help.png");
        Image help2Img = ImageUtil.getImage("/img/help2.png");
        helpBt = new JButton();
        helpBt.setIcon(new ImageIcon(helpImg));
        helpBt.setSize(125* WIDTH_GAME/532, 35*WIDTH_GAME/532);
        helpBt.setLocation(getWidth() / 2 - 125*WIDTH_GAME/532/2, 300*H_FRAME/532);
        add(helpBt);
        helpBt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                iMenu.showHelp();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                helpBt.setIcon(new ImageIcon(help2Img));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                helpBt.setIcon(new ImageIcon(helpImg));
            }
        });
    }

    public void initExitBt() {
        Image exitImg = ImageUtil.getImage("/img/exit.png");
        Image exit2Img = ImageUtil.getImage("/img/exit2.png");
        exitBt = new JButton();
        exitBt.setIcon(new ImageIcon(exitImg));
        exitBt.setSize(125* WIDTH_GAME/532, 35*WIDTH_GAME/532);
        exitBt.setLocation(getWidth() / 2 - 125*WIDTH_GAME/532/2, 350*H_FRAME/532);
        add(exitBt);
        exitBt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                iMenu.exitGame();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                exitBt.setIcon(new ImageIcon(exit2Img));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                exitBt.setIcon(new ImageIcon(exitImg));
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        bg.draw(g2d);
        logo.draw(g2d);
    }
}
