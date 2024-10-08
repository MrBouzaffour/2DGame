package main;

import entity.Player;
import tile.TileManeger;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel  implements Runnable{
    // Screen setting
    final int originalTileSize = 16;// Tile : 16*16
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // Width : 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // Height : 576 pixels
    int FPS = 60;

    TileManeger tileM = new TileManeger(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);



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
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // To compute the FPS
        long timer  = 0;
        int drawCount = 0;

        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) /drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta>1)
            {
                update();
                repaint();
                delta--;
                drawCount ++;
            }
            if (timer >= 1000000000)
            {
                System.out.println("FPS:"+drawCount);
                drawCount = 0;
                timer=0;
            }
        }
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
