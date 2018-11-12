import javax.swing.*;
import java.awt.*;
import ru.rinkashikachi.sweeper.Game;
import ru.rinkashikachi.sweeper.Box;
import ru.rinkashikachi.sweeper.Coords;
import ru.rinkashikachi.sweeper.Ranges;

public class JavaSweeper extends JFrame{

    private Game game;
    private JPanel panel;
    private final int COLUMNS = 9;
    private final int ROWS = 9;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args){
        new JavaSweeper().setVisible(true);
    }

    private JavaSweeper(){
        game = new Game(COLUMNS,ROWS);
        game.start();
        initPanel();
        initFrame();
        setImages();
    }

    private void initPanel(){
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coords coord: Ranges.getAllCoords()){
                    g.drawImage((Image)game.getBox(coord).image,
                            coord.x*IMAGE_SIZE, coord.y*IMAGE_SIZE,this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x*IMAGE_SIZE,
                Ranges.getSize().y*IMAGE_SIZE));
        add(panel);
    }

    private void initFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaSweeper");
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
        pack();
    }

    private Image getImage(String name){
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    private void setImages(){
        for (Box box : Box.values())
            box.image = getImage(box.name());
    }
}