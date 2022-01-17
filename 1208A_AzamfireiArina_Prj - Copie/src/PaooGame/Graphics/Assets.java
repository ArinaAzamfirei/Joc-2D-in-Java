package PaooGame.Graphics;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
    static boolean flag=false;
    static long now;
    static long then;
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    ///erou
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;
    public static BufferedImage heroUp;
    public static BufferedImage heroDown;

    ///inamic 1
    public  static  BufferedImage Enemy1Left;
    public  static BufferedImage Enemy1Right;
    public  static BufferedImage Enemy1Up;
    public  static BufferedImage Enemy1Down;

    ///inamic2
    public  static  BufferedImage Enemy2Left;
    public  static BufferedImage Enemy2Right;
    public  static BufferedImage Enemy2Up;
    public  static BufferedImage Enemy2Down;

    ///elementele hartii
    public static BufferedImage grass;
    public static BufferedImage tree;
    public static BufferedImage tree2;
    public static BufferedImage bush;
    public static BufferedImage treestump;
    public static BufferedImage darktree;
    public static BufferedImage darkgrass;
    public static BufferedImage thorns;
    public static BufferedImage darkstone;
    public static BufferedImage tulip;

    ///States.
    public static BufferedImage menu;  // background-ul meniului
    public static BufferedImage about; // informatii referitoare la joc
    public static BufferedImage settings; // setarile jocului
    public static  BufferedImage YouLost; //imaginea cand pierde jucatorul
    public static BufferedImage YouWon;//imaginea cand se castiga jocul

    ///Soundtrack-ul jocului.
    public static AudioInputStream menuBackgroundMusic;
    public static AudioInputStream level1BackgroundMusic;
    public static AudioInputStream level2BackgroundMusic;
    public static AudioInputStream tulippickSound;
    public static Clip tulippick;
    public static Clip menuMusic;
    public static Clip level1music;
    public static Clip level2music;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet characterSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/stella.png"), 64,64);
        SpriteSheet enemy1Sheet= new SpriteSheet(ImageLoader.LoadImage("/textures/Monster2.png"), 74,98);
        SpriteSheet enemy2Sheet= new SpriteSheet(ImageLoader.LoadImage("/textures/Monster1.png"),98,98);
        SpriteSheet tileSheet= new SpriteSheet(ImageLoader.LoadImage("/textures/spritesheet.png"), 64,64);
            /// Se obtin subimaginile corespunzatoare elementelor necesare.

        ///Imagini pentru States.
        menu=ImageLoader.LoadImage("/textures/Meniu.jpg");
        about=ImageLoader.LoadImage("/textures/About.jpg");
        settings=ImageLoader.LoadImage("/textures/Settings.jpg");
        YouLost=ImageLoader.LoadImage("/textures/bkg.png");
        YouWon=ImageLoader.LoadImage("/textures/bkg2.png");

        ///Cropam imaginile necesare pentru caracterul principal.
        heroDown = characterSheet.crop(0,0);
        heroLeft = characterSheet.crop(1, 0);
        heroRight = characterSheet.crop(2, 0);
        heroUp = characterSheet.crop(3,0);

        ///Cropam imaginile necesare pentru inamicul nr.1.
        Enemy1Right= enemy1Sheet.crop(0,1);
        Enemy1Left= enemy1Sheet.crop(1,0);
        Enemy1Up= enemy1Sheet.crop(0,0);
        Enemy1Down= enemy1Sheet.crop(2,0);

        ///Cropam imaginile necesare pentru inamicul nr.2.
        Enemy2Up= enemy2Sheet.crop(0,0);
        Enemy2Down= enemy2Sheet.crop(0,0);
        Enemy2Left= enemy2Sheet.crop(0,0);
        Enemy2Right=enemy2Sheet.crop(0,0);

        ///Cropam imaginile necesare pentru tiles.
        grass=tileSheet.crop(1,2);
        tree= tileSheet.crop(1,1);
        tree2=tileSheet.crop(0,2);
        bush= tileSheet.crop(0,1);
        treestump=tileSheet.crop(2,2);
        darktree=tileSheet.crop(2,1);;
        darkgrass=tileSheet.crop(1,0);;
        thorns=tileSheet.crop(0,0);;
        darkstone=tileSheet.crop(2,0);;
        tulip=ImageLoader.LoadImage("/textures/tulip.png");

        ///Citirea fisierelor wav pentru sunet.
        menuBackgroundMusic = AudioLoader.LoadAudio("/menuStateMusic.wav");
        level1BackgroundMusic= AudioLoader.LoadAudio("/level1StateMusic (mp3cut.net) (1).wav");
        level2BackgroundMusic=AudioLoader.LoadAudio("/level2StateMusic.wav");
        tulippickSound= AudioLoader.LoadAudio("/gmae.wav");
        try {

            menuMusic = AudioSystem.getClip();
            menuMusic.open(menuBackgroundMusic);
            level1music=AudioSystem.getClip();
            level1music.open(level1BackgroundMusic);
            level2music=AudioSystem.getClip();
            level2music.open(level2BackgroundMusic);
            tulippick=AudioSystem.getClip();
            tulippick.open(tulippickSound);

        }
        catch (LineUnavailableException | IOException e)
        {
            System.err.println("Eroare la incarcarea sunetelor in Assets.");
        }

    }

    /*! \fn public static boolean secondElapsed()
        \brief Functie ce contorizeaza trecerea unei secunde.
    */
    public static boolean secondElapsed()
    {
        if(!flag)
        {
            then= System.nanoTime();
            flag=true;
        }
        now=System.nanoTime();
        if(now-then>=1000000000)
        {
            flag=false;
            return true;
        }
        return false;
    }
}
