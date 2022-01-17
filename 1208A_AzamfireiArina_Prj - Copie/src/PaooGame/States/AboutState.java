package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class AboutState extends State
{
    /*! \fn public AboutState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public AboutState(RefLinks refLink)
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
        ///System.out.println(RefLinks.GetMouseManager().getMouseX()+ " " + RefLinks.GetMouseManager().getMouseY()  );
        if (refLink.GetMouseManager().getMouseX() >= 6 && refLink.GetMouseManager().getMouseX() <= 243) //coordonate calculate folosind print-ul de mai sus
        {

            if (refLink.GetMouseManager().getMouseY() >= 615 && refLink.GetMouseManager().getMouseY() <= 700) //coordonate calculate folosind print-ul de mai sus
            {
                if (refLink.GetMouseManager().isLeftPressed()) {


                    State.SetState(refLink.GetGame().getMenuState());

                }
            }
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniu about.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.about, 0, 0, refLink.GetWidth(), refLink.GetHeight(), null);
    }
}
