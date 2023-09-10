package gamePlay;

import engines.kernel.Entity;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {



    public Player(int heightEntity, int widthEntity) throws IOException {
        super(heightEntity, widthEntity, Type.Physical, 2);
        score = 0;
        name = "Player";
        killed = false;
        BufferedImage image = ImageIO.read(new File("src/main/resources/assets/images/Spacecraft/craft.png"));
        graphicalObject.setImage(image);

    }


}
