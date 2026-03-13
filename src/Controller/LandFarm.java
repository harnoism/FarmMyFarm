package Controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

public class LandFarm {

    @FXML
    private GridPane gridPane;
    private Label moneyLabel;

    public static Player player = new Player();
    public static LandFarm instance;
    public List<Land> lands = new ArrayList<>();

    public void initialize() {
        instance = this;
        int rows = 6;
        int columns = 9;

        gridPane.setHgap(7);
        gridPane.setVgap(7);
        updateMoney();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                boolean unlocked = row < 2;
                int cost = (row * columns + col) * 50;

                Land land = new Land(unlocked, cost);
                lands.add(land);

                gridPane.setHalignment(land.getButton(), javafx.geometry.HPos.CENTER);
                gridPane.setValignment(land.getButton(), javafx.geometry.VPos.CENTER);
                gridPane.setAlignment(Pos.CENTER);
                gridPane.add(land.getButton(), col, row);
            }
        }
    }

    public void restoreLands(List<SaveData.LandData> landDataList) {
        if (landDataList == null) return;
        for (int i = 0; i < Math.min(lands.size(), landDataList.size()); i++) {
            lands.get(i).restore(landDataList.get(i));
        }
    }

    public void setMoneyLabel(Label moneyLabel) {
        this.moneyLabel = moneyLabel;
        updateMoney();
    }

    public void updateMoney() {
        if (ShopController.instance != null) {
            ShopController.instance.updateMoneyLabel();
        }
    }
}