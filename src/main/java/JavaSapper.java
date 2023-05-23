import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import SapperPackage.*;

public class JavaSapper extends JFrame {
    private Game game;
    private JPanel panel, panel2;
    private JButton play;
    private JLabel sizeOfPictureLabel, bombsLeftLabel, flagsLeftLabel, stateLabel, flagsLeftLabelLabel;
    private JTextField sizeOfPictureTextField, bombNumberText, flagNumberText;
    private int amountBombs = 5;
    private int flagsLeft = amountBombs;
    private int imgsize = 100;
    private int number = 30;
    private int rows = (int) Math.ceil(Math.sqrt(number));
    private int cols = (int) Math.ceil(Math.sqrt(number));
    private int consty = 25;
    private int RB = 0;

    public static void main(String[] args) {
        new JavaSapper();
    }
    private JavaSapper(){
        game = new Game(cols,rows, amountBombs);
        game.start();
        Ranges.setSize(new Coords(cols,rows));
        putPicture();
        flagsLeftLabel();
        stateLabel();
        flagsLeftLabel();
        CreatePanel();
        CreateFrame();

    }
    private int offsetX;
    private int getFlagsleft() {
        return flagsLeft;
    }
    private void stateLabel(){
        stateLabel = new JLabel("Hello!");
        add(stateLabel, BorderLayout.SOUTH);
    }
    private void flagsLeftLabel(){
        flagsLeftLabelLabel = new JLabel();
        flagsLeftLabelLabel.setText("FLAGS LEFT " + flagsLeft);
        add(flagsLeftLabelLabel, BorderLayout.PAGE_START);
    }

    private void CreatePanel(){

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coords coords : Ranges.getAllCoords()) {
                    offsetX = coords.y % 2 == 0 ? 0 : imgsize / 2;
                    int x = 25 + coords.x * imgsize + offsetX;
                    int y = coords.y * imgsize - coords.y * consty;
                    g.drawImage((Image) game.getBox(coords).image, x, y, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = (e.getX() - offsetX) / imgsize;
                int y = (e.getY() - offsetX) / (imgsize -  consty);
                Coords coords = new Coords(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLB(coords);
                    panel.repaint();

                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    enumlist flag = game.getBox(coords);
                    if (flag == enumlist.flag) {
                        if (flagsLeft <= amountBombs){
                            game.delFlag(coords);
                            flagsLeft++;
                        }
                    } else {
                        if (flagsLeft > 0){
                            game.pressRB(coords);
                            flagsLeft--;
                        }
                    }

                    panel.repaint();
                    flagsLeftLabelLabel.setText("FLAGS LEFT " + flagsLeft);
                }
                if (e.getButton() == MouseEvent.BUTTON2) {
                    flagsLeft = amountBombs;
                    flagsLeftLabelLabel.setText("FLAGS LEFT " + flagsLeft);
                    game.start();
                    panel.repaint();
                }
                stateLabel.setText(stateMessage());
                panel.repaint();
                if (game.getState() == WinLosePlaying.BOMBED){
                    flagsLeft = amountBombs;
                    flagsLeftLabelLabel.setText("FLAGS LEFT " + flagsLeft);
                }
            }
        });



        panel.setPreferredSize(new Dimension(Ranges.getSize().x * imgsize + imgsize, Ranges.getSize().y * imgsize));
        add(panel);
    }

    private String stateMessage() {
        switch (game.getState()){
            case PLAYING: return "GOOD LUCK";
            case BOMBED: return "YOU LOOOOOSE";
            case WIN: return "YOU WON! CONGRATULATIONS!!";
            default:return null;
        }
    }


    private void CreateFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java sapper");
        setResizable(true);
        setVisible(true);
        setIconImage(printImage("mushroom"));
        pack();
        setLocationRelativeTo(null);
    }

    private void putPicture(){
        for (enumlist box: enumlist.values()){
            box.image = printImage(box.name().toLowerCase());
        }
    }
    private Image printImage(String name){
//     String filename = "imagesSapper/" + name + ".png";
       ImageIcon picture = new ImageIcon("res/imagesSapper/" + name + ".png");
       return picture.getImage();
    }


}
