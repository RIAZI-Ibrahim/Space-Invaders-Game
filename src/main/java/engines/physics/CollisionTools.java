package engines.physics;


public class CollisionTools {
    static int margeX = 17;
    public static boolean checkCollisionWorld(int heightW, int widthW, PhysicalObject entity, int newX, int newY){
        return  !((newY >= heightW - entity.height || newY < 0) || (newX > widthW - entity.width - margeX || newX < margeX));
    }
    public static boolean checkCollisionRight(int widthW, int widthObject, int newX){
        return  !(newX > widthW - widthObject - margeX);
    }
    public static boolean checkCollisionLeft(int newX){
        return  !(newX < margeX);
    }

    public static boolean checkCollisionObject(int x1, int y1, int width1, int x2, int y2, int height2, int width2){
        return  !((y1 > y2 + height2 || y1 < y2 - height2)
                || (x1 + width1 < x2 || x1 > x2 + width2));
    }
}
