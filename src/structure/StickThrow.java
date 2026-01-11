package structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StickThrow {
    public static int stickThrow()
    {
        Random rand = new Random();
        int sum=0;
        for(int i=0 ; i<4;i++)
        {
            Stick stick = new Stick();
            sum += stick.getValue();
        }
        return sum == 0 ? 5 : sum;
    }
    public static Map<Integer, Double> getProbabilities()
    {
        Map<Integer, Double> probs = new HashMap<>();
        probs.put(1, 4.0 / 16); // c(4,1)
        probs.put(2, 6.0 / 16); // c(4,2)
        probs.put(3, 4.0 / 16); // c(4,3)
        probs.put(4, 1.0 / 16); // c(4,4)
        probs.put(5, 1.0 / 16); // كلها فاتحة
        return probs;
    }
}
