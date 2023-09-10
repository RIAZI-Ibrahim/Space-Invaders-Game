package engines.kernel;

import engines.AI.AIObject;
import engines.graphics.GraphicalObject;
import engines.graphics.Scene;
import engines.physics.CollisionTools;
import engines.physics.PhysicalObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Entity implements Subject{


    public enum Type {Ai, Physical, Graphical}


    public GraphicalObject graphicalObject;
    public PhysicalObject physicalObject;
    public AIObject aiObject;

    public int widthEntity;
    public int heightEntity;

    public boolean killed = false;

    public int x ;

    public int value;

    public int score;

    public int y ;
    private List<Observer> observers;


    public String name = "entity";

    public boolean isCollide = false;

    private boolean changed;

    Type type;

    private final Object MUTEX = new Object();

    public Entity(GraphicalObject graphicalObject, AIObject aiObject) {
        this.graphicalObject = graphicalObject;
        this.aiObject = aiObject;
        this.observers = new ArrayList<>();
        this.type = Type.Ai;
    }

    public Entity(GraphicalObject graphicalObject, PhysicalObject physicalObject) {
        this.graphicalObject = graphicalObject;
        this.physicalObject = physicalObject;
        this.observers = new ArrayList<>();
        this.type = Type.Physical;
    }

    public Entity(GraphicalObject graphicalObject){
        this.graphicalObject = graphicalObject;
        this.observers=new ArrayList<>();
        this.type = Type.Graphical;

    }

    public Entity(int heightEntity, int widthEntity, Type typeEntity, int speed) {
        this.x =  0;
        this.y =  0;
        this.heightEntity = heightEntity;
        this.widthEntity = widthEntity;
        this.graphicalObject = new GraphicalObject(new Point(x,y));
        if (typeEntity == Type.Ai) this.aiObject = new AIObject(name,x,y,widthEntity,heightEntity, speed);
        else this.physicalObject = new PhysicalObject(name,x,y,widthEntity,heightEntity, speed);
        this.observers=new ArrayList<>();
        this.type = typeEntity;
    }



    public GraphicalObject getGraphicalObject() {
        return graphicalObject;
    }

    public void setGraphicalObject(GraphicalObject graphicalObject) {
        this.graphicalObject = graphicalObject;
    }

    public PhysicalObject getPhysicalObject() {
        return physicalObject;
    }

    public void setPhysicalObject(PhysicalObject physicalObject) {
        this.physicalObject = physicalObject;
    }
    public AIObject getAiObject() {
        return aiObject;
    }

    public void setAiObject(AIObject aiObject) {
        this.aiObject = aiObject;
    }

    @Override
    public void register(Observer obj) {
        if(obj == null) throw new NullPointerException("Null Observer");
        synchronized (MUTEX) {
            if(!observers.contains(obj)) observers.add(obj);
        }
    }

    @Override
    public void unregister(Observer obj) {
        synchronized (MUTEX) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed)
                return;
            observersLocal = new ArrayList<>(this.observers);
            this.changed=false;
        }
        for (Observer obj : observersLocal) {
            obj.updateEntities();
            // y aura que le kernel
        }
    }

    @Override
    public String toString() {
        return "Entity{" +
                "graphicalObject=" + graphicalObject +
                ", physicalObject=" + physicalObject +
                '}';
    }

    @Override
    public PhysicalObject getPhysicalUpdate(Observer obj) {
        return this.physicalObject;
    }

    @Override
    public GraphicalObject getGraphicalUpdate(Observer obj) {
        return this.graphicalObject;
    }

    @Override
    public AIObject getAiUpdate(Observer obj) {
        return this.aiObject;
    }


    public void move(int x, int y){
        this.x =x ;
        this.y = y;
        notifyObservers();
    }

    public void setPhysicalPositions(int x,int y ){
        this.physicalObject.setPosition(this.name,new Point(x,y));
        notifyObservers();
    }

    public void setGraphicalPositions(int x , int y ){
        this.graphicalObject.setPosition(new Point(x,y));
        notifyObservers();
    }

    public void setAiPositions(int x , int y ){
        this.aiObject.setPosition(new Point(x,y));
        notifyObservers();
    }

    public void setPyhsicalObjectPositions(int x , int y ){
        this.x = x;
        this.y = y;
        setPhysicalPositions(x, y);
        setGraphicalPositions(x, y);
    }

    public void setAiObjectPositions(int x , int y ){
        this.x = x;
        this.y = y;
        setAiPositions(x, y);
        setGraphicalPositions(x, y);
    }

    public void setCollision(boolean isCollide){
        this.isCollide = isCollide;
    }

    public boolean getAndResetCollision(){
        boolean temp = isCollide;
        this.isCollide = false;
        return temp;
    }
    public void setImage(String imagePath){
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            graphicalObject.setImage(image);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Observer> getObservers() {
        return observers;
    }

    public int getSpeed(){
        if (this.type == Type.Physical)
             return this.physicalObject.getSpeed();
        else return this.aiObject.getSpeed();
    }


}


