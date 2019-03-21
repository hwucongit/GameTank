package ui;

import interfaces.Constants;
import interfaces.IMenu;
import manager.SoundManager;

import javax.swing.*;
import java.awt.*;

public class MyContainer extends JPanel implements Constants,IMenu {
    public static final String MENU = "MENU";
    public static final String START = "START";
    public static final String SCORE = "SCORE";
    public static final String HELP = "HELP";
    private GamePanel gamePanel;
    private CardLayout cardLayout;
    private MenuPanel menuPanel;
    private HelpPanel helpPanel;
    private SoundManager soundManager;
    public MyContainer(){
        setSize(W_FRAME,H_FRAME);
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        initComponents();
        addComponents();
        soundManager();
    }
    public void initComponents(){
        gamePanel = new GamePanel(this);
        menuPanel = new MenuPanel(this);
        helpPanel = new HelpPanel();
        helpPanel.setiMenu(this);

    }
    public void addComponents(){
        add(gamePanel,START);
        add(menuPanel,MENU);
        add(helpPanel,HELP);
        cardLayout.show(this,MENU);
    }
    public void soundManager(){
    }

    @Override
    public void start() {
        cardLayout.show(this,START);
        gamePanel.requestFocus();
    }

    @Override
    public void showHightScore() {
    }

    @Override
    public void showHelp() {
        cardLayout.show(this,HELP);
    }

    @Override
    public void exitGame() {
        int select = JOptionPane.showConfirmDialog(this,"Exit Game ?","Confirm",JOptionPane.YES_NO_OPTION);
        if(select == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    @Override
    public void backMenu() {
        cardLayout.show(this,MENU);
    }
}
