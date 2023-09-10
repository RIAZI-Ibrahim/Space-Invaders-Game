package engines.kernel;

import engines.graphics.GraphicalObject;

public interface Observer {

    //method to updateEntities the observer, used by subject
    public void updateEntities();

    //attach with subject to observe
    public void setSubject(Subject sub);
}
