package ui;

import interfaces.Constants;
import interfaces.IMenu;
import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelpPanel extends JPanel implements Constants {
    private JLabel lb, lb1, lb2;
    private JButton backBt;
    private IMenu iMenu;

    public void setiMenu(IMenu iMenu) {
        this.iMenu = iMenu;
    }

    public HelpPanel(){
        setSize(WIDTH_GAME,HEIGHT_GAME);
        setLayout(null);
        lb = new JLabel();
        lb.setSize(250*WIDTH_GAME/532,50*WIDTH_GAME/532);
        lb.setLocation(getWidth()/2 - 25*WIDTH_GAME/532/2,50*WIDTH_GAME/532);
        lb.setForeground(Color.RED);
        lb.setFont(new Font("Tahoma",Font.BOLD,25*WIDTH_GAME/532));
        lb.setText("Hướng dẫn chơi !!!");
        add(lb);
        
        lb1 = new JLabel();
        lb1.setSize(500*WIDTH_GAME/532,50*WIDTH_GAME/532);
        lb1.setLocation(20*WIDTH_GAME/532/2,100*WIDTH_GAME/532);
        lb1.setForeground(Color.BLUE);
        lb1.setFont(new Font("Tahoma",Font.BOLD,25*WIDTH_GAME/532));
        lb1.setText("Dùng phím SPACE để bắn!");
        add(lb1);
        
        lb2 = new JLabel();
        lb2.setSize(500*WIDTH_GAME/532,50*WIDTH_GAME/532);
        lb2.setLocation(20*WIDTH_GAME/532/2,150*WIDTH_GAME/532);
        lb2.setForeground(Color.BLUE);
        lb2.setFont(new Font("Tahoma",Font.BOLD,25*WIDTH_GAME/532));
        lb2.setText("Dùng các phím mũi tên để di chuyển !");
        add(lb2);
        
        backBt = new JButton();
        Image backImage = ImageUtil.getImage("/img/back1.png");
        Image back2Image = ImageUtil.getImage("/img/back2.png");
        backBt.setIcon(new ImageIcon(backImage));
        backBt.setBounds(10*WIDTH_GAME/532,10*WIDTH_GAME/532,120*WIDTH_GAME/532,30*WIDTH_GAME/532);
        add(backBt);
        backBt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                iMenu.backMenu();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                backBt.setIcon(new ImageIcon(back2Image));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                backBt.setIcon(new ImageIcon(backImage));
            }
        });
    }
}
