package engines.graphics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.awt.Color;

import java.util.ArrayList;


class GraphicalEngineTest {

    GraphicalEngine graphicalEngine = new GraphicalEngine();
    Scene scene;

    @Test
    void generateScene() {
        scene = graphicalEngine.generateScene(100, 100);

        Scene scene1 = new Scene(graphicalEngine, 100, 100);


        Assertions.assertInstanceOf(Scene.class, scene);

        Assertions.assertInstanceOf(Scene.class, scene1);


        Assertions.assertEquals(scene.getWidth(), scene1.getWidth());

        Assertions.assertEquals(scene.getHeight(), scene1.getHeight());

        Assertions.assertEquals(scene.getEntities(), scene1.getEntities());

    }

    @Test
    void setSceneBackgroundColor() {

        scene = graphicalEngine.generateScene(100, 100);

        scene.setBackgroundColor(Color.BLACK);

        Assertions.assertEquals(scene.getColor(), Color.BLACK);

        scene.setBackgroundColor(Color.RED);

        Assertions.assertEquals(scene.getColor(), Color.RED);


        scene.setBackgroundColor(Color.BLUE);

        Assertions.assertEquals(scene.getColor(), Color.BLUE);


    }


    @Test
    void addToScene() {

        ArrayList<GraphicalObject> graphicalObjectArrayList = new ArrayList<>();

        scene = graphicalEngine.generateScene(100, 100);

        GraphicalObject graphicalObject = new GraphicalObject(new Point(100, 100));

        graphicalObjectArrayList.add(graphicalObject);

        graphicalEngine.addToScene(scene, graphicalObject);

        Assertions.assertEquals(scene.getEntities().get(0), graphicalObject);

        GraphicalObject graphicsObj;

        for (int i = 0; i < 10; i++) {
            graphicsObj = new GraphicalObject(new Point(i * 10, i * 10));

            graphicalObjectArrayList.add(graphicsObj);

            graphicalEngine.addToScene(scene, graphicsObj);

        }

        Assertions.assertEquals(graphicalObjectArrayList.size(), scene.getEntities().size());

        ArrayList<GraphicalObject> sceneGraphicalObjects = scene.getEntities();

        for (int i = 0; i < scene.getEntities().size(); i++) {
            Assertions.assertEquals(graphicalObjectArrayList.get(i), sceneGraphicalObjects.get(i));

        }

    }


    @Test
    void removeFromScene() {

        scene = graphicalEngine.generateScene(100, 100);

        GraphicalObject graphicalObject = new GraphicalObject(new Point(100, 100));


        graphicalEngine.addToScene(scene, graphicalObject);

        Assertions.assertEquals(scene.getEntities().get(0), graphicalObject);


        scene.removeEntity(graphicalObject);


        Assertions.assertEquals(scene.getEntities().size(), 0);

    }

    @Test
    void movable() {
        GraphicalObject graphicalObject = new GraphicalObject(new Point(100, 100));

        graphicalEngine.movable(graphicalObject, 100, 102);

        Assertions.assertEquals(graphicalObject.position, new Point(100, 102));

        graphicalEngine.movable(graphicalObject, 200, -12);

        Assertions.assertEquals(graphicalObject.position, new Point(200, -12));

        graphicalEngine.movable(graphicalObject, 0, 0);

        Assertions.assertEquals(graphicalObject.position, new Point(0, 0));
    }

}