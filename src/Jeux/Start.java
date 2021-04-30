package Jeux;

import javax.swing.*;
import java.awt.*;

public class Start extends JFrame {

    JPanel principal;
    JPanel haut;
    JPanel centre;
    JPanel bas;

    GridBagConstraints gbc;

    JLabel titre;
    JLabel nomJ1;
    JLabel nomJ2;

    JTextField J1;
    JTextField J2;

    JButton cancel;
    JButton suivant;

    public Start() {
        super("Morpion");
        Image icon = new ImageIcon("img/icone.png").getImage();
        this.setIconImage(icon);

        principal = new JPanel();
        principal.setLayout(new BorderLayout());

        // HAUT
        haut = new JPanel();
        haut.setLayout(new FlowLayout());

        titre = new JLabel("Bienvenue :");
        titre.setFont(new Font("Arial", Font.PLAIN, 32));
        haut.add(titre);
        principal.add(haut,BorderLayout.NORTH);


        // CENTRE
        centre = new JPanel();
        centre.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        nomJ1 = new JLabel("Joueur 1 :");
        nomJ1.setFont(new Font("Arial", Font.PLAIN, 24));
        centre.add(nomJ1,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        J1 = new JTextField();
        J1.setPreferredSize( new Dimension( 200, 30 ) );
        J1.setFont(new Font("Arial", Font.PLAIN, 20));
        centre.add(J1,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        nomJ2 = new JLabel("Joueur 2 :");
        nomJ2.setFont(new Font("Arial", Font.PLAIN, 24));
        centre.add(nomJ2,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        J2 = new JTextField();
        J2.setPreferredSize( new Dimension( 200, 30 ) );
        J2.setFont(new Font("Arial", Font.PLAIN, 20));
        centre.add(J2,gbc);

        principal.add(centre,BorderLayout.CENTER);

        // BAS
        bas = new JPanel();
        bas.setLayout(new FlowLayout());

        cancel = new JButton("Annuler");
        cancel.setFont(new Font("Arial", Font.PLAIN, 20));
        cancel.setBackground(Color.WHITE);
        cancel.addActionListener(actionEvent -> dispose());
        bas.add(cancel);

        suivant = new JButton("Suivant");
        suivant.setFont(new Font("Arial", Font.PLAIN, 20));
        suivant.setBackground(Color.WHITE);
        suivant.addActionListener(actionEvent -> {
            if(J1.getText().equals("")){J1.setText("Joueur 1");}
            if(J2.getText().equals("")){J2.setText("Joueur 2");}
            this.dispose();
            new Jeu(J1.getText(),J2.getText());
        });
        bas.add(suivant);

        principal.add(bas,BorderLayout.SOUTH);

        this.getContentPane().add(principal);
        this.setPreferredSize(new Dimension(600, 300));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Start();
    }
}
