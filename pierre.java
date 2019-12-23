import java.util.*; 
import java.awt.*;
import javax.swing.*; 

    public  class pierre extends JPanel{       
private int x;
private int y;
private Color c;
private int liberter=0;
private Groupepierre groupe=null; 


public pierre(int x, int y) {
        this.x = x;
        this.y = y;
     
    }

    public pierre(int x, int y, Color c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getC() {
        return c;
    }
    
    public void setC(Color C){
        this.c=C;   
    }
      public int getLiberter() {
        return liberter;
    }
       public void setLiberter(int liberter) {
        this.liberter = liberter;
    }
       public void setGroup(Groupepierre p){
           this.groupe=p;           // fixe le groupe
       }

    public Groupepierre getGroupe() {
        return groupe;
    }
    
    public boolean hasGroup(){
       if(groupe==null)
        return false;
       else 
           return true;
           }
       
        public static void main(String[] args) {
     
        }
  }

