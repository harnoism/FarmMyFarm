package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ModalChoicePlant {
    @FXML
    public AnchorPane root;
    @FXML
    public Button btnPatate;
    @FXML
    public Button btnTomate;
    @FXML
    public Button btnBrocoli;
    @FXML
    public Button btnCarrot;
    @FXML
    public Button btnMais;

    @FXML
    public Button btnPoule;
    @FXML
    public Button btnVache;
    @FXML
    public Button btnCochon;
    @FXML
    private String selectedPlant = null;

    @FXML
    public void initialize() {
        String base = "-fx-font-size: 13px;" +
                "-fx-font-family: 'Segoe UI Emoji';" +
                "-fx-background-color: #795548;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-radius: 8px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0, 0, 3);" +
                "-fx-translate-y: 0;";

        String hover = "-fx-font-size: 13px;" +
                "-fx-font-family: 'Segoe UI Emoji';" +
                "-fx-background-color: #8D6E63;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-radius: 8px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 6);" +
                "-fx-translate-y: -2;";


        for (Button btn : new Button[]{btnPatate, btnMais, btnCarrot, btnBrocoli, btnTomate, btnPoule, btnVache, btnCochon}) {
            btn.setStyle(base);
            btn.setOnMouseEntered(e -> btn.setStyle(hover));
            btn.setOnMouseExited(e -> btn.setStyle(base));
        }
    }


    public String selectedPlant() {
        return selectedPlant;
    }

    @FXML
    public void choosePatate() {
        selectedPlant = "Patate";
        System.out.println("Patate choisis");
        close();
    }

    @FXML
    public void chooseMais() {
        selectedPlant = "Mais";
        System.out.println("Mais choisis");
        close();
    }

    @FXML
    public void chooseTomate() {
        selectedPlant = "Tomate";
        System.out.println("Tomate choisis");
        close();
    }

    @FXML
    public void chooseBrocoli() {
        selectedPlant = "Brocoli";
        System.out.println("Brocoli choisis");
        close();
    }

    @FXML
    public void choosePoule() {
        selectedPlant = "Poule";
        System.out.println("Poule");
        close();
    }

    @FXML
    public void chooseVache() {
        selectedPlant = "Vache";
        System.out.println("Vache");
        close();
    }

    @FXML
    public void chooseCochon() {
        selectedPlant = "Cochon";
        System.out.println("Cochon");
        close();
    }

    @FXML
    public void chooseCarrot() {
        selectedPlant = "Carrot";
        System.out.println("Carrot choisis");
        close();
    }

    @FXML
    public void close() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

}
