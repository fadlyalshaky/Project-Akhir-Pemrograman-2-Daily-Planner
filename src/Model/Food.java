/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author asus
 */
public class Food {
    private String nama;
    private double kalori;
    private double karbo;
    private double protein;
    private double lemak;
    private double serat;

    public Food(String nama, double kalori, double karbo,
                double protein, double lemak, double serat) {
        this.nama = nama;
        this.kalori = kalori;
        this.karbo = karbo;
        this.protein = protein;
        this.lemak = lemak;
        this.serat = serat;
    }

    public String getNama() {
        return nama;
    }

    public double getKalori() {
        return kalori;
    }

    public double getKarbo() {
        return karbo;
    }

    public double getProtein() {
        return protein;
    }

    public double getLemak() {
        return lemak;
    }

    public double getSerat() {
        return serat;
    }
}