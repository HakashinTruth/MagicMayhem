/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicmayhem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author 9706
 */
public class MagicMayhem extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Button playButton, optionsButton, exitButton, backButton;
    Group menuBtn, playroot;
    public Scene mainMenuScene, playScene, optionScene;
    Slider slide;
    Label label;
    Image MagicLogo, SoundLogo, OptionLogo,playerTwoSprite;
    ImageView tittle, volume, Optittle,playerOneSprite,playerTwo,playerOne;
    Player playerone, playertwo;
    boolean moveUp, moveDown, turnRight, turnLeft = false;
    boolean arrowUp, arrowDown, arrowRight, arrowLeft = false;
    double playerspeed = 4;
    double bulletspeed=6;
    double playerrotation = 3.50;
    double playerwidth = 30;
    double playerheight = 50;
    double bulletRadius=5.00;
    int width = (int) Screen.getPrimary().getBounds().getWidth();
    int height = (int) Screen.getPrimary().getBounds().getHeight();
    ArrayList <Bullet> projectiles;
    Random wrand = new Random(width);
    Random hrand = new Random(height);
    
    @Override
    public void start(Stage primaryStage) {

        // Tittle image UI
        MagicLogo = new Image(getClass().getResourceAsStream("latestLogo2.png"));
        tittle = new ImageView(MagicLogo);
        tittle.setScaleX(1.5);
        tittle.setLayoutX(width / 2 - 250);
        tittle.setLayoutY(60);

        //plaay button UI        
        playButton = new Button("Play");
        playButton.setBackground(null);
        playButton.setLayoutX(width / 2);
        playButton.setLayoutY(height / 2 - 100);
        playButton.setScaleX(3);
        playButton.setScaleY(2);
        playButton.setTextFill(Color.WHITE);

        // option button UI
        optionsButton = new Button("Options");
        optionsButton.setBackground(null);
        optionsButton.setLayoutX(width / 2);
        optionsButton.setLayoutY(height / 2);
        optionsButton.setScaleX(3);
        optionsButton.setScaleY(2);
        optionsButton.setTextFill(Color.WHITE);

        //exit button UI
        exitButton = new Button("Exit");
        exitButton.setBackground(null);
        exitButton.setLayoutX(width / 2);
        exitButton.setLayoutY(height / 2 + 100);
        exitButton.setScaleX(3);
        exitButton.setScaleY(2);
        exitButton.setTextFill(Color.WHITE);

        //back button UI
        backButton = new Button("Back");
        backButton.setBackground(null);
        backButton.setLayoutY(height - 40);
        backButton.setLayoutX(50);
        backButton.setScaleX(3);
        backButton.setScaleY(2);
        backButton.setTextFill(Color.WHITE);

        //action events for Play
        playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            playButton.setStyle("-fx-font-weight: bold");
            playButton.setScaleX(3.2);
            playButton.setScaleY(2.2);
        });
        playButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            playButton.setStyle("-fx-font-weight: regular");
            playButton.setScaleX(3);
            playButton.setScaleY(2);
        });
        EventHandler<ActionEvent> PlayEvent = (ActionEvent e) -> {
            PlayMethod(primaryStage);
        };playButton.setOnAction(PlayEvent);
        

        //action events for Options
        optionsButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            optionsButton.setStyle("-fx-font-weight: bold");
            optionsButton.setScaleX(3.2);
            optionsButton.setScaleY(2.2);
        });
        optionsButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            optionsButton.setStyle("-fx-font-weight: regular");
            optionsButton.setScaleX(3);
            optionsButton.setScaleY(2);
        });
        EventHandler<ActionEvent> OptionEvent = (ActionEvent e) -> {
            OptionMethod(primaryStage);
            
        };
        optionsButton.setOnAction(OptionEvent);

        // Exit button events 
        exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            exitButton.setStyle("-fx-font-weight: bold");
            exitButton.setScaleX(3.2);
            exitButton.setScaleY(2.2);
        });
        exitButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            exitButton.setStyle("-fx-font-weight: regular");
            exitButton.setScaleX(3);
            exitButton.setScaleY(2);
        });
        EventHandler<ActionEvent> ExitEvent = (ActionEvent e) -> {
            System.exit(0);
        };
        exitButton.setOnAction(ExitEvent);

        //Back event handler
        backButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            backButton.setStyle("-fx-font-weight: bold");
            backButton.setScaleX(3.2);
            backButton.setScaleY(2.2);
        });
        backButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            backButton.setStyle("-fx-font-weight: regular");
            backButton.setScaleX(3);
            backButton.setScaleY(2);
        });

        menuBtn = new Group(tittle, playButton, optionsButton, exitButton);
        playroot = new Group(backButton);

        mainMenuScene = new Scene(menuBtn, width, height, Color.BLACK);
        
        primaryStage.setMaximized(true);
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    // Play Scene
    private void PlayMethod(Stage primaryStage) {       

 //       playerTwoSprite = new Image(getClass().getResourceAsStream("Playertwo.png"));
//       playerTwo = new ImageView(playerTwoSprite);
        // Main Game loop
        playerone = new Player(wrand,hrand,playerheight, playerwidth, bulletRadius, playerspeed, playerrotation, Color.BLUE,width,height);
        playertwo = new Player(wrand,hrand,playerheight, playerwidth, bulletRadius, playerspeed, playerrotation, Color.ORANGE,width,height);
        projectiles = new ArrayList();
         playroot.getChildren().addAll(playerone.rect, playertwo.rect);
         // MAZE MAP GENERATION
        Maze Gen = new Maze(width, height);
        playroot.getChildren().addAll(Gen.walls);

        //Animation TIMER
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Bullet bullet2 = new Bullet(bulletRadius, Color.WHITE, playertwo.getX() + (playerheight/2), playertwo.getY() + (playerwidth / 2), playertwo.rect.getRotate(), bulletspeed);
                Bullet bullet = new Bullet(bulletRadius, Color.WHITE, playerone.getX() + (playerheight/2), playerone.getY() + (playerwidth / 2), playerone.rect.getRotate(), bulletspeed);
                // WASD player one
                playScene.setOnKeyPressed((KeyEvent event) -> {
                    switch (event.getCode()) {
                    // player 1 keys
                             case W: moveUp = true;break;
                             case S:moveDown = true;break;
                             case A:turnLeft = true;break;
                             case D:turnRight = true;break;
                    // player 2 keys
                             case UP:arrowUp = true;break;
                             case DOWN:arrowDown = true; break;
                             case LEFT:arrowLeft = true;break;
                             case RIGHT:arrowRight = true;break;
                    }
                });
                playScene.setOnKeyReleased((KeyEvent event) -> {
                    switch (event.getCode()) {
                        //player 1 keys
                             case W:    moveUp = false;  break;
                             case S:  moveDown = false;  break;
                             case A:  turnLeft  = false;  break;
                             case D: turnRight  = false;  break;
                    // player 2 keys
                             case UP:    arrowUp = false;break;
                            case DOWN:  arrowDown = false;  break;
                            case LEFT:  arrowLeft  = false;  break;
                            case RIGHT: arrowRight  = false;  break;
                               }
                    //player one bullet
                    if (event.getCode() == KeyCode.R) {
                        projectiles.add(bullet);
                        playroot.getChildren().addAll(projectiles.get((projectiles.size() - 1)).getCircle());
                    }
                    //player two bullet
                    if (event.getCode() == KeyCode.M) {
                        projectiles.add(bullet2);
                        playroot.getChildren().addAll(projectiles.get((projectiles.size() - 1)).getCircle());
                    }
                });
                //player 1
                if (moveUp) playerone.moveup();
                if (moveDown) playerone.movedown();
                if (turnLeft)  playerone.antirotate();
                if (turnRight)  playerone.rotate();

                //player 2
              if (arrowUp) playertwo.moveup();
              if (arrowDown)playertwo.movedown();
              if (arrowRight) playertwo.rotate();
              if (arrowLeft) playertwo.antirotate();   

                // dead evet
                if (playerone.dead) {
                    playroot.getChildren().removeAll(playerone.rect, projectiles.get((projectiles.size() - 1)).getCircle());
                }
                if (playertwo.dead) {
                    playroot.getChildren().removeAll(playertwo.rect, projectiles.get((projectiles.size() - 1)).getCircle());
                }
                // bullet updater

                Iterator<Bullet> it = projectiles.iterator();

                while (it.hasNext()) {
                    Bullet b = it.next();
                    b.move();
                    //collision detection
                    if (b.getCircle().getBoundsInParent().intersects(playerone.rect.getBoundsInParent())) {
                        playerone.dead = true;
                    }
                    if (b.getCircle().getBoundsInParent().intersects(playertwo.rect.getBoundsInParent())) {
                        playertwo.dead = true;
                    }
                    // BALL TO MAZE COLLISION
                    if (b.getX() >= width || b.getX() <= 0) {
                        b.verticalColisionmove();

                    }
                    if (b.getY() >= height || b.getY() <= 0) {
                        b.horizontalColisionmove();

                    }
                    for (Wall wall : Gen.walls) {
                        if (wall.getBoundsInParent().intersects(b.getCircle().getBoundsInParent())) {
                            if (wall.isVertical) {
                                b.verticalColisionmove();

                            } else if (!wall.isVertical) {
                                b.horizontalColisionmove();

                            }
                        }
                    }
                }
                // player:MAZE COLIISION 
                for (Wall wall : Gen.walls) {
                    if (wall.getBoundsInParent().intersects(playerone.rect.getBoundsInParent())) {
                        //what player will do
                        //player 1
                        if (moveUp) {
                            playerone.antimoveup();
                        }
                        if (moveDown) {
                            playerone.antimovedown();
                        }
                        if (turnLeft) {
                            playerone.colantirotate();
                        }
                        if (turnRight) {
                            playerone.colrotate();
                        }
                    }
                    if (wall.getBoundsInParent().intersects(playertwo.rect.getBoundsInParent())) {
                        //player 1
                        if (arrowUp) {
                            playertwo.antimoveup();
                        }
                        if (arrowDown) {
                            playertwo.antimovedown();
                        }
                        if (arrowLeft) {
                            playertwo.colantirotate();
                        }
                        if (arrowRight) {
                            playertwo.colrotate();
                        }
                    }
                }
            }

        };
        timer.start();

        playScene = new Scene(playroot, width, height, Color.BLACK);
        EventHandler<ActionEvent> event = (ActionEvent e) -> {
            timer.stop();
            start(primaryStage);
        };
        backButton.setOnAction(event);
        primaryStage.setScene(playScene);
    }

    //  Option  Scene
    private void OptionMethod(Stage primaryStage) {
        // Option  tittle
        OptionLogo = new Image(getClass().getResourceAsStream("Options.png"));
        Optittle = new ImageView(OptionLogo);
        Optittle.setLayoutY(50);
        Optittle.setLayoutX(width / 2 - 250);

        // Slider
        slide = new Slider(0, 100, 0);
        slide.setLayoutX(width / 2);
        slide.setLayoutY(Optittle.getLayoutY() + 300);
        slide.setScaleX(3);
        slide.setScaleY(3);

        // Volume Icon
        SoundLogo = new Image(getClass().getResourceAsStream("soundicon.png"));
        volume = new ImageView(SoundLogo);
        volume.setLayoutY(slide.getLayoutY() - 75);
        volume.setLayoutX(slide.getLayoutX() - 400);

        playroot.getChildren().addAll(volume, Optittle, slide);
        optionScene = new Scene(playroot, width, height, Color.BLACK);
        EventHandler<ActionEvent> event = (ActionEvent e) -> {
            start(primaryStage);
        };
        backButton.setOnAction(event);
        primaryStage.setScene(optionScene);
    }
}
