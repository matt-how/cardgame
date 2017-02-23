import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.window.*;
import org.jsfml.window.event.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.*;

/**
 * Created by Matt on 18/01/2017.
 */
public class Driver
{
    private static int screenWidth  = 1024;
    private static int screenHeight = 768;
    private static int framerate = 30;


    public static void run() {
        //
        // Create a window
        //
        RenderWindow window = new RenderWindow( );
        window.create(new VideoMode(screenWidth, screenHeight),"Card Quest", WindowStyle.DEFAULT);
        window.setFramerateLimit(30); // 60FPS master-race
        boolean playersTurn = true;
        ArrayList<Button> buttons = new ArrayList<Button>();
        Board gameBoard = new Board();


        Button moveButton = new Button("move.png",800,500,0);
        buttons.add(moveButton);

        Deck deck = new Deck();
        deck.shuffle();

        ArrayList<Card> hand = new ArrayList<Card>();
        for(int i = 0; i<5;i++){
            hand.add(deck.drawCard());
            hand.get(i).setX(i*((int)hand.get(i).getWidth()+20));
            hand.get(i).setY(screenHeight - (int)hand.get(i).getHeight());
        }

        Texture imgTexture = new Texture();
        imgTexture.setSmooth(true);
        try {
            imgTexture.loadFromFile(FileSystems.getDefault().getPath("MenuWithCards", "MenuBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading texture");
        }
        // Sets menu background texture as a sprite to be displayed on screen
        Sprite img = new Sprite(imgTexture);
        img.setPosition(new Vector2f(0, 0));

        //
        // Main loop
        //
        while (window.isOpen( )) {
            // Clear the screen
            window.clear(Color.RED);
            window.draw(img);
            //place updates between here and display

            gameBoard.draw(window);

            moveButton.draw(window);
            for(int i=0; i < hand.size();i++){
                hand.get(i).draw(window);
            }




            // Handle any events
            for (Event event : window.pollEvents( )) {
                if (event.type == Event.Type.CLOSED) {
                    // the user pressed the close button
                    window.close( );
                }
                if(event.type == Event.Type.MOUSE_BUTTON_PRESSED && playersTurn){
                    for(int i = 0; i < buttons.size(); i++){
                        if(Mouse.getPosition(window).x > buttons.get(i).x && Mouse.getPosition(window).x < buttons.get(i).x+buttons.get(i).getWidth() && Mouse.getPosition(window).y > buttons.get(i).y && Mouse.getPosition(window).y < buttons.get(i).y+buttons.get(i).getHeight()) {
                            switch (buttons.get(i).getButtonID()){
                                case 0:
                                    gameBoard.playerMove();
                            }
                            playersTurn = false;
                        }
                    }
                    for(int i = 0; i < hand.size(); i++) {
                        if (Mouse.getPosition(window).x > hand.get(i).x && Mouse.getPosition(window).x < hand.get(i).x + hand.get(i).getWidth() && Mouse.getPosition(window).y > hand.get(i).y && Mouse.getPosition(window).y < hand.get(i).y + hand.get(i).getHeight()) {
                            cardEffect(hand.get(i).getID(),gameBoard);
                            hand.remove(i);
                            hand.add(deck.drawCard());
                            for(int j = 0; j<5;j++){
                                hand.get(j).setX(j*((int)hand.get(j).getWidth()+20));
                                hand.get(j).setY(screenHeight - (int)hand.get(j).getHeight());
                            }
                            playersTurn=false;
                        }
                    }
                }
            }

            //enemy turn goes here
            if(!playersTurn){
                gameBoard.enemyTurn();
                gameBoard.reduceTimers();
                playersTurn = true;
            }

            // Update the display with any changes
            window.display( );
        }
    }

    public static void main (String args[ ]) {

        mainMenu();
    }

    private static void cardEffect(int cardID, Board gameBoard){
        switch (cardID){
            case 0:
                gameBoard.playerMove(-1);
                break;
            case 1:
                gameBoard.damageSquare(3,1,Character.elementalType.NONE);
                break;
            case 2:
                gameBoard.damageSquare(1,1, Character.elementalType.WATER);
                //gameBoard.stunCharacter(1);
                break;
            case 3:
                gameBoard.setElementalBoosts(3, Character.elementalType.EARTH, 2);
                break;
            case 4:
                gameBoard.setElementalBoosts(3, Character.elementalType.ELECTRIC, 2);
                break;
            case 6:
                gameBoard.setElementalBoosts(3, Character.elementalType.FIRE, 2);
                break;
            case 7:
                gameBoard.damageSquare(2, 1, Character.elementalType.FIRE);
                gameBoard.damageSquare(2, 2, Character.elementalType.FIRE);
                gameBoard.damageSquare(2, 3, Character.elementalType.FIRE);
                gameBoard.damageSquare(2, 4, Character.elementalType.FIRE);
                gameBoard.damageSquare(2, 5, Character.elementalType.FIRE);
                break;
            case 8:
                gameBoard.castBolt(2,Character.elementalType.FIRE);
                break;
            case 11:
                gameBoard.castBolt(2,Character.elementalType.WATER);
                gameBoard.healCharacter(0,2);
                break;
            case 13:
                gameBoard.damageSquare(1,1, Character.elementalType.ELECTRIC);
                //gameBoard.stunCharacter(1);
                break;
            case 15:
                gameBoard.playerMove(3);
                break;
            case 19:
                gameBoard.setElementalBoosts(3, Character.elementalType.WATER, 2);
                break;
            default:
                gameBoard.damageSquare(1,1,Character.elementalType.NONE);
                break;

        }
    }

    private static void mainMenu() {
        // Creates a window displaying the Main Menu screen
        RenderWindow menuWindow = new RenderWindow();
        menuWindow.create(new VideoMode(screenWidth, screenHeight), "Card Quest", WindowStyle.DEFAULT);

        ArrayList<Button> menuButtons = new ArrayList<>();

        // Create buttons for menu screen
        Button play = new Button("MenuWithCards/playButton.png", 352, 384, 0);
        Button options = new Button("MenuWithCards/optionsButton.png", 352, 500, 1);
        Button quit = new Button("MenuWithCards/quitButton.png", 352, 616, 2);

        // Add buttons to menuButtons array
        menuButtons.add(play);
        menuButtons.add(options);
        menuButtons.add(quit);

        // Create texture for menu background
        Texture imgTexture = new Texture();
        imgTexture.setSmooth(true);
        try {
            imgTexture.loadFromFile(FileSystems.getDefault().getPath("MenuWithCards", "MainMenuFinal.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading texture");
        }
        // Sets menu background texture as a sprite to be displayed on screen
        Sprite img = new Sprite(imgTexture);
        img.setPosition(new Vector2f(0, 0));

        // While loop dictating behaviour while the menu window is open
        while (menuWindow.isOpen()) {
            menuWindow.clear(Color.BLACK);
            // Draw background and buttons on screen
            menuWindow.draw(img);
            play.draw(menuWindow);
            options.draw(menuWindow);
            quit.draw(menuWindow);

            menuWindow.display();

            // Controls actions when various events occur such as mouse clicks
            for (Event event : menuWindow.pollEvents()) {
                if (event.type == Event.Type.CLOSED)
                    menuWindow.close();

                if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                    for (int i = 0; i < menuButtons.size(); i++) {
                        if (Mouse.getPosition(menuWindow).x > menuButtons.get(i).x && Mouse.getPosition(menuWindow).x < menuButtons.get(i).x + menuButtons.get(i).getWidth() && Mouse.getPosition(menuWindow).y > menuButtons.get(i).y && Mouse.getPosition(menuWindow).y < menuButtons.get(i).y + menuButtons.get(i).getHeight()) {
                            switch (menuButtons.get(i).getButtonID()) {
                                case 0: {
                                    menuWindow.close();
                                    run();
                                    break;
                                }
                                case 1: {
                                    menuWindow.close();
                                    optionsMenu();
                                    break;
                                }
                                case 2: {
                                    menuWindow.close();
                                    break;
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    private static void optionsMenu(){

        RenderWindow optionsWindow = new RenderWindow();
        optionsWindow.create(new VideoMode(screenWidth, screenHeight), "Card Quest", WindowStyle.DEFAULT);

        ArrayList<Button> optionsButtons = new ArrayList<>();

        Button soundOnBox = new Button("MenuWithCards/checked.png", 300,  284, 0);
        Button soundOffBox = new Button("MenuWithCards/unchecked.png", 400,  284, 1);
        Button FPS30Box = new Button("MenuWithCards/checked.png", 300,  450, 2);
        Button FPS25Box = new Button("MenuWithCards/unchecked.png", 400,  450, 3);
        Button FPS20Box = new Button("MenuWithCards/unchecked.png", 500,  450, 4);
        Button play = new Button("MenuWithCards/playButton.png", 124,  600, 5);
        Button quit = new Button("MenuWithCards/quitButton.png", 596,  600, 6);

        Font verdana = new Font();
        try {
            verdana.loadFromFile(FileSystems.getDefault().getPath("fonts", "Verdana.ttf"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading font");
        }

        ArrayList<Text> optionsText = new ArrayList<>();

        Text sound = new Text("Sound:", verdana);
        sound.setPosition(new Vector2f(100, 284));
        Text soundOn = new Text("On", verdana);
        soundOn.setPosition(new Vector2f(310, 225));
        Text soundOff = new Text("Off", verdana);
        soundOff.setPosition(new Vector2f(410, 225));
        Text FPSSelect = new Text("FPS:", verdana);
        FPSSelect.setPosition(new Vector2f(100, 400));
        Text FPS20 = new Text("20", verdana);
        FPS20.setPosition(new Vector2f(510, 400));
        Text FPS25 = new Text("25", verdana);
        FPS25.setPosition(new Vector2f(410, 400));
        Text FPS30 = new Text("30", verdana);
        FPS30.setPosition(new Vector2f(310, 400));

        optionsText.add(sound);
        optionsText.add(soundOn);
        optionsText.add(soundOff);
        optionsText.add(FPSSelect);
        optionsText.add(FPS30);
        optionsText.add(FPS25);
        optionsText.add(FPS20);

        optionsButtons.add(soundOnBox);
        optionsButtons.add(soundOffBox);
        optionsButtons.add(FPS30Box);
        optionsButtons.add(FPS25Box);
        optionsButtons.add(FPS20Box);
        optionsButtons.add(play);
        optionsButtons.add(quit);

        Texture imgTexture = new Texture();
        imgTexture.setSmooth(true);
        try {
            imgTexture.loadFromFile(FileSystems.getDefault().getPath("MenuWithCards", "MenuBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading texture");
        }

        Sprite optionsBackground = new Sprite(imgTexture);
        optionsBackground.setPosition(new Vector2f(0, 0));

        while (optionsWindow.isOpen()) {
            optionsWindow.clear(Color.CYAN);

            optionsWindow.draw(optionsBackground);

            for (int i = 0; i < optionsButtons.size(); i++) {
                optionsButtons.get(i).draw(optionsWindow);
            }

            for (int i = 0; i< optionsText.size(); i++) {
                optionsWindow.draw(optionsText.get(i));
            }

            optionsWindow.display();


            for (Event event : optionsWindow.pollEvents()) {
                if (event.type == Event.Type.CLOSED)
                    optionsWindow.close();

                if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                    for (int i = 0; i < optionsButtons.size(); i++) {
                        if (Mouse.getPosition(optionsWindow).x > optionsButtons.get(i).x && Mouse.getPosition(optionsWindow).x < optionsButtons.get(i).x + optionsButtons.get(i).getWidth() && Mouse.getPosition(optionsWindow).y > optionsButtons.get(i).y && Mouse.getPosition(optionsWindow).y < optionsButtons.get(i).y + optionsButtons.get(i).getHeight()) {
                            switch (optionsButtons.get(i).getButtonID()) {
                                case 0:
                                    System.out.println("Sound On Pressed");
                                    optionsButtons.get(0).changeTexture("MenuWithCards/checked.png");
                                    optionsButtons.get(1).changeTexture("MenuWithCards/unchecked.png");
                                    //sound.setVolume(100);
                                    break;
                                case 1:
                                    System.out.println("Sound Off Pressed");
                                    optionsButtons.get(1).changeTexture("MenuWithCards/checked.png");
                                    optionsButtons.get(0).changeTexture("MenuWithCards/unchecked.png");
                                    //sound.setVolume(0);
                                    break;
                                case 2:
                                    optionsButtons.get(2).changeTexture("MenuWithCards/checked.png");
                                    optionsButtons.get(3).changeTexture("MenuWithCards/unchecked.png");
                                    optionsButtons.get(4).changeTexture("MenuWithCards/unchecked.png");
                                    framerate = 30;
                                    break;
                                case 3:
                                    optionsButtons.get(3).changeTexture("MenuWithCards/checked.png");
                                    optionsButtons.get(2).changeTexture("MenuWithCards/unchecked.png");
                                    optionsButtons.get(4).changeTexture("MenuWithCards/unchecked.png");
                                    framerate = 25;
                                    break;
                                case 4:
                                    optionsButtons.get(4).changeTexture("MenuWithCards/checked.png");
                                    optionsButtons.get(2).changeTexture("MenuWithCards/unchecked.png");
                                    optionsButtons.get(3).changeTexture("MenuWithCards/unchecked.png");
                                    framerate = 20;
                                    break;
                                case 5:
                                    optionsWindow.close();
                                    run();
                                    break;
                                case 6:
                                    optionsWindow.close();
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }
}
