package Controller;

import Controller.Player;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import javafx.scene.control.Label;

public class LandFarm {
    @FXML
    private GridPane gridPane;
    private Label moneyLabel;

    public static Player player = new Player();
    public static LandFarm instance;

    public void initialize() {
        instance = this;
        int rows = 6;
        int columns = 9;

        gridPane.setHgap(3); // espace horizontal entre les cases
        gridPane.setVgap(3);

        updateMoney();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Land land = new Land();

                gridPane.setHalignment(land.getButton(), javafx.geometry.HPos.CENTER);
                gridPane.setValignment(land.getButton(), javafx.geometry.VPos.CENTER);
                gridPane.setAlignment(Pos.CENTER);

                gridPane.add(land.getButton(), col, row);
            }
        }
    }

    public void setMoneyLabel(Label moneyLabel) {
        this.moneyLabel = moneyLabel;
        updateMoney(); // met à jour dès qu’on reçoit le Label
    }

    public void updateMoney() {
        if (moneyLabel != null) {
            moneyLabel.setText(String.valueOf(player.getMoney()));
        }
        /*
        if (ShopController.instance != null) {
            ShopController.instance.updateMoneyLabel();
        }
        */
    }

}
