package ui;

import interfaces.Constants;
import interfaces.IMenu;
import utils.ImageUtil;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame implements Constants {
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private HelpPanel helpPanel;
    private MyContainer myContainer;

    public GUI() {
        setTitle("Tank90");
        Image icon = ImageUtil.getImage("/img/bossyellow_left.png");
        this.setIconImage(icon);
        setSize(W_FRAME, H_FRAME);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        myContainer = new MyContainer();
        add(myContainer);
    }
}
