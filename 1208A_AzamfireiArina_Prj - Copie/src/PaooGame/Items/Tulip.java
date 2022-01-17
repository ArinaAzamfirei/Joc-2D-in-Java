package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tulip extends Item{

    private BufferedImage tulip;
    /*! \fn public Tulip(RefLinks refLink, float x, float y, int width, int height)
       \brief Constructor de initializare al clasei

       \param  reflink Referinte "shortcut" catre alte referinte
       \param  x   Pozitia pe axa X a "tablei" de joc a imaginii entitatii.
       \param  y   Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.
       \param  width   Latimea imaginii entitatii.
       \param  height  Inaltimea imaginii entitatii.
    */
    public Tulip(RefLinks refLink, float x, float y, int width, int height) {
        super(refLink, x, y, width, height);
        tulip= Assets.tulip;
    }

    ///Metoda abstracta destinata actualizarii starii curente
    @Override
    public void Update() {

    }
    ///Metoda abstracta destinata desenarii starii curente
    @Override
    public void Draw(Graphics g) { g.drawImage(tulip, (int)x, (int)y, width, height, null); }
    public Rectangle GetBounds(){
        return new Rectangle((int)x, (int)y, tulip.getWidth(null),tulip.getHeight(null));
    }
}
