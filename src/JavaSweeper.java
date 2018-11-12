import javax.swing.*;
import java.awt.*;
import ru.rinkashikachi.sweeper.Box;

public class JavaSweeper extends JFrame{

    private JPanel panel;
    private final int COLUMNS = 15;
    private final int ROWS = 1;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args){
        new JavaSweeper().setVisible(true);
    }

    private JavaSweeper(){
        initPanel();
        initFrame();
        setImages();
    }

    private void initPanel(){
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Box box : Box.values())
                    g.drawImage((Image)box.image,box.ordinal()*IMAGE_SIZE,0,this);
            }
        };
        panel.setPreferredSize(new Dimension(
                COLUMNS*IMAGE_SIZE,ROWS*IMAGE_SIZE));
        add(panel);
    }

    private void initFrame(){
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaSweeper");
        setResizable(false);
        setLocationRelativeTo(null);
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