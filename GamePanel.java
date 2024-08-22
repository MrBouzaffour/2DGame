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

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // To use this we need to implement Runnable



    // Player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;  // 0.016 s
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null)
        {
            // Update 1 : update information such as character position
            update();
            // Update 2 : draw the screen with updated information
            repaint(); // calling the paintComponent()
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
               Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
   }
    public void update(){
        if(keyH.upPressed)
        {
            playerY -=playerSpeed;
        }
        if(keyH.downPressed)
        {
            playerY+=playerSpeed;
        }
        if (keyH.leftPressed)
        {
            playerX-=playerSpeed;
        }
        if (keyH.rightPressed)
        {
            playerX+=playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX,playerY,tileSize,tileSize);
        g2.dispose();
    }
}
