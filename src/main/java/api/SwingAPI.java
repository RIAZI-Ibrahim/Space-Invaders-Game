package api;

/**
 * API Swing
 */
public class SwingAPI {
    /**
     * Récupérer les méthodes pour les écouteurs
     * @return instance SwingListener
     */
    public static SwingListener getListenerMethods() {
        return SwingListener.getInstance();
    }

    /**
     * Récupérer les méthodes pour le rendement graphique
     * @return instance SwingRenderer
     */
    protected static SwingRenderer getRendererMethods() {
        return SwingRenderer.getInstance();
    }

    /**
     * Récupérer les méthodes pour la fenêtre
     * @return instance SwingWindow
     */
    protected static SwingWindow getWindowMethods() {
        return SwingWindow.getInstance().getInstance();
    }
}
