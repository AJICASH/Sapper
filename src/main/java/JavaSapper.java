import javax.swing.*;
import java.awt.*;
import SapperPackage.Coords;
import SapperPackage.Ranges;
import SapperPackage.enumlist;
import org.w3c.dom.ls.LSOutput;

public class JavaSapper extends JFrame {
    private JPanel panel, panel2;
    private JButton play;
    private JLabel sizeOfPictureLabel, bombsLeftLabel, flagsLeftLabel;
    private JTextField sizeOfPictureTextField, bombNumberText, flagNumberText;
    private int imgsize = 50;
    private int number = 79;
    private int rows = (int) Math.ceil(Math.sqrt(number));
    private int cols = (int) Math.ceil(Math.sqrt(number));
    private double consty = 12.5;

    public static void main(String[] args) {
        new JavaSapper();
    }
    private JavaSapper(){
        Ranges.setSize(new Coords(cols,rows));
        putPicture();
        CreatePanel();
        CreateFrame();
    }


    private void CreatePanel(){

        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coords coords: Ranges.getAllCoords()){
                    System.out.println(coords.x);
                    System.out.println(coords.y);
                    if (coords.y % 2 == 0){
                        g.drawImage((Image) enumlist.values()[(coords.x + coords.y) % enumlist.values().length].image, 12 + coords.x * imgsize, (int) (coords.y * imgsize - coords.y * consty), this);
                    }else {
                        g.drawImage((Image) enumlist.values()[(coords.x + coords.y) % enumlist.values().length].image, 12 + coords.x  * imgsize + 25, (int) (coords.y * imgsize - coords.y * consty), this);
                    }
                }
            }
        };
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * imgsize + imgsize, Ranges.getSize().y * imgsize));
        add(panel);
    }
    private void CreateFrame(){
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java sapper");
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setIconImage(printImage("defusedbomb"));
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
