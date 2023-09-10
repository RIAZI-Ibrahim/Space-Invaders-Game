package api;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Écouteur d'actions utilisateur Swing
 */
public class SwingListener {
    /**
     * Instance unique
     */
    private static SwingListener instance;

    /**
     * Constructeur privé
     */
    private SwingListener() {}

    /**
     * Récupérer l'instance
     * @return instance
     */
    protected static SwingListener getInstance() {
        if (instance == null) instance = new SwingListener();
        return instance;
    }

    /**
     * Ajouter un écouteur sur les touches du clavier
     * @param listener écouteur
     */
    public void addKeyListener(KeyListener listener) {
        SwingWindow.getInstance().getWindow().addKeyListener(listener);
    }

    /**
     * Supprimer un écouteur sur les touches du clavier
     * @param listener écouteur
     */
    public void removeKeyListener(KeyListener listener) {
        SwingWindow.getInstance().getWindow().removeKeyListener(listener);
    }

    /**
     * Ajouter un écouteur sur les boutons de souris
     * @param listener écouteur
     */
    public void addMouseListener(MouseListener listener) {
        SwingWindow.getInstance().getWindow().addMouseListener(listener);
    }

    /**
     * Supprimer un écouteur sur les boutons de souris
     * @param listener écouteur
     */
    public void removeMouseListener(MouseListener listener) {
        SwingWindow.getInstance().getWindow().removeMouseListener(listener);
    }
}
