package engines.graphics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class GraphicalObjectTest {

    GraphicalObject graphicalObject;

    @Test
    void setPosition() {
        graphicalObject = new GraphicalObject(new Point(100, 100));

        graphicalObject.setPosition(new Point(200, 200));

        Assertions.assertEquals(graphicalObject.position, new Point(200, 200));

        graphicalObject.setPosition(new Point(0, 0));

        Assertions.assertEquals(graphicalObject.position, new Point(0, 0));


    }

    @Test
    void getName() {
        graphicalObject = new GraphicalObject("object", new Point(100, 100));
        Assertions.assertEquals(graphicalObject.getName(), "object");

        graphicalObject = new GraphicalObject("test", new Point(100, 100));
        Assertions.assertEquals(graphicalObject.getName(), "test");


    }

    @Test
    void getImage() {
        setImage();
    }

    @Test
    void setImage() {

        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_BINARY);
        graphicalObject = new GraphicalObject(new Point(100, 100));

        graphicalObject.setImage(bufferedImage);

        Assertions.assertEquals(bufferedImage, graphicalObject.getImage());

        bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);


        graphicalObject.setImage(bufferedImage);

        Assertions.assertEquals(bufferedImage, graphicalObject.getImage());


    }

    @Test
    void setScene() {

        GraphicalEngine graphicalEngine = new GraphicalEngine();
        Scene scene = graphicalEngine.generateScene(100, 100);

        graphicalObject = new GraphicalObject(new Point(100, 100));

        graphicalObject.setScene(scene);

        Assertions.assertEquals(graphicalObject.getScene(), scene);


        Scene scene1 = new Scene(graphicalEngine, 100, 100);

        graphicalObject.setScene(scene1);

        Assertions.assertEquals(graphicalObject.getScene(), scene1);


    }


}