/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDate;
/**
 *
 * @author asus
 */
public class DailyPlan {
    private String no;
    private LocalDate tanggal;
    private String waktu;
    private String kegiatan;

    public DailyPlan() {
    }
    public DailyPlan(String no, LocalDate tanggal, String waktu, String kegiatan) {
        this.no = no;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.kegiatan = kegiatan;
    }

    public String getNo() { return no; }
    public LocalDate getTanggal() { return tanggal; }
    public String getWaktu() { return waktu; }
    public String getKegiatan() { return kegiatan; }
}