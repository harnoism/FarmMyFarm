package Animals;

import Controller.Stocks;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class Pig extends Animals {

    public Pig() {
        this.name = "Pig";
        this.buyMoney = 200;
        this.durations = new ArrayList<>(Arrays.asList(4, 2));
        this.imagePath = new ArrayList<>(Arrays.asList("image/cochon.png", "image/cochon2.png"));
    }

    public void startEatingCycle() {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(10), e -> {

                    int corn = Stocks.stocks.getOrDefault("Carrot", 0);

                    if (corn > 0) {

                        Stocks.stocks.put("Carrot", corn - 1);

                        Stocks.stocks.put("Bacon",
                                Stocks.stocks.getOrDefault("Bacon", 0) + 1);

                        System.out.println("Le cochon mange des carrots !");
                        System.out.println("La cochon produit du bacon !");

                    } else {

                        System.out.println("Le cochon a faim !");
                    }
                    if (Controller.ShopController.instance != null) {
                        Controller.ShopController.instance.updateAllStocks();
                    }

                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
