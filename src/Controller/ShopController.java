package Controller;

import Plants.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ShopController {

    @FXML private Label moneyLabel;

    @FXML private Label patateInventaire;
    @FXML private Label maisInventaire;
    @FXML private Label carrotInventaire;
    @FXML private Label tomateInventaire;
    @FXML private Label brocoliInventaire;

    @FXML private Label patateStocks;
    @FXML private Label maisStocks;
    @FXML private Label carrotStocks;
    @FXML private Label tomateStocks;
    @FXML private Label brocoliStocks;

    public static ShopController instance;

    @FXML
    private void initialize() {
        instance = this;
        updateMoneyLabel();
        updateAllStocks();
        updateAllInventaire();
    }

    public void updateMoneyLabel() {
        if (moneyLabel != null) {
            moneyLabel.setText(LandFarm.player.getMoney() + " 💰");
        }
    }

    @FXML
    public void buyPatateSeed() {
        Plant plant = new Patate();
        if (LandFarm.player.getMoney() >= plant.buyMoney) {
            LandFarm.player.removeMoney(plant.buyMoney);
            Stocks.addSeed("Patate", 1);
            updateMoneyLabel();
            updateAllInventaire();
            LandFarm.instance.updateMoney();
        }
    }

    @FXML
    public void buyMaisSeed() {
        Plant plant = new Mais();
        if (LandFarm.player.getMoney() >= plant.buyMoney) {
            LandFarm.player.removeMoney(plant.buyMoney);
            Stocks.addSeed("Mais", 1);
            updateMoneyLabel();
            updateAllInventaire();
            LandFarm.instance.updateMoney();
        }
    }

    @FXML
    public void buyCarrotSeed() {
        Plant plant = new Carrot();
        if (LandFarm.player.getMoney() >= plant.buyMoney) {
            LandFarm.player.removeMoney(plant.buyMoney);
            Stocks.addSeed("Carrot", 1);
            updateMoneyLabel();
            updateAllInventaire();
            LandFarm.instance.updateMoney();
        }
    }

    @FXML
    public void buyBrocoliSeed() {
        Plant plant = new Brocoli();
        if (LandFarm.player.getMoney() >= plant.buyMoney) {
            LandFarm.player.removeMoney(plant.buyMoney);
            Stocks.addSeed("Brocoli", 1);
            updateMoneyLabel();
            updateAllInventaire();
            LandFarm.instance.updateMoney();
        }
    }

    @FXML
    public void buyTomateSeed() {
        Plant plant = new Tomate();
        if (LandFarm.player.getMoney() >= plant.buyMoney) {
            LandFarm.player.removeMoney(plant.buyMoney);
            Stocks.addSeed("Tomate", 1);
            updateMoneyLabel();
            updateAllInventaire();
            LandFarm.instance.updateMoney();
        }
    }

    @FXML
    public void sellAllStocks() {
        int totalmoney = 0;
        for (String plant : Stocks.stocks.keySet()) {
            int quantity = Stocks.stocks.get(plant);
            Plant plants = null;
            if (plant.equals("Patate"))  plants = new Patate();
            if (plant.equals("Mais"))    plants = new Mais();
            if (plant.equals("Tomate"))  plants = new Tomate();
            if (plant.equals("Brocoli")) plants = new Brocoli();
            if (plant.equals("Carrot"))  plants = new Carrot();
            if (plants != null) totalmoney += quantity * plants.sellMoney;
            Stocks.stocks.put(plant, 0);
        }
        LandFarm.player.addMoney(totalmoney);
        updateMoneyLabel();
        updateAllStocks();
        LandFarm.instance.updateMoney();
    }


    public void updateAllInventaire() {
        if (patateInventaire != null)
            patateInventaire.setText("Patate: " + Stocks.seeds.getOrDefault("Patate", 0));
        if (maisInventaire != null)
            maisInventaire.setText("Mais: " + Stocks.seeds.getOrDefault("Mais", 0));
        if (carrotInventaire != null)
            carrotInventaire.setText("Carrot: " + Stocks.seeds.getOrDefault("Carrot", 0));
        if (tomateInventaire != null)
            tomateInventaire.setText("Tomate: " + Stocks.seeds.getOrDefault("Tomate", 0));
        if (brocoliInventaire != null)
            brocoliInventaire.setText("Brocoli: " + Stocks.seeds.getOrDefault("Brocoli", 0));
    }

    // Labels stocks = récoltes
    public void updateAllStocks() {
        if (patateStocks != null)
            patateStocks.setText("Patate: " + Stocks.stocks.getOrDefault("Patate", 0));
        if (maisStocks != null)
            maisStocks.setText("Mais: " + Stocks.stocks.getOrDefault("Mais", 0));
        if (carrotStocks != null)
            carrotStocks.setText("Carrot: " + Stocks.stocks.getOrDefault("Carrot", 0));
        if (tomateStocks != null)
            tomateStocks.setText("Tomate: " + Stocks.stocks.getOrDefault("Tomate", 0));
        if (brocoliStocks != null)
            brocoliStocks.setText("Brocoli: " + Stocks.stocks.getOrDefault("Brocoli", 0));
    }

    @FXML
    public void ouvrirFenetre(ActionEvent event) {
        try {
            FXMLLoader modalloader = new FXMLLoader(getClass().getResource("/FXML/shopWindow.fxml"));
            Parent root = modalloader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Shop");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}