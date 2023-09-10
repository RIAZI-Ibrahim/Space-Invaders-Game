package engines.physics;

import java.awt.*;

public class PhysicalObject {

    public int x ;

    public int y ;

    public Point position;

    public String name;

    public int width;

    public int height;

    public int speed ;

    public PhysicalObject(String name, int x, int y, int width, int height, int speed) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.position = new Point(x,y);
    }

    public PhysicalObject(int x , int y ){
        this.x = x;
        this.y = y ;
        this.name = "physicalObject";
        this.position = new Point(x,y);
    }

    public PhysicalObject(Point position){
        this.x = position.x;
        this.y = position.y ;
        this.name = "physicalObject";
        this.position =position;
    }




    public Point getPosition() {
        return position;
    }

    public void setPosition(String name ,Point position) {
        this.name = name;
        this.position = position;
        this.x=position.x;
        this.y=position.y;
    }
    public void setPosition(int x, int y) {
        this.x= x;
        this.y= y;
        this.position.x = x;
        this.position.y = y;


    }

    @Override
    public String toString() {
        return "PhysicalObject{" +
                "position=" + position +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


}
