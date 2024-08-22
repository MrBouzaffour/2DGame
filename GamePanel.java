import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    // Screen setting
    final int originalTileSize = 16; // 16*16 Tile
    final int scale = 3; // it looks 16 * 3 = 46
    final int tileSize = originalTileSize * scale; // 48 *48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }




}
