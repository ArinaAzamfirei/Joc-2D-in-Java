package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class YouWonState extends YouLostState
{
    /*! \fn public AboutState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public YouWonState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniu about.
     */
    @Override
    public void Update()
    {
        Assets.level2music.stop();
        ///System.out.println(RefLinks.GetMouseManager().getMouseX()+ " " + RefLinks.GetMouseManager().getMouseY()  );
        ///Butonul pentru a avea optiunea de a relua jocul
        if (refLink.GetMouseManager().getMouseX() >= 436 && refLink.GetMouseManager().getMouseX() <= 923) //coordonate calculate folosind print-ul de mai sus
        {

            if (refLink.GetMouseManager().getMouseY() >= 390 && refLink.GetMouseManager().getMouseY() <= 444) //coordonate calculate folosind print-ul de mai sus
            {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.level2music.stop();
                    retry();
                    State.SetState(refLink.GetGame().getMenuState());
                    ///oprim thread-ul pentru 0.5 secunde pentru a nu se apasa si butonul ABOUT din MenuState
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("Eroare la intreruperea thread-ului ");
                    }

                }
            }
        }

    }
    protected void retry()
    {
        hero.SetX(100);
        hero.SetY(100);
        hero.SetScore(0);
        hero.SetLife(1);
        timer1=30;
        refLink.SetMap(map);


    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniu about.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {


        g.drawImage(Assets.YouWon, 0, 0, refLink.GetWidth(), refLink.GetHeight(), null);
    }
}
