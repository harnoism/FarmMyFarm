package Plants;

import java.util.ArrayList;
import java.util.Arrays;

public class Carrot extends Plant {
    public Carrot() {
        this.name = "Carrot";
        this.buyMoney = 100;
        this.sellMoney = 300;
        this.price = 50;
        this.durations = new ArrayList<>(Arrays.asList(2, 4));
        this.emojiList = new ArrayList<>(Arrays.asList("🌱", "\uD83C\uDF3F", "\uD83E\uDD55"));
    }
}