package es.isabeljaimeatienza.pakito;



import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.print.attribute.standard.Media;




/**
 * JavaFX App
 */
public class App extends Application {
    
    int vida = 2;
    int score = 0;
    // Bola que le será lanzada al imageView_personaje-----------------------------------------------------
    short ballCenterX = 1000; //poner variables globales debajo del class
    byte ballCurrentSpeedX = 2; //esto hará que cambiemos la posición cuando queramos, es decir, le damos la velocidad, lo que le vamos sumando o restando
                                  // dependiendo si quiero que vaya hacia atrás para que vaya a la izquierda o sumando y que vaya a la derecha
    byte ballDirectionX = 1; //multiplicas velocidad por dirección
      
    short ballCenterY = 80; //poner variables globales debajo del class
    byte ballCurrentSpeedY = 2; //esto hará que cambiemos la posición cuando queramos, es decir, le damos la velocidad, lo que le vamos sumando o restando
                                  // dependiendo si quiero que vaya hacia atrás para que vaya a la izquierda o sumando y que vaya a la derecha
    byte ballDirectionY = 1;
    int bola = 1000;
   
    //Panel donde ocurrirá nuestro juego-----------------
    final short SCENE_HEIGHT =800;//poniendo final hacemos una constante-- mayúsculas para que directamente sepamos que son constantes
    final short SCENE_WIDTH = 1080;
 
    //Para la imagen que usaremos de fondo-------------------------------------
    int fondoX1 = 0;
    int fondoX2 = SCENE_WIDTH; 
    
    //Personaje de nuestra historia---------------------------------------------
    short personajeHeight = 50;
    short personajeWidht = 30;
    short personajePosY = 0;
    byte personajeCurrentSpeed = 10; 
    byte personajeDirection = 0; 
    short personajePosX = 0;
    
    //Pua de nuestra historia---------------------------------------------
    short guitarraHeight = 15;
    short guitarraWidht = 20;
    short guitarraPosY = 0;
    byte guitarraCurrentSpeed = 10; 
    byte guitarraDirection = 0; 
    short guitarraPosX = 0;
    
        // Rectangulo que agruparemos con el imageView_guitarra------------------------------
    short rectGuitarraHeight =15;
    short rectGuitarraWidth = 20;
    short rectGuitarraPosY = 0;
    byte rectGuitarraCurrentSpeed = 10; 
    byte rectGuitarraDirection = 0; 
    short rectGuitarraPosX = 0;
    
    // Rectangulo que agruparemos con el imageView_personaje------------------------------
    short rectpersonajeHeight =50;
    short rectpersonajeWidth = 30;
    short rectpersonajePosY = 0;
    byte rectpersonajeCurrentSpeed = 10; 
    byte rectpersonajeDirection = 0; 
    short rectPersonajePosX = 0;
    
    // Sobre el grupo creado (personaje+rectángulo)-----------
    Group groupPersonaje = new Group();
    short groupPersonajeHeight = 50;
    short groupPersonajeWidht = 30;
    short groupPersonajePosY = 0;
    short groupPersonajePosX = 0;
    byte groupPersonajeCurrentSpeed = 10; 
    /* nuestro personaje puede hacer movimientos hacia arriba o hacia abajo.
    Debido a las 2 direcciones posibles necesitaremos declararlas. Y hará movimientos
    verticales y X hará los horizontales. La dirección puede ser '-1' -' 0'- '1'*/
    byte groupPersonajeDirectionY = 0; 
    byte groupPersonajeDirectionX = 0; 
    
     // Sobre el grupo creado (guitarra+rectángulo)-----------
    Group groupGuitarra;
    short groupGuitarraHeight = 50;
    short groupGuitarraWidht = 30;
    short groupGuitarraPosY=550;
    short groupGuitarraPosX =1000;
    byte groupGuitarraCurrentSpeed = 10; 
    byte groupGuitarraDirectionX = 0; 
    byte groupGuitarraDirectionY = 0; 
    int guitarra = 1000;
    //Para que se mueva el personaje hacia arriba
    boolean arriba = false;
    boolean abajo = false;
    boolean derecha = false;
    boolean izquierda = false;
    boolean transparente = false;
    
    Text text = new Text();
    short TEXT_SIZE= 20;
    Text text2 = new Text();
    short TEXT2_SIZE= 20;
     //me retorna un número
    
    
     
   
        
    /**
     *
     * @param stage
     */
    
