import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


public class terrain extends JPanel implements ActionListener,MouseListener{
    private int t;
    private int taille;
    private int point;
    private ArrayList<pierre> pierres; // list des pierres 
    private ArrayList<Groupepierre> gp; // groupe de pierre du plateaux
    private JButton bouton1;
    private JButton bouton2;
    private JButton bouton3;
    private JButton abandon;
     private JButton passer;
    private int v; // espace entre chaque case du goban
    private boolean joueur =  true; // a true joueur au pierre noir et si false joueur au pierre blanche
    private static final int DEBUT_TERRAIN_X = 50;
    private static final int DEBUT_TERRAIN_Y = 50;
    private int pcapturenoir=0;
    private int pcaptureblanc=0;
    private int abandonner=0;
    private int passe=0;
    private int contpasse=0;
    public terrain(int taille) {
        this.taille = taille;
        
        if(taille==9){
            point=5;
            t=500;
        }
        if(taille==13){
            point=5;
            t=750;
        }
        if(taille==19){
            point=9;
            t=900;
        }
        this.pierres= new ArrayList<>();// initialisation liste de pierres
        this.gp=new ArrayList<Groupepierre>();// initialisation liste de groupepierre
        bouton1 = new JButton(" 9x9 ");
        bouton2 = new JButton("13x13");
        bouton3 = new JButton("19x19");
        abandon=new JButton("Abandon");
        passer=new JButton("Passer");
        ButtonGroup bg = new ButtonGroup();
        ButtonGroup ab = new ButtonGroup();
        bg.add(bouton1);
        bg.add(bouton2);
        bg.add(bouton3);
        ab.add(abandon);
        ab.add(passer);
        this.add(bouton1);
        this.add(bouton2);
        this.add(bouton3);
        this.add(abandon);
        this.add(passer);
        bouton1.addActionListener(this);
        bouton2.addActionListener(this);
        bouton3.addActionListener(this);
        abandon.addActionListener(this);
        passer.addActionListener(this);
        this.addMouseListener(this);
    }
    
    public terrain(){
        taille=19;
        t=500;
        this.pierres= new ArrayList<>();// initialisation liste de pierres
        this.gp=new ArrayList<Groupepierre>();// initialisation liste de groupepierre
    }
    
    @Override
    public void paintComponent(Graphics pinceau){
        super.paintComponent(pinceau);
        pinceau.drawRect(DEBUT_TERRAIN_X, DEBUT_TERRAIN_Y, t, t);
        v=t/(taille-1);
        int x1=DEBUT_TERRAIN_X;
        int y1=DEBUT_TERRAIN_Y;
        int x2=DEBUT_TERRAIN_X+t;
        int y2=DEBUT_TERRAIN_Y;
        
        if(taille==9){
            int centerx=x1+4*v;
            int centery=y1+4*v;
            pinceau.fillOval(centerx-7,centery-7, 15, 15);
            pinceau.fillOval(centerx-2*v-7,centery-2*v-7, 15, 15);
            pinceau.fillOval(centerx+2*v-7,centery+2*v-7, 15, 15);
            pinceau.fillOval(centerx+2*v-7,centery-2*v-7, 15, 15);
            pinceau.fillOval(centerx-2*v-7,centery+2*v-7, 15, 15);// je met -10 pour centrer les cercles
        }
        if(taille==13){
            int centerx=x1+6*v;
            int centery=y1+6*v;
            pinceau.fillOval(centerx-7,centery-7, 15, 15);
            pinceau.fillOval(centerx-3*v-7,centery-3*v-7, 15, 15);
            pinceau.fillOval(centerx+3*v-7,centery+3*v-7, 15, 15);
            pinceau.fillOval(centerx+3*v-7,centery-3*v-7, 15, 15);
            pinceau.fillOval(centerx-3*v-7,centery+3*v-7, 15, 15);// je met -10 pour centrer les cercles
        }
        
        if(taille==19){
            int centerx=x1+9*v;
            int centery=y1+9*v;
            pinceau.fillOval(centerx-7,centery-7, 15, 15);
            pinceau.fillOval(centerx-6*v-7,centery-6*v-7, 15, 15);
            pinceau.fillOval(centerx+6*v-7,centery+6*v-7, 15, 15);
            pinceau.fillOval(centerx+6*v-7,centery-6*v-7, 15, 15);
            pinceau.fillOval(centerx-6*v-7,centery+6*v-7, 15, 15);// je met -10 pour centrer les cercles
            //cela est les 4 points supplémentaire du goban 19*19
            pinceau.fillOval(centerx-7,centery-6*v-7, 15, 15);
            pinceau.fillOval(centerx-7,centery+6*v-7, 15, 15);
            pinceau.fillOval(centerx+6*v-7,centery-7, 15, 15);
            pinceau.fillOval(centerx-6*v-7,centery-7, 15, 15);
        }
        
        for(int i=1;i<taille-1;i++){
            y1+=v;
            y2+=v;
            pinceau.drawLine(x1,y1,x2,y2);// code qui permet de dessiner le terrain
        }
        x2=50;
        y1=50;
        y2=50+t;
        for(int i=1;i<taille-1;i++){
            x1+=v;
            x2+=v;
            pinceau.drawLine(x1 , y1, x2, y2);// code qui permet de dessiner le terrain
        }
        
        System.out.println("\n\n\n");
        
        for(int i=0;i<pierres.size();i++){
               verifliberter(pierres.get(i));
               checkgrouppierre(pierres.get(i));
 
            if(pierres.get(i).getC()==Color.BLACK){
              //  System.out.println("la pierre "+ i + " a nb liberter:"+pierres.get(i).getLiberter());
                pinceau.fillOval(pierres.get(i).getX()-15,pierres.get(i).getY()-15,30,30);// dessine une pierre noire
            }
            else {
                //System.out.println("la pierre "+ i + " a nb liberter:"+pierres.get(i).getLiberter());
                pinceau.drawOval(pierres.get(i).getX()-15,pierres.get(i).getY()-15,30,30);//dessine une pierre blanche 
            }
        }
                   for(int j=0;j<gp.size();j++){
                  System.out.println("les libertées du groupe de pierre : "+j+" sont de : "+ gp.get(j).getTotalLib());  
               }
    }
    
