import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import SapperPackage.*;

public class JavaSapper extends JFrame {
    private Game game;
    private JPanel panel2;
    private JButton play;
    private JLabel stateLabel, flagsLeftLabelLabel;
    private JTextField flagNumberText;
    private int amountBombs;
    private int flagsLeft = amountBombs;
    private int imgsize = 100;
    private int number ;
    private int rows;
    private int cols;
    private int consty = 25;
    private int RB = 0;
    private void showInputDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JTextField amountBombsField = new JTextField();
        JTextField numberField = new JTextField();

        panel.add(new JLabel("Amount of Bombs:"));
        panel.add(amountBombsField);
        panel.add(new JLabel("Number of Cells:"));
        panel.add(numberField);

        Object[] options = {"PLAY!", "EXIT GAME"};
        int result = JOptionPane.showOptionDialog(null, panel, "Enter Game Settings", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (result == 0) {
            try {
                amountBombs = Integer.parseInt(amountBombsField.getText());
                number = Integer.parseInt(numberField.getText());
                rows = (int) Math.ceil(Math.sqrt(number));
                cols = (int) Math.ceil(Math.sqrt(number));
                flagsLeft = amountBombs;
                Ranges.setSize(new Coords(cols, rows));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.");
                showInputDialog();
            }
        } else {
            System.exit(0);
        }
    }



    public static void main(String[] args) {
        new JavaSapper();
    }
    private JavaSapper(){
        showInputDialog();
        createGame();
        putPicture();
        flagsLeftLabel();
        stateLabel();
        flagsLeftLabel();
        CreatePanel();
        CreateFrame();

    }

    private void createGame() {
        game = new Game(cols,rows, amountBombs);
        game.start();
        Ranges.setSize(new Coords(cols,rows));
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
    public int steps = 0;
    private void CreatePanel(){

        panel2 = new JPanel() {
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

        panel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = (e.getX() - offsetX) / imgsize;
                int y = (e.getY() - offsetX) / (imgsize - consty / 3 * 4);
                Coords coords = new Coords(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLB(coords);
                    panel2.repaint();
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

                    panel2.repaint();
                    flagsLeftLabelLabel.setText("FLAGS LEFT " + flagsLeft);
                }
                if (e.getButton() == MouseEvent.BUTTON2) {
                    flagsLeft = amountBombs;
                    flagsLeftLabelLabel.setText("FLAGS LEFT " + flagsLeft);
                    game.start();
                    panel2.repaint();
                }
                stateLabel.setText(stateMessage());
                panel2.repaint();
                if (game.getState() == WinLosePlaying.BOMBED){
                    flagsLeft = amountBombs;
                    flagsLeftLabelLabel.setText("FLAGS LEFT " + flagsLeft);
                }

            }
        });



        panel2.setPreferredSize(new Dimension(Ranges.getSize().x * imgsize + imgsize, Ranges.getSize().y * imgsize));
        add(panel2);
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
       ImageIcon picture = new ImageIcon("res/imagesSapper/" + name + ".png");
       return picture.getImage();
    }


}
