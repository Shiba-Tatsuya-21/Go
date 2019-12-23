import java.util.*; 
import java.awt.*;
import javax.swing.*; 

    public class Main extends JPanel{       
    
    @Override
public void paintComponent(Graphics pinceau){
    super.paintComponent(pinceau);
    
    
}    
        
        
        
        public static void main(String[] args) {
     
JFrame fenetre = new JFrame();
fenetre.setSize(1000, 1000);// création de la fenetre graphique au dimension demander 
fenetre.setLocation(0,0);
fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
JPanel jp = new JPanel();// création du panel 
fenetre.add(jp);
jp.setBackground(Color.WHITE);// on fixe le fond a la couleur de blanc

fenetre.setVisible(true);


    }    
  }

