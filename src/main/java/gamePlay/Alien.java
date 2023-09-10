package gamePlay;

import engines.kernel.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Alien extends Entity {
    boolean killed;
    public Alien(int heightEntity, int widthEntity) {
        super(heightEntity, widthEntity, Type.Ai, 1);
        name = "Aliens";
        value = 10;
        killed = false;
        try {
            BufferedImage image = ImageIO.read(new File("src/main/resources/assets/images/Aliens/alienHaut1.png"));
            graphicalObject.setImage(image);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


