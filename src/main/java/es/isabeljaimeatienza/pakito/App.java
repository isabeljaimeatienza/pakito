package es.isabeljaimeatienza.pakito;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;



/**
 * JavaFX App
 */
public class App extends Application {
     int score = 0; 
    
    // Bola que le será lanzada al imageView_personaje-----------------------------------------------------
    short ballCenterX = 80; //poner variables globales debajo del class
    byte ballCurrentSpeedX = 2; //esto hará que cambiemos la posición cuando queramos, es decir, le damos la velocidad, lo que le vamos sumando o restando
                                  // dependiendo si quiero que vaya hacia atrás para que vaya a la izquierda o sumando y que vaya a la derecha
    byte ballDirectionX = 1; //multiplicas velocidad por dirección
      
    short ballCenterY = 80; //poner variables globales debajo del class
    byte ballCurrentSpeedY = 2; //esto hará que cambiemos la posición cuando queramos, es decir, le damos la velocidad, lo que le vamos sumando o restando
                                  // dependiendo si quiero que vaya hacia atrás para que vaya a la izquierda o sumando y que vaya a la derecha
    byte ballDirectionY = 1;
   
    //Panel donde ocurrirá nuestro juego-----------------
    final short SCENE_HEIGHT =800;//poniendo final hacemos una constante-- mayúsculas para que directamente sepamos que son constantes
    final short SCENE_WIDTH = 1080;
 
    //Para la imagen que usaremos de fondo-------------------------------------
    int fondoX1 = 0;
    int fondoX2 = SCENE_WIDTH; 
    
    //Personaje de nuestra historia---------------------------------------------
    short personajeHeight = 50;
    short personajeWidht = 30;
    short personajePosY = (short)((SCENE_HEIGHT- personajeHeight)/2);
    byte personajeCurrentSpeed = 40; 
    byte personajeDirection = 0; 
    short personajePosX = (short)((SCENE_WIDTH - personajeHeight)/2);
    
    // Rectangulo que agruparemos con el imageView_personaje------------------------------
    short rectpersonajeHeight =50;
    short rectpersonajeWidth = 30;
    short rectpersonajePosY = (short)((SCENE_HEIGHT- rectpersonajeHeight)/2);
    byte rectpersonajeCurrentSpeed = 40; 
    byte rectpersonajeDirection = 0; 
    short rectPersonajePosX = (short)((SCENE_WIDTH - rectpersonajeHeight)/2);
    
    // Sobre el grupo creado (personaje+rectángulo)-----------
    short groupPersonajeHeight = 50;
    short groupPersonajeWidht = 30;
    short groupPersonajePosY = (short)((SCENE_HEIGHT- groupPersonajeHeight)/2);
    byte groupPersonajeCurrentSpeed = 40; 
    byte groupPersonajeDirection = 0; 
    short groupPersonajePosX = (short)((SCENE_WIDTH - groupPersonajeHeight)/2);
   
    /**
     *
     * @param stage
     */
    
 @Override
    public void start(Stage stage) {
 
        //Creación de nuevo objeto, en este caso se trata de nuestro App donde jugaremos--------------
        Pane root = new Pane(); //lo guardo en una variable que he llamado root (el App)
        var scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);//Crea ventana de esa medida usando la variable root
        stage.setTitle("Pim Pam Jump");
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        

        // Creación de nuevo objeto, en este caso se trata de la imagen que usaremos para el fondo---------
        javafx.scene.image.Image image1 = new javafx.scene.image.Image(getClass().getResourceAsStream("/images/fondo.png"));
        ImageView imageView_fondo = new ImageView(image1);
        javafx.scene.image.Image image2 = new javafx.scene.image.Image(getClass().getResourceAsStream("/images/fondo.png"));
        ImageView imageView_fondo2 = new ImageView(image2);
        
        imageView_fondo2.setX(fondoX2);
        imageView_fondo2.setY(0);
        
        imageView_fondo.setFitHeight (SCENE_HEIGHT);
        imageView_fondo.setFitWidth(0);
        imageView_fondo2.setFitHeight (SCENE_HEIGHT);
        imageView_fondo2.setFitWidth(SCENE_WIDTH);
        
        //Imagen que usaré como imageView_personaje-----------------------------------------------------------------------
        javafx.scene.image.Image image3 = new javafx.scene.image.Image(getClass().getResourceAsStream("/images/personaje.png"));
        ImageView imageView_personaje = new ImageView(image3);
        imageView_personaje.setX(SCENE_HEIGHT/4);
        imageView_personaje.setY(SCENE_WIDTH/2); 
        
    
        root.getChildren().add(imageView_fondo);
        root.getChildren().add(imageView_fondo2);
        root.getChildren().add(imageView_personaje);
        
