import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class ShopController {
    @FXML
    public void ouvrirFenetre(ActionEvent event){
        try {
            FXMLLoader modalloader = new FXMLLoader(getClass().getResource("/FXML/shopWindow.fxml"));
            Parent root = modalloader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modal Windows");

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}