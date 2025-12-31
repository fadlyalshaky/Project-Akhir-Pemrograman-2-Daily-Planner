/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author asus
 */

public class StopwatchController {
    private long startTime;
    private long pauseTime;
    private boolean running = false;

    private final JLabel timeLabel;
    private final DefaultListModel<String> lapModel;
    private final Timer timer;

    private int lapCount = 1;

    public StopwatchController(JLabel timeLabel, JList<String> lapList) {
        this.timeLabel = timeLabel;
        this.lapModel = new DefaultListModel<>();
        lapList.setModel(lapModel);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
    }

    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            timer.start();
            running = true;
        }
    }

    public void pause() {
        if (running) {
            pauseTime = System.currentTimeMillis();
            timer.stop();
            running = false;
        }
    }

    public void resume() {
        if (!running) {
            long now = System.currentTimeMillis();
            startTime += (now - pauseTime);
            timer.start();
            running = true;
        }
    }

    public void stop() {
        timer.stop();
        running = false;
        lapModel.clear();
        lapCount = 1;
        timeLabel.setText("00:00:00:00");
    }

    public void lap() {
        if (running) {
            lapModel.addElement(
                "LAP " + lapCount + "  " + timeLabel.getText()
            );
            lapCount++;
        }
    }

    private void updateTime() {
        long now = System.currentTimeMillis();
        long elapsed = now - startTime;

        long hours = elapsed / (1000 * 60 * 60);
        long minutes = (elapsed / (1000 * 60)) % 60;
        long seconds = (elapsed / 1000) % 60;
        long millis = (elapsed % 1000) / 10;

        String time = String.format(
            "%02d:%02d:%02d:%02d",
            hours, minutes, seconds, millis
        );

        timeLabel.setText(time);
    }   
}
