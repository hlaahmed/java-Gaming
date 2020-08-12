
package ZombieGame ;



import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import static java.lang.Math.abs;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.animation.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;


   enum Direction{ RIGHT,LEFT,UP,DOWN};
   
public class ZombieGame extends Application {
    boolean opensecondlevel=true;
    boolean openthirdlevel =true;
    boolean startbuttonpressed=false;
    AudioClip audio = new AudioClip("file:Newfolder/nightmare.WAV");
    AudioClip audio1 = new AudioClip("file:Newfolder/radionoise.WAV");
    Button button;
    Button nextlvl;
    Cylinder passagetube1;
    Cylinder passagetube2;
    Cylinder passagetube3;
    int count=0;

    Rectangle l1;
    Rectangle l2;
    Rectangle l3;
    Rectangle floor;
        
    ImageView v1;
    ImageView v2;   
    ImageView v3;
    ImageView v4;
    ImageView m;
    ImageView b1v;
    ImageView b2v;
    ImageView zFlyv;
    ImageView Dv;
    ImageView b3v;
    Circle shoot;
    boolean haswon;
    Image zomb=new Image("file:Newfolder\\Zombiewave.gif");
    Image w = new Image("file:Newfolder\\Hero_Left.png");
    Image w2 = new Image("file:Newfolder\\Hero_Right.png");
    Image b1 = new Image( "file:Newfolder\\Lvl1Background.jpg");
    Image b2 = new Image("file:Newfolder\\wp2312576-scary-castle-wallpapers.jpg");
    AudioClip zombiesound = new AudioClip("file:Newfolder/ZombieSound.mp3");
    AudioClip ag = new AudioClip("file:Newfolder/bullet.mp3");
    //methods
        // zombies colliding with hero
         public static void move(Pane p,ImageView m,ImageView b1v)
         {
             
            zombiesmoving(p,m,b1v);
            for(Node zombie:p.getChildren())
                  if(zombie instanceof ImageView && zombie!=m && !(zombie.equals(b1v)))
                  {
                      ImageView z=(ImageView)zombie;
                      
                      if(hascollision(z.getX(),z.getY(),m))
                      {
                          if(z.isVisible())
                          {
                              
                                      JOptionPane.showMessageDialog(null, "Game Over!");
                                      System.exit(0);
                                
                          }
                      }
                  }
          
         }
          
         public static boolean hascollision(double x,double y,ImageView m)
         {
             if(y==m.getY())
             {
                 if(abs(x-m.getX())<=20)
                 {
                     return true;
                 }
                 return false;
             }
             return false;
         }
         // hero colliding with zombies
        static void checkcollision(Pane p,ImageView m) 
         {
             for(Node n:p.getChildren())
                  {
                      if(n instanceof ImageView && !(n.equals(m)))
                      {
                          ImageView zombie=(ImageView)n;
                        if(m.getY()==zombie.getY()&&(zombie.isVisible()))  
                        {
                            if(abs(zombie.getX()-m.getX())<=20)
                            {
                                
                                JOptionPane.showMessageDialog(null, "Game Over!");
                                  System.exit(0);
                            }
                        }
                      }
                  }
         }
        public void Win(ImageView Diamond,Pane p,ImageView m) 
         {
           
                        if(m.getX()>= Diamond.getX()-50)  
                        {
                            
                                
                                JOptionPane.showMessageDialog(null, "You found the cure !");
                                  System.exit(0);
                            
                        }
                      
                  
         }
   public static void zombiesmoving(Pane p,ImageView m,ImageView b1v) 
              {
                 
                  double x=0; double y=0;
                  for(Node n:p.getChildren())
                  {
                       if(n instanceof ImageView && !(n.equals(m))&& !(n.equals(b1v)))
                      {
                          ImageView zombie =(ImageView)n;
                         
                              
                          
                              x=zombie.getX();
                          
                                 double nx=x;
                              nx=(x-1<20)?700:x-1;
                            
                               zombie.setX(nx);
                          
                           
                      }
                       
                     }
                  
              }
      
    class zombies extends Pane
    {
         
      
        private Direction dir=Direction.RIGHT;
        
