package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import Plants.Plant;
import Plants.Mais;
import Plants.Patate;

public class Land {
    @FXML
    public Button land;
    public Plant plant;
    public String typePlant;
    @FXML
    private ModalChoicePlant modalChoicePlant;


    public Land(String typePlant) {
        this.land = new Button("🆕");
        this.typePlant = typePlant;

        addPlant();
    }

    public void addPlant () {
        if (typePlant.equals("Patate")) {
            this.plant = new Patate();
        }

        if (typePlant.equals("Maïs")) {
            this.plant = new Mais();
        }

        land.setOnAction(e -> {
            System.out.println(plant.name);
            this.plant.growthDuration(this.land);

            if (this.plant.collectAuthorized) {
                Stocks.instance.add(this.plant.name, 1);
                this.plant.collectAuthorized = false;
                land.setText("🆕");
                System.out.println(Stocks.stocks);
            }else{
                modalChoicePlant.visible(true);
            }
        });
    }

    public Button getButton() {
        return land;
    }
}
