
package PaooGame.Maps;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map extends MapFactory
{
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private int [][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/

    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink, int mapNumber)
    {
        /// Retine referinta "shortcut".
        this.refLink = refLink;
        ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld(mapNumber);
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public  void Update()
    {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g)
    {
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for(int y = 0; y < refLink.GetGame().GetHeight()/Tile.TILE_HEIGHT; y++)
        {
            for(int x = 0; x < refLink.GetGame().GetWidth()/Tile.TILE_WIDTH; x++)
            {
                GetTile(x, y).Draw(g, (int)x * Tile.TILE_HEIGHT, (int)y * Tile.TILE_WIDTH);
            }
        }
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.grassTile;
        }
        return t;
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */
    private void LoadWorld(int mapNumber)
    {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
        ///Se stabileste latimea hartii in numar de dale.
        width = 20;
        ///Se stabileste inaltimea hartii in numar de dale
        height = 11;
        ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];
        //Se incarca matricea cu coduri
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
               tiles[x][y]=getMap(mapNumber)[y][x];
            }
        }
    }

    /*! \fn private int MiddleEastMap(int x ,int y)
        \brief O harta incarcata static.

        \param x linia pe care se afla codul dalei de interes.
        \param y coloana pe care se afla codul dalei de interes.
     */

        static final int Level1Map[][] = {

                {3, 4, 1, 1, 1, 3, 3, 3, 1, 1, 3, 4, 1, 3, 1, 4, 3, 1, 1, 1},
                {1, 0, 0, 4, 1, 1, 2, 2, 3, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 1},
                {2, 2, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 4, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 0, 0, 4, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 3},
                {2, 4, 0, 0, 2, 0, 0, 0, 0, 2, 0, 2, 0, 0, 4, 0, 2, 2, 0, 3},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 2, 0, 0, 2, 0, 2},
                {2, 2, 0, 0, 2, 2, 2, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 2},
                {3, 2, 2, 2, 2, 3, 2, 2, 2, 1, 2, 2, 2, 2, 2, 0, 0, 2, 0, 2},
                {1, 1, 4, 3, 3, 3, 1, 1, 4, 3, 3, 1, 3, 3, 2 , 2, 2, 2, 2, 2}};


       static final int  Level2Map[][]= {

                {6, 6, 6, 6, 5, 6, 8, 6, 7, 6, 8, 6, 5, 6, 6, 6, 5, 6, 6, 6},
                {6, 5, 5, 8, 6, 5, 7, 7, 7, 7, 7, 7, 7, 6, 7, 7, 7, 7, 6, 6},
                {7, 7, 5, 5, 7, 7, 7, 5, 5, 8, 5, 5, 7, 5, 5, 8, 5, 5, 5, 6},
                {7, 5, 5, 5, 8, 5, 5, 5, 5, 5, 7, 7, 7, 8, 5, 5, 5, 5, 5, 7},
                {7, 5, 7, 5, 5, 5, 8, 5, 5, 5, 5, 8, 5, 5, 5, 5, 8, 5, 5, 7},
                {6, 7, 7, 7, 7, 5, 5, 5, 5, 7, 7, 7, 5, 5, 5, 5, 5, 5, 5, 6},
                {6, 8, 5, 5, 7, 5, 5, 5, 8, 7, 5, 7, 5, 8, 5, 5, 7, 7, 5, 6},
                {7, 5, 8, 5, 5, 8, 5, 5, 5, 7, 8, 5, 5, 7, 7, 5, 8, 7, 5, 7},
                {7, 7, 5, 5, 7, 7, 7, 5, 5, 7, 5, 5, 5, 5, 7, 5, 5, 7, 8, 7},
                {6, 7, 7, 7, 7, 6, 7, 7, 7, 6, 7, 7, 7, 7, 7, 5, 5, 7, 5, 7},
                {6, 6, 8, 6, 5, 6, 5, 6, 8, 6, 6, 8, 5, 6, 7, 7, 7, 7, 7, 7}};

}