package PaooGame.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    public boolean leftPressed, rightPressed;
    private int MouseX, MouseY;

    public MouseManager()
    {
        // /nu avem nimic de intializat in constructor.
    }

    ///GETTERS
    public boolean isLeftPressed()
    {
        return leftPressed;
    }
    public boolean isRightPressed()
    {
        return rightPressed;
    }
    public int getMouseX()
    {
        return MouseX;
    }
    public int getMouseY()
    {
        return MouseY;
    }

    ///Metode

    @Override
    public void mousePressed(MouseEvent e) {
      if(e.getButton() == MouseEvent.BUTTON1)  // BUTTON1= butonul pentru click stanga;
          leftPressed=true;
      else if(e.getButton() == MouseEvent.BUTTON3)  //BUTTON3= butonul pentru click dreapta;
          rightPressed=true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)  // BUTTON1= butonul pentru click stanga;
            leftPressed=false;
        else if(e.getButton() == MouseEvent.BUTTON3)  //BUTTON3= butonul pentru click dreapta;
            rightPressed=false;

    }
    @Override
    public void mouseMoved(MouseEvent e) {
            MouseX=e.getX();
            MouseY=e.getY();
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }


}
