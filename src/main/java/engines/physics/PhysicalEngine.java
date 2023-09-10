package engines.physics;

import engines.AI.AIObject;

import java.util.ArrayList;

public class PhysicalEngine {

    public void movable(PhysicalObject entity,int x , int y) {
        entity.setPosition(x, y);
    }

    public boolean isCollide(PhysicalObject entity, int newX, int newY, ArrayList<PhysicalObject> entitiesGame) {
        PhysicalObject tempEntity = new PhysicalObject("tempObject",newX, newY, entity.width, entity.height, 0);
        for (PhysicalObject e : entitiesGame) {
            if(e != entity && CollisionTools.checkCollisionObject
                    (tempEntity.x, tempEntity.y, tempEntity.width, e.x, e.y, e.height, e.width)){
                return true;
            }
        }
        return false;
    }
    public boolean isCollide(int heightW, int widthW, PhysicalObject entity, int newX, int newY) {
        return CollisionTools.checkCollisionWorld(heightW, widthW, entity, newX, newY);
    }
    public boolean isCollideRight(int widthW, int widthObject, int newX) {
        return CollisionTools.checkCollisionRight(widthW, widthObject, newX);
    }
    public boolean isCollideLeft(int newX) {
        return CollisionTools.checkCollisionLeft(newX);
    }
    public boolean collideObjectToObject(PhysicalObject entity, int x2, int y2, int height2, int width2, int newX, int newY) {
        PhysicalObject tempEntity;
        if(entity != null){
            tempEntity = new PhysicalObject("tempObject", newX, newY, entity.width, entity.height, 0);
            return CollisionTools.checkCollisionObject
                    (tempEntity.x, tempEntity.y,tempEntity.width,  x2, y2, height2, width2);
        }
        return false;
    }
    public boolean collideObjectToObject(AIObject entity, int x2, int y2, int height2, int width2, int newX, int newY) {
        PhysicalObject tempEntity;
        if(entity != null){
            tempEntity = new PhysicalObject("tempObject", newX, newY, entity.width, entity.height, 0);
            return CollisionTools.checkCollisionObject
                    (tempEntity.x, tempEntity.y,tempEntity.width,  x2, y2, height2, width2);
        }
        return false;
    }
    public int[] move(PhysicalObject entity, String direction) {
        int newX,newY;
        if (direction.equals("left")) {
            newX = entity.x - entity.speed;
            newY = entity.y;
            entity.setPosition(newX, newY);
            return new int[]{newX, newY};
        }
        if (direction.equals("right")) {
            newX = entity.x + entity.speed;
            newY = entity.y;
            entity.setPosition(newX, newY);
            return new int[]{newX, newY};
        }
        if (direction.equals("up")) {
            newY = entity.y - entity.speed;
            newX = entity.x;
            entity.setPosition(newX, newY);
            return new int[]{newX, newY};
        }
        if (direction.equals("down")) {
            newY = entity.y + entity.speed;
            newX = entity.x;
            entity.setPosition(newX, newY);
            return new int[]{newX, newY};
        }
        return null;
    }
}