    public pierre verification(int x,int y){// permet de vérifier que lon soit proche d'une intersection
        int marge;
        if(taille==19)
            marge=10;
        else {
            marge=18;     
        }
        int i=1;
        int interx,intery;
       
        while(i<marge){
            if((x+i)%v==0){
                break;
            }
            i++;
        }
        if(i<marge){
            interx=x+i;
        }
        else {
            i=1;
            while(i<marge){
                if((x-i)%v==0){
                    break;
                }
                i++;
            }
            if(i<marge){
                interx=x-i;
            }
            else{ 
            return null;
            }
        }
        i=1;
        
        while(i<marge){
            if((y+i)%v==0){
                break;
            }
            i++;
        }
        if(i<marge){
            intery=y+i;
        }
        else {
            i=1;
            while(i<marge){
                if((y-i)%v==0){
                    break;
                }
                i++;
            }
            if(i<marge){
                intery=y-i;
            }
            else {
               
                return null;
            }
        }  
       if(taille<19)
           return new pierre(interx-12,intery-12); // on fait -12 car v=62 en taille 13 et 9 alors qu il 
       //vaut 50 en 19 alors que les coordonnées déute en 50 50
       else 
         return new pierre(interx,intery);  
    }
    
    public boolean checkemplacement(int x, int y){
        boolean res=true;
        if((x<50 || x>50+t) || (y<50 || y>50+t)){
            return false;
        }
        int i=0;
        while(i<pierres.size()){
            if(pierres.get(i).getX()== x && pierres.get(i).getY()== y ){
                res=false;     
                break;
            }          
            i++;
        }
        return res;
    }
       
    public int verifliberter(pierre p){
        int lib=4;
   
        if(checkemplacement(p.getX()-v,p.getY())==false)// on verifie si ya une pierre a gauche
            lib--;
        
        if(checkemplacement(p.getX()+v,p.getY())==false)// on verifie si ya une pierre a droite
            lib--;
        
        if(checkemplacement(p.getX(),p.getY()-v)==false)// on verifie si ya une pierre en haut
            lib--;
        
        if(checkemplacement(p.getX(),p.getY()+v)==false)// on verifie si ya une pierre en bas
            lib--;
              
       
        p.setLiberter(lib);
        return lib;
    }
    
    public boolean checkPierreAcoter(pierre p){// return true si ya une pierre a coter 

        if(checkemplacement(p.getX()-v,p.getY()))// on verifie si ya une pierre a gauche
           return true;
        
        if(checkemplacement(p.getX()+v,p.getY()))// on verifie si ya une pierre a droite
                       return true;
        
        if(checkemplacement(p.getX(),p.getY()-v))// on verifie si ya une pierre en haut
                     return true;
        
        if(checkemplacement(p.getX(),p.getY()+v))// on verifie si ya une pierre en bas
                      return true;

        return false;
    }
   
