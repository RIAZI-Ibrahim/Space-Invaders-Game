package engines.physics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PhysicalObjectTest {

    PhysicalObject physicalObject ;

    @Test
    void getPosition() {

        Assertions.assertNull(physicalObject);

        physicalObject = new PhysicalObject(100,100);

        Assertions.assertEquals(new Point(100,100),physicalObject.getPosition());

    }

    @Test
    void setPosition() {

        Assertions.assertNull(physicalObject);

        physicalObject = new PhysicalObject(100,100);

        physicalObject.setPosition(200,200);

        Assertions.assertEquals(new Point(200,200),physicalObject.getPosition());
        Assertions.assertEquals(physicalObject.getPosition().x,200);
        Assertions.assertEquals(physicalObject.getPosition().y,200);

        Assertions.assertEquals(physicalObject.x,200);
        Assertions.assertEquals(physicalObject.y,200);


    }

    @Test
    void testSetPosition() {
        setPosition();
    }


}