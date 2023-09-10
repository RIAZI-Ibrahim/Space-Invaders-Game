package api;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre JFrame (bibliothèque Swing)
 */
public class SwingWindow {
    /**
     * Instance unique
     */
    private static SwingWindow instance;

    /**
     * Constructeur privé
     */
    private SwingWindow() {}

    /**
     * Récupérer l'instance
     * @return instance
     */
    public static SwingWindow getInstance() {
        if (instance == null) instance = new SwingWindow();
        return instance;
    }

    /**
     * Fenêtre
     */
    private final JFrame window = new JFrame();

    /**
     * Initialiser la fenêtre
     * @param title titre
     */
    public void initWindow(String title) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(title);
        window.setVisible(true);
    }

    /**
     * Terminer l'exécution de la fenêtre
     */
    public void stopWindow() {
        window.dispose();
    }

    /**
     * Rafraichir la fenêtre
     */
    public void refreshWindow() {
        SwingScene currentScene = getCurrentScene();
        if (currentScene != null)
            currentScene.repaint();
    }

    /**
     * Afficher une scène
     * @param scene scène
     */
    public void showScene(SwingScene scene) {
        //Suppression des éléments de la scène
        window.getContentPane().removeAll();
        //Ajout de la nouvelle scène
        window.getContentPane().add(scene, 0);
        //Dimensionnement de la fenêtre à la scène
        window.pack();
        //Centrer la fenêtre
        window.setLocationRelativeTo(null);
        //Définition du fond de la fenêtre
        setBackgroundColor(scene.backgroundColor);

    }

    /**
     * Définir la couleur de fond
     * @param color couleur
     */
    public void setBackgroundColor(Color color) {
        window.getContentPane().setBackground(color);
    }

    /**
     * Obtenir la scène courante
     * @return scène actuelle
     */
    public SwingScene getCurrentScene() {
        return (SwingScene) window.getContentPane().getComponent(0);
    }

    /**
     * Obtenir la fenêtre courante
     * @return fenêtre courante
     */
    public JFrame getWindow() { return window; }

    /**
     * Récupérer la marge supérieure inutile
     * @return marge supérieure
     */
    public int getUselessTopGap() {
        return window.getHeight()-window.getContentPane().getHeight();
    }
}
