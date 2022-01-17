package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.States.LastLevelState;
import PaooGame.States.PlayState;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character
{
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private static Hero instance = null;
    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y)
    {
            ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
            ///Seteaza imaginea de start a eroului
        image = Assets.heroDown;
        score=0;
            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 32;

            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;


    }
    //SINGLETON
    public static Hero getInstance(RefLinks refLink, float x, float y)
    {
        if(instance==null)
        {
            instance = new Hero(refLink,x,y);
        }
        return instance;
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
            ///Verifica daca a fost apasata o tasta
        GetInput();
            ///Actualizeaza pozitia
        Move();
            ///Actualizeaza imaginea
        if(refLink.GetKeyManager().left == true)
        {
            image = Assets.heroLeft;
        }
        if(refLink.GetKeyManager().right == true) {
            image = Assets.heroRight;
        }
        if(refLink.GetKeyManager().up==true)
        {
            image=Assets.heroUp;
        }
        if(refLink.GetKeyManager().down==true)
        {
            image=Assets.heroDown;
        }
        CheckCollisions();
    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
            ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
            ///Verificare apasare tasta "sus"
        if(refLink.GetKeyManager().up)
        {
            yMove = -speed;
        }
            ///Verificare apasare tasta "jos"
        if(refLink.GetKeyManager().down)
        {
            yMove = speed;
        }
            ///Verificare apasare tasta "left"
        if(refLink.GetKeyManager().left)
        {
            xMove = -speed;
        }
            ///Verificare apasare tasta "dreapta"
        if(refLink.GetKeyManager().right)
        {
            xMove = speed;
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafic in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        Font font= new Font("Verdana", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("LIVES: " + String.valueOf(this.life), 10, 30);
        g.drawString("SCORE: "+ String.valueOf(this.score), 1100, 30);
        g.drawImage(image, (int)x, (int)y, width, height, null);

            ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
    public Rectangle GetBounds(){

        return new Rectangle((int)x, (int)y, image.getWidth(null),image.getHeight(null));
    }

    /*! \fn public void CheckCollisions()

        \brief g Verica coliziunile cu elementele active din joc(tulips).
     */
    public void CheckCollisions() {
        ArrayList<Tulip> tulipp = PlayState.GetTulipList();

        ArrayList<Tulip> tulip= LastLevelState.GetTulipList();

        for (int i = 0; i < tulipp.size(); ++i) {
            Tulip temptulipp = tulipp.get(i);
            if (GetBounds().intersects(temptulipp.GetBounds())) {
                if(!Assets.tulippick.isRunning()) { // daca Clipul audio nu este deja pornit
                    Assets.tulippick.setFramePosition(0);
                    Assets.tulippick.start(); // pornim clipul audio
                }
                PlayState.RemoveTulip(temptulipp);
                score++;

            }
        }

        for (int i = 0; i < tulip.size(); ++i) {
            Tulip temptulip = tulip.get(i);
            if (GetBounds().intersects(temptulip.GetBounds())) {
                if(!Assets.tulippick.isRunning()) { // daca Clipul audio nu este deja pornit
                    Assets.tulippick.setFramePosition(0);
                    Assets.tulippick.start(); // pornim clipul audio
                }
                LastLevelState.RemoveTulip(temptulip);
                score++;

            }
        }
    }

}
