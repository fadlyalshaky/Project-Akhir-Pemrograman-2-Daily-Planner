/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.util.HashMap;
import Model.Food;
/**
 *
 * @author asus
 */
public class NutritionController {
 private HashMap<String, Food> data = new HashMap<>();

    public NutritionController() {

        data.put("Brokoli", new Food("Brokoli", 34, 6.6, 2.8, 0.4, 2.6));
        data.put("Wortel", new Food("Wortel", 41, 9.6, 0.9, 0.2, 2.8));

        data.put("Nasi Putih", new Food("Nasi Putih", 130, 28, 2.7, 0.3, 0.4));
        data.put("Nasi Goreng", new Food("Nasi Goreng", 168, 25, 3.5, 5.5, 1.2));

        data.put("Ayam Dada", new Food("Ayam Dada", 165, 0, 31, 3.6, 0));
        data.put("Ayam Goreng", new Food("Ayam Goreng", 260, 8, 18, 18, 0));

        data.put("Ikan Goreng", new Food("Ikan Goreng", 240, 10, 20, 15, 0));

        data.put("Telur", new Food("Telur", 155, 1.1, 13, 11, 0));
        data.put("Telur Rebus", new Food("Telur Rebus", 154, 1.1, 13, 11, 0));

        data.put("Tempe", new Food("Tempe", 193, 9, 20, 11, 1.4));
        data.put("Tempe Goreng", new Food("Tempe Goreng", 270, 13, 14, 20, 1));

        data.put("Tahu", new Food("Tahu", 76, 1.9, 8, 4.8, 0.3));
        data.put("Tahu Goreng", new Food("Tahu Goreng", 271, 8, 12, 22, 0.8));

        data.put("Sayur Sop", new Food("Sayur Sop", 60, 8, 2, 1, 2));
        data.put("Mie Goreng", new Food("Mie Goreng", 196, 25, 4, 8, 1));

        data.put("Apel", new Food("Apel", 52, 14, 0.3, 0.2, 2.4));
        data.put("Pisang", new Food("Pisang", 89, 23, 1.1, 0.3, 2.6));

        data.put("Susu", new Food("Susu", 42, 5, 3.4, 1, 0));
    }

    public double[] hitung(String nama, double gram) {
        Food f = data.get(nama);

        if (f == null || gram <= 0) {
            return new double[]{0, 0, 0, 0, 0};
        }

        double faktor = gram / 100;

        return new double[]{
            f.getKalori() * faktor,
            f.getKarbo() * faktor,
            f.getProtein() * faktor,
            f.getLemak() * faktor,
            f.getSerat() * faktor
        };
    }
}
