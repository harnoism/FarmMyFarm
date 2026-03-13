package Controller;

import java.util.HashMap;
import java.util.List;

public class SaveData {
    public double money;
    public HashMap<String, Integer> seeds;
    public HashMap<String, Integer> stocks;
    public List<LandData> lands; // état de chaque parcelle

    public SaveData(double money, HashMap<String, Integer> seeds,
                    HashMap<String, Integer> stocks, List<LandData> lands) {
        this.money = money;
        this.seeds = seeds;
        this.stocks = stocks;
        this.lands = lands;
    }

    // Représente une parcelle
    public static class LandData {
        public boolean isUnlocked;
        public int unlockCost;
        public String typePlant;   // null si vide
        public boolean isAnimal;   // true si c'est un animal
        public boolean collectAuthorized;

        public LandData(boolean isUnlocked, int unlockCost, String typePlant,
                        boolean isAnimal, boolean collectAuthorized) {
            this.isUnlocked = isUnlocked;
            this.unlockCost = unlockCost;
            this.typePlant = typePlant;
            this.isAnimal = isAnimal;
            this.collectAuthorized = collectAuthorized;
        }
    }
}