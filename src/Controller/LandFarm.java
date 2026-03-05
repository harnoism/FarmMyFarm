package Controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class LandFarm {
    @FXML
    private Button land;
    @FXML
    private GridPane gridPane;

    public void initialize() {
        int rows = 6;
        int columns = 6;

        // Controller.Land[][] lands = new Controller.Land[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Land land = new Land("Maïs");

                gridPane.setHalignment(land.getButton(), javafx.geometry.HPos.CENTER);
                gridPane.setValignment(land.getButton(), javafx.geometry.VPos.CENTER);
                gridPane.setAlignment(Pos.CENTER);

                gridPane.add(land.getButton(), col, row);
            }
        }
    }
}
