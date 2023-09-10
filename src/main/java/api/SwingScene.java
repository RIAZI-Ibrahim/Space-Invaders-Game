package api;

import javax.swing.*;
import java.awt.*;

/**
 * Scène (JPanel) utilisant la librairie Swing
 */
public class SwingScene extends JPanel {
    /**
     * Hauteur
     */
    protected int height;

    /**
     * Largeur
     */
    protected int width;

    /**
     * Position horizontale
     */
    protected int xLocation;

    /**
     * Position verticale
     */
    protected int yLocation;

    /**
     * Graphismes 2D
     */
    protected Graphics2D graphics;

    /**
     * Couleur de fond
     */
    protected Color backgroundColor;

    /**
     * Constructeur
     * @param height hauteur
     * @param width largeur
     */
    protected SwingScene(int height, int width) {
        this.height = height;
        this.width = width;
        this.xLocation = 0;
        this.yLocation = 0;
        this.backgroundColor = Color.black;
        setBackground(backgroundColor);
    }

    /**
     * Définir la position de la scène dans la fenêtre
     * @param x position horizontale
     * @param y position verticale
     */
    public void setLocation(int x, int y) {
        xLocation = x;
        yLocation = y;
    }

    /**
     * Définir la taille de la scène
     * @param height hauteur
     * @param width largeur
     */
    public void setSize(int height, int width) {
        this.height = height;
        this.width = width;
    }

    /**
     * Définir la couleur de fond
     * @param red rouge
     * @param green vert
     * @param blue bleu
     */
    public void setBackgroundColor(int red, int green, int blue) {
        backgroundColor = new Color(red, green, blue);
        SwingWindow.getInstance().setBackgroundColor(backgroundColor);
        setBackground(backgroundColor);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
    }

    // GETTERS //

    public Graphics2D get2DGraphics() { return graphics; }

    @Override
    public int getHeight() { return height; }

    @Override
    public int getWidth() { return width; }

    public int getXLocation() { return xLocation; }

    public int getYLocation() { return yLocation; }
}
