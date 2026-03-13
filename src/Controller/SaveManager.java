package Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {

    private static final String SAVE_FILE = "save.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void save() {
        // Collecte l'état de chaque parcelle
        List<SaveData.LandData> landDataList = new ArrayList<>();
        if (LandFarm.instance != null) {
            for (Land land : LandFarm.instance.lands) {
                landDataList.add(land.getLandData());
            }
        }

        SaveData data = new SaveData(
                LandFarm.player.getMoney(),
                new java.util.HashMap<>(Stocks.seeds),
                new java.util.HashMap<>(Stocks.stocks),
                landDataList
        );

        try (Writer writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(data, writer);
            System.out.println("Sauvegarde réussie !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAndQuit(Stage stage) {
        save();

        try {
            FXMLLoader loader = new FXMLLoader(SaveManager.class.getResource("/fxml/menu.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("FarmMyFarm");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            System.out.println("Aucune sauvegarde trouvée.");
            return;
        }

        try (Reader reader = new FileReader(file)) {
            SaveData data = gson.fromJson(reader, SaveData.class);
            LandFarm.player.setMoney(data.money);
            Stocks.seeds = data.seeds;
            Stocks.stocks = data.stocks;

            // Restaure les parcelles
            if (LandFarm.instance != null && data.lands != null) {
                LandFarm.instance.restoreLands(data.lands);
            }

            System.out.println("Chargement réussi !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}