        // Comenzamos con la animación del fondo, en este caso se mueve de modo lateral----------------
        Timeline timeline;
        timeline = new Timeline(
                // 0.017 ~= 60 FPS
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                    fondoX1--;
                    fondoX2--;
                    
                    imageView_fondo.setX(fondoX1);
                    imageView_fondo2.setX(fondoX2);
                    if (fondoX2 == 0){
                        fondoX1 = 0;
                        fondoX2 = SCENE_WIDTH;
                        imageView_fondo.setX(fondoX1);
                        imageView_fondo2.setX(fondoX2);
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();  
       
    //new Circle--> crear un objeto de la clase Circle
    Circle circleBall = new Circle(); //aquí voy a guardar una bola, con new me creo objeto circulo
    //llamando a métodos del objeto circleBall
    circleBall.setCenterX(0); // obligatoriamente debe de tener una medida, double permite decimales
    circleBall.setCenterY(600);
    circleBall.setRadius(20);//son métodos: nosequé. lo que sea
    circleBall.setFill(javafx.scene.paint.Color.TRANSPARENT);//Cambiar el color de la bola


    double r= circleBall.getRadius()*2; //me retorna un número

   //Circle circleBall2= new Circle(10, 30, 7); es otro modo de hacer la bola pero con menos líneas

    root.getChildren().add(circleBall);//los hijos hace referencia a las cosas que contiene el App
       
        
     
   
    //Creación de rectángulo    
    javafx.scene.shape.Rectangle rectpersonaje = new javafx.scene.shape.Rectangle(90,130);
    rectpersonaje.setLayoutX(SCENE_HEIGHT/4); 
    rectpersonaje.setLayoutY(SCENE_WIDTH/2);
    rectpersonaje.setFill(javafx.scene.paint.Color.WHITE);
    // Creación del grupo donde encontraremos la imagen con el rectángulo   
    Group groupPersonaje = new Group();
    //agrupamos el rectangulo creado + el perosnaje
    groupPersonaje.getChildren().add(rectpersonaje);
    groupPersonaje.getChildren().add(imageView_personaje);
    root.getChildren().add(groupPersonaje);

        
    //reconocer teclas-detectarlas
    scene.setOnKeyPressed((final KeyEvent keyEvent) -> {
        switch(keyEvent.getCode()){
            case UP:
                //groupPersonaje.setLayoutY((groupPersonaje.getLayoutY())-3);
                groupPersonajeDirection = -1;
                break;
            case DOWN:
                groupPersonaje.setLayoutY((groupPersonaje.getLayoutY())+3);
                groupPersonajePosY= 1;
                break;
            case RIGHT:
                groupPersonaje.setLayoutX((groupPersonaje.getLayoutX())+3);
                groupPersonajePosX= 1;
                break;
            case LEFT:
                groupPersonaje.setLayoutX((groupPersonaje.getLayoutX())-3);
                groupPersonajePosX= -1;
                break;
        }
        });
        
        
    Timeline timeline2;
        timeline2 = new Timeline(
                // 0.017 ~= 60 FPS
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                    circleBall.setCenterX(ballCenterX);
                    ballCenterX+=ballCurrentSpeedX * ballDirectionX;
                    
                    // Hará que el imageView_personaje se mueva hacia arriba solo al pusar UP
                    groupPersonaje.setLayoutY(groupPersonajePosY);
                    groupPersonajePosY += groupPersonajeCurrentSpeed * groupPersonajeDirection;
                    if (groupPersonajePosY <= 0|| groupPersonajePosY >= SCENE_HEIGHT-groupPersonajeHeight) {
                        groupPersonajeDirection = 0;
                        groupPersonajePosY = 0;
                       
                    }else if (groupPersonajePosY >= SCENE_HEIGHT- groupPersonajeHeight){
                        groupPersonajeDirection = 0;
                        groupPersonajePosY = (short) (SCENE_HEIGHT - groupPersonajeHeight);
                    }
                    
                    Shape shapeCollision = Shape.intersect(circleBall, rectpersonaje);
                    
                    boolean vaciaCollision = shapeCollision.getBoundsInParent().isEmpty();
                    if (vaciaCollision == false){
                        System.out.println("Ha colisionado");
                    }
                })
        );

   timeline2.setCycleCount(Timeline.INDEFINITE);
   timeline2.play();
   

    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
    

}