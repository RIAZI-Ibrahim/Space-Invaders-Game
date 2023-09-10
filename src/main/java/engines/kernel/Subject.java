package engines.kernel;

import engines.AI.AIObject;
import engines.graphics.GraphicalObject;
import engines.physics.PhysicalObject;

public interface Subject {

    //methods to register and unregister observers
    public void register(Observer obj);
    public void unregister(Observer obj);

    //method to notify observers of change
    public void notifyObservers();

    //method to get updates from subject
    public PhysicalObject getPhysicalUpdate(Observer obj);

    public GraphicalObject getGraphicalUpdate(Observer obj);

    public AIObject getAiUpdate(Observer obj);

}
