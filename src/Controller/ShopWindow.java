package Controller;

import Animals.Animals;
import Animals.Chicken;
import Animals.Cow;
import Animals.Pig;
import Plants.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShopWindow {

    @FXML
    private Label moneyLabel;
    @FXML
    private Label patateSeeds;
    @FXML
    private Label maisSeeds;
    @FXML
    private Label carrotSeeds;
    @FXML
    private Label tomateSeeds;
    @FXML
    private Label brocoliSeeds;
    @FXML
    private Label vacheBuy;
    @FXML
    private Label pouleBuy;

    public static ShopWindow instance;

    @FXML
    private void initialize() {
        instance = this;
        updateMoneyLabel();
        updateAllSeeds();
    }

    public void updateMoneyLabel() {
        if (moneyLabel != null)
            moneyLabel.setText(String.valueOf(LandFarm.player.getMoney()));
    }

    @FXML
    public void buyPatateSeed() {
        Plant plant = new Patate();
        if (LandFarm.player.getMoney() >= plant.buyMoney) {
            LandFarm.player.removeMoney(plant.buyMoney);
            Stocks.addSeed("Patate", 1);
            updateMoneyLabel();
            updateAllSeeds();
            if (LandFarm.instance != null) LandFarm.instance.updateMoney();
            if (ShopController.instance != null) ShopController.instance.updateAllInventaire();
        }
    }

    @FXML
    public void buyMaisSeed() {
        Plant plant = new Mais();
        if (LandFarm.player.getMoney() >= plant.buyMoney) {
            LandFarm.player.removeMoney(plant.buyMoney);
            Stocks.addSeed("Mais", 1);
            updateMoneyLabel();
            updateAllSeeds();
            if (LandFarm.instance != null) LandFarm.instance.updateMoney();
            if (ShopController.instance != null) ShopController.instance.updateAllInventaire();
        }
    }

    @FXML
    public void buyCarrotSeed() {
        Plant plant = new Carrot();
        if (LandFarm.player.getMoney() >= plant.buyMoney) {
            LandFarm.player.removeMoney(plant.buyMoney);
            Stocks.addSeed("Carrot", 1);
            updateMoneyLabel();
            updateAllSeeds();
            if (LandFarm.instance != null) LandFarm.instance.updateMoney();
            if (ShopController.instance != null) ShopController.instance.updateAllInventaire();
        }
    }

    @FXML
    public void buyTomateSeed() {
        Plant plant = new Tomate();
        if (LandFarm.player.getMoney() >= plant.buyMoney) {
            LandFarm.player.removeMoney(plant.buyMoney);
            Stocks.addSeed("Tomate", 1);
            updateMoneyLabel();
            updateAllSeeds();
            if (LandFarm.instance != null) LandFarm.instance.updateMoney();
            if (ShopController.instance != null) ShopController.instance.updateAllInventaire();
        }
    }

    @FXML
    public void buyBrocoliSeed() {
        Plant plant = new Brocoli();
        if (LandFarm.player.getMoney() >= plant.buyMoney) {
            LandFarm.player.removeMoney(plant.buyMoney);
            Stocks.addSeed("Brocoli", 1);
            updateMoneyLabel();
            updateAllSeeds();
            if (LandFarm.instance != null) LandFarm.instance.updateMoney();
            if (ShopController.instance != null) ShopController.instance.updateAllInventaire();
        }
    }
    @FXML
    public void buypoule() {
        Animals animals = new Chicken();
        if (LandFarm.player.getMoney() >= animals.buyMoney) {
            LandFarm.player.removeMoney(animals.buyMoney);
            Stocks.addSeed("Poule", 1);
            updateMoneyLabel();
            updateAllSeeds();
            if (LandFarm.instance != null) LandFarm.instance.updateMoney();
            if (ShopController.instance != null) ShopController.instance.updateAllInventaire();
        }
    }
    @FXML
    public void buyvache() {
        Animals animals = new Cow();
        if (LandFarm.player.getMoney() >= animals.buyMoney) {
            LandFarm.player.removeMoney(animals.buyMoney);
            Stocks.addSeed("Vache", 1);
            updateMoneyLabel();
            updateAllSeeds();
            if (LandFarm.instance != null) LandFarm.instance.updateMoney();
            if (ShopController.instance != null) ShopController.instance.updateAllInventaire();
        }
    }
    @FXML
    public void buycochon() {
        Animals animals = new Pig();
        if (LandFarm.player.getMoney() >= animals.buyMoney) {
            LandFarm.player.removeMoney(animals.buyMoney);
            Stocks.addSeed("Cochon", 1);
            updateMoneyLabel();
            updateAllSeeds();
            if (LandFarm.instance != null) LandFarm.instance.updateMoney();
            if (ShopController.instance != null) ShopController.instance.updateAllInventaire();
        }
    }

    public void updateAllSeeds() {
        if (patateSeeds != null) patateSeeds.setText("Patate: " + Stocks.seeds.getOrDefault("Patate", 0));
        if (maisSeeds != null) maisSeeds.setText("Mais: " + Stocks.seeds.getOrDefault("Mais", 0));
        if (carrotSeeds != null) carrotSeeds.setText("Carrot: " + Stocks.seeds.getOrDefault("Carrot", 0));
        if (tomateSeeds != null) tomateSeeds.setText("Tomate: " + Stocks.seeds.getOrDefault("Tomate", 0));
        if (brocoliSeeds != null) brocoliSeeds.setText("Brocoli: " + Stocks.seeds.getOrDefault("Brocoli", 0));
    }
}