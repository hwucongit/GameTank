package ui;

import interfaces.Constants;
import interfaces.IMenu;
import model.Score;
import model.Size;
import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScorePanel extends JPanel implements Constants {
    private Score score;
    private JButton backBt;
    private IMenu iMenu;

    public void setScore(Score score) {
        this.score = score;
    }

    public ScorePanel(IMenu iMenu){
        this.iMenu = iMenu;
        //score = new Score(getX()+ W_ITEM,getY() + 2*H_ITEM,null,null);
        setLocation(WIDTH_GAME,0);
        setSize(W_FRAME - WIDTH_GAME,H_FRAME);
        setBackground(Color.ORANGE);
        setLayout(null);
        initComponents();
    }
    public void initComponents(){

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        score.draw(g2d);
    }
}
