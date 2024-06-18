package davids.unit5;

import java.text.DecimalFormat;
import java.util.ArrayList;

// I chose to comment the Player class as it is much more important and its code is more difficult to understand over the Gamebooth class

public class Player {

    DecimalFormat df = new DecimalFormat("0.00");
    private String name;
    private double money;
    private ArrayList<String> prizesWon = new ArrayList();

    public Player(String name, double money) { // Constructor that takes in String and double as intializes name and money attributes to those respectively
        this.name = name;
        this.money = money;
    }

    public Player() { // Default constructor for when I want to construct a Player object that will be changed
        this.name = "";
        this.money = 0.0;
    }

    /**
     * A method that prints if the player has won, lost, (depending on boolean
     * returned after calling game() method) or doesn't have sufficient funds
     * for a game and adds the respective prizes to their prizesWon attribute
     * and adds to their winPrizeCount/losePrizeCount attributes
     *
     * @param gameobj is the chosen Gamebooth object (game) by user
     */
    public void playGame(Gamebooth gameobj) {
        System.out.println(name + " goes to play " + gameobj.toString());
        if (this.money - gameobj.getCost() < 0) // If they don't have enough money they don't play
        {
            System.err.println(this.name + " is stopped at the booth. Sorry! " + this.name + " doesn't have enough money to play!");
        } else {
            this.money -= gameobj.getCost(); // Subtracts cost of game from Player money attribute
            if (gameobj.game()) { // Calls game() method which returns boolean if they won (true) or lost (false)
                System.out.println("\t" + this.name + " won the game! They win a " + gameobj.getWinPrize() + "!");
                this.prizesWon.add(gameobj.getWinPrize()); // Gets the winPrize attribute from the game and adds it to prizesWon attribute of Player
                gameobj.setWinPrizeCount(gameobj.getWinPrizeCount() + 1); // Adds 1 to the retrived win prize count attribute and sets that to the new win prize count
            } else {
                System.out.println("\t" + this.name + " lost the game... Their consolation prize is a " + gameobj.getLosePrize());
                this.prizesWon.add(gameobj.getLosePrize()); // Same as first if
                gameobj.setLosePrizeCount(gameobj.getLosePrizeCount() + 1);
            }
        }
    }

    /**
     * A method that returns a String value of the name attribute of the Player
     * object
     *
     * @return String name
     */
    public String getName() { // Very useful getter as the Player objects' names are retrieved a lot in my main and works the same as the rest of the getters, except getting a different attribute
        return name;
    }

    public double getMoney() {
        return money;
    }

    public ArrayList<String> getPrizesWon() {
        return prizesWon;
    }

    /**
     * A method that allows you to edit/set the String value of the name
     * attribute of the Player object as the argument
     *
     * @param name The value/name you want to set the original name attribute to
     */
    public void setName(String name) { // This setter was useful for my user Player object as I could default construct the user Player object earlier and set the name attribute to whatever the user inputted later 
        this.name = name;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setPrizesWon(ArrayList<String> prizesWon) {
        this.prizesWon = prizesWon;
    }

    /**
     * A method that overrides the toString() method from the object class that
     * creates and returns a String that contains the attributes of the Player
     * object such as the name, money, and prizesWon attributes. If prizesWon is
     * empty then it is said the Player has won nothing in the String
     *
     * @return the created String about the Player
     */
    @Override
    public String toString() {
        String s = name + " has $" + df.format(money) + " and has won:\n";

        if (prizesWon.isEmpty()) // If they didn't play any games then they can't have any prizes
        {
            s += "nothing because they haven't played any games.";
        } else {
            for (int i = 0; i < prizesWon.size(); i++) { // Adds each element of prizesWon ArrayList to the returned String and adds comma if there is a prize after it
                if (i == prizesWon.size() - 1) {
                    s += prizesWon.get(i);
                } else {
                    s += prizesWon.get(i) + ", ";
                }
            }
            if (prizesWon.size() == 1) // For plurality sake
            {
                s += " and has played " + prizesWon.size() + " game.";
            } else {
                s += " and has played " + prizesWon.size() + " games.";
            }
        }
        return s;
    }

    /**
     * A method that overrides the equals() method from the object class and
     * compares to Player objects by their money and name attributes.
     *
     * @param obj The object you want to compare to and is what gets casted to a
     * Player object
     * @return boolean if money and name attirbutes are the same then it returns
     * true, otherwise returns false
     */
    @Override
    public boolean equals(Object obj) {
        Player temp = (Player) obj;
        return this.money == temp.getMoney() && this.name.equals(temp.getName());
    }

}
