import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel  implements Runnable{
    // Screen setting
    final int originalTileSize = 16; // 16*16 Tile
    final int scale = 3; // it looks 16 * 3 = 46
    final int tileSize = originalTileSize * scale; // 48 *48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels



    // our time system
    Thread gameThread; // To use this we need to implement Runnable


    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }


    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        while(gameThread != null)
        {
//            System.out.println("The game loop is running");

            // Update 1 : update information such as character position
            update();
            // Update 2 : draw the screen with updated information
            repaint(); // calling the paintComponent()
        }
    }

    public void update(){}
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(100,100,tileSize,tileSize);
        g2.dispose();
    }
}
