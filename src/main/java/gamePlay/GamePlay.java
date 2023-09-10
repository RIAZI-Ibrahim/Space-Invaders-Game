package gamePlay;

import engines.graphics.Scene;
import engines.kernel.Entity;
import engines.kernel.Kernel;


import java.awt.Color;
import java.io.IOException;
import java.util.*;


public class GamePlay implements Runnable {

    Kernel kernel;

    Thread gameThread;

    Entity player;

    List<List<Entity>> aliens;

    static int[] killedAlienPostion = new int[2];

    static int[] choosedAlienPostion = new int[2];


    ArrayList<Bullet> shoots;


    public HashMap<SONG , String > SongsMap;

    static int ctpTours;

    static int timeS;

    int score;

    int nbDown;

    int remaningLife;

    boolean isShooted;

    boolean isKilled;

    boolean inGame;

    boolean pos1;

    boolean iCanDown;

    boolean upLevel;

    boolean leftFlag;

    boolean isplayed;

    ArrayList<Entity> entitiesGame;

    int heightWorld;

    int liminteHeightWorld = 60;

    int widthWorld;

    int speedAliens;


    ArrayList<Castle[][]> castle;

    Saucer saucer;

    List<String> pathsAliens;

    List<String> pathsPlayer;

    boolean isSaucer = false;

    Entity scoreEntity;

    Entity timeEntity;

    Entity greenBar;

    Deque<Entity> viewLife;

    public Scene gameScene;

    public Scene menuView;

    private Scene winView;

    private Scene looseView;

    public GamePlay() throws IOException {

        SongsMap = new HashMap<>();

        killedAlienPostion[0] = -1;

        killedAlienPostion[1] = -1;

        kernel = new Kernel();

        shoots = new ArrayList<>();

        castle = new ArrayList<>();

        pathsAliens = new ArrayList<>();
        pathsAliens.add("src/main/resources/assets/images/Aliens/alienHaut1.png");
        pathsAliens.add("src/main/resources/assets/images/Aliens/alienHaut2.png");
        pathsAliens.add("src/main/resources/assets/images/Aliens/alienMeurt.png");

        pathsPlayer = new ArrayList<>();
        pathsPlayer.add("src/main/resources/assets/images/Spacecraft/craft.png");
        pathsPlayer.add("src/main/resources/assets/images/Spacecraft/destroy.png");

        viewLife = new LinkedList<>();


        initSongs();

        heightWorld = 600;
        widthWorld = 600;

        // je bind la gameScene au Jframe
        initMenuView();

        gameScene = kernel.generateScene(heightWorld, widthWorld);
        kernel.bindScene(gameScene);

        //initGamePlay();
        kernel.enableKeyboardIO();
        kernel.start();

    }

    public void initGameEntities() throws IOException {

        entitiesGame = new ArrayList<>();

        //-------------------------------Player initialization-------------------------------------------------//

        initPlayer();

        //-------------------------------Castel initialization-------------------------------------------------//

        initCastle();

        //-------------------------------Aliens initialization-----------------------------------------------------//

        initAliens();

        //-------------------------------Saucer initialization-------------------------------------------------//

        initSaucer();

        //----------------------------------------------------------------------------------------------------------//

        }