       public pierre checkPierreGroupe(pierre p){// si ya une pierre a coter la renvoie
           pierre res;
           res= getPierre(p.getX()-v,p.getY());
        if(res!=null)// on verifie si ya une pierre a gauche
           return res;
       
        res=getPierre(p.getX()+v,p.getY());
        if(res!=null)// on verifie si ya une pierre a droite
           return res;
        res=getPierre(p.getX(),p.getY()-v);
        if(res!=null)// on verifie si ya une pierre en haut
           return res;
        res=getPierre(p.getX(),p.getY()+v);
        if(res!=null)// on verifie si ya une pierre en bas
           return res;

        return res;
    }
    
    
        public pierre getPierre(int x, int y){// méthode qui retourne la premiere pierre au coordonnées x et y a coter si elle existe
  
        if((x<50 || x>50+t) || (y<50 || y>50+t)){
            return null;
        }
        int i=0;
        
        pierre res =null; 
        while(i<pierres.size()){
            if(pierres.get(i).getX()== x && pierres.get(i).getY()== y ){
                res=pierres.get(i);
                break;
            }
            
            i++;
        }

      return res;
    }
    
       
       
    
        public void checkgrouppierre(pierre p){// fonction qui verifie si ya une pierre a coter qui verifie que la pierre a la meme couleur que le groupe de pierre si oui l'ajoute au groupe de pierre 
            pierre m;
            
           for(int i=0;i<pierres.size();i++){
               m= checkPierreGroupe(p);
               if(m!=null){    
                   if(m.getC()==p.getC()){
                       if(m.getGroupe()!=null){
                         if(m.getGroupe()!=p.getGroupe())
                            m.getGroupe().add(p);
                           //System.out.println("creation groupe et liberter = "+gp.get(0).getTotalLib()+"la pierre est en x : "+ p.getX() +" et en y :" + p.getY());
                       }
                       else{
                           
                          /* for(int j=0;j<gp.size();j++){
                             
                           }*/
                             //System.out.println("les liberstées sont de : "+gp.get(0).getTotalLib());
                           gp.add(new Groupepierre(m,p));
                       //     System.out.println("log:"+gp.get(0).getTotalLib());
                           
                       }      
                    }
                }    
            }    
        } 
    
    
    public static void main(String[] args) {
        
        JFrame fenetre = new JFrame();
        fenetre.setSize(1500, 2000);// création de la fenetre graphique au dimension demander
        fenetre.setLocation(0,0);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        terrain jp = new terrain(9);// création du panel
        fenetre.add(jp);
        jp.setBackground(Color.WHITE);// on fixe le fond a la couleur de blanc
        
        fenetre.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()== bouton1){
            taille=9;
            t=500;
            pierres.clear();
        }
        else if(ae.getSource()== bouton2){
            taille=13;
            t=750;
            pierres.clear();
         }
        else if(ae.getSource()== bouton3){
            taille=19;
            t=900;
            pierres.clear();
        }else if(ae.getSource()==abandon){
            abandonner=1;
            this.setBackground(Color.RED);
        }
        else if(ae.getSource()==passer){
            passe=1;
            this.setBackground(Color.BLUE);
        }
        
        this.repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
    
     
        
      if(abandonner==0){
          if(passe==1 && joueur==true){
              joueur=false;
              passe=0;
              contpasse=contpasse+1;
              if(passe==1 && joueur==false)
                 contpasse=contpasse+1; 
          }
          if(passe==1 && joueur==false){
              joueur=true;
              passe=0; 
              contpasse=contpasse+1;
              if(passe==1 && joueur==true)
                   contpasse=contpasse+1;
          }
             if(contpasse==2)
         this.setBackground(Color.GREEN);

            pierre p = verification(me.getX(),me.getY());
            if(p!=null){
                if(checkemplacement(p.getX(),p.getY())){
                   // verifliberter(p);//on verifie les liberter de la nouvelle pierre
                    if(joueur==true){
                        p.setC(Color.BLACK);
                        pierres.add(p);
                        
                        //System.out.println("Voici les coordonnées de la pierre p en x : "+p.getX() +" en Y :"+p.getY());
                     
                        joueur=false;
                    }
                    else {
                        p.setC(Color.WHITE);
                        pierres.add(p);
                        joueur=true;
                    }
                    this.repaint();
                   // checkgrouppierre(p);
                }
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        
    }
    
    @Override
    public void mouseReleased(MouseEvent me) {
        
    }
    
    @Override
    public void mouseEntered(MouseEvent me) {
        
    }
    
    @Override
    public void mouseExited(MouseEvent me) {
        
    }
}