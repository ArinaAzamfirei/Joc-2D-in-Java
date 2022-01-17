package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.AudioLoader;
import PaooGame.RefLinks;

import java.awt.*;
import java.sql.SQLException;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State
{
    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public SettingsState(RefLinks refLink)
    {
            ///Apel al construcotrului clasei de baza.
        super(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update()
    {
       /// System.out.println(RefLinks.GetMouseManager().getMouseX()+ " " + RefLinks.GetMouseManager().getMouseY()  );
        if (refLink.GetMouseManager().getMouseX() >= 6 && refLink.GetMouseManager().getMouseX() <= 320) //coordonate calculate folosind print-ul de mai sus
        {

            if (refLink.GetMouseManager().getMouseY() >= 593 && refLink.GetMouseManager().getMouseY() <= 700) //coordonate calculate folosind print-ul de mai sus
            {
                if (refLink.GetMouseManager().isLeftPressed()) {


                    State.SetState(refLink.GetGame().getMenuState());

                }
            }
        }

        ///MUTE
        if (refLink.GetMouseManager().getMouseX() >= 409 && refLink.GetMouseManager().getMouseX() <= 560) //coordonate calculate folosind print-ul de mai sus
        {

            if (refLink.GetMouseManager().getMouseY() >= 391 && refLink.GetMouseManager().getMouseY() <= 501) //coordonate calculate folosind print-ul de mai sus
            {
                if (refLink.GetMouseManager().isLeftPressed()) {

                    try {
                        AudioLoader.setVolume(Assets.menuMusic, refLink.GetDatabase().getGameVolume0());
                        AudioLoader.setVolume(Assets.level1music,refLink.GetDatabase().getGameVolume0());
                        AudioLoader.setVolume(Assets.level2music,refLink.GetDatabase().getGameVolume0());
                    }
                    catch (SQLException e)
                    {
                        System.err.println("Eroare Baza de Date");
                    }

                }
            }
        }

        ///Volum joc 60
        if (refLink.GetMouseManager().getMouseX() >= 620 && refLink.GetMouseManager().getMouseX() <= 748) //coordonate calculate folosind print-ul de mai sus
        {

            if (refLink.GetMouseManager().getMouseY() >= 388 && refLink.GetMouseManager().getMouseY() <= 496) //coordonate calculate folosind print-ul de mai sus
            {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    try {
                        AudioLoader.setVolume(Assets.menuMusic, refLink.GetDatabase().getGameVolume60());
                        AudioLoader.setVolume(Assets.level1music, refLink.GetDatabase().getGameVolume60());
                        AudioLoader.setVolume(Assets.level2music, refLink.GetDatabase().getGameVolume60());
                    }
                    catch (SQLException e)
                    {
                        System.err.println("Eroare Baza de Date");
                    }

                }
            }
        }

        ///Volum joc 100
        if (refLink.GetMouseManager().getMouseX() >= 818 && refLink.GetMouseManager().getMouseX() <= 953) //coordonate calculate folosind print-ul de mai sus
        {

            if (refLink.GetMouseManager().getMouseY() >= 395 && refLink.GetMouseManager().getMouseY() <= 493) //coordonate calculate folosind print-ul de mai sus
            {
                if (refLink.GetMouseManager().isLeftPressed()) {


                    try {
                        AudioLoader.setVolume(Assets.menuMusic, refLink.GetDatabase().getGameVolume100());
                        AudioLoader.setVolume(Assets.level1music, refLink.GetDatabase().getGameVolume100());
                        AudioLoader.setVolume(Assets.level2music, refLink.GetDatabase().getGameVolume100());
                    }
                    catch (SQLException e)
                    {
                        System.err.println("Eroare Baza de Date");
                    }

                }
            }
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
            g.drawImage(Assets.settings,0,0, refLink.GetWidth(), refLink.GetHeight(), null);
    }
}
