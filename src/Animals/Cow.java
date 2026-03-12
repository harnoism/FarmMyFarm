package Animals;

import Controller.Stocks;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class Cow extends Animals {

    public Cow() {
        this.name = "Cow";
        this.buyMoney = 400;
        this.durations = new ArrayList<>(Arrays.asList(1, 2));
        this.imagePath = new ArrayList<>(Arrays.asList("image/vache.png", "image/vache2.png"));
    }
    public void startEatingCycle() {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(10), e -> {

                    int corn = Stocks.stocks.getOrDefault("Brocoli", 0);

                    if (corn > 0) {

                        Stocks.stocks.put("Brocoli", corn - 1);

                        Stocks.stocks.put("Lait",
                                Stocks.stocks.getOrDefault("Lait", 0) + 1);

                        System.out.println("Le vache mange des carrots !");
                        System.out.println("La vache produit du lait !");

                    } else {

                        System.out.println("La vache a faim !");
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