        public zombies()
        {
            // creation of bullets
              shoot=new Circle(5, Color.YELLOW);
              shoot.setStroke(Color.ORANGE);
          
           
            b1v = new ImageView(b1);
            b1v.setFitHeight(800);
            b1v.setFitWidth(800);
     
            //zombies
           v1 = new ImageView(zomb);
            v1.setX(700);
           v1.setY(50);
           v1.setFitHeight(120);
           v1.setFitWidth(100);
           
           v2 = new ImageView(zomb);
           v2.setX(700);
           v2.setY(250);
           v2.setFitHeight(120);
           v2.setFitWidth(100);
           
           v3 = new ImageView(zomb);
           v3.setX(700);
           v3.setY(450);
           v3.setFitHeight(120);
           v3.setFitWidth(100);
           
           v4 = new ImageView(zomb);
           v4.setX(700);
           v4.setY(650);
           v4.setFitHeight(120);
           v4.setFitWidth(100);
            
        //hero
           m = new ImageView(w2);
           m.setX(15);
           m.setY(50);
           m.setFitHeight(150);
           m.setFitWidth(120);
            
        //tubes to move to a different floor
             passagetube1=new Cylinder(50,200);
             passagetube1.setLayoutX(550);
             passagetube1.setLayoutY(270);
             
             passagetube2=new Cylinder(50,200);
             passagetube2.setLayoutX(350);
             passagetube2.setLayoutY(470);
             
             passagetube3=new Cylinder(50,200);
             passagetube3.setLayoutX(150);
             passagetube3.setLayoutY(670);
            
             //floors
             l1=new Rectangle(0,170, 800,10);
             l2=new Rectangle(0,370, 800,10);
             l3=new Rectangle(0,570, 800,10);
             l1.setFill(Color.BLACK);
             l2.setFill(Color.BLACK);
             l3.setFill(Color.BLACK);
             //ground
            floor =new Rectangle(0,750,800, 50);
            floor.setFill(Color.BLACK);
           this.getChildren().addAll(b1v,floor,l1,l2,l3,passagetube1,passagetube2,passagetube3,v1,v2,v3,v4,m,shoot);
         
          //reaching the last floor and winning
           this.setOnKeyPressed((KeyEvent event) -> {
             if(m.getY()==650&&m.getX()>=730&& !(v4.isVisible()||v3.isVisible()||v2.isVisible()||v1.isVisible())&&opensecondlevel)
             {
                  
                
                   nextlvl=new Button("next level");
                  this.getChildren().add(nextlvl);
                  nextlvl.setOnAction(
                          e ->
                          {
                               this.getChildren().clear();
                               zombieslvl2 zz=new zombieslvl2();
                               this.getChildren().add(zz);
                               zz.requestFocus();
                               opensecondlevel=false;
                          }
                  );
             }
             //shooting projectiles
             
                   if(event.getCode()==KeyCode.ENTER)
                   {
                      
                                 
                                  ag.play(0.5);    
                        
                          shoot.setCenterY(m.getY()+70);
                          shoot.setCenterX(m.getX()+100);
                          final Timeline timeline = new Timeline();
                          timeline.setDelay(Duration.millis(8));
                          timeline.setCycleCount(1);
                    
                    
                   
                        if(m.getY()==50)
                        {
                            
                            
                             count=count+1;
                            if(count==5)
                            {
                                v1.setVisible(false);
                                count=0;
                            }
                                
                             shoot.setVisible(v1.isVisible());
                               
                     
                            final KeyValue kv = new KeyValue(shoot.centerXProperty(),v1.getX());
                            final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                            timeline.getKeyFrames().add(kf);
                            timeline.play();
                        }
                        
                         if(m.getY()==250)
                        {
                              
                           
                             count=count+1;
                            if(count==5)  
                            {
                                v2.setVisible(false);
                          
                                count=0;
                            }
                             shoot.setVisible(v2.isVisible());
                           
                          
                             final KeyValue kv = new KeyValue(shoot.centerXProperty(),v2.getX());
                            final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                            timeline.getKeyFrames().add(kf);
                            timeline.play();
                        }
                          if(m.getY()==450)
                        {
                              
                            
                            if(count==5)
                               {
                                  
                                 v3.setVisible(false);
                                count=0;
                            }
                            shoot.setVisible(v3.isVisible());
                            count=count+1;
                         
                            final KeyValue kv = new KeyValue(shoot.centerXProperty(),v3.getX());
                            final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                            timeline.getKeyFrames().add(kf);
                            timeline.play();
                        }
                           if(m.getY()==650)
                        {
                            
                            if(count==5)
                            {
                                
                                v4.setVisible(false);
                                
                                count=0;
                            
                            }
                            
                            shoot.setVisible(v4.isVisible());
                            count=count+1;
                         
                            final KeyValue kv = new KeyValue(shoot.centerXProperty(),v4.getX());
                            final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                            timeline.getKeyFrames().add(kf);
                            timeline.play();
                        }
                        
                   }
                  //hero movement
                   if(event.getCode()==KeyCode.RIGHT)
                   {
                       
                        m.setImage(w2);
                        m.setFitWidth(120);
                       
                         m.setVisible(true);
                       if(m.getX()<730)
                       m.setX(m.getX()+20);
                           
                          ZombieGame.checkcollision(this,m);
                         
                      
                   }
                   if(event.getCode()==KeyCode.LEFT)
                   {  
                       
                        m.setImage(w);
                        m.setFitWidth(190);
                         m.setVisible(true);
                        if(m.getX()>40)
                      m.setX(m.getX()-20);
                      ZombieGame.checkcollision(this,m);
                     
                      
                      
                   }
                   if(event.getCode()==KeyCode.DOWN)
                   {                      
                        
                       ZombieGame.checkcollision(this,m);
                       if(m.getY()==50)
                       {
                           if(420<m.getX()&&m.getX()<520)
                           {
                               m.setVisible(false);
                               m.setY(m.getY()+200);
                           }
                                 
                           
                       }
                       else if (m.getY()==250)
                               {
                                    
                                   if(220<m.getX()&&m.getX()<310)
                                   {
                                       m.setVisible(false);
                                       m.setY(m.getY()+200);
                                       
                                   }
                               }
                          
                        else if (m.getY()==450)
                               {
                                     
                                   if(20<m.getX()&&m.getX()<110)
                                                                              
                                   {
                                       m.setVisible(false);
                                       m.setY(m.getY()+200);
                                   }
                               } 
                      
                   }
                  
                     
               
          });
         //zombies movement
           Pane p=this;
          EventHandler<ActionEvent>  anim = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
               if(startbuttonpressed)
                        ZombieGame.move(p ,m,b1v);
                   
                               
            }
            
        };
        Timeline t = new Timeline(new KeyFrame(Duration.millis(10), anim));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        }
        
      
     
      
       
           
    }
    /////////////////////////////////////////////////////// level 2


    class zombieslvl2 extends Pane
    {
        Image GtZ=new Image("file:Newfolder\\GiantZombie.png");
        
        public zombieslvl2()
        {
           // creation of bullets
            b2v = new ImageView(b2);
            b2v.setFitHeight(800);
            b2v.setFitWidth(800);
     
            shoot=new Circle(5, Color.YELLOW);
            shoot.setStroke(Color.ORANGE);
          
            
            

            //zombies
           
           
           v4 = new ImageView(GtZ);
           v4.setX(450);
           v4.setY(485);
           v4.setFitHeight(350);
           v4.setFitWidth(350);
            
        //hero
           m = new ImageView(w2);
           m.setX(15);
           m.setY(635);
           m.setFitHeight(150);
           m.setFitWidth(120);

           this.getChildren().addAll(b2v,v4,m,shoot);
           int i=0;
           this.setOnKeyPressed((KeyEvent event) -> {
               if(!(v4.isVisible()||v3.isVisible()||v2.isVisible()||v1.isVisible())&&openthirdlevel)
             {
                  
                
                  nextlvl =new Button("next level");
                  this.getChildren().add(nextlvl);
                  nextlvl.setOnAction(
                          e ->
                          {
                               this.getChildren().clear();
                               zombieslvl3 zz=new zombieslvl3();
                               this.getChildren().add(zz);
                               zz.requestFocus();
                               openthirdlevel=false;
                          }
                  );
             }
              if(event.getCode()==KeyCode.ENTER)     
              {
                      
                       
                        
                        ag.play();
                       shoot.setCenterY(m.getY()+70);
                          
                       shoot.setCenterX(m.getX()+100);
                          
                       final Timeline timeline = new Timeline();
                          
                       timeline.setDelay(Duration.millis(8));
                          
                       timeline.setCycleCount(1);
                    
             
                           
                       if(m.getY()==635&&v4.isVisible())
                       {
                             count=count+1;
                               
                           if(count==5)
                           {
                               
                                
                               v1 = new ImageView(zomb);
                               v1.setX(v4.getX());
                               v1.setY(635);
                               v1.setFitHeight(120);
                               v1.setFitWidth(100);
           
                               v2 = new ImageView(zomb);
                               v2.setX(v4.getX()-100);
                               v2.setY(635);
                               v2.setFitHeight(120);
                               v2.setFitWidth(100);
           
                               v3 = new ImageView(zomb);
                               v3.setX(v4.getX()+100);
                               v3.setY(635);
                               v3.setFitHeight(120);
                               v3.setFitWidth(100);     
                               this.getChildren().addAll(v1,v2,v3);
                               v4.setVisible(false);
                               count=0;
                            
                            }
                            
                           shoot.setVisible(v4.isVisible());
                            
                         
                           
                           final KeyValue kv = new KeyValue(shoot.centerXProperty(),v4.getX());
                           final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                           timeline.getKeyFrames().add(kf);
                           timeline.play();
                        
                       }
                           
                       else
                       {
                         
                           
                           
                               if(v1.isVisible()){
                                     count=count+1;
                                   final KeyValue kv = new KeyValue(shoot.centerXProperty(),v1.getX());
                                   final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                                   timeline.getKeyFrames().add(kf);
                                   timeline.play();
                               if(count==5)
                               {
                               
                                   v1.setVisible(false);
                                   count=0;
                               }
                               
                         
                           shoot.setVisible(v1.isVisible());
                            
                         
                         
                           }
                               
                             if(!(v1.isVisible())&&v2.isVisible()){
                                 final KeyValue kv = new KeyValue(shoot.centerXProperty(),v2.getX());
                                 final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                                 timeline.getKeyFrames().add(kf);
                                 timeline.play();
                            if(count==5)
                               {
                               
                                   v2.setVisible(false);
                                   count=0;
                               }
                         
                           shoot.setVisible(v2.isVisible());
                            
                           count=count+1;
                         
                           
                               }
                             if(!(v1.isVisible())&&!(v2.isVisible())&&v3.isVisible())
                             {
                                 final KeyValue kv = new KeyValue(shoot.centerXProperty(),v3.getX());
                                 final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                                 timeline.getKeyFrames().add(kf);
                                 timeline.play();
                                  if(count==5)
                               {
                               
                                   v3.setVisible(false);
                                   count=0;                             
                               }
                         
                           shoot.setVisible(v3.isVisible());
                            
                           count=count+1;
                         
                           
                             }
                       }
                               
                        
                   
              }
                  //hero movement
                   if(event.getCode()==KeyCode.RIGHT)
                   {
                        ZombieGame.checkcollision(this,m);
                        m.setImage(w2);
                        m.setFitHeight(150);
                        m.setFitWidth(120);
                       
                         m.setVisible(true);
                       if(m.getX()<730)
                       m.setX(m.getX()+20);
                           
                        
                         
                      
                   }
                   if(event.getCode()==KeyCode.LEFT)
                   {
                        ZombieGame.checkcollision(this,m);
                        m.setImage(w);
                        m.setVisible(true);
//                        m.setY(600);
                        m.setFitHeight(150);
                        m.setFitWidth(190);
                        if(m.getX()>40)
                      m.setX(m.getX()-20);
                    
                     
                   }
              
              });
           
         //zombies movement
        
                Pane p2 =this;  
         EventHandler<ActionEvent>  anim = new EventHandler<ActionEvent>()
         {
            @Override
            public void handle(ActionEvent event)
            {
               if(startbuttonpressed)
                   
                        ZombieGame.move(p2,m,b2v);
                   
                               
            }
            
        };
        Timeline t = new Timeline(new KeyFrame(Duration.millis(50), anim));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        
        }
        
       
         
    }
         
        
   ////////////////////////////////////////////////////////level 3 
    
     class zombieslvl3 extends Pane
     {
         Image zFly = new Image("file:Newfolder\\zombieFly.gif");
         Image D = new Image("file:Newfolder\\cure.png");
         Image b3 = new Image("file:Newfolder\\istockphoto-515863543-612x612.jpg");
        
         public zombieslvl3()
         {
            b3v = new ImageView(b3);
            b3v.setFitHeight(800);
            b3v.setFitWidth(800);
            
            shoot=new Circle(5, Color.YELLOW);
            shoot.setStroke(Color.ORANGE);
            
           // count =0;


            Dv = new ImageView(D);
            Dv.setX(700);
            Dv.setY(635);
            Dv.setFitWidth(100);
            Dv.setFitHeight(100);
            
              zFlyv = new ImageView(zFly);         
                zFlyv.setX(575);
                zFlyv.setY(250);
                zFlyv.setFitHeight(150);
                zFlyv.setFitWidth(150);  
            
            v1 = new ImageView(zomb);
            v1.setX(350);
            v1.setY(635);
            v1.setFitHeight(120);
            v1.setFitWidth(100);

            v2 = new ImageView(zomb);
            v2.setX(v1.getX()-100);
            v2.setY(635);
            v2.setFitHeight(120);
            v2.setFitWidth(100);

            v3 = new ImageView(zomb);
            v3.setX(v1.getX()+100);
            v3.setY(635);
            v3.setFitHeight(120);
            v3.setFitWidth(100);
           
            m = new ImageView(w2);
            m.setX(15);
            m.setY(635);
            m.setFitHeight(150);
            m.setFitWidth(120);
            this.getChildren().addAll(b3v,zFlyv,Dv,v1,v2,v3,m,shoot);

  
            this.setOnKeyPressed((KeyEvent event) -> {
                if(!(v1.isVisible()||v2.isVisible() || v3.isVisible() || zFlyv.isVisible()))
                {
                     Win(Dv, this, m);
                }
            
              if(event.getCode()==KeyCode.ENTER)     
              {
                      
                       
                        
                        ag.play();
                       shoot.setCenterY(m.getY()+70);
                          
                       shoot.setCenterX(m.getX()+100);
                          
                       final Timeline timeline = new Timeline();
                          
                       timeline.setDelay(Duration.millis(8));
                          
                       timeline.setCycleCount(1);
                         
                           
                           
                               
                       if(v1.isVisible())
                       {
                                     
                           count=count+1;
                           final KeyValue kv = new KeyValue(shoot.centerXProperty(),v1.getX());
                           final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                           timeline.getKeyFrames().add(kf);
                           timeline.play();
                               
                           if(count==5)     
                           {
                               
                                   v1.setVisible(false);
                                   count=0;
                               
                           }
                               
                         
                           shoot.setVisible(v1.isVisible());
                            
                         
                         
                           }
                               
                             if(!(v1.isVisible())&&v2.isVisible()){
                                 final KeyValue kv = new KeyValue(shoot.centerXProperty(),v2.getX());
                                 final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                                 timeline.getKeyFrames().add(kf);
                                 timeline.play();
                            if(count==5)
                               {
                               
                                   v2.setVisible(false);
                                   count=0;
                               }
                         
                           shoot.setVisible(v2.isVisible());
                            
                           count=count+1;
                         
                           
                               }
                             if(!(v1.isVisible())&&!(v2.isVisible())&&v3.isVisible())
                             {
                                 final KeyValue kv = new KeyValue(shoot.centerXProperty(),v3.getX());
                                 final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                                 timeline.getKeyFrames().add(kf);
                                 timeline.play();
                                  if(count==5)
                               {
                               
                                   v3.setVisible(false);
                                   count=0;                             
                               }
                         
                           shoot.setVisible(v3.isVisible());
                            
                           count=count+1;
                         
                           
                             }
                             if(!(v1.isVisible())&& !(v2.isVisible()) && !(v3.isVisible()) && zFlyv.isVisible() && zFlyv.getY()==m.getY())
                             {
                                  final KeyValue kv = new KeyValue(shoot.centerXProperty(),zFlyv.getX());
                                 final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                                 timeline.getKeyFrames().add(kf);
                                 timeline.play();
                                  if(count==5)
                               {
                               
                                   zFlyv.setVisible(false);
                                   count=0;                             
                               }
                         
                           shoot.setVisible(zFlyv.isVisible());
                            
                           count=count+1;
                             }
    
              }
                  //hero movement
                   if(event.getCode()==KeyCode.RIGHT)
                   {
                        ZombieGame.checkcollision(this,m);
                        m.setImage(w2);
                        m.setFitHeight(150);
                        m.setFitWidth(120);
                       
                         m.setVisible(true);
                       if(m.getX()<730)
                       m.setX(m.getX()+20);
                           
                                 if(m.getX()>=300)   
        
                                 {
                
                                     zFlyv.setY(m.getY());
                         
            
         
                                 }
                    
                      
                   }
                   if(event.getCode()==KeyCode.LEFT)
                   {
                        ZombieGame.checkcollision(this,m);
                        m.setImage(w);
                        m.setVisible(true);
//                        m.setY(600);
                        m.setFitHeight(150);
                        m.setFitWidth(190);
                        if(m.getX()>40)
                      m.setX(m.getX()-20);
                    
                     
                   }
              
              });

                            Pane p3 =this;  
         EventHandler<ActionEvent>  anim = new EventHandler<ActionEvent>()
         {
            @Override
            public void handle(ActionEvent event)
            {
               if(startbuttonpressed)
                 
                       for(Node n: getChildren())
                       {
                           
                          
                           double x=0; double y=0;
                           if((n.equals(v1)) || (n.equals(v2)) || (n.equals(v3)))        
                           {
                                          
                               ImageView zombie =(ImageView)n;
                                
                                x=zombie.getX();
                                 
                                double nx=x;
                              
                                nx=(x-1<20)?700:x-1;
                            
                               
                                zombie.setX(nx);                      
                           }      
                           
                       }
                   
                               
            }
            
        };
                     

        Timeline t = new Timeline(new KeyFrame(Duration.millis(50), anim));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
             
         }
     }
     ///////////////////////////////////////////////////// menu
   class Menu extends BorderPane{
          final double dim = 100;
          final double w= 50;
          final double spc = 5;
          
       public Menu(){
               ZombieGame.zombies cz=new ZombieGame.zombies();
               Scene sc=new Scene(cz,790,800);
     
                cz.requestFocus(); 
                  
              audio.play(1, 0,1, 0, 1);
             Image zb=new Image("file:Newfolder\\MenuPointer.png",dim,w, startbuttonpressed, startbuttonpressed);
   
         BackgroundImage z = new BackgroundImage(zb,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
         
         Background background = new Background(z);
           Image b = new Image("file:newfolder\\Menu_Background.png");
         BackgroundImage bb = new BackgroundImage(b,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
      
        VBox opsPane = new VBox();
      opsPane.setAlignment(Pos.BOTTOM_CENTER);
        opsPane.setPrefSize(16*dim, w);
        this.setBottom(opsPane);
     
     
            Button startbtn = new Button("Start Game");
            startbtn.setPrefSize(4*dim, w);
             startbtn.setBackground(background);
                startbtn.setFont(Font.font("Times New Roman", 20));

startbtn.setOnAction(new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent event){
             audio.stop();
              zombiesound.play(1);
                startbuttonpressed=true;
                Stage newWindow=new Stage();
                newWindow.setTitle("Zombies Game");
                newWindow.setScene(sc);
                newWindow.setResizable(false);
                newWindow.show();
                
    }
});
        Button helpbtn = new Button("Help");
        helpbtn.setPrefSize(4*dim, w);
        helpbtn.setBackground(background);
                helpbtn.setFont(Font.font("Times New Roman", 20));
        
        helpbtn.setOnAction(new EventHandler<ActionEvent>(){
    public void handle(ActionEvent event){
        
       Label secondLabel = new Label("In order to win you should reach the last floor without touching the zombies");
                StackPane sc= new StackPane();
                sc.getChildren().add(secondLabel);
                Scene secondsc = new Scene(sc, 550, 150);
                Stage newWindow=new Stage();
                newWindow.setTitle("Zombies Game");
                newWindow.setScene(secondsc);
                newWindow.setResizable(false);
                newWindow.show();
    }
});
       Button creditsbtn = new Button("Credits");
        creditsbtn.setBackground(background);
       creditsbtn.setPrefSize(4*dim, w);
                creditsbtn.setFont(Font.font("Times New Roman", 20));
                creditsbtn.setOnAction(new EventHandler<ActionEvent>(){
    public void handle(ActionEvent event){
        
       Label secondLabel = new Label("Concept & Design : Dina Amr , Hla Ahmed , Mayar Sherif ,"
                        + "Program & Design : Hla Ahmed , "
                        + "Graphics : Mayar Sherif , "
                        + "Additional: Dina Amr");
                StackPane sc= new StackPane();
                sc.getChildren().add(secondLabel);
                Scene secondsc = new Scene(sc, 1000, 150);
                Stage newWindow=new Stage();
                newWindow.setTitle("Credits");
                newWindow.setScene(secondsc);
                newWindow.setResizable(false);
                newWindow.show();
    }
});
                 Button op = new Button();
        op.setText("Options");
         op.setBackground(background);
        op.setPrefSize(4*dim,w );
                op.setFont(Font.font("Times New Roman", 20));

        op.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Label secondLabel = new Label("To move Right press Right Arrow ,"
                        + "To move Left press left Arrow,"
                        + "To move Down press Down Arrow");
                StackPane sc= new StackPane();
                sc.getChildren().add(secondLabel);
                Scene secondsc = new Scene(sc, 1000, 50);
                Stage newWindow=new Stage();
                newWindow.setTitle("Options");
                newWindow.setScene(secondsc);
                newWindow.setResizable(false);
                newWindow.show();
                
            }
        });
        
         Button s = new Button();
       
         s.setBackground(background);
        s.setText("Sound Effects");
        s.setPrefSize(4*dim, w);
                s.setFont(Font.font("Times New Roman", 20));

        s.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Label secondLabel = new Label("To move Right press Right Arrow ,"
                        + "To move Left press left Arrow,"
                        + "To move Down press Down Arrow");
                StackPane sc= new StackPane();
                sc.getChildren().add(secondLabel);
                Scene secondsc = new Scene(sc, 1000, 50);
                Stage newWindow=new Stage();
                newWindow.setTitle("Sound Effect");
                newWindow.setScene(secondsc);
                newWindow.setResizable(false);
                newWindow.show();
                
            }
        });
                
       Button endbtn = new Button("Exit");
       endbtn.setBackground(background);
       endbtn.setPrefSize(4*dim, w);
                       endbtn.setFont(Font.font("Times New Roman", 20));

       endbtn.setOnAction(e -> Platform.exit());
       
       opsPane.getChildren().addAll(startbtn,helpbtn,op,s,creditsbtn,endbtn);
       
        this.setBottom(opsPane);
        
       
        Background bf = new Background(bb);
        this.setBackground(bf);
        

}

            
       
}
       
    public void start(Stage primaryStage) {
      
      
       Menu cp = new Menu();
        Scene scc = new Scene(cp);
          
        cp.setPrefSize(780, 720);

        primaryStage.setScene(scc);
        primaryStage.setTitle("Zombies");
        primaryStage.setResizable(false);
        
               
       primaryStage.show();       
}
      
       public static void main(String[] args) {
        launch(args);
    }
}