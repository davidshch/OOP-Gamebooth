package davids.unit5;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Name: David Shcherbatykh Thurs Dec 7 ICS3U Description: An
 * application that simulates a trip to a carnival with two friends David and
 * Joe where you can input your name and how much money you are bringing (Player
 * objects of Player class). Each person has three carnival games (Gamebooth
 * objects of Gamebooth class) to choose from, Balloon Darts, Break a Plate, and
 * Ring Toss. If the player wins three tries in a row then they win the
 * "winning" prize for that game. If they lose one of their tries, then they
 * recieve the consolation prize for that game. The user can type quit when they
 * want to leave the carnival and a summary of the amount of money everyone has,
 * the number of games they played, what prizes they won, as well as how many
 * winning prizes and consolation prizes were given out from each game.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    // Constructing Gamebooth objects with name, money, and win/lose prizes as arguments as a static variable so they are shared between methods 
    static Gamebooth plate = new Gamebooth("Break a Plate", 1.50, "Pig Plush Toy", 0, "Plastic Dinosaur", 0);
    static Gamebooth balloon = new Gamebooth("Balloon Darts", 2.00, "Tiger Plush", 0, "Sticker", 0);
    static Gamebooth rings = new Gamebooth("Ring Toss", 2.00, "Bear Key Chain", 0, "Pencil", 0);
    static Gamebooth[] games = {balloon, plate, rings}; // Gamebooth array to iterate over to choose which game

    static final String BLUE = "\u001B[34m";
    static final String CYANBKG = "\u001B[46m";
    static final String GREEN = "\u001B[32m";
    static final String GREENBKG = "\u001B[42m";
    static final String MAGENTA = "\u001B[35m";
    static final String MAGENTABKG = "\u001B[45m";
    static final String YELLOWBKG = "\u001B[43m";
    static final String RESET = "\u001B[0m";
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");
        // Constructing Player objects with name nad money as arguments except for third one which is being default constructed so it can be changed later by user
        Player David = new Player("David", 20.00);
        Player Joe = new Player("Joe", 15.50);
        Player user = new Player();
        Player currentPlayer = new Player(); // To know which player's turn it is for later and is also default constructed as it wil be changed

        String userInput;
        double userMoney;
        Player[] players = {David, Joe, user}; // Player loop to iterate over to find which player will go

        System.out.println(CYANBKG + "------------Welcome to the Incredible Carnival!------------" + RESET);
        System.out.println("Hello! I'm the ticket master!");
        System.out.print("I see you are with your friends " + BLUE + "David" + RESET + " and " + GREEN + "Joe" + RESET + ". ");
        while (true) { // Reprompts until valid name is inputted
            System.out.print(MAGENTA + "What is your name? " + RESET);
            userInput = input.nextLine();

            if (userInput.toLowerCase().equals("david") || userInput.toLowerCase().equals("joe")) { // David and Joe are already created Player objects so it avoids confusion/errors by making sure the user doesn't choose that
                System.err.println(userInput + " is already going to the carnival with you.");
                continue;
            } else if (userInput.toLowerCase().equals("quit")) { // Prevents user from choosing "quit" as their name to prevent errors as quit is the senteniel 
                System.err.println("\"Quit\"? That's not your name! Please put your actual name.");
                continue;
            }
            user.setName(userInput); // Sets the name attribute of user Player object to whatever user inputed
            break;
        }
        // Reprompts until valid double is inputted
        while (true) {
            System.out.print(RESET + "How much money are you bringing to the carnival, " + MAGENTA + user.getName() + RESET + "? ");
            try {
                userMoney = Double.parseDouble(input.nextLine());
                if (userMoney <= 0) { // Prevents user having negative or no money
                    System.err.println("Come on! You have to have at least SOMETHING.");
                    continue;
                }
                user.setMoney(userMoney); // Sets the money attribute to whatever user inputted
                break;
            } catch (InputMismatchException | NumberFormatException e) {
            }
        }

        OUTER: // OUTER is used to break out to when senteniel is inputted
        while (true) { // Loops playing until senteniel is entered where it goes to OUTER
            OUTER1: // OUTER1 is for when a valid player is inputted so it can run the next code
            while (true) { // Loops until valid player is inputted
                System.out.println("-------------------------------------------------------------------------------------------");
                System.out.println("Choose a player or type quit: " + BLUE + "David($" + df.format(David.getMoney()) + ")" + RESET + " or " + GREEN + "Joe($" + df.format(Joe.getMoney()) + ")" + RESET + " or " + MAGENTA + user.getName() + "($" + df.format(user.getMoney()) + ")" + RESET); // Displays each players's money by getting their name nd money attributes  
                userInput = input.nextLine();
                if (userInput.equalsIgnoreCase("quit")) { // Breaks if quit is entered
                    break OUTER;
                }
                // Loops though players array and compares input to the name attribute of each player; breaks out when player is found
                for (Player player : players) {
                    if (userInput.equalsIgnoreCase(player.getName())) {
                        currentPlayer = player; // currentPlayer stores the inputted player 
                        break OUTER1;
                    }
                }
                System.err.println("That player isn't here! Please choose a player that is at the carnival.");
            }
            currentPlayer.playGame(whichGame(currentPlayer)); // Runs whichGame method with the currentPlayer as an argument and runs the playGame method when whichGame returns Gamebooth object that was chosen

        }
        // Uses toString() methods on all players to display their name, money, and prizes
        // Prints summary of each game
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Thanks for playing! Here's a summary of the players and games: ");
        System.out.println(BLUE + David.toString() + RESET);
        System.out.println(GREEN + Joe.toString() + RESET);
        System.out.println(MAGENTA + user.toString() + RESET);
        System.out.println("");
        System.out.println(GREENBKG + balloon.printSummary());
        System.out.println(YELLOWBKG + rings.printSummary());
        System.out.println(MAGENTABKG + plate.printSummary());

    }

    /**
     * A method that prompts the user for which game the chosen player (as a
     * parameter) wants to play
     *
     * @param p The player that was chosen by user to call playGame() with
     * whatever Gamebooth object is chosen by user in this method
     * @return Gamebooth object which is the chosen game by the user for the
     * player to play
     */
    public static Gamebooth whichGame(Player p) {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose one of the games:\n" + GREENBKG + balloon.toString());
        System.out.println(YELLOWBKG + rings.toString());
        System.out.println(MAGENTABKG + plate.toString() + RESET);
        String gameChosen;
        while (true) { // To keep prompting
            gameChosen = input.nextLine();
            for (Gamebooth game : games) { // Similar to choosing the player it loops through the Gamebooth object array until it finds a match or else it reprompts
                if (gameChosen.equalsIgnoreCase(game.getName())) {
                    return game;
                }
            }
            System.err.println("Please input a valid game. Make sure to check your spelling."); // If a match isn't found this will print
        }
    }

}