    public void startGameThread() {
        kernel.switchScene(menuView);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void initEntity(Entity entity) {
        entity.register(kernel);
        kernel.setSubject(entity);
        entitiesGame.add(entity);
        kernel.addToScene(gameScene, entity);
    }

    public void initPlayer() throws IOException {
        player = new Player(32, 32);

        initEntity(player);

        player.setPyhsicalObjectPositions(300, 500);

        player.killed = false;

        isKilled = false;
    }

    public void initAliens() {
        aliens = new ArrayList<>();
        int x = 560;
        int y = 90;
        for (int i = 0; i < 5; i++) {
            aliens.add(new ArrayList<>());
            for (int j = 0; j < 11; j++) {
                aliens.get(i).add(new Alien(32, 32));
                initEntity(aliens.get(i).get(j));
                aliens.get(i).get(j).setAiObjectPositions(x, y);
                x -= 40;
            }
            y += 40;
            x = 560;
        }
        speedAliens = aliens.get(0).get(0).aiObject.speed;




    }

    public void initSaucer() {
        saucer = new Saucer(32, 32);
        initEntity(saucer);
        saucer.setAiObjectPositions(17, 40);
        isSaucer = true;
    }

    public void initCastle() {
        int ecartCastl = 55;

        for (int i = 0; i < 4; i++) {
            castle.add(new Castle[Castle.nbLines][Castle.nbColumns]);
        }
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < Castle.nbLines; i++) {
                for (int j = 0; j < Castle.nbColumns; j++) {
                    castle.get(k)[i][j] = new Castle(80 + k * (Castle.widthCastle + ecartCastl), 400);
                    initEntity(castle.get(k)[i][j]);
                    castle.get(k)[i][j].setAiObjectPositions(castle.get(k)[i][j].xPos, castle.get(k)[i][j].yPos);
                }
            }
            initTabCastels(castle.get(k));
            drowCastle(castle.get(k), Castle.nbLines, Castle.nbColumns);
        }

    }

    public void generateSaucer() {
        saucer = new Saucer(32, 32);
        initEntity(saucer);
        saucer.setAiObjectPositions( 18, 40);
        isSaucer = true;
        playMusic(SongsMap.get(SONG.SAUCER_MOUVEMENT));
    }

    public void shoot(Entity entity, boolean isP) {
        Bullet bullet = generateBulletAliens(entity.x + (entity.widthEntity / 2) + 2, entity.y);
        bullet.isPressed = isP;
        if (isP) bullet.setImage("src/main/resources/assets/images/shot.png");
        else bullet.setImage("src/main/resources/assets/images/shotAlien.png");
        shoots.add(bullet);
        kernel.addToScene(gameScene, shoots.get(shoots.indexOf(bullet)));
    }

    public Bullet generateBulletAliens(int x, int y) {
        Bullet bullet = new Bullet(3, 1);
        bullet.setPyhsicalObjectPositions(x, y - bullet.heightEntity);
        initEntity(bullet);
        return bullet;
    }


    public void killBullet(Bullet bullet) {
        kernel.erase(bullet);
        entitiesGame.remove(bullet);
        kernel.entities.remove(bullet);
        if (!bullet.isPressed) {
            shoots.remove(bullet);
        } else {
            shoots.remove(bullet);
            isShooted = false;
        }
    }


    public Kernel getKernel() {
        return kernel;
    }

    @Override
    public void run() {
        double drawInterval = 1_800 / 60;
        double delta = 0;
        long lastTime = System.currentTimeMillis();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.currentTimeMillis();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                try {

                    updatePlayer();
                    updateAliens();
                    updateSaucer();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (ctpTours % 20 == 0) {
                    pos1 = !pos1;
                }

                if (ctpTours % 225 == 0) {
                    aliensShoot();
                }

                if (!isSaucer && upLevel) {
                    generateSaucer();
                    upLevel = false;
                }



                kernel.updateEntities();
                kernel.refreshWindow();
                delta--;
                drawCount++;
                ctpTours++;
            }
            if (timer >= 1000) {
                System.out.println("FPS:" + drawCount);
                aliensShoot();
                drawCount = 0;
                timer = 0;
                timeS += 1;
                if(inGame){
                    if(timeS > 99) {
                        timeEntity.setGraphicalPositions(timeEntity.x - 10, timeEntity.y);
                        kernel.afficheTexte(timeEntity,"TIME : " + timeS + " S");
                    }
                    else kernel.afficheTexte(timeEntity,"TIME : " + timeS + " S");
                }
            }
        }

    }

    public void updatePlayer() throws IOException, InterruptedException {
        if (inGame) {
            if (kernel.getKeyHandler().leftPressed) {
                kernel.isCollide(heightWorld, widthWorld, player, player.x - player.getSpeed(),
                        player.y, entitiesGame);
                if (!player.getAndResetCollision())
                    kernel.move(player, "left");
            }

            if (kernel.getKeyHandler().rightPressed) {
                kernel.isCollide(heightWorld, widthWorld, player, player.x + player.getSpeed(),
                        player.y, entitiesGame);
                if (!player.getAndResetCollision())
                    kernel.move(player, "right");

            }

            if (kernel.getKeyHandler().STyped) {
                if (!isShooted && !isKilled) {
                    shoot(player, true);
                    isShooted = true;
                    playSE(SongsMap.get(SONG.PLAYER_SHOOT));
                }
            }
            Bullet bullet ;

            for (int i = 0; i < shoots.size(); i++) {
                bullet = shoots.get(i);
                if (bullet.isPressed) {
                    if (bullet.y < -5) {
                        killBullet(bullet);
                    } else {
                        alienKilled(bullet, aliens);
                        getBrickToEliminate(bullet);
                        destroySaucer(saucer, bullet);
                        if (isShooted) bullet.tick();
                    }
                }
                if (!bullet.isPressed) {
                    if (bullet.y <= heightWorld - liminteHeightWorld) {
                        killPlayerBullet(bullet, bullet.x, bullet.y + bullet.getSpeed(), player);
                        getBrickToEliminate(bullet);
                        destroySaucer(saucer, bullet);
                        bullet.tick();
                    } else killBullet(bullet);
                }
            }
        }

        if (kernel.getKeyHandler().enterPressed && !isKilled && !inGame) {
            initGamePlay();
            kernel.switchScene(gameScene);
            inGame = true;
        }
        if (kernel.getKeyHandler().Rtyped && isKilled) {
            initMenuView();
            kernel.switchScene(menuView);
            isKilled = false;
            initGamePlay();
        }
    }

    public void chooseDirection() throws IOException {
        if (kernel.isCollideWithRightdboard(aliens, widthWorld)) {
            for (List<Entity> alien : aliens) {
                for (Entity entity : alien) {
                    if (entity != null) {
                        killPlayerWithCollision(entity, entity.x - entity.getSpeed(),
                                entity.y, player);
                        kernel.move(entity, "left");
                        chooseImage(entity, pos1, pathsAliens);
                    }
                }
            }
            leftFlag = true;
        } else if (kernel.isCollideWithLefdboard(aliens)) {
            for (int i = 0; i < aliens.size(); i++) {
                for (int j = 0; j < aliens.get(i).size(); j++) {
                    if (aliens.get(i).get(j) != null && !player.killed && iCanDown) {
                        if (aliensToucheCastle(aliens.get(i).get(j), aliens.get(i).get(j).x, (aliens.size() - 1 - i) * 40 + aliens.get(i).get(j).y + aliens.get(i).get(j).getSpeed() + 12)) {
                            kernel.move(aliens.get(0).get(0), "up");
                            kernel.move(aliens.get(0).get(1), "up");
                            iCanDown = false;
                        }
                        killPlayerWithCollision(aliens.get(i).get(j), aliens.get(i).get(j).x,
                                aliens.get(i).get(j).y + aliens.get(i).get(j).getSpeed(), player);
                        kernel.move(aliens.get(i).get(j), "down");
                        chooseImage(aliens.get(i).get(j), pos1, pathsAliens);
                    }
                }
            }
            nbDown++;
            leftFlag = false;
        }
    }

    public void updateAliens() throws IOException {
        if (inGame) {
            if (killedAlienPostion[0] != -1) {
                eliminateAlien(killedAlienPostion);
                killedAlienPostion[0] = -1;
            }
            if (leftFlag) {
                for (List<Entity> alien : aliens) {
                    for (Entity entity : alien) {
                        if (entity != null) {
                            killPlayerWithCollision(entity, entity.x - entity.getSpeed(),
                                    entity.y, player);
                            kernel.move(entity, "left");
                            chooseImage(entity, pos1, pathsAliens);
                        }
                    }
                }
            } else {
                for (List<Entity> alien : aliens) {
                    for (Entity entity : alien) {
                        if (entity != null) {
                            killPlayerWithCollision(entity, entity.x + entity.getSpeed(),
                                    entity.y, player);
                            kernel.move(entity, "right");
                            chooseImage(entity, pos1, pathsAliens);
                            if (entity.getSpeed() <= 3 && nbDown % 9 == 0) {
                                entity.aiObject.speed += 1;
                                upLevel = true;
                            }
                        }


                    }
                }
            }
            chooseDirection();
        }
    }

    public void alienKilled(Entity bulletAlien, List<List<Entity>> aliens) {
        killedAlienPostion[0] = -1;
        killedAlienPostion[1] = -1;
        for (int i = 0; i < aliens.size(); i++) {
            for (int j = 0; j < aliens.get(i).size(); j++) {
                if (aliens.get(i).get(j) != null &&
                        kernel.collideObjectToObject(bulletAlien, aliens.get(i).get(j), bulletAlien.x, bulletAlien.y - bulletAlien.physicalObject.speed)) {
                    killBullet((Bullet) bulletAlien);
                    aliens.get(i).get(j).killed = true;
                    killedAlienPostion[0] = i;
                    killedAlienPostion[1] = j;
                    return;
                }
            }
        }
    }

    public void eliminateAlien(int[] alienKilledPostion) {
        if (!aliens.isEmpty() && !aliens.get(alienKilledPostion[0]).isEmpty()) {
            chooseImage(aliens.get(alienKilledPostion[0]).get(alienKilledPostion[1]), false, pathsAliens);
            score += aliens.get(alienKilledPostion[0]).get(alienKilledPostion[1]).value;
            kernel.afficheTexte(scoreEntity,"SCORE : " + score);
            aliens.get(alienKilledPostion[0]).get(alienKilledPostion[1]).killed = true;
            kernel.erase(aliens.get(alienKilledPostion[0]).get(alienKilledPostion[1]));
            aliens.get(alienKilledPostion[0]).set(alienKilledPostion[1], null);
        }
    }

    public void chooseAlien() {
        Random random = new Random();
        choosedAlienPostion[0] = -1;
        choosedAlienPostion[1] = -1;
        if (!aliens.isEmpty()) {
            do {
                int colonneChoosed = random.nextInt(11);
                for (int i = 0; i < 5; i++) {
                    if (aliens.get(i).get(colonneChoosed) != null) {
                        choosedAlienPostion[0] = i;
                        choosedAlienPostion[1] = colonneChoosed;
                    }

                }
            } while (choosedAlienPostion[0] == -1);
        }
    }

    public void aliensShoot() {
        if (!isKilled && inGame) {
            chooseAlien();
            shoot(aliens.get(choosedAlienPostion[0]).get(choosedAlienPostion[1]), false);
            playSE(SongsMap.get(SONG.ALIEN_SHOOT));
        }
    }

    public void killPlayerBullet(Entity bulletPlayer, int newX, int newY, Entity player) throws IOException, InterruptedException {
        if (kernel.collideObjectToObject(bulletPlayer, player, newX, newY)) {
            killBullet((Bullet) bulletPlayer);
            remaningLife --;
            playSE(SongsMap.get(SONG.PLAYER_KILLED));
            chooseImage(player, true, pathsPlayer);
            kernel.erase(player);
            entitiesGame.remove(player);
            kernel.entities.remove(player);
            if (remaningLife <= 0) {;
                player.killed = true;
                isKilled = true;
                initLooseView();
                kernel.switchScene(looseView);
                inGame = false;
            }
            else {
                kernel.erase(viewLife.pollLast());
                initPlayer();
            }
        }
    }

    public void killPlayerWithCollision(Entity killer, int newX, int newY, Entity player) throws IOException {
        if (kernel.collideObjectToObject(killer, player, newX, newY)) {
            player.killed = true;
            chooseImage(player, true, pathsPlayer);
            kernel.erase(player);
            entitiesGame.remove(player);
            kernel.entities.remove(player);
            isKilled = true;
            inGame = false;
            initLooseView();
            kernel.switchScene(looseView);
        }
    }

    public void initTabCastels(Castle[][] castle) {
        // On remplit toutes les cases du tableau avec true
        for (int i = 0; i < Castle.nbLines; i++) {
            for (int j = 0; j < Castle.nbColumns; j++) {
                castle[i][j].isBrick = true;
                // castle.get(i).get(j). = true;
            }
        }
        // On remplit toutes les cases sans brique du tableau avec false
        // Biseautage du haut du château
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                castle[i][j].isBrick = false;
                castle[i][Castle.nbColumns - j - 1].isBrick = false;
            }
        }
        for (int i = 2; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                castle[i][j].isBrick = false;
                castle[i][Castle.nbColumns - j - 1].isBrick = false;
            }
        }
        for (int i = 4; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                castle[i][j].isBrick = false;
                castle[i][Castle.nbColumns - j - 1].isBrick = false;
            }
        }
        // Entrée du château
        for (int i = 18; i < Castle.nbLines; i++) {
            for (int j = 10; j < Castle.nbColumns - 10; j++) {
                castle[i][j].isBrick = false;
            }
        }
        // Biseautage entrée du château
        for (int i = 16; i < 18; i++) {
            for (int j = 12; j < Castle.nbColumns - 12; j++) {
                castle[i][j].isBrick = false;
                castle[i][Castle.nbColumns - j - 1].isBrick = false;
            }
        }
        for (int i = 14; i < 16; i++) {
            for (int j = 14; j < Castle.nbColumns - 14; j++) {
                castle[i][j].isBrick = false;
                castle[i][Castle.nbColumns - j - 1].isBrick = false;
            }
        }
        for (int i = 4; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                castle[i][j].isBrick = false;
                castle[i][Castle.nbColumns - j - 1].isBrick = false;
            }
        }
    }

    // Dessin du château
    public void drowCastle(Castle[][] castles, int nbLines, int nbColumns) {
        for (int i = 0; i < nbLines; i++) {
            for (int j = 0; j < nbColumns; j++) {
                if (castles[i][j].isBrick) {
                    kernel.paintRectangle(castles[i][j], Color.GREEN, Castle.dimention, Castle.dimention);
                    castles[i][j].setAiObjectPositions(castles[i][j].xPos + 2 * j, castles[i][j].yPos + 2 * i);
                } else {
                    kernel.paintRectangle(castles[i][j], Color.BLACK, Castle.dimention, Castle.dimention);
                    castles[i][j].setAiObjectPositions(castles[i][j].xPos + 2 * j, castles[i][j].yPos + 2 * i);
                }
            }
        }
    }

    public void destroyCastle(Castle castle) {
        kernel.rePaintRectangle(castle, Color.BLACK);
    }


    public boolean aliensToucheCastle(Entity alien, int newX, int newY) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < Castle.nbLines; j++) {
                for (int k = 0; k < Castle.nbColumns; k++) {
                    if (castle.get(i)[j][k].isBrick && kernel.collideObjectToObject(alien, castle.get(i)[j][k], newX, newY))
                        return true;
                    else iCanDown = true;
                }
            }
        }
        return false;
    }

    public void getBrickToEliminate(Bullet bullet) {
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < Castle.nbLines; i++) {
                for (int j = 0; j < Castle.nbColumns; j++) {
                    if (castle.get(k)[i][j].isBrick && kernel.getColorRect((Entity) castle.get(k)[i][j]) != Color.BLACK &&
                            kernel.collideObjectToObject(bullet, castle.get(k)[i][j], bullet.x, bullet.y - bullet.physicalObject.speed)) {
                        destroyCastle(castle.get(k)[i][j]);

                        if (i + 1 < Castle.nbLines && castle.get(k)[i + 1][j].isBrick) {
                            destroyCastle(castle.get(k)[i + 1][j]);
                            castle.get(k)[i][j].isBrick = false;
                        }
                        if (j + 1 < Castle.nbColumns && castle.get(k)[i][j + 1].isBrick) {
                            destroyCastle(castle.get(k)[i][j + 1]);
                            castle.get(k)[i][j].isBrick = false;
                        }
                        if ((j + 1 < Castle.nbColumns && i + 1 < Castle.nbLines) && castle.get(k)[i + 1][j + 1].isBrick) {
                            destroyCastle(castle.get(k)[i + 1][j + 1]);
                            castle.get(k)[i][j].isBrick = false;
                        }


                        if (i - 1 > 0 && castle.get(k)[i - 1][j].isBrick) {
                            destroyCastle(castle.get(k)[i - 1][j]);
                            castle.get(k)[i][j].isBrick = false;
                        }
                        if ((j + 1 < Castle.nbColumns && i - 1 > 0) && castle.get(k)[i - 1][j + 1].isBrick) {
                            destroyCastle(castle.get(k)[i - 1][j + 1]);
                            castle.get(k)[i][j].isBrick = false;
                        }
                        killBullet(bullet);
                        entitiesGame.remove(castle.get(k)[i][j]);
                        break;
                    }
                }
            }
        }
    }

    public void updateSaucer() {
        if (saucer != null && inGame) {
            kernel.move(saucer, "right");
            if (!kernel.isCollideRight(widthWorld, saucer.widthEntity, saucer.x + saucer.getSpeed())) {
                killSauser(saucer);
            }
            if (!isplayed) {
                playSE(SongsMap.get(SONG.SAUCER_MOUVEMENT));
                isplayed = true;
            }
        }
    }

    public void killSauser(Saucer saucer) {
        kernel.erase(saucer);
        entitiesGame.remove(saucer);
        kernel.entities.remove(saucer);
        isSaucer = false;
    }

    public void destroySaucer(Entity saucer, Bullet bullet) {
        if (kernel.collideObjectToObject(bullet, saucer, bullet.x, bullet.y - bullet.physicalObject.speed)) {
            //kernel.stopMusic();
            score += saucer.value;
            Entity scoreAugmentationImage = kernel.creatEntity(100, 30, gameScene);
            scoreAugmentationImage.setImage("src/main/resources/assets/images/soucoupe100.png");
            try{
                Thread.sleep(55);
            }catch (Exception e){}
            kernel.erase(scoreAugmentationImage);
            kernel.afficheTexte(scoreEntity,"SCORE : " + score);
            killBullet(bullet);
            killSauser((Saucer) saucer);
        }

    }

    public void chooseImage(Entity entity, boolean pos1, List<String> paths) {
        // Méthode qui charge l'image de l'alien selon son état et sa position (1 ou 2)
        if (!entity.killed) {
            if (pos1) entity.setImage(paths.get(0));
            else if (paths.get(1) != null) entity.setImage(paths.get(1));
        } else {
            if (paths.size() > 2) {
                System.out.println(paths.get(2));
                entity.setImage(paths.get(2));
            } else {
                entity.setImage(paths.get(1));
            }
        }
    }

    public void initGamePlay() throws IOException {

        score = 0;

        ctpTours = 0;

        timeS = 0;

        nbDown = 0;

        remaningLife = 3;

        isShooted = false;

        isKilled = false;

        inGame = false;

        pos1 = true;

        iCanDown = true;

        leftFlag = true;

        upLevel = false;

        isplayed = false;

        gameScene = kernel.generateScene(heightWorld,widthWorld);
        aliens = new ArrayList<>();
        entitiesGame = new ArrayList<>();
        initGameEntities();

        for (Entity entity : entitiesGame) {
            kernel.addToScene(gameScene, entity);
        }

        greenBar = kernel.creatEntityToDrow(17, 550, Color.GREEN, gameScene);
        kernel.paintRectangle(greenBar, Color.GREEN, 566, 8);

        scoreEntity = kernel.creatEntityToDrow(17, 30, Color.GREEN, gameScene);
        kernel.afficheTexte(scoreEntity, "SCORE : " + score);

        timeEntity = kernel.creatEntityToDrow(490, 30, Color.GREEN, gameScene);
        kernel.afficheTexte(timeEntity, "TIME : " + timeS + "S");

        for (int i = 0; i < 3; i++) {
            Entity entity = kernel.creatEntity((i * 32 + 5) + 18, 560, gameScene);
            entity.setImage("src/main/resources/assets/images/Spacecraft/craft.png");
            viewLife.add(entity);
        }
    }

    public void initMenuView() throws IOException {
        if(isKilled) {
            stopMusic();
        }
        menuView = kernel.menuViewParams();
        playMusic(SongsMap.get(SONG.MENU));
    }

    public void initWinView() throws IOException {
        winView = kernel.winViewParams(score);
    }

    public void initLooseView() throws IOException {
        looseView = kernel.looseViewParams(score);
        stopMusic();
    }

    public void initSongs(){
        SongsMap.put(SONG.MENU , "src/main/resources/assets/sounds/Menu.wav");
        SongsMap.put(SONG.PLAYER_SHOOT,"src/main/resources/assets/sounds/shoot.wav");
        SongsMap.put(SONG.ALIEN_SHOOT,"src/main/resources/assets/sounds/shoot.wav");
        SongsMap.put(SONG.SAUCER_MOUVEMENT,"src/main/resources/assets/sounds/sonSoucoupePasse.wav");
        SongsMap.put(SONG.PLAYER_KILLED,"src/main/resources/assets/sounds/invaderkilled.wav");
    }

    public void playMusic(String path){
        kernel.playMusic(path);
    }
    public void playSE(String path){
        kernel.playSE(path);
    }

    public void stopMusic(){
        kernel.stopMusic();
    }

    public static void main(String[] args) throws IOException {
        GamePlay game = new GamePlay();
        game.startGameThread();
    }
}