    @Override
    public void start(Stage stage) {
 
        //Creación de nuevo objeto, en este caso se trata de nuestro App donde jugaremos--------------
        Pane root = new Pane(); //lo guardo en una variable que he llamado root (el App)
        var scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);//Crea ventana de esa medida usando la variable root
        stage.setTitle("PAKITO");
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
        
        /*String musicFile = "Otsukare.mp3"; 
        Media sound;
        mApplause = new AudioClip(this.getClass().getResource("/sounds/applause.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);*/
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
        imageView_personaje.setX(0);
        imageView_personaje.setY(0); 
        
        javafx.scene.image.Image image4 = new javafx.scene.image.Image(getClass().getResourceAsStream("/images/guitarra.png"));
        ImageView imageView_guitarra = new ImageView(image4);
        imageView_personaje.setX(0);
        imageView_personaje.setY(0); 
        imageView_guitarra.setFitHeight(90);
        imageView_guitarra.setFitWidth(40);
    
        root.getChildren().add(imageView_fondo);
        root.getChildren().add(imageView_fondo2);
        root.getChildren().add(imageView_personaje);
        root.getChildren().add(imageView_guitarra);

       
        //new Circle--> crear un objeto de la clase Circle
        Circle circleBall = new Circle(); //aquí voy a guardar una bola, con new me creo objeto circulo
        //llamando a métodos del objeto circleBall
        circleBall.setCenterX(0); // obligatoriamente debe de tener una medida, double permite decimales
        circleBall.setCenterY(600);
        circleBall.setRadius(15);//son métodos: nosequé. lo que sea
        circleBall.setFill(javafx.scene.paint.Color.RED);//Cambiar el color de la bola
        double r= circleBall.getRadius()*2;




       //Circle circleBall2= new Circle(10, 30, 7); es otro modo de hacer la bola pero con menos líneas

        root.getChildren().add(circleBall);//los hijos hace referencia a las cosas que contiene el App


        //Creación de rectángulo para personaje
        javafx.scene.shape.Rectangle rectpersonaje = new javafx.scene.shape.Rectangle(90,130);
        rectpersonaje.setLayoutX(0); 
        rectpersonaje.setLayoutY(0);
        rectpersonaje.setFill(javafx.scene.paint.Color.TRANSPARENT);
        
        //Creación de rectángulo para pua  
        javafx.scene.shape.Rectangle rectguitarra = new javafx.scene.shape.Rectangle(60,100);
        rectguitarra.setLayoutX(0); 
        rectguitarra.setLayoutY(0);
        rectguitarra.setFill(javafx.scene.paint.Color.TRANSPARENT);
        
        // Creación del grupo donde encontraremos la imagen con el rectángulo   
        groupGuitarra = new Group();
    //agrupamos el rectangulo creado + púa 
        groupGuitarra.getChildren().add(rectguitarra);
        groupGuitarra.getChildren().add(imageView_guitarra);
        groupGuitarra.setLayoutX(groupGuitarraPosX);
        groupGuitarra.setLayoutY(groupGuitarraPosY);
        root.getChildren().add(groupGuitarra);
        
    // Creación del grupo donde encontraremos la imagen con el rectángulo   
        Group groupPersonaje = new Group();
        //agrupamos el rectangulo creado + el perosnaje
        groupPersonaje.getChildren().add(rectpersonaje);
        groupPersonaje.getChildren().add(imageView_personaje);
        groupPersonajePosX = SCENE_WIDTH/4;
        groupPersonajePosY= (550);
        root.getChildren().add(groupPersonaje);

        // LAYOUTS PARA MOSTRAR PUNTUACIONES 
        // LAYOUT PRINCIPAL
        HBox paneScores = new HBox();
        paneScores.setTranslateY (20);
        paneScores.setMinWidth (SCENE_WIDTH);
        paneScores.setAlignment(Pos.CENTER);
        paneScores.setSpacing(100);
        root.getChildren().add(paneScores);

        //Layout para puntuación actual
        HBox paneCurrentScore = new HBox();
        paneCurrentScore.setSpacing(10);
        paneScores.getChildren().add (paneCurrentScore);

          //Texto de etiqueta para la puntuación
        Text textTitleScore = new Text ("Score:");
        textTitleScore.setFont(Font.font(20));
        textTitleScore.setFill(Color.WHITE);

