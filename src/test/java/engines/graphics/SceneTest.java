package engines.graphics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SceneTest {

    Random random = new Random();


    GraphicalEngine graphicalEngine = new GraphicalEngine();

    Scene scene = new Scene(graphicalEngine,100,100);

    @Test
    void addEntity() {

        GraphicalObject graphicalObject ;


        for (int i = 0; i < 15; i++) {

            graphicalObject = new GraphicalObject(new Point(random.nextInt(),random.nextInt()));

            scene.addEntity(graphicalObject);

            Assertions.assertEquals(scene.getEntities().get(i),graphicalObject);


        }



    }

    @Test
    void removeEntity() {
        GraphicalObject graphicalObject ;


        for (int i = 0; i < 15; i++) {

            graphicalObject = new GraphicalObject(new Point(random.nextInt(),random.nextInt()));

            scene.addEntity(graphicalObject);

            Assertions.assertEquals(scene.getEntities().get(i),graphicalObject);

        }

    }

    @Test
    void setBackgroundColor() {
        scene.setBackgroundColor(Color.BLACK);
        Assertions.assertEquals(scene.getBackground(),Color.BLACK);

        scene.setBackgroundColor(Color.RED);
        Assertions.assertEquals(scene.getBackground(),Color.RED);

        scene.setBackgroundColor(Color.GRAY);
        Assertions.assertEquals(scene.getBackground(),Color.GRAY);

        scene.setBackgroundColor(Color.GREEN);
        Assertions.assertEquals(scene.getBackground(),Color.GREEN);


    }

    @Test
    void getHeight() {

        Assertions.assertEquals(scene.getHeight(),100);

    }

    @Test
    void getWidth() {

        Assertions.assertEquals(scene.getHeight(),100);

    }

    @Test
    void getEntities() {


        GraphicalObject graphicalObject ;

        ArrayList<GraphicalObject> graphicalObjectArrayList = new ArrayList<>();


        for (int i = 0; i < 15; i++) {
            graphicalObject = new GraphicalObject(new Point(random.nextInt(),random.nextInt()));

            scene.addEntity(graphicalObject);

            graphicalObjectArrayList.add(graphicalObject);

            Assertions.assertEquals(scene.getEntities().get(i),graphicalObject);

        }

        Assertions.assertEquals(graphicalObjectArrayList,scene.getEntities());



    }

    @Test
    void getGraphicsEngine() {
        Assertions.assertEquals(graphicalEngine,scene.getGraphicsEngine());
    }

    @Test
    void getColor() {
        assertNull(scene.getColor());
    }
}