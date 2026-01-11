package structure;

import java.util.Random;

public class Stick {
    private boolean darkSide;

    public Stick() {
        Random rand= new Random();
        this.darkSide= rand.nextBoolean();
    }

    public Stick(boolean darkSide) {
        this.darkSide = darkSide;
    }
    public int getValue()
    {
        return darkSide?1:0;
    }

    public boolean isDarkSide() {
        return darkSide;
    }
}
