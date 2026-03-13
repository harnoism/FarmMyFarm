package Controller;

import Animals.Chicken;
import Animals.Cow;
import Animals.Pig;
import Plants.*;
import javafx.scene.control.Button;

public class Land {

    public Button land;
    public Plant plant;
    public String typePlant;
    public boolean isAnimal = false;
    public boolean isPlanted = false;
    public boolean isUnlocked;
    public int unlockCost;

    // Styles
    private static final String styleBase =
            "-fx-font-size: 24px;" +
                    "-fx-font-family: 'Segoe UI Emoji';" +
                    "-fx-border-radius: 6px;" +
                    "-fx-border-color: #388E3C;" +
                    "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #66BB6A, #4CAF50);" +
                    "-fx-background-radius: 6px;" +
                    "-fx-border-width: 1px;" +
                    "-fx-translate-y: 0;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0, 0, 3);";

    private static final String styleLocked =
            "-fx-font-size: 14px;" +
                    "-fx-font-family: 'Segoe UI Emoji';" +
                    "-fx-border-radius: 6px;" +
                    "-fx-border-color: #222;" +
                    "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #555, #333);" +
                    "-fx-background-radius: 6px;" +
                    "-fx-text-fill: #FFD700;" +
                    "-fx-border-width: 1px;";

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

    public Land(boolean unlocked, int unlockCost) {
        this.isUnlocked = unlocked;
        this.unlockCost = unlockCost;
        this.land = new Button();
        this.land.setPrefSize(90, 70);
        this.land.setMinSize(90, 70);
        this.land.setMaxSize(90, 70);

        if (!isUnlocked) {
            land.setText("🔒 " + unlockCost);
            land.setStyle(styleLocked);
        } else {
            land.setText("     ");
            land.setStyle(styleBase);
        }

        this.land.setOnMouseEntered(e -> {
            if (isUnlocked && !isPlanted) land.setStyle(styleHover);
        });

        this.land.setOnMouseExited(e -> {
            if (isUnlocked && !isPlanted) land.setStyle(styleBase);
        });

        land.setOnAction(e -> handleClick());
    }

    private void handleClick() {
        // Cas 1 : parcelle verrouillée
        if (!isUnlocked) {
            if (LandFarm.player.getMoney() >= unlockCost) {
                LandFarm.player.removeMoney(unlockCost);
                isUnlocked = true;
                land.setText("     ");
                land.setStyle(styleBase);
                if (ShopController.instance != null) ShopController.instance.updateMoneyLabel();
                if (LandFarm.instance != null) LandFarm.instance.updateMoney();
                System.out.println("Parcelle débloquée !");
            } else {
                System.out.println("Pas assez d'argent ! Il faut " + unlockCost);
            }
            return;
        }

        // Cas 2 : parcelle vide — choisir quoi planter
        if (!isPlanted) {
            ModalChoiceController modal = new ModalChoiceController();
            String choix = modal.ouvrirFenetre();
            if (choix == null) return;

            this.typePlant = choix;
            boolean isAnimalChoice = choix.equals("Poule") || choix.equals("Vache") || choix.equals("Cochon");

            if (isAnimalChoice) {
                // Les animaux ne nécessitent pas de graine
                this.isAnimal = true;
                isPlanted = true;
                land.setStyle(stylePlanted);
                addPlant();
                if (ShopController.instance != null) ShopController.instance.updateAllInventaire();
            } else {
                // Les plantes nécessitent une graine
                if (Stocks.removeSeed(typePlant)) {
                    this.isAnimal = false;
                    isPlanted = true;
                    land.setStyle(stylePlanted);
                    addPlant();
                    if (ShopController.instance != null) ShopController.instance.updateAllInventaire();
                } else {
                    System.out.println("Pas de graines pour : " + typePlant);
                    this.typePlant = null;
                }
            }
            return;
        }

        // Cas 3 : animal posé — pas de récolte manuelle
        if (isAnimal) {
            System.out.println("C'est un animal, il produit automatiquement !");
            return;
        }

        // Cas 4 : plante prête à récolter
        if (this.plant != null && this.plant.collectAuthorized) {
            String plantName = this.plant.name != null ? this.plant.name : "Inconnu";
            Stocks.stocks.merge(plantName, 1, Integer::sum);
            LandFarm.player.addMoney((int) this.plant.sellMoney);

            if (LandFarm.instance != null) LandFarm.instance.updateMoney();
            if (ShopController.instance != null) {
                ShopController.instance.updateAllStocks();
                ShopController.instance.updateAllInventaire();
            }

            // Remet la parcelle à zéro
            this.plant = null;
            this.typePlant = null;
            this.isAnimal = false;
            isPlanted = false;
            land.setText("     ");
            land.setGraphic(null);
            land.setStyle(styleBase);

        } else {
            System.out.println("Plante pas encore prête !");
        }
    }

    public void addPlant() {
        if (typePlant == null) return;

        switch (typePlant) {
            case "Patate"  -> { this.plant = new Patate();  this.plant.growthDuration(this.land); }
            case "Mais"    -> { this.plant = new Mais();    this.plant.growthDuration(this.land); }
            case "Tomate"  -> { this.plant = new Tomate();  this.plant.growthDuration(this.land); }
            case "Carrot"  -> { this.plant = new Carrot();  this.plant.growthDuration(this.land); }
            case "Brocoli" -> { this.plant = new Brocoli(); this.plant.growthDuration(this.land); }
            case "Poule" -> {
                Chicken chicken = new Chicken();
                chicken.startEatingCycle();
                chicken.growthDuration(this.land);
            }
            case "Vache" -> {
                Cow cow = new Cow();
                cow.startEatingCycle();
                cow.growthDuration(this.land);
            }
            case "Cochon" -> {
                Pig pig = new Pig();
                pig.startEatingCycle();
                pig.growthDuration(this.land);
            }
        }
    }

    // Pour la sauvegarde
    public SaveData.LandData getLandData() {
        boolean collectReady = (plant != null && plant.collectAuthorized);
        return new SaveData.LandData(isUnlocked, unlockCost, typePlant, isAnimal, collectReady);
    }

    // Pour le chargement
    public void restore(SaveData.LandData data) {
        this.isUnlocked = data.isUnlocked;
        this.unlockCost = data.unlockCost;

        if (!isUnlocked) {
            land.setText("🔒 " + unlockCost);
            land.setStyle(styleLocked);
            return;
        }

        land.setText("     ");
        land.setStyle(styleBase);

        if (data.typePlant == null) return;

        this.typePlant = data.typePlant;
        this.isAnimal = data.isAnimal;
        this.isPlanted = true;
        land.setStyle(stylePlanted);

        addPlant();

        if (!isAnimal && data.collectAuthorized && this.plant != null) {
            this.plant.collectAuthorized = true;
            land.setText("✅");
        }
    }

    public Button getButton() {
        return land;
    }
}