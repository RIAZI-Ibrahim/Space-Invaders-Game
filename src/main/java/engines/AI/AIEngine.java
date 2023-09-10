package engines.AI;

public class AIEngine {
    /**
     *
     * AIEngine
     */


    /**
     *
     * @param entity
     * @param direction
     * @return un tableau [x, y] des nouvelles cordonnées de l'objet selon la direction
     */
    public int[] move(AIObject entity,String direction){
        int newX, newY;
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
            newY = entity.y - 2 * entity.speed;
            newX = entity.x;
            entity.setPosition(newX, newY);
            return new int[]{newX, newY};
        }
        if (direction.equals("down")) {
            newY = entity.y + 2 * entity.speed;
            newX = entity.x;
            entity.setPosition(newX, newY);
            return new int[]{newX, newY};
        }
        return null;
    }
}
