package Animals;

import java.util.ArrayList;
import java.util.Arrays;

public class Cow extends Animals {

    public Cow() {
        this.name = "Chicken";
        this.buyMoney = 100;
        this.durations = new ArrayList<>(Arrays.asList(1, 2));
        this.imagePath = new ArrayList<>(Arrays.asList("image/Cow.png", "\uD83C\uDF3F"));
    }

}