           //Texto para la puntuación
        Text textScore = new Text (String.valueOf(vida));
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.WHITE);

          //Setting font to the text 
        text.setFont(Font.font(null, FontWeight.BOLD, 50));
        text.setStyle("-fx-font-size: 40px;"); 
        text.setStroke(Color.RED);
        text.setFill(Color.WHITE);

        //setting the position of the text
        text.setX(500); 
        text.setY(50);          

        //Setting the text to be added. 
        text.setText("Life:" + String.valueOf(vida)); 
        //Creating a Group object  
        root.getChildren().add(text);
        
        //Setting font to the text 
        text2.setFont(Font.font(null, FontWeight.BOLD, 50));
        text2.setStyle("-fx-font-size: 40px;"); 
        text2.setStroke(Color.BLUE);
        text2.setFill(Color.WHITE);

        //setting the position of the text
        text2.setX(680); 
        text2.setY(50);   

        text2.setText("Score:" + String.valueOf(score)); 
        //Creating a Group object  
        root.getChildren().add(text2);

        String musicFile = "Otsukare.wav.mp3";     // For example

       
        //reconocer teclas-detectarlas
        scene.setOnKeyPressed((final KeyEvent keyEvent) -> {
            switch(keyEvent.getCode()){

                case UP:
                    groupPersonajePosY -= 100;
                    
                    break;
                case DOWN:
                    groupPersonajePosY += 5;
                   
                    break;
                case RIGHT:
                   groupPersonajePosX += 5;
                   
                    break;
                case LEFT:
                    groupPersonajePosX -= 5; 
                    
                    break;
            }
        });

        
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

        Timeline timeline2;
        timeline2 = new Timeline(
                // 0.017 ~= 60 FPS
            new KeyFrame(Duration.seconds(0.017), (var ActionEvent ) -> { 
                System.out.println(vida);
                circleBall.setCenterX(ballCenterX);
                ballCenterX-=ballCurrentSpeedX * ballDirectionX;
                if (ballCenterX >=SCENE_WIDTH){
                    ballCenterX = 6;
                    ballCurrentSpeedY = 5;
                }
                
               
               guitarra--;
               groupGuitarra.setLayoutX(guitarra);
                System.out.println(guitarra);
                  
                

                groupPersonaje.setLayoutY(groupPersonajePosY);
                groupPersonajePosY+=groupPersonajeCurrentSpeed*groupPersonajeDirectionY;
                if (groupPersonajeDirectionY == -100){
                    groupPersonajePosX = 100;
                    groupPersonajePosY = 100;
                    
                }
                              
                groupPersonaje.setLayoutX(groupPersonajePosX);
                groupPersonajePosX+=groupPersonajeCurrentSpeed*groupPersonajeDirectionX;
                if (groupPersonajePosX >= 600){
                    groupPersonajePosX -= 5;
                    groupPersonajeCurrentSpeed =0;
   
                }


                Shape shapeCollision = Shape.intersect(circleBall, rectpersonaje);

                boolean vaciaCollision = shapeCollision.getBoundsInLocal().isEmpty();

                if (vida>0){
                    if (vaciaCollision == false){  
                        ballCenterX = 1080;
                        
                        //Irá restando  cada vez que colisione
                        vida--;
                        text.setText("Vida:" + String.valueOf(vida));
                        System.out.println(vida);
                    }
                         Shape shapeCollision2 = Shape.intersect(rectpersonaje, rectguitarra); 
                

                boolean vaciaCollisionGuitarra = shapeCollision2.getBoundsInLocal().isEmpty();
                if (vaciaCollisionGuitarra == false){  
                    guitarra = 1080;
                    //Irá restando  cada vez que colisione
                    score ++;
                    text2.setText("Score:" + String.valueOf(score));
                    System.out.println(score);
                }
                }else if (vida == 0){

                    //HARÁ QUE EL PERSONAJE DESAPAREZCA
                   groupPersonaje.setVisible(transparente);
                 
          
                    //Setting font to the text 
                   text.setFont(Font.font(null, FontWeight.BOLD, 50));
                   text.setStyle("-fx-font-size: 50px;"); 
                   text.setStroke(Color.BLUE);
                   text.setFill(Color.WHITE);

                   //setting the position of the text
                   text.setX(SCENE_WIDTH/2); 
                   text.setY(SCENE_HEIGHT/2);          

                   //Setting the text to be added. 
                   text.setText("GAME OVER"); 
                   //Creating a Group object  
                   root.getChildren().add(text);

                }
                
         
            })
        );

        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();
   
     
    }
    
    private void resetGame(){
        vida=3;
        text.setText(String.valueOf(vida));
        ballCenterX = 10;
        ballCurrentSpeedY = 3;
        groupPersonaje.setVisible(true);
        score = 0;
        text2.setText(String.valueOf(score));
        
    }

//        /**
//         *
//         * @param args
//         */
//        private void Sonido(){
//
//    }
            /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
    

}