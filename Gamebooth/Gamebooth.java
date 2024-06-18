package davids.unit5;

import java.text.DecimalFormat;
import java.util.Random;

// Chose to comment Player class instead as it is more important and diffcult to understand

public class Gamebooth {
    Random rand = new Random();
    DecimalFormat df = new DecimalFormat("0.00");
    private String name;
    private double cost;
    private String winPrize;
    private int winPrizeCount;
    private String losePrize;
    private int losePrizeCount;

    public Gamebooth(String name, double cost, String winPrize, int winPrizeCount, String losePrize, int losePrizeCount) {
        this.name = name;
        this.cost = cost;
        this.winPrize = winPrize;
        this.winPrizeCount = winPrizeCount;
        this.losePrize = losePrize;
        this.losePrizeCount = losePrizeCount;
    }

    public boolean game() {
        boolean win = true;
        for (int i = 0; i < 3; i++) {
            int chance = rand.nextInt(2) + 1;
            if (chance == 1)
                win = false;
        }
        return win;
    }
    
    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getWinPrize() {
        return winPrize;
    }

    public int getWinPrizeCount() {
        return winPrizeCount;
    }

    public String getLosePrize() {
        return losePrize;
    }

    public int getLosePrizeCount() {
        return losePrizeCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setWinPrize(String winPrize) {
        this.winPrize = winPrize;
    }

    public void setWinPrizeCount(int winPrizeCount) {
        this.winPrizeCount = winPrizeCount;
    }

    public void setLosePrize(String losePrize) {
        this.losePrize = losePrize;
    }

    public void setLosePrizeCount(int losePrizeCount) {
        this.losePrizeCount = losePrizeCount;
    }
    
    @Override
    public String toString() {
        return this.name + "($" + df.format(this.cost) + "): Winning Prize -> " + this.winPrize + " - Consolation Prize -> " + this.losePrize;
    }
    
    public String printSummary() {
        String s = name + ": ";
        s += getWinPrizeCount();
        if (getWinPrizeCount() == 1)
            s += (" winning prize; ");
        else
            s += (" winning prizes; ");
        s += getLosePrizeCount();
        if (getLosePrizeCount() == 1)
            s += (" losing prize");
        else
            s += (" losing prizes");
        return s;
    }
    
}
