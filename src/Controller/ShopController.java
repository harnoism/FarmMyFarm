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


    @FXML private Label patateStocks;
    @FXML private Label maisStocks;
    @FXML private Label carrotStocks;
    @FXML private Label tomateStocks;
    @FXML private Label brocoliStocks;
    @FXML private Label oeufStocks;
    @FXML private Label baconStocks;
    @FXML private Label laitStocks;

    @FXML private Label patateSeeds;
    @FXML private Label maisSeeds;
    @FXML private Label carrotSeeds;
    @FXML private Label tomateSeeds;
    @FXML private Label brocoliSeeds;


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
            moneyLabel.setText(String.valueOf(LandFarm.player.getMoney()));
        }
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
        if (oeufStocks != null)
            oeufStocks.setText("Oeufs: " + Stocks.stocks.getOrDefault("Oeuf", 0));
        if (baconStocks != null)
            baconStocks.setText("Bacon: " + Stocks.stocks.getOrDefault("Bacon", 0));
        if (laitStocks != null)
            laitStocks.setText("Lait: " + Stocks.stocks.getOrDefault("Lait", 0));
    }

    public void updateAllInventaire() {
        if (patateSeeds != null) patateSeeds.setText("Patate: " + Stocks.seeds.getOrDefault("Patate", 0));
        if (maisSeeds != null)   maisSeeds.setText("Mais: "     + Stocks.seeds.getOrDefault("Mais", 0));
        if (carrotSeeds != null) carrotSeeds.setText("Carrot: " + Stocks.seeds.getOrDefault("Carrot", 0));
        if (tomateSeeds != null) tomateSeeds.setText("Tomate: " + Stocks.seeds.getOrDefault("Tomate", 0));
        if (brocoliSeeds != null)brocoliSeeds.setText("Brocoli: "+ Stocks.seeds.getOrDefault("Brocoli", 0));
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
        if (LandFarm.instance != null) LandFarm.instance.updateMoney();
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

            updateMoneyLabel();
            updateAllStocks();
            updateAllInventaire();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}