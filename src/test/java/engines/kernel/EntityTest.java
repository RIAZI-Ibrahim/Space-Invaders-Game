package engines.kernel;

import engines.AI.AIObject;
import engines.graphics.GraphicalObject;
import engines.physics.PhysicalObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;


class EntityTest {

    Entity entity ;

    @Test
    void getGraphicalObject() {
        Point position = new Point(100,100);
        GraphicalObject graphicalObject = new GraphicalObject(position);
        PhysicalObject physicalObject = new PhysicalObject(position);
        entity = new Entity(graphicalObject,physicalObject);
        Assertions.assertEquals(entity.getGraphicalObject(),graphicalObject);

    }

    @Test
    void setGraphicalObject() {
        Point position = new Point(100,100);
        GraphicalObject graphicalObject = new GraphicalObject(position);
        PhysicalObject physicalObject = new PhysicalObject(position);
        entity = new Entity(graphicalObject,physicalObject);


        GraphicalObject graphicalObject1 = new GraphicalObject(position);

        entity.setGraphicalObject(graphicalObject1);

        Assertions.assertNotEquals(entity.getGraphicalObject(),graphicalObject);

        Assertions.assertEquals(entity.getGraphicalObject(),graphicalObject1);

    }

    @Test
    void getPhysicalObject() {

        Point position = new Point(100,100);
        GraphicalObject graphicalObject = new GraphicalObject(position);
        PhysicalObject physicalObject = new PhysicalObject(position);
        entity = new Entity(graphicalObject,physicalObject);
        Assertions.assertEquals(entity.getPhysicalObject(),physicalObject);


    }

    @Test
    void setPhysicalObject() {

        Point position = new Point(100,100);
        GraphicalObject graphicalObject = new GraphicalObject(position);
        PhysicalObject physicalObject = new PhysicalObject(position);
        entity = new Entity(graphicalObject,physicalObject);


        PhysicalObject physicalObject1 = new PhysicalObject(position);

        entity.setPhysicalObject(physicalObject1);

        Assertions.assertNotEquals(entity.getPhysicalObject(),physicalObject);
        Assertions.assertEquals(entity.getPhysicalObject(),physicalObject1);


    }

    @Test
    void getAiObject() {
        Point position = new Point(100,100);
        GraphicalObject graphicalObject = new GraphicalObject(position);
        PhysicalObject physicalObject = new PhysicalObject(position);
        entity = new Entity(graphicalObject,physicalObject);

        Assertions.assertNull(entity.getAiObject());

    }

    @Test
    void setAiObject() {
        Point position = new Point(100,100);
        GraphicalObject graphicalObject = new GraphicalObject(position);
        PhysicalObject physicalObject = new PhysicalObject(position);
        entity = new Entity(graphicalObject,physicalObject);

        Assertions.assertNull(entity.getAiObject());


        AIObject aiObject = new AIObject(position);

        entity.setAiObject(aiObject);

        Assertions.assertEquals(entity.getAiObject(),aiObject);

    }

    @Test
    void register() {
        Kernel kernel = new Kernel();

        Point position = new Point(100,100);
        GraphicalObject graphicalObject = new GraphicalObject(position);
        PhysicalObject physicalObject = new PhysicalObject(position);
        entity = new Entity(graphicalObject,physicalObject);

        Assertions.assertEquals(entity.getObservers(),new ArrayList<>());

        entity.register(kernel);

        Assertions.assertEquals(entity.getObservers().get(0),kernel);

    }

    @Test
    void unregister() {
        Kernel kernel = new Kernel();

        Point position = new Point(100,100);

        GraphicalObject graphicalObject = new GraphicalObject(position);

        PhysicalObject physicalObject = new PhysicalObject(position);

        entity = new Entity(graphicalObject,physicalObject);

        Assertions.assertEquals(entity.getObservers(),new ArrayList<>());

        entity.register(kernel);

        Assertions.assertEquals(entity.getObservers().get(0),kernel);


        entity.unregister(kernel);

        Assertions.assertEquals(entity.getObservers(),new ArrayList<>());


    }


    @Test
    void move() {
        Point position = new Point(100,100);

        GraphicalObject graphicalObject = new GraphicalObject(position);

        PhysicalObject physicalObject = new PhysicalObject(position);

        entity = new Entity(graphicalObject,physicalObject);

        entity.move(0,0);


        Assertions.assertEquals(entity.x , 0);
        Assertions.assertEquals(entity.y , 0);



    }

    @Test
    void setPhysicalPositions() {

        Point position = new Point(100,100);

        GraphicalObject graphicalObject = new GraphicalObject(position);

        PhysicalObject physicalObject = new PhysicalObject(position);

        entity = new Entity(graphicalObject,physicalObject);

        entity.setPhysicalPositions(0,0);


        Assertions.assertEquals(entity.physicalObject.x , 0);
        Assertions.assertEquals(entity.physicalObject.y , 0);



    }

    @Test
    void setGraphicalPositions() {


        Point position = new Point(100,100);

        GraphicalObject graphicalObject = new GraphicalObject(position);

        PhysicalObject physicalObject = new PhysicalObject(position);

        entity = new Entity(graphicalObject,physicalObject);

        entity.setGraphicalPositions(0,0);


        Assertions.assertEquals(entity.graphicalObject.position.x , 0);
        Assertions.assertEquals(entity.graphicalObject.position.y , 0);

    }


    @Test
    void setCollision() {

        Point position = new Point(100,100);

        GraphicalObject graphicalObject = new GraphicalObject(position);

        PhysicalObject physicalObject = new PhysicalObject(position);

        entity = new Entity(graphicalObject,physicalObject);


        Assertions.assertFalse(entity.isCollide);


        entity.setCollision(true);

        Assertions.assertTrue(entity.isCollide);

        entity.setCollision(false);

        Assertions.assertFalse(entity.isCollide);

    }

    @Test
    void getAndResetCollision() {
        Point position = new Point(100,100);

        GraphicalObject graphicalObject = new GraphicalObject(position);

        PhysicalObject physicalObject = new PhysicalObject(position);

        entity = new Entity(graphicalObject,physicalObject);

        Assertions.assertFalse(entity.getAndResetCollision());

        entity.setCollision(true);

        Assertions.assertTrue(entity.getAndResetCollision());

        Assertions.assertFalse(entity.getAndResetCollision());
    }

    @Test
    void setImage() {
    }
}