package Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class ModalChoicePlant {
    @FXML
    public GridPane modalChoicePlant;

    public ModalChoicePlant(){

    }

    public void visible(boolean display) {
        modalChoicePlant.setVisible(display);
    }
}
