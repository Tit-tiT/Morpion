package Jeux;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bouton extends JButton {
    int x;
    int y;
    boolean actif=false;
    int by=0;


    public Bouton(int x,int y){
       // super(String.valueOf(x)+" "+String.valueOf(y));
        this.x = x;
        this.y = y;
    }

    public int getX2() {
        return x;
    }

    public int getY2() {
        return y;
    }

    public void setActif(boolean actif, int j) {
        this.actif = actif;
        this.by = j;
    }

    public boolean isActif() {
        return actif;
    }

    public int getBy() {
        return by;
    }

}
