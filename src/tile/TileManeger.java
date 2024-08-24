package tile;

import main.GamePanel;

public class TileManeger {
    GamePanel gp;
    Tile[] tile;

    public TileManeger(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[10];
    }
}
