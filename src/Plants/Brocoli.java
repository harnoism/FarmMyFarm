package Plants;

import java.util.ArrayList;
import java.util.Arrays;

public class Brocoli extends Plant {
    public Brocoli() {
        this.name = "Brocoli";
        this.buyMoney = 120;
        this.sellMoney = 400;
        this.price = 75;
        this.durations = new ArrayList<>(Arrays.asList(3, 6));
        this.emojiList = new ArrayList<>(Arrays.asList("🌱", "\uD83C\uDF3F", "\uD83E\uDD66\u200B"));
    }
}