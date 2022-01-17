package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Items.*;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;

import java.awt.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{


    public static Map map;    /*!< Referinta catre harta curenta.*/
    public Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Enemy enemy;
    int tulipCount=70;
    public static int timer1=30;

    static ArrayList<Tulip> tulip=new ArrayList<Tulip>();
    Random rand= new Random();
    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului
        map = new Map(refLink,1);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);

            ///Construieste eroul
        hero = Hero.getInstance(refLink,100,100); // hero folosind sablonul SINGLETON
        ///Construieste inamicul
        enemy= new Enemy(refLink, 1109, 260);

        ///Genereaza random entitatile active(lalele) ce trebuie adunate.
        for(int k=0; k <= tulipCount; ++k)
        {addTulips(new Tulip(refLink, rand.nextInt(960), rand.nextInt(960), 32, 32));}

    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        if(!Assets.level1music.isRunning()) { /// daca Clipul audio nu este deja pornit
            Assets.level1music.setFramePosition(0);
            Assets.level1music.start(); /// pornim clipul audio
        }

        ///System.out.println(RefLinks.GetMouseManager().getMouseX()+ " " + RefLinks.GetMouseManager().getMouseY()  );
        map.Update();
        hero.Update();
        enemy.Update();
        if(Math.abs(hero.GetY() - enemy.GetY()) <10 && Math.abs(hero.GetX() - enemy.GetX()) <10)
        {
            if(hero.GetLife()>0)
                hero.SetLife(hero.GetLife()-1);
        }
        if(hero.GetLife()<=0 || timer1==0)
        {
           State.SetState(refLink.GetGame().getYouloststate());
        }

        if(hero.GetScore() == 50)
        {
            State.SetState(refLink.GetGame().getLastlevelState());
        }

        //System.out.println(RefLinks.GetMouseManager().getMouseX()+ " " + RefLinks.GetMouseManager().getMouseY()  );
        if (refLink.GetMouseManager().getMouseX() >= 1101 && refLink.GetMouseManager().getMouseX() <= 1232) //coordonate calculate folosind print-ul de mai sus
        {

            if (refLink.GetMouseManager().getMouseY() >= 656 && refLink.GetMouseManager().getMouseY() <= 681) //coordonate calculate folosind print-ul de mai sus
            {
                if (refLink.GetMouseManager().isLeftPressed()) {

                    Save();

                }
            }
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);
        hero.Draw(g);
        enemy.Draw(g);
        for(int i=0; i<tulip.size(); ++i)
        {
            Tulip tempTulip=tulip.get(i);
            tempTulip.Draw(g);
        }

        if(Assets.secondElapsed())
            timer1--;
        g.drawString("Time Left: " + timer1,54,677);
        Font font= new Font("Verdana", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("LEVEL 1 ", 580, 30);
        g.drawString("SAVE GAME",1100,677);

    }
    /*! \fn public void addTulips(Tulip t)
         Functii pentru adaugarea elementelor active.
    */
    public void addTulips(Tulip m){
        tulip.add(m);

    }
    public static ArrayList<Tulip> GetTulipList(){
        return tulip;
    }
    /*! \fn public void addTulips(Tulip t)
         Functie pentru eliminarea elementelor active atunci cand exista coliziune cu eroul.
    */
    public static void RemoveTulip(Tulip t){
        Assets.tulippick.stop();
        tulip.remove(t);
    }
    /*! \fn protected void loadFromDB() throws SQLException
       \brief Functie care incarca parametrii jocului din baza de date pentru implementarea conceptului de Load Game.
    */
    protected void loadFromDatabase(){
        try {
            hero.SetX(refLink.GetDatabase().getHeroX());
            hero.SetY(refLink.GetDatabase().getHeroY());
            hero.SetScore(refLink.GetDatabase().getHeroScore());
            timer1 = (int) refLink.GetDatabase().getTimer1();

        }
        catch (SQLException e)
        {
            System.err.print("Eroare in PlayState->LoadFromDB");
        }
    }

    /*! \fn protected void handleSave()
   \brief Functie care care salveaza jocul pentru LoadGame.
   */
    protected void Save() {

        try {
            if (State.GetPreviousState() == refLink.GetGame().getMenuState()) {
                refLink.GetDatabase().saveSettings(hero.GetX(), hero.GetY(),hero.GetScore(), 1, 0, PlayState.timer1, LastLevelState.timer2);
            }
            if (State.GetPreviousState() == refLink.GetGame().getPlayState()) {

                refLink.GetDatabase().saveSettings(hero.GetX(), hero.GetY(), hero.GetScore(), 0, 1, PlayState.timer1, LastLevelState.timer2);

            }

        }
        catch (SQLException e)
        {
            System.err.print("Eroare PlayState->Handle Save la incarcare din baza de date.");
        }
    }
}

