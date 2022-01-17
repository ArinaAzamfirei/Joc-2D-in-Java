package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.sql.SQLException;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends PlayState {
    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        //AudioLoader.setVolume(Assets.menuMusic, 50);

    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update() {

        if(!Assets.menuMusic.isRunning()) { // daca Clipul audio nu este deja pornit
            Assets.menuMusic.setFramePosition(0);
            Assets.menuMusic.start(); // pornim clipul audio
        }

        ///System.out.println(RefLinks.GetMouseManager().getMouseX()+ " " + RefLinks.GetMouseManager().getMouseY()  );
        // BUTONUL NEW GAME
        if (refLink.GetMouseManager().getMouseX() >= 490 && refLink.GetMouseManager().getMouseX() <= 800) //coordonate calculate folosind print-ul de mai sus
        {

            if (refLink.GetMouseManager().getMouseY() >= 208 && refLink.GetMouseManager().getMouseY() <= 260) //coordonate calculate folosind print-ul de mai sus
            {
                if (refLink.GetMouseManager().isLeftPressed()) {
                    Assets.menuMusic.stop();
                    refLink.SetMap(PlayState.map);
                    State.SetState(refLink.GetGame().getPlayState());

                }
            }
        }
        ///BUTONUL LOAD GAME
        if (refLink.GetMouseManager().getMouseX() >= 529 && refLink.GetMouseManager().getMouseX() <= 800) //coordonate calculate folosind print-ul de mai sus
        {

            if (refLink.GetMouseManager().getMouseY() >= 278 && refLink.GetMouseManager().getMouseY() <= 333) //coordonate calculate folosind print-ul de mai sus
            {
                if (refLink.GetMouseManager().isLeftPressed()) {

                    Assets.menuMusic.stop();
                    try {
                        if (refLink.GetDatabase().getIsInLvl1() == 1) {
                            State.SetState(refLink.GetGame().getPlayState());
                            refLink.SetMap(PlayState.map);
                            hero.SetLife(1);
                            loadFromDatabase();

                        }
                        if (refLink.GetDatabase().getIsInLvl2() == 1) {
                            State.SetState(refLink.GetGame().getLastlevelState());
                            refLink.SetMap(LastLevelState.map2);
                            hero.SetLife(1);
                            loadFromDatabase();
                        }

                    } catch (SQLException e) {
                        System.err.println("Eroare in MenuState->Update->Baza de date.");
                    }

                }

            }
        }
        //BUTONUL OPTIONS
        if (refLink.GetMouseManager().getMouseX() >= 524 && refLink.GetMouseManager().getMouseX() <= 800) //coordonate calculate folosind print-ul de mai sus
        {

            if (refLink.GetMouseManager().getMouseY() >= 350 && refLink.GetMouseManager().getMouseY() <=402) //coordonate calculate folosind print-ul de mai sus
            {
                if (refLink.GetMouseManager().isLeftPressed()) {


                    State.SetState(refLink.GetGame().getSettingsState());

                }
            }
        }

        //BUTONUL ABOUT
        if (refLink.GetMouseManager().getMouseX() >= 524 && refLink.GetMouseManager().getMouseX() <= 800) //coordonate calculate folosind print-ul de mai sus
        {

            if (refLink.GetMouseManager().getMouseY() >= 415 && refLink.GetMouseManager().getMouseY() <=471 ) //coordonate calculate folosind print-ul de mai sus
            {
                if (refLink.GetMouseManager().isLeftPressed()) {


                    State.SetState(refLink.GetGame().getAboutState());

                }
            }
        }

        // butonul EXIT - la apasarea acestuia se iese din joc
        if (refLink.GetMouseManager().getMouseX() >= 528 && refLink.GetMouseManager().getMouseX() <= 800)
             if(refLink.GetMouseManager().getMouseY()>=480 && refLink.GetMouseManager().getMouseY()<=540)
                {
                    if(refLink.GetMouseManager().isLeftPressed())
                        {
                            Assets.menuMusic.stop();
                            System.exit(0);
                        }
                }
    }
    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
        @Override
        public void Draw (Graphics g)
        {
            g.drawImage(Assets.menu, 0, 0, refLink.GetWidth(), refLink.GetHeight(), null);
        }

}

