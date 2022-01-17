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
public class LastLevelState extends PlayState
{


    public static Map map2;    /*!< Referinta catre harta curenta.*/
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Enemy enemy;
    private Enemy2 enemy2;
    public static int timer2=25;
    int tulipCount=80;

    static ArrayList<Tulip> tulip=new ArrayList<Tulip>();
    Random rand= new Random();

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public LastLevelState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza
        super(refLink);
        ///Construieste harta jocului
        map2 = new Map(refLink,2);
        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map2);

        ///Construieste eroul
        hero = new Hero(refLink,100, 100);
        ///Construiesc inamicii
        enemy= new Enemy(refLink, 1109, 260);
        enemy2= new Enemy2(refLink,482,155);
        ///Generez random entitatile active(lalele) ce trebuie adunate.
        for(int k=0; k < tulipCount;++k)
        {addTulips(new Tulip(refLink, rand.nextInt(960), rand.nextInt(860), 32, 32));}


    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        Assets.level1music.stop();
        if(!Assets.level2music.isRunning()) { // daca Clipul audio nu este deja pornit
            Assets.level2music.setFramePosition(0);
            Assets.level2music.start(); // pornim clipul audio
        }
        //System.out.println(RefLinks.GetMouseManager().getMouseX()+ " " + RefLinks.GetMouseManager().getMouseY()  );
        map2.Update();
        hero.Update();
        enemy.Update();
        enemy2.Update();
        ///Coliziuni cu inamicul nr1.
        if(Math.abs(hero.GetY() - enemy.GetY()) <10 && Math.abs(hero.GetX() - enemy.GetX()) <10)
        {
            if(hero.GetLife()>0)
                hero.SetLife(hero.GetLife()-1);
        }
        ///Coliziuni cu inamicul nr2.
        if(Math.abs(hero.GetY() - enemy2.GetY()) <10 && Math.abs(hero.GetX() - enemy2.GetX()) <10)
        {
            if(hero.GetLife()>0)
                hero.SetLife(hero.GetLife()-1);
        }
        ///Conditie pentru castigarea jocului.
        if(hero.GetScore() == 40)
        {
            State.SetState(refLink.GetGame().getYouwonstate());
        }
        ///Conditiile pentru pierderea jocului.
        if(hero.GetLife()<=0 || timer2==0)
        {
            State.SetState(refLink.GetGame().getYouloststate());
        }


        /// System.out.println(RefLinks.GetMouseManager().getMouseX()+ " " + RefLinks.GetMouseManager().getMouseY()  );
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
        map2.Draw(g);
        hero.Draw(g);
        enemy.Draw(g);
        enemy2.Draw(g);

        for(int i=0; i<tulip.size(); ++i)
        {
            Tulip tempTulip=tulip.get(i);
            tempTulip.Draw(g);
        }
        if(Assets.secondElapsed())
            timer2--;
        g.drawString("Time Left: " + timer2,54,677);
        Font font= new Font("Verdana", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("LEVEL 2 ", 580, 30);
        g.drawString("SAVE GAME",1100,677);
    }

    /*! \fn public void addTulips(Tulip t)
          Functii pentru adaugarea elementelor active.
     */
    public void addTulips(Tulip t){
        tulip.add(t);

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
            timer2 = (int) refLink.GetDatabase().getTimer2();
        }
        catch (SQLException e)
        {
            System.err.print("Eroare in PlayState->LoadFromDB");
        }
    }
}
