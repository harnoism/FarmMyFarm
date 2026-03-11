package Animals;

import java.util.ArrayList;
import java.util.Arrays;

public class Pig extends Animals {

    public Pig() {
        this.name = "Pig";
        this.buyMoney = 100;
        this.durations = new ArrayList<>(Arrays.asList(1, 2));
        this.imagePath = new ArrayList<>(Arrays.asList("image/Pig.png", "\uD83C\uDF3F"));
    }

}