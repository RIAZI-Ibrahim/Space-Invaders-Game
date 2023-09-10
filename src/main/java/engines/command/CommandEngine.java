package engines.command;

import api.SwingAPI;

public class CommandEngine {
    public KeyHandler keyHandler ;

    /**
     * CommandEngine qui prend en @param un objet de type KeyHandler
     */

    public CommandEngine() {
        keyHandler = new KeyHandler();
    }

    /**
     * activer les entrées/sorties clavier
     */

    public void enableKeyboardIO() {
        SwingAPI.getListenerMethods().addKeyListener(keyHandler);
    }

    /**
     * Désactiver les entrées/sorties clavier
     */
    public void disableKeyboardIO() {
        SwingAPI.getListenerMethods().removeKeyListener(keyHandler);
    }
}
