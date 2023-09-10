package engines.command;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    /**
     * KeyHandler qui implémente KeyListenner
     */


    public boolean UpPressed, downPressed, rightPressed, leftPressed, STyped , enterPressed, Rtyped;

    /**
     *
     * prend en parametre @param keyEvent et selon l'entrée tapée met le boolean correspondant à true
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        char code = keyEvent.getKeyChar();
        if (code == 's'  || code == 'S' ) {
            STyped = true;
        }
        if (code == 'r'  || code == 'R' ) {
            Rtyped = true;
        }

    }

    /**
     *
     * prend en parametre @param keyEvent et selon l'entrée pressed met le boolean correspondant à true
     */

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_UP) {
            UpPressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            enterPressed = true;
        }

    }

    /**
     *
     * prend en parametre @param keyEvent et selon l'entrée relâchée  met le boolean correspondant à false
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_UP) {
            UpPressed = false;
        }
        if (code == 's'  || code == 'S' ) {
            STyped = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            enterPressed = false;
        }
        if (code == 'r'  || code == 'R' ) {
            Rtyped = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }

    }

}

