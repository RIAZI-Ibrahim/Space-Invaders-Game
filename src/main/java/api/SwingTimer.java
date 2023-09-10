package api;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Timer Swing
 */
public class SwingTimer {
    /**
     * Instance unique
     */
    private static SwingTimer instance;

    /**
     * Timers associés à leur nom
     */
    private final Map<String,Timer> timers = new HashMap<>();

    /**
     * Constructeur
     */
    private SwingTimer() {}

    /**
     * Récupérer l'instance unique
     * @return instance
     */
    public static SwingTimer getInstance() {
        if (instance == null) instance = new SwingTimer();
        return instance;
    }

    /**
     * Démarrer un timer
     * @param name nom
     * @param delay délai
     * @param command commande
     */
    public void startTimer(String name, int delay, Runnable command) {
        if (!timers.containsKey(name))
            timers.put(name, new Timer(delay, e -> command.run()));
        timers.get(name).start();
    }

    /**
     * Redémarrer un timer
     * @param name nom
     */
    public void restartTimer(String name) { timers.get(name).restart(); }

    /**
     * Stopper un timer
     * @param name nom
     */
    public void stopTimer(String name) {
        timers.get(name).stop();
    }

    // GETTERS //

    public Map<String, Timer> getTimers() {
        return timers;
    }
}
