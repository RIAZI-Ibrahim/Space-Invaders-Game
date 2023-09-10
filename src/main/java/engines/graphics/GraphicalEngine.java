package engines.graphics;

import api.SwingRenderer;
import engines.kernel.Entity;
import java.awt.*;



/**
 * An extension of javax.swing.JFrame that can draw images.
 */
public class GraphicalEngine   {


    public GraphicalEngine() {
    }


    /**
     * @param height  hauteur de la scene
     * @param width   largeur de la scene
     * @return un nouvel Objet de type scene
     */


    public Scene generateScene(int height, int width) {
        return new Scene(this, height, width);
    }


    /**
     *
     * @param scene
     * @param r
     * @param g
     * @param b
     * Prends une scene en parameter et trois int
     * Chaque paramètre (rouge, vert et bleu) définit l'intensité de la couleur sous la forme d'un entier compris entre 0 et 255.
     * Et définit la couleur d'arrière-plan de la scène
     */
    public void setSceneBackgroundColor(Scene scene, int r, int g, int b) {
        scene.setBackgroundColor(new Color(r,g,b));
    }


    public void refreshWindow() {
        Window.refresh();
    }



    public void paint(GraphicalObject graphicEntity) {
        cover(graphicEntity);
    }


    /**
     *
     * @param graphicalObject
     * paint un objet graphical sur une Scene
     */
    public void cover (GraphicalObject graphicalObject){
        SwingRenderer.getInstance().renderImage(graphicalObject.getImage(),graphicalObject.position.x,graphicalObject.position.y);
    }

    public void paintRect(GraphicalObject graphicalentity){
        coverRect(graphicalentity);
    }


    /**
     * @param graphicalEntity
     * paint un objet graphical de type text sur une Scene
     */
    public void paintText(GraphicalObject graphicalEntity){
        coverTxt(graphicalEntity);
    }

    public void coverTxt(GraphicalObject graphicalObject){
        SwingRenderer.getInstance().renderText(graphicalObject.texte,graphicalObject.color, graphicalObject.fontTextSize,
                graphicalObject.centerTexte, graphicalObject.position.x, graphicalObject.position.y, 20, 20);
    }


    public void coverRect(GraphicalObject graphicalObject){
        if (graphicalObject.getColoredRectangle() != null)
            SwingRenderer.getInstance().renderRect(graphicalObject.getColoredRectangle().height, graphicalObject.getColoredRectangle().width, graphicalObject.position.x, graphicalObject.position.y, graphicalObject.color);

    }

    /**
     * @param scene
     * @param entity
     * ajouter une Graphical Object a la scene
     */

    public void addToScene(Scene scene, GraphicalObject entity) {
        scene.addEntity(entity);
    }

    /**
     * affiche la scene dans une fenêtre
     */
    public void showWindow() {
        Window.show();
    }

    /**
     *
     * rajoute @param scene sur une fenêtre
     */

    public void bindScene(Scene scene) {
        Window.bindScene(scene);
    }

    /**
     *
     * @param graphicalObject
     * @param x
     * @param y
     * définit la position x et y du graphical Objet dans la scene
     */
    public void movable(GraphicalObject graphicalObject , int x , int y  ){
        graphicalObject.setPosition(new Point(x, y));
    }

    /**
     * @param entity
     * supprime le graphicalEntity  de la scene
     */
    public void erase(GraphicalObject entity) {
        Scene scene = entity.scene;
        if (scene != null)
            scene.removeEntity(entity);
    }
}
