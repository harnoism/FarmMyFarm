package Animals;

import java.util.ArrayList;
import java.util.Arrays;

public class Chicken extends Animals {

    public Chicken() {
        this.name = "Chicken";
        this.buyMoney = 100;
        this.durations = new ArrayList<>(Arrays.asList(2, 2));
        this.imagePath = new ArrayList<>(Arrays.asList("image/Chicken.png", "image/Chicken2.png"));
    }

}