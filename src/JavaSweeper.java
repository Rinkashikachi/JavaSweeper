import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ru.rinkashikachi.sweeper.Game;
import ru.rinkashikachi.sweeper.Box;
import ru.rinkashikachi.sweeper.Coords;
import ru.rinkashikachi.sweeper.Ranges;

public class JavaSweeper extends JFrame{

    private Game game;
    private JPanel panel;
    private JLabel label;

    private final int COLUMNS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args){
        new JavaSweeper().setVisible(true);
    }

    private JavaSweeper(){
        game = new Game(COLUMNS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
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
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coords coord = new Coords(x,y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.leftButtonPressed(coord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.rightButtonPressed(coord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x*IMAGE_SIZE,
                Ranges.getSize().y*IMAGE_SIZE));
        add(panel);
    }

    private void initFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaSweeper");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }

    private void initLabel(){
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
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

    private String getMessage(){
        switch (game.getState()){
            case PLAYING: return "Think twice!";
            case BOMBED: return "Bad luck!";
            case WON: return "Congratulations!";
            default: return "Welcome!";
        }
    }
}