import java.util.*; 
import java.awt.*;
import javax.swing.*; 

    public  class Groupepierre{       
 private ArrayList<pierre> pierres;
 private int liberter;

    public Groupepierre() {
        this.pierres = new ArrayList<pierre>();  
    
    }
    public Groupepierre(pierre p, pierre p2){
              this.liberter=0;
              this.pierres = new ArrayList<pierre>();  
              //this.pierres.add(p);//ajoute pierre p a arraylist pierres
              //this.pierres.add(p2);
              p.setGroup(this);
              p2.setGroup(this);
              this.add(p);//appel add ajoute la pierre dans l'arraylist
              this.add(p2);
    }
public void add(pierre p){
    pierres.add(p);
    this.liberter+=p.getLiberter();
    this.liberter-=2;
    }

public pierre get(int i){
    return pierres.get(i);
}
public int getTotalLib(){
    return this.liberter;
}
        
}
