package engines.graphics;

import api.SwingAPI;
import api.SwingWindow;

import java.awt.*;

/**
 * Fenêtre
 */
public class Window extends SwingAPI {
    /**
     * Hauteur
     */
    private static int height;

    /**
     * Largeur
     */
    private static int width;

    /**
     * Échelle
     */
    private static int scale = 1;

    /**
     * Titre de la fenêtre
     */
    private static String title = "";

    /**
     * Scène affichée
     */
    private static Scene actualScene;

    /**
     * Afficher la fenêtre
     */
    protected static void show() {
        try {
            if (actualScene == null)
                throw new Exception("Aucune scène spécifiée");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        init();
    }

    /**
     * Initialiser la fenêtre
     */
    private static void init() {
        SwingWindow.getInstance().initWindow(title);
    }

    /**
     * Rafraichir la fenêtre
     */
    protected static void refresh() { SwingWindow.getInstance().refreshWindow(); }

    /**
     * Terminer l'exécution de la fenêtre
     */
    protected static void stop() {
        SwingWindow.getInstance().stopWindow();
    }

    /**
     * Attacher une scène
     * @param scene scène
     */
    protected static void bindScene(Scene scene) {
        Window.actualScene = scene;
        Window.height = scene.getHeight();
        Window.width = scene.getWidth();
        SwingWindow.getInstance().showScene(actualScene);
    }

    /**
     * Définir la couleur de fond de la fenêtre
     * @param color couleur
     */
    protected void setBackgroundColor(Color color) {
        SwingWindow.getInstance().setBackgroundColor(color);
    }

    // GETTERS & SETTERS //

    public static int getScale() {
        return scale;
    }

    public static void setScale(int scale) {
        Window.scale = scale;
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        Window.title = title;
    }

    public static int getHeight() { return height; }

    public static int getWidth() { return width; }

    public static Scene getActualScene() { return actualScene; }
}
