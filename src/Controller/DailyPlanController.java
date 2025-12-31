/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.DailyPlan;
import util.JsonUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author asus
 */
public class DailyPlanController {
    private final List<DailyPlan> list;
    private final JTable table;

    public DailyPlanController(JTable table) {
        this.table = table;
        this.list = JsonUtil.load();
        tampilkanKeTable(list);
    }

    public void save(DailyPlan dp) {
        list.add(dp);
        JsonUtil.save(list);
        tampilkanKeTable(list);
    }

    public void delete(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
            JsonUtil.save(list);
            tampilkanKeTable(list);
        }
    }

    public void update(int index, DailyPlan dpBaru) {
        if (index >= 0 && index < list.size()) {
            list.set(index, dpBaru);
            JsonUtil.save(list);
            tampilkanKeTable(list);
        }
    }

    public void filterTanggal(LocalDate tanggal) {
        List<DailyPlan> hasil = new ArrayList<>();
        for (DailyPlan dp : list) {
            if (dp.getTanggal().equals(tanggal)) {
                hasil.add(dp);
            }
        }
        tampilkanKeTable(hasil);
    }

    private void tampilkanKeTable(List<DailyPlan> data) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (DailyPlan dp : data) {
            model.addRow(new Object[]{
                dp.getNo(),
                dp.getTanggal().format(fmt),
                dp.getWaktu(),
                dp.getKegiatan()
            });
        }
    }
}
