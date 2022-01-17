package PaooGame;
import java.sql.*;
public class Database {
        Connection c;
        Statement stmt;
        ResultSet rs;
        public Database()
        {
            //ca in laborator
            try
            {
                Class.forName("org.sqlite.JDBC"); //incarcam driverul
                c = DriverManager.getConnection("jdbc:sqlite:Settings.db"); // facem conexiunea cu baza de date.
                stmt = c.createStatement();
            }
            catch (Exception e)
            {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

    /*! \fn public int getGameVolume0() throws SQLException
    \brief Functie de extragere a volumului din baza de date cand jucatorul apasa primul buton din settings.(volume=0)
 */
    public int getGameVolume0() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM GameSettings;");
        return rs.getInt("GAME_MUSIC_MUTE");
    }
    /*! \fn public int getGameVolume0() throws SQLException
   \brief Functie de extragere a volumului din baza de date cand jucatorul apasa al doilea buton din settings.(volume=60)
*/
    public int getGameVolume60() throws SQLException{
            rs=stmt.executeQuery("SELECT *FROM GameSettings");
            return rs.getInt("GAME_MUSIC_60_");
    }

    /*! \fn public int getGameVolume0() throws SQLException
   \brief Functie de extragere a volumului din baza de date cand jucatorul apasa al treilea buton din settings.(volume=100)
*/
    public int getGameVolume100() throws SQLException{
        rs=stmt.executeQuery("SELECT *FROM GameSettings");
        return rs.getInt("GAME_MUSIC_100_");
    }

    /*! \fn public void saveSettings(float heroX, float heroY, int heroScore, int level1, int level2, int timer1, int timer2) throws SQLException
       \brief Functie de salvare a parametrilor vitali din joc pentru a-i incarca la apasarea butonului "Load Game".

       \param heroX pozitia eroului pe harta pe axa X.
       \param heroY pozitia eroului pe harta pe axa Y.
       \param heroScore scorul erolului.
       \param level1 ne spune daca suntem in harta corespunzatoare nivelului 1.
       \param level2 ne spune daca suntem in harta corespunzatoare nivelului 2.
       \param timer1 reprezinta momentul de timp in care se afla eroul in momentul salvarii in harta corespunzatoare nivelului 1.
       \param timer2 reprezinta momentul de timp in care se afla eroul in momentul salvarii in harta corespunzatoare nivelului 2.
    */
    public void saveSettings(float heroX, float heroY, int heroScore, int level1, int level2, int timer1, int timer2) throws SQLException {
        String instruction = "INSERT INTO SAVEGAME(HERO_X,HERO_Y,HERO_SCORE,IS_IN_LEVEL1,IS_IN_LEVEL2, TIMER1, TIMER2)" +
                " VALUES ("+heroX+", "+heroY+", "+heroScore+", "+level1+", "+level2+", "+timer1+", "+timer2+");";
        stmt.executeUpdate(instruction);
    }

    /*! \fn public float getHeroX() throws SQLException
        \brief Functie de extragere a coordonatei X a eroului din baza de date.
     */
    public float getHeroX() throws SQLException {
        float x = 0;
        rs=stmt.executeQuery("SELECT * FROM SAVEGAME;");
        while(rs.next())
        {
            x=rs.getFloat("HERO_X");
        }
        return x;
    }

    /*! \fn public float getHeroY() throws SQLException
        \brief Functie de extragere a coordonatei Y a eroului din baza de date.
     */
    public float getHeroY() throws SQLException {
        float y = 0;
        rs=stmt.executeQuery("SELECT * FROM SAVEGAME;");
        while(rs.next())
        {
            y=rs.getFloat("HERO_Y");
        }
        return y;
    }

    /*! \fn public int getHeroScore() throws SQLException
        \brief Functie de extragere a scorului eroului din baza de date.
     */
    public int getHeroScore() throws SQLException {
        int x = 0;
        rs=stmt.executeQuery("SELECT * FROM SAVEGAME;");
        while(rs.next())
        {
            x=rs.getInt("HERO_SCORE");
        }
        return x;
    }

    /*! \fn public int getIsInLvl1() throws SQLException
        \brief Functie de extragere pentru aflarea eroului in nivelul 1.
     */
    public int getIsInLvl1() throws SQLException {
        int x = 0;
        rs=stmt.executeQuery("SELECT * FROM SAVEGAME;");
        while(rs.next())
        {
            x=rs.getInt("IS_IN_LEVEL1");
        }
        return x;
    }

    /*! \fn public int getIsInLvl2() throws SQLException
        \brief Functie de extragere pentru aflarea eroului in nivelul 2.
     */
    public int getIsInLvl2() throws SQLException {
        int x = 0;
        rs=stmt.executeQuery("SELECT * FROM SAVEGAME;");
        while(rs.next())
        {
            x=rs.getInt("IS_IN_LEVEL2");
        }
        return x;
    }

    /*! \fn public long getIterator1() throws SQLException
        \brief Functie de extragere a momentului de timp in care se afla eroului in harta 1 din baza de date.
     */
    public long getTimer1() throws SQLException {
        int x = 0;
        rs=stmt.executeQuery("SELECT * FROM SAVEGAME;");
        while(rs.next())
        {
            x=rs.getInt("TIMER1");
        }
        return x;
    }

    /*! \fn public long getIterator2() throws SQLException
        \brief Functie de extragere a momentului de timp in care se afla eroului in harta 2 din baza de date.
     */
    public long getTimer2() throws SQLException {
        int x = 0;
        rs=stmt.executeQuery("SELECT * FROM SAVEGAME;");
        while(rs.next())
        {
            x=rs.getInt("TIMER2");
        }
        return x;
    }

}
