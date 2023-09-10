package api;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Générateur de graphismes à partir de Swing
 */
public class SwingRenderer {
    /**
     * Instance unique
     */
    private static SwingRenderer instance;

    /**
     * Constructeur privé
     */
    private SwingRenderer() {}

    /**
     * Récupérer l'instance
     * @return instance
     */
    public static SwingRenderer getInstance() {
        if (instance == null) instance = new SwingRenderer();
        return instance;
    }

    /**
     * Textures chargées
     */
    private final Map<String,BufferedImage> loadedTextures = new HashMap<>();

    /**
     * Générer un rectangle
     * @param height hauteur en pixels
     * @param width largeur en pixels
     * @param x position horizontale
     * @param y position verticale
     * @param color couleur
     */
    public void renderRect(int height, int width, int x, int y, Color color) {
        Graphics2D graphics2D = getCurrentGraphics();
        if (graphics2D != null) {
            graphics2D.setColor(color);
            x += getCurrentScene().xLocation;
            y += getCurrentScene().yLocation;
            graphics2D.fillRect(x, y, width, height);
        }
    }

    /**
     * Générer un carré texturisé
     * @param height hauteur en pixels
     * @param width largeur en pixels
     * @param x position horizontale
     * @param y position verticale
     * @param link lien vers la texture
     */
    public void renderTexturedRect(int height, int width, int x, int y, String link) {
        Graphics2D graphics2D = getCurrentGraphics();
        if (graphics2D != null) {
            x += getCurrentScene().xLocation;
            y += getCurrentScene().yLocation;
            graphics2D.drawImage(loadedTextures.get(link), x, y, width, height, null);
        }
    }

    /**
     * Générer un texte
     * @param text texte
     * @param color couleur
     * @param fontSize taille de la police
     * @param x position horizontale
     * @param y position verticale
     * @param height hauteur
     * @param width largeur
     * @param center centrer le texte
     */
    public void renderText(String text, Color color, int fontSize, boolean center, int x, int y, int height, int width) {
        Graphics2D graphics2D = getCurrentGraphics();
        if (graphics2D != null) {
            graphics2D.setFont(new Font("Arial", Font.PLAIN, fontSize));
            if (center) {
                FontMetrics metrics = graphics2D.getFontMetrics();
                x = x + (width - metrics.stringWidth(text)) / 2;
                y = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
            }
            x += getCurrentScene().xLocation;
            y += getCurrentScene().yLocation;
            graphics2D.setColor(color);
            graphics2D.drawString(text, x, y);
        }
    }

    public void renderImage(BufferedImage image,int x , int y ){
        Graphics2D graphics2D = getCurrentGraphics();
        if (graphics2D != null) {
            x += getCurrentScene().xLocation;
            y += getCurrentScene().yLocation;
            graphics2D.drawImage(image,x,y,null);
        }
    }



    /**
     * Charger une texture
     * @param link lien du fichier
     */
    public void loadTexture(String link) {
        BufferedImage texture = getBufferedImage(link);
        loadedTextures.put(link, texture);
        texture.flush();
    }

    /**
     * Charger un fichier de textures
     * @param link lien du fichier
     * @param sheetHeight nombre de textures sur la hauteur
     * @param sheetWidth nombre de textures sur la largeur
     */
    public void loadSpriteSheet(String link, int sheetHeight, int sheetWidth) {
        BufferedImage texture = getBufferedImage(link);
        int partWidth = texture.getWidth() / sheetWidth;
        int partHeight = texture.getHeight() / sheetHeight;
        for (int row = 0; row < sheetHeight; row++) {
            for (int col = 0; col < sheetWidth; col++) {
                int cropX = col * partWidth;
                int cropY = row * partHeight;
                loadedTextures.put(link + row + "-" + col, texture.getSubimage(cropX, cropY, partWidth, partHeight));
            }
        }
        texture.flush();
    }

    /**
     * Récupérer une image dans un tampon
     * @param link lien vers l'image
     * @return tampon
     */
    private BufferedImage getBufferedImage(String link) {
        BufferedImage texture = null;
        try {
            texture = ImageIO.read(SwingRenderer.class.getResourceAsStream("/" + link));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return texture;
    }

    /**
     * Vérifier si une texture a été chargée
     * @param link lien de la texture
     * @return booléen
     */
    public boolean isTextureLoaded(String link) {
        return loadedTextures.containsKey(link);
    }

    /**
     * Obtenir une couleur Swing
     * @param red rouge
     * @param green vert
     * @param blue bleu
     * @return couleur swing
     */
    public Color getSwingColor(int red, int green, int blue) {
        return new Color(red, green, blue);
    }

    // GETTERS //

    public Graphics2D getCurrentGraphics() {
        if (SwingWindow.getInstance().getCurrentScene() != null)
            return SwingWindow.getInstance().getCurrentScene().get2DGraphics();
        return null;
    }

    public SwingScene getCurrentScene() {
        return SwingWindow.getInstance().getCurrentScene();
    }
}
