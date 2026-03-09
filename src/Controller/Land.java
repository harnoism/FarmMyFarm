package Controller;

import Plants.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.lang.classfile.Label;

public class Land {
    @FXML
    public Button land;
    public Plant plant;
    public String typePlant;
    private boolean isPlanted = false;

    // Styles
    private static final String styleBase =
            "-fx-font-size: 24px;" +
                    "-fx-font-family: 'Segoe UI Emoji';" +
                    "-fx-border-radius: 6px;" +
                    "-fx-border-color: #388E3C;" +
                    "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #66BB6A, #4CAF50);" +
                    "-fx-background-radius: 6px;" +
                    "-fx-border-width: 1px;" +
                    "-fx-translate-y: 0;";

    private static final String styleHover =
            "-fx-font-size: 24px;" +
                    "-fx-font-family: 'Segoe UI Emoji';" +
                    "-fx-border-radius: 6px;" +
                    "-fx-border-color: #388E3C;" +
                    "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #A5D6A7, #81C784);" +
                    "-fx-background-radius: 6px;" +
                    "-fx-border-width: 1px;" +
                    "-fx-translate-y: -1;";

    private static final String stylePlanted =
            "-fx-font-size: 24px;" +
                    "-fx-font-family: 'Segoe UI Emoji';" +
                    "-fx-border-radius: 6px;" +
                    "-fx-border-color: #4E342E;" +
                    "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #6D4C41, #795548);" +
                    "-fx-background-radius: 6px;" +
                    "-fx-border-width: 1px;" +
                    "-fx-translate-y: 2;";

    public Land() {
        this.land = new Button("     ");
        this.land.setPrefSize(80, 80);
        this.land.setMinSize(80, 80);
        this.land.setMaxSize(80, 80);
        this.land.setStyle(styleBase);

        // Hover uniquement si pas planté
        this.land.setOnMouseEntered(e -> {
            if (!isPlanted) {
                land.setStyle(styleHover);
            }
        });

        this.land.setOnMouseExited(e -> {
            if (!isPlanted) {
                land.setStyle(styleBase);
            }
        });

        this.typePlant = null;
        this.plant = null;

        // Le clic ouvre le modal, puis plante
        land.setOnAction(e -> {
            if (this.plant == null) {
                // Ouvre le modal et attend le choix
                ModalChoiceController modal = new ModalChoiceController();
                String choix = modal.ouvrirFenetre();

                if (choix == null) return; // Annulé

                if (Stocks.removeSeed(typePlant)) {

                    addPlant();
                    isPlanted = true;
                    land.setStyle(stylePlanted);

                } else {

                    System.out.println("Pas de graines !");
                }
            } else {
                // Plante déjà présente : récolter si possible
                this.plant.growthDuration(this.land);

                if (this.plant.collectAuthorized) {
                    String plantName = this.plant.name != null ? this.plant.name : "Inconnu";
                    Stocks.instance.add(plantName, 1);

                    LandFarm.player.addMoney(this.plant.price);
                    if (LandFarm.instance != null) {
                        LandFarm.instance.updateMoney();
                    }

                    if (ShopController.instance != null) {
                        ShopController.instance.updateAllStocks();
                    }

                    this.plant.collectAuthorized = false;
                    this.plant = null; // Réinitialise la parcelle
                    isPlanted = false;
                    land.setText("     ");
                    land.setStyle(styleBase);
                    System.out.println(Stocks.stocks);
                }
            }
        });
    }

    public void addPlant() {

        if (typePlant == null) return;

        if (typePlant.equals("Patate")) {
            this.plant = new Patate();
        }
        if (typePlant.equals("Mais")) {
            this.plant = new Mais();
        }
        if (typePlant.equals("Tomate")) {
            this.plant = new Tomate();
        }
        if (typePlant.equals("Carrot")) {
            this.plant = new Carrot();
        }
        if (typePlant.equals("Brocoli")) {
            this.plant = new Brocoli();
        }
        if (this.plant != null) {
            // Lance la croissance IMMÉDIATEMENT après la plantation
            this.plant.growthDuration(this.land);
        }
    }

    public Button getButton() {
        return land;
    }
}

