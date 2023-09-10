package engines.audio;


import javax.sound.sampled.*;
import java.io.File;

public class Audio {

    public Clip clip;

    /**
     * Audio represente le moteur Audio du jeu
     */


    /**
     * Constructeur
     */
    public Audio(){}


    /**
     * @param path prends le chemin d'un ficher.wav et le transforme en Clip Object
     */

    public void setFile(String path){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * fonction qui sert à démarrer une piste audio
     */
    public void play(){
        clip.start();
    }

    /**
     * fonction qui sert à looper sur une piste audio
     */
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * fonction qui sert à arrêter une piste audio
     */
    public void stop(){
        clip.stop();
    }



}
