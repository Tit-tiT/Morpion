package Jeux;

import javax.swing.*;
import java.awt.*;

public class Jeu extends JFrame {
    private final String J1;
    private final String J2;
    int tour=0;

    JPanel principal;
    JPanel haut;
    JPanel centre;

    JLabel titre;

    Bouton[] bouton;

    public Jeu(String J1, String J2){
        this.J1 = J1;
        this.J2 = J2;

        bouton=new Bouton[9];
        int x=0;
        int y=0;
        for(int i=0;i<9;i++){
            if(y>2) {
                y=0;
                x++;
            }
            bouton[i]= new Bouton(x,y);
            y++;
        }

        principal = new JPanel();
        principal.setLayout(new BorderLayout());

        // HAUT
        haut = new JPanel();
        haut.setLayout(new FlowLayout());

        titre = new JLabel("Joueur 1 :");
        titre.setFont(new Font("Arial", Font.PLAIN, 32));
        haut.add(titre);
        principal.add(haut,BorderLayout.NORTH);

        // CENTRE
        centre = new JPanel();
        centre.setLayout(new GridLayout(3,3));

        for(int i=0;i<9;i++){
            centre.add(bouton[i]);
        }


        principal.add(centre,BorderLayout.CENTER);

        this.getContentPane().add(principal);
        this.setPreferredSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public boolean gagner(Bouton[] bouton, int j){
        Bouton[] boutAct = new Bouton[9];
        int x0=0;
        int x1=0;
        int x2=0;
        int y0=0;
        int y1=0;
        int y2=0;
        int diag1=0;
        int diag2=0;

        for(Bouton but : bouton){
            if(but.isActif() && but.by==j){
                boutAct[boutAct.length-1]=but;
            }
        }
        for(Bouton but : boutAct){
            if(but.getX()==0){
                x0++;
            }
            if(but.getY()==0){
                y0++;
            }
            if(but.getX()==1){
                x1++;
            }
            if(but.getY()==1){
                y1++;
            }
            if(but.getX()==2){
                x2++;
            }
            if(but.getY()==2){
                y2++;
            }
            if(but.getX()==0 && but.getY()==0 || but.getX()==2 && but.getY()==2){
                diag1++;
            }
            if(but.getX()==0 && but.getY()==2 || but.getX()==2 && but.getY()==0){
                diag2++;
            }
            if(but.getX()==1 && but.getY()==1){
                diag1++;
                diag2++;
            }
        }
        return x0 == 3 || x1 == 3 || x2 == 3 || y0 == 3 || y1 == 3 || y2 == 3 || diag1 == 3 || diag2 == 3;
    }

    public String getJ1() {
        return J1;
    }

    public String getJ2() {
        return J2;
    }
}
