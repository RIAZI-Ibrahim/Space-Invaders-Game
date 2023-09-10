package gamePlay;

import engines.kernel.Entity;
import engines.kernel.Kernel;

import java.awt.*;

public class Castle extends Entity {

    static final int dimention = 2;
    static final int widthCastle = 72;
    static final int heightCastle = 54;
    static final int nbLines = heightCastle / dimention;
    static final int nbColumns = widthCastle / dimention;

    public int yPos;
    public int xPos;

    public boolean isBrick;


    public Castle (int xPos, int yPos) {
        super(dimention, dimention, Type.Ai, 0);
        graphicalObject.paintRectangle(xPos, yPos, dimention, dimention, Color.GREEN);
        this.xPos = xPos;
        this.yPos = yPos;
    }

}
