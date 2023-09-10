package engines.graphics;

import api.SwingScene;

import java.awt.*;
import java.util.ArrayList;

/**
 * Scène
 */
public class Scene extends SwingScene {
    /**
     * Moteur graphique
     */
    private final GraphicalEngine graphicsEngine;

    /**
     * Liste des entités présentes dans la scène
     */
    private final ArrayList<GraphicalObject> entities = new ArrayList<>();


    private Color color;

    /**
     * Consctructeur par défaut
     * @param height hateur
     * @param width largeur
     */
    protected Scene(GraphicalEngine graphicsEngine, int height, int width) {
        super(height, width);
        this.graphicsEngine = graphicsEngine;
    }



    /**
     * Ajouter une entité à la scène
     * @param entity entité
     */
    protected void addEntity(GraphicalObject entity) {
        if (!entities.contains(entity)) {
            entities.add(entity);
            entity.setScene(this);
        }
    }

    /**
     * Supprimer une entité présente sur la scène
     * @param entity entité
     */
    protected void removeEntity(GraphicalObject entity) {
        entities.remove(entity);
    }

    /**
     * Définir la couleur de fond
     * @param color couleur
     */
    protected void setBackgroundColor(Color color) {
        this.color = color;
        super.setBackgroundColor(color.getRed(), color.getGreen(), color.getBlue());
    }

   /* @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g.create();
        graphics.setColor(backgroundColor);
        graphics.fillRect(xLocation, yLocation, width, height);
        for (int i = 0; i < entities.size(); i++)
            graphicsEngine.paint(entities.get(i));
        graphics.dispose();
    }*/

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g.create();
        graphics.setColor(backgroundColor);
        graphics.fillRect(xLocation, yLocation, width, height);
        for (int i = 0; i < entities.size(); i++){
            graphicsEngine.paint(entities.get(i));
            graphicsEngine.paintRect(entities.get(i));
            graphicsEngine.paintText(entities.get(i));
        }
        graphics.dispose();
    }


    // GETTERS //

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<GraphicalObject> getEntities() {
        return entities;
    }

    public GraphicalEngine getGraphicsEngine() {
        return graphicsEngine;
    }


    public Color getColor() {
        return color;
    }
}
