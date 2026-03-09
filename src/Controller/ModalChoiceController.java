package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalChoiceController {

    private String selectedPlant = null;

    // Ouvre le modal et ATTEND la fermeture avant de continuer
    public String ouvrirFenetre() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/modalChoicePlant.fxml")
            );
            Parent root = loader.load();

            ModalChoicePlant controller = loader.getController();

            Stage modalStage = new Stage();
            modalStage.setTitle("Choose");
            modalStage.setScene(new Scene(root));

            // bloque l'exécution jusqu'à fermeture du modal
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.showAndWait();

            // Récupère le choix APRÈS fermeture
            selectedPlant = controller.selectedPlant();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return selectedPlant;
    }
}