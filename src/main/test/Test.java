import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Test {
    public static void main(String args[]){
        JFrame w = new JFrame() ;
        w.setSize(300 , 400) ;

        MyPanel mp = new MyPanel() ;
        w.add(mp) ;

        w.addKeyListener(mp) ;
        mp.addKeyListener(mp) ;

        w.setVisible(true) ;
    }
}

class MyPanel extends JPanel implements KeyListener{
    char c[] = new char[1000] ;
    int size = 0 ;
    int cursor = 0 ;//光标位置信息

    public void paint(Graphics g){
        super.paint(g) ;
        for(int i = 0 ; i < size ; i ++){
            g.drawString(""+c[i], 10+8*i, 10) ;
        }

        //光标
        g.drawLine(10+cursor*8, 0, 10+cursor*8, 10) ;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z){
            for (int i = size; i > cursor; i--) {
                c[i] = c[i - 1];
            }
            c[cursor] = e.getKeyChar();
            size ++;
            cursor ++;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (cursor > 0) {
                cursor--;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (cursor < size) {
                cursor++;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            if (cursor < size) {
                for (int i = cursor; i < size; i++) {
                    c[i] = c[i + 1];
                }
                size--;
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }
}