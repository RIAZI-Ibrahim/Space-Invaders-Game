package gamePlay;

import engines.kernel.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bullet extends Entity {

    public boolean isPressed;
    public Bullet(int heightEntity, int widthEntity) {
        super(heightEntity, widthEntity, Type.Physical, 7);
        isPressed = false;
        name = "Bullet";
    }

    public void setImage(String path){
        try {
            BufferedImage image = ImageIO.read(new File(path));
            graphicalObject.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        if(isPressed){
            int newY = y - this.physicalObject.speed;
            setPyhsicalObjectPositions(x,newY);

        } else{
            int newY = y + this.physicalObject.speed;
            setPyhsicalObjectPositions(x,newY);
        }
    }
}
