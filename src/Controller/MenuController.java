package Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.File;

public class MenuController {

    @FXML
    private Button btnCharger; // ajoute fx:id="btnCharger" sur le bouton dans le FXML

    @FXML
    public void initialize() {
        // Désactive le bouton si aucune sauvegarde
        File saveFile = new File("save.json");
        if (!saveFile.exists()) {
            btnCharger.setDisable(true);
            btnCharger.setOpacity(0.4);
        }
    }

    @FXML
    public void nouvellePartie(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/interface.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("FarmMyFarm");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chargerPartie(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/interface.fxml"));
            Parent root = loader.load();

            // Charge la sauvegarde APRÈS l'initialisation
            SaveManager.load();

            // Met à jour l'affichage avec les données chargées
            if (ShopController.instance != null) {
                ShopController.instance.updateMoneyLabel();
                ShopController.instance.updateAllStocks();
                ShopController.instance.updateAllInventaire();
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("FarmMyFarm");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void quitter() {
        Platform.exit();
    }
}