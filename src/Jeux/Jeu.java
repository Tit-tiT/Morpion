package Jeux;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Jeu extends JFrame implements ActionListener {
    private final String J1;
    private final String J2;
    int tour=1;

    JPanel principal;
    JPanel haut;
    JPanel centre;

    JLabel titre;

    Bouton[] bouton;

    public Jeu(String J1, String J2){
        super("Morpion");
        Image icon = new ImageIcon("img/icone.png").getImage();
        this.setIconImage(icon);

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

        titre = new JLabel("Au tour de "+ J1);
        titre.setFont(new Font("Arial", Font.PLAIN, 32));
        haut.add(titre);
        principal.add(haut,BorderLayout.NORTH);

        // CENTRE
        centre = new JPanel();
        centre.setLayout(new GridLayout(3,3));

        for(int i=0;i<9;i++){
            bouton[i].setBackground(Color.WHITE);
            centre.add(bouton[i]);
            bouton[i].addActionListener(clic);

        }


        principal.add(centre,BorderLayout.CENTER);


        this.getContentPane().add(principal);
        this.setPreferredSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public boolean gagner(Bouton[] bouton, int j){

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
                if(but.getX2()==0){
                    x0++;
                }
                if(but.getY2()==0){
                    y0++;
                }
                if(but.getX2()==1){
                    x1++;
                }
                if(but.getY2()==1){
                    y1++;
                }
                if(but.getX2()==2){
                    x2++;
                }
                if(but.getY2()==2){
                    y2++;
                }
                if(but.getX2()==0 && but.getY2()==0 || but.getX2()==2 && but.getY2()==2){
                    diag1++;
                }
                if(but.getX2()==0 && but.getY2()==2 || but.getX2()==2 && but.getY2()==0){
                    diag2++;
                }
                if(but.getX2()==1 && but.getY2()==1){
                    diag1++;
                    diag2++;
                }
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {}

    ActionListener clic = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(tour==1 && !((Bouton)e.getSource()).isActif()){
                ImageIcon ico = new ImageIcon("img/cercle.png");
                Image img = ico.getImage();
                Image cercle = img.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
                Bouton but = ((Bouton)e.getSource());
                but.setActif(true,tour);
                but.setIcon(new ImageIcon(cercle));

                if(gagner(bouton,tour)){
                    int rep = JOptionPane.showConfirmDialog(null,J1+" a gagné, voulez-vous recommencer ?", "BRAVO", JOptionPane.YES_NO_OPTION);
                    if(rep == JOptionPane.YES_OPTION){
                        dispose();
                        new Jeu(J2,J1);
                    }
                    dispose();
                }
                titre.setText("Au tour de "+ J2);
                tour=2;
            }
            else if(tour==2 && !((Bouton)e.getSource()).isActif()){
                ImageIcon ico = new ImageIcon("img/croix.png");
                Image img = ico.getImage();
                Image croix = img.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
                Bouton but = ((Bouton)e.getSource());
                but.setActif(true,tour);
                but.setIcon(new ImageIcon(croix));

                if(gagner(bouton,tour)){
                    int rep = JOptionPane.showConfirmDialog(null,J2+" a gagné, voulez-vous recommencer ?", "BRAVO", JOptionPane.YES_NO_OPTION);
                    if(rep == JOptionPane.YES_OPTION){
                        dispose();
                        new Jeu(J2,J1);
                    }
                    dispose();
                }
                titre.setText("Au tour de "+ J1);
                tour=1;
            }

            int i=0;
            for(Bouton but : bouton){
                if(but.isActif()){
                    i++;
                }
            }
            if(i==9 && !gagner(bouton,1) && !gagner(bouton,2)){
                int rep = JOptionPane.showConfirmDialog(null,"Exæquo, voulez-vous recommencer ?", "Exæquo", JOptionPane.YES_NO_OPTION);
                if(rep == JOptionPane.YES_OPTION){
                    dispose();
                    new Jeu(J2,J1);
                }
                dispose();
            }

        }
    };
}
