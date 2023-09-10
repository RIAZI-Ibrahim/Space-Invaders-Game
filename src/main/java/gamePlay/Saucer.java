package gamePlay;

import engines.kernel.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Saucer extends Entity {

    public boolean killed;

    public Saucer(int heightEntity, int widthEntity){
        super(heightEntity, widthEntity, Type.Ai, 1);
        name = "Saucer";
        killed = false;
        value = 100;
        try {
            BufferedImage image = ImageIO.read(new File("src/main/resources/assets/images/soucoupe.png"));
            graphicalObject.setImage(image);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
