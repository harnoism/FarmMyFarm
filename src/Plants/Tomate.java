package Plants;

import java.util.ArrayList;
import java.util.Arrays;

public class Tomate extends Plant {
    public Tomate() {
        this.name = "Tomate";
        this.buyMoney = 50;
        this.sellMoney = 75;
        this.price = 25;
        this.durations = new ArrayList<>(Arrays.asList(1, 1));
        this.emojiList = new ArrayList<>(Arrays.asList("🌱", "\uD83C\uDF3F", "\u200B\uD83C\uDF45"));
    }
}