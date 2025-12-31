package Controller;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import Model.AlarmModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.*;
import util.LocalDateTimeAdapter;

public class AlarmController {

    private static final String FILE_ALARM = "alarm.json";

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();

    private final List<AlarmModel> alarms = new ArrayList<>();

    private DefaultListModel<String> listModel;
    private JLabel clockLabel;
    private JList<String> alarmList;

    private final java.util.Timer timer = new java.util.Timer(true);
    private boolean started = false;

    public void setView(JLabel clockLabel, JList<String> alarmList) {
        this.clockLabel = clockLabel;
        this.alarmList = alarmList;

        listModel = new DefaultListModel<>();
        alarmList.setModel(listModel);

        loadFromFile();
        if (!started) {
            startClock();
            startAlarmChecker();
            started = true;
        }
    }

    private void startClock() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() ->
                    clockLabel.setText(
                        LocalTime.now().format(
                            DateTimeFormatter.ofPattern("HH : mm : ss")
                        )
                    )
                );
            }
        }, 0, 1000);
    }

    public void setAlarm(LocalDate tanggal, int hour, int minute, String sound) {
        LocalDateTime dateTime = tanggal.atTime(hour, minute);
        AlarmModel alarm = new AlarmModel(dateTime, sound);

        alarms.add(alarm);
        listModel.addElement(formatDisplay(alarm));
        saveToFile();
    }

    public void removeAlarm(int index) {
        if (index >= 0 && index < alarms.size()) {
            alarms.remove(index);
            listModel.remove(index);
            saveToFile();
        }
    }

    private void startAlarmChecker() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalDateTime now =
                        LocalDateTime.now().withSecond(0).withNano(0);

                for (int i = alarms.size() - 1; i >= 0; i--) {
                    AlarmModel alarm = alarms.get(i);

                    if (alarm.isActive()
                            && !alarm.getDateTime().isAfter(now)) {

                        alarm.deactivate();

                        SwingUtilities.invokeLater(() -> {
                            playSound(alarm.getSound());
                            JOptionPane.showMessageDialog(
                                null,
                                "⏰ ALARM!\n" +
                                alarm.getDateTime().format(
                                    DateTimeFormatter.ofPattern(
                                        "EEEE, dd-MM-yyyy HH:mm"
                                    )
                                ),
                                "Alarm",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        });

                        alarms.remove(i);
                        listModel.remove(i);
                        saveToFile();
                    }
                }
            }
        }, 0, 1000);
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_ALARM)) {
            gson.toJson(alarms, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
     File file = new File(FILE_ALARM);
        if (!file.exists()) return;
        
        if (file.length() == 0) return;

    try (FileReader reader = new FileReader(file)) {
        Type type = new TypeToken<List<AlarmModel>>() {}.getType();
        List<AlarmModel> data = gson.fromJson(reader, type);

        if (data != null) {
            alarms.clear();
            listModel.clear();
            alarms.addAll(data);

            for (AlarmModel a : alarms) {
                listModel.addElement(formatDisplay(a));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    private String formatDisplay(AlarmModel a) {
        return a.getDateTime().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        ) + " (" + a.getSound() + ")";
    }

    private void playSound(String soundName) {
        try {
            AudioInputStream audio =
                AudioSystem.getAudioInputStream(
                    getClass().getResource(
                        "/Alarm_Sounds/" + soundName + ".wav"
                    )
                );

            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Gagal memutar suara: " + soundName
            );
        }
    }
}
