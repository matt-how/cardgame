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
public class Driver {
    private static int screenWidth = 1024;
    private static int screenHeight = 768;


    public static void run() {
        //
        // Create a window
        //
        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(screenWidth, screenHeight), "Card Quest", WindowStyle.DEFAULT);
        window.setFramerateLimit(30); // 60FPS master-race
        boolean playersTurn = true;
        ArrayList<Button> buttons = new ArrayList<Button>();
        Board gameBoard = new Board();


        Button moveButton = new Button("move.png", 800, 500, 0);
        buttons.add(moveButton);

        Deck deck = new Deck();
        deck.shuffle();

        ArrayList<Card> hand = new ArrayList<Card>();
        for (int i = 0; i < 5; i++) {
            hand.add(deck.drawCard());
            hand.get(i).setX(i * ((int) hand.get(i).getWidth() + 20));
            hand.get(i).setY(screenHeight - (int) hand.get(i).getHeight());
        }


        //
        // Main loop
        //
        while (window.isOpen()) {
            // Clear the screen
            window.clear(Color.RED);
            //place updates between here and display

            gameBoard.draw(window);

            moveButton.draw(window);
            for (int i = 0; i < hand.size(); i++) {
                hand.get(i).draw(window);
            }


            // Handle any events
            for (Event event : window.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    // the user pressed the close button
                    window.close();
                }
                if (event.type == Event.Type.MOUSE_BUTTON_PRESSED && playersTurn == true) {
                    for (int i = 0; i < buttons.size(); i++) {
                        if (Mouse.getPosition(window).x > buttons.get(i).x && Mouse.getPosition(window).x < buttons.get(i).x + buttons.get(i).getWidth() && Mouse.getPosition(window).y > buttons.get(i).y && Mouse.getPosition(window).y < buttons.get(i).y + buttons.get(i).getHeight()) {
                            switch (buttons.get(i).getButtonID()) {
                                case 0:
                                    gameBoard.playerMove();
                            }
                            playersTurn = false;
                        }
                    }
                    for (int i = 0; i < hand.size(); i++) {
                        if (Mouse.getPosition(window).x > hand.get(i).x && Mouse.getPosition(window).x < hand.get(i).x + hand.get(i).getWidth() && Mouse.getPosition(window).y > hand.get(i).y && Mouse.getPosition(window).y < hand.get(i).y + hand.get(i).getHeight()) {
                            cardEffect(hand.get(i).getID(), gameBoard);
                            hand.remove(i);
                            hand.add(deck.drawCard());
                            for (int j = 0; j < 5; j++) {
                                hand.get(j).setX(j * ((int) hand.get(j).getWidth() + 20));
                                hand.get(j).setY(screenHeight - (int) hand.get(j).getHeight());
                            }
                            playersTurn = false;
                        }
                    }
                }
            }

            //enemy turn goes here
            if (!playersTurn) {
                gameBoard.enemyTurn();
            }
            playersTurn = true;
            //

            // Update the display with any changes
            window.display();
        }
    }

    public static void main(String args[]) {
        mainMenu();
    }

    private static void cardEffect(int cardID, Board gameBoard) {
        switch (cardID) {
            case 0:
                gameBoard.playerMove(-1);
            default:
                gameBoard.damageSquare(10, 1);

        }
    }

    private static void mainMenu() {
        // Creates a window displaying the Main Menu screen
        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(screenWidth, screenHeight), "Card Quest", WindowStyle.DEFAULT);

        Button play = new Button("MenuWithCards/playButton.png", 352, 384, 0);
        Button options = new Button("MenuWithCards/optionsButton.png", 352, 500, 1);
        Button quit = new Button("MenuWithCards/quitButton.png", 352, 616, 2);

        Texture imgTexture = new Texture();
        imgTexture.setSmooth(true);
        try {
            imgTexture.loadFromFile(FileSystems.getDefault().getPath("MenuWithCards", "MainMenuFinal.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading texture");
        }

        Sprite img = new Sprite(imgTexture);
        img.setPosition(new Vector2f(0, 0));

        while (window.isOpen()) {
            window.clear(Color.BLACK);

            window.draw(img);
            play.draw(window);
            options.draw(window);
            quit.draw(window);

            window.display();


            for (Event event : window.pollEvents()) {
                if (event.type == Event.Type.CLOSED)
                    window.close();

                if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                    if (Mouse.getPosition(window).x > play.x && Mouse.getPosition(window).x < play.x + play.getWidth() && Mouse.getPosition(window).y > play.y && Mouse.getPosition(window).y < play.y + play.getHeight()) {
                    // If play button pressed
                        window.close();
                        run();
                    }
                   // else if (Mouse.getPosition(window).x > options.x && Mouse.getPosition(window).x < options.x + options.getWidth() && Mouse.getPosition(window).y > options.y && Mouse.getPosition(window).y < options.y + options.getHeight())


                    else if (Mouse.getPosition(window).x > quit.x && Mouse.getPosition(window).x < quit.x + quit.getWidth() && Mouse.getPosition(window).y > quit.y && Mouse.getPosition(window).y < quit.y + quit.getHeight()) {
                    // If quit button pressed
                        window.close();
                    }
                }
            }

        }
    }
}
