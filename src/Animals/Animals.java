package Animals;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;
import javafx.scene.image.Image;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Animals {
    public String name;
    public double buyMoney;
    public ArrayList<Integer> durations;
    public ArrayList<String> imagePath;
    public boolean collectAuthorized = false;

    public void growthDuration(Button land) {
        if (!collectAuthorized) {

            Image img1 = new Image(getClass().getResourceAsStream("/" + imagePath.get(0)));
            Image img2 = new Image(getClass().getResourceAsStream("/" + imagePath.get(1)));

            ImageView view = new ImageView(img1);
            view.setFitWidth(60);
            view.setFitHeight(60);
            land.setGraphic(view);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(2), e -> {
                        view.setImage(img2);
                    }),
                    new KeyFrame(Duration.seconds(4), e -> {
                        view.setImage(img1);
                    })
            );

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    public abstract void startEatingCycle();
}
