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
            ImageView view1 = new ImageView(img1);
            view1.setFitWidth(40);
            view1.setFitHeight(40);
            land.setGraphic(view1);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(durations.get(0)), e -> {
                        Image img2 = new Image(getClass().getResourceAsStream("/" + imagePath.get(1)));
                        ImageView view2 = new ImageView(img2);
                        view2.setFitWidth(40);
                        view2.setFitHeight(40);
                        land.setGraphic(view2);
                    })
            );

            timeline.setOnFinished(e -> collectAuthorized = false);
            timeline.play();
        }
    }
}
