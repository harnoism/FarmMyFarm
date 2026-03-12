package Animals;

import Controller.Stocks;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Chicken extends Animals {

    public Chicken() {
        this.name = "Chicken";
        this.buyMoney = 100;
        this.durations = new ArrayList<>(Arrays.asList(2, 2));
        this.imagePath = new ArrayList<>(Arrays.asList("image/Chicken.png", "image/Chicken2.png"));
    }

    public void startEatingCycle() {

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(10), e -> {

                    int corn = Stocks.stocks.getOrDefault("Mais", 0);

                    if (corn > 0) {

                        Stocks.stocks.put("Mais", corn - 1);

                        Stocks.stocks.put("Oeuf",
                                Stocks.stocks.getOrDefault("Oeuf", 0) + 1);

                        System.out.println("La poule mange du maïs !");
                        System.out.println("La poule produit un oeuf !");

                    } else {

                        System.out.println("La poule a faim !");
                    }
                    if (Controller.ShopController.instance != null) {
                        Controller.ShopController.instance.updateAllStocks();
                    }

                